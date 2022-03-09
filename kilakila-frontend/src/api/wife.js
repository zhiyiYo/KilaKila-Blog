import axios from "axios"


/**
 * 小歪二次元图像
 * @returns promise
 */
function ixiaowai() {
    return axios.get('/ixiaowai/api/api.php?return=json')
}

export { ixiaowai }