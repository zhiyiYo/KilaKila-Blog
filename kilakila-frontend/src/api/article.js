import request from '../utils/request'

/**
 * 获取热门文章
 * @returns promise
 */
function hotArticleList() {
    return request.get('/article/hotArticleList')
}


export { hotArticleList }