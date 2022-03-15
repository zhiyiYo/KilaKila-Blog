import request from '../utils/request'


/**
 * 获取归档及其文章数量统计
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

/**
 * 获取归档列表
 * @param {number} pageNum 当前页码
 * @param {number} pageSize 每页条目数
 * @returns promise
 */
function getArchiveList(pageNum, pageSize) {
    return request({
        url: "/archive/archiveList",
        params: {
            pageNum, pageSize
        }
    })
}

export { getArchiveCountList, getArchiveList }