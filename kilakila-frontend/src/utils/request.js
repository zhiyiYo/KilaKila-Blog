import axios from 'axios'
import { getToken } from '../utils/storage'
import { ElMessageBox, ElMessage } from 'element-plus'
import router from '../router/index'
import errorCode from '../utils/errorCode'


axios.defaults.headers['Content-Type'] = "application/json;charset=utf-8"

const service = axios.create({
    baseURL: "http://localhost:8081/"
})


// 请求拦截
service.interceptors.request.use(config => {
    // 设置令牌
    let token = getToken()
    if (token) {
        config.headers['token'] = token
    }

    return config
}, error => {
    console.error(error)
    Promise.reject(error);
})


// 响应拦截
service.interceptors.response.use(response => {
    // 服务器响应的数据中的状态码
    const code = response.data.code || 200
    if (code === 200) return response.data.data;

    const msg = errorCode.get(code)
    if (code === 401) {
        ElMessageBox.confirm(
            '前辈需要登录之后才能继续该操作哦，是否立马登录？',
            '一条友善的提示',
            {
                confirmButtonText: '来啦老弟',
                cancelButtonText: '拒绝',
                type: 'warning',
            }

        ).then(() => {
            router.push('/login')
        })
    } else if (msg) {
        ElMessage.error(msg)
    } else {
        ElMessage.warning(response.data.msg || '啊哦，发生了未知错误')
    }

    return Promise.reject('error')
}, error => {
    const { status } = error.response
    if (status === 401) {
        ElMessage.warning('前辈需要登录后才能继续当前操作哦')
    } else if (status === 403) {
        ElMessage.warning('前辈权限不足，无法执行当前操作')
    } else if (status === 404) {
        ElMessage.warning('请求的资源不存在哦')
    } else if (status >= 500) { // 服务端异常
        ElMessage.warning('服务器出现异常啦')
    }

    return Promise.reject(error);
})


export default service
