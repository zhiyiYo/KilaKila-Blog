import axios from "axios"
import { ElMessage } from 'element-plus'


const service = axios.create({
    baseURL: "/image"
})

service.interceptors.response.use(response => {

    const code = response.data.code || 200
    if (code === 200) {
        return response.data.data
    }

    let msg = response.data.code + " " + response.data.msg
    ElMessage.error(msg)

    return Promise.reject('上传图片失败：' + msg)
})

/**
 * 上传图片
 * @param {File} file 图片文件
 * @returns promise
 */
function uploadImage(file) {
    let formData = new FormData();
    formData.append("file", file)
    return service({
        url: "/upload",
        method: "post",
        data: formData,
    })
}

export { uploadImage }