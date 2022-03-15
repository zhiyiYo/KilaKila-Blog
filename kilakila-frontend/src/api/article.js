import request from '../utils/request'

/**
 * 获取热门文章
 * @returns promise
 */
function getHotArticleList() {
    return request.get('/article/hotArticleList')
}


/**
 * 获取文章总数统计
 */
function getArticleCount() {
    return request.get('/article/count')
}


/**
 * 获取发表的文章
 * @param {number} pageNum 当前页码
 * @param {number} pageSize 每一页的文章数
 * @param {number} categoryId 文章所属分类，可以不传
 * @param {number} tagId 文章的标签，可以不传
 * @param {string} date 文章的发表日期，格式为 `yyyy/MM`，比如 `2021/1` 或 `2021/02`，可以不传
 * @returns promise
 */
function getPostArticleList(pageNum, pageSize, categoryId, tagId, date) {
    let params = { pageNum, pageSize, categoryId, tagId, date }

    return request({
        url: '/article/articleList',
        params
    })
}


/**
 * 获取文章详情
 * @param {number} id 文章 id
 * @returns promise
 */
function getArticleDetails(id) {
    return request.get('/article/' + id)
}


/**
 * 更新文章阅读数
 * @param {number} id 文章 id
 * @returns promise
 */
function updateViewCount(id) {
    return request({
        url: "/article/updateViewCount/" + id,
        method: "put",
    })
}


/**
 * 获取上一篇和下一篇博客
 * @param {number} id 文章 id
 * @returns promise
 */
function getPreviousNextArticle(id) {
    return request.get("/article/previousNextArticle/" + id)
}


/**
 * 添加文章
 * @param {object} article 文章
 * @returns promise
 */
function addArticle(article) {
    return request({
        url: "/article",
        method: "post",
        data: article,
        needAuthentication: true
    })
}

/**
 * 编辑文章
 * @param {object} article 文章
 * @returns promise
 */
function editArticle(article) {
    return request({
        url: "/article",
        method: "put",
        data: article,
        needAuthentication: true
    })
}


export {
    getHotArticleList,
    getArticleCount,
    getPostArticleList,
    getArticleDetails,
    updateViewCount,
    getPreviousNextArticle,
    addArticle,
    editArticle
}