import request from "../utils/request"


/**
 * 获取文章评论列表
 * @param {number} articleId 文章 articleId
 * @param {number} pageNum 页码
 * @param {number} pageSize 每页评论数
 */
function getCommentList(articleId, pageNum, pageSize) {
    return request({
        url: "/comment/commentList",
        params: { articleId, pageNum, pageSize }
    })
}


export { getCommentList }