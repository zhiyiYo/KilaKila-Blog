import request from "../utils/request"

/**
 * 获取所有文章分类
 * @returns promise
 */
function getCategoryList() {
    return request.get("/category/categoryList")
}


export { getCategoryList }