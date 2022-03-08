import request from '../utils/request'


/**
 * 获取归档的数量统计
 * @param {number} pageNum 当前页码
 * @param {number} pageSize 每页条目数
 * @returns promise
 */
function getArchiveCountList(pageNum, pageSize) {
    return request({
        url: "/archive/archiveCountList",
        params: {
            pageNum, pageSize
        }
    })
}

export { getArchiveCountList }