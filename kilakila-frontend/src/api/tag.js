import request from "../utils/request"

/**
 * 获取所有标签的文章数量
 * @returns promise
 */
function getTagCountList() {
    return request.get("/tag/tagCountList")
}


export { getTagCountList }