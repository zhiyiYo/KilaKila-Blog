import request from "../utils/request"


/**
 * 获取文章评论列表
 * @param {number} articleId 文章 id
 * @param {number} pageNum 页码
 * @param {number} pageSize 每页评论数
 */
function getCommentList(articleId, pageNum, pageSize) {
    return request({
        url: "/comment/commentList",
        params: { articleId, pageNum, pageSize }
    })
}


/**
 * 添加评论
 * @param {number} articleId 文章 id
 * @param {string} content 评论内容
 */
function addComment(articleId, content) {
    return request({
        url: "/comment",
        method: "post",
        data: { articleId, content },
        needAuthentication: true
    })
}


/**
 * 更新评论
 * @param {number} id 评论 id
 * @param {string} content 评论内容
 */
function updateComment(id, content) {
    return request({
        url: "/comment",
        method: "put",
        data: {id, content},
        needAuthentication: true
    })
}


/**
 *
 * @param {number} id 删除评论
 */
function deleteComment(id) {
    return request({
        url: `/comment/${id}`,
        method: "delete",
        needAuthentication: true
    })
}


export { getCommentList, addComment, deleteComment, updateComment }