import request from "../utils/request"

/**
 * 获取所有分类的文章数量
 * @returns promise
 */
function getCategoryCountList() {
    return request.get("/category/categoryCountList")
}


export { getCategoryCountList }