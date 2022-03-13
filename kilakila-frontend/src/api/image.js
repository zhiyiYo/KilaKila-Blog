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
 * @param {RefImpl} progress 上传进度
 * @returns promise
 */
function uploadImage(file, progress) {
    let formData = new FormData();
    formData.append("file", file)
    return service({
        url: "/upload",
        method: "post",
        data: formData,
        onUploadProgress(event) {
            let v = Math.round(event.loaded / event.total * 100)
            progress.value = v == 100 ? 80 : v
        },

    })
}

export { uploadImage }