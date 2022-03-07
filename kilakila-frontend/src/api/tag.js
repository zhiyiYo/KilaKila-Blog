import request from "../utils/request"

/**
 * 获取所有文章标签
 * @returns promise
 */
function getTagList() {
    return request.get("/tag/tagList")
}


export { getTagList }