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


export { getHotArticleList, getArticleCount }