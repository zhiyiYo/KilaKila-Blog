/**
 * 使用默认缩略图
 * @param {Event} event 错误事件
 */
var defaultThumbnail = require("@/assets/image/article-thumbnail.jpg");

function useDefaultThumbnail(event) {
    console.log(event);
    event.target.src = defaultThumbnail;
}

export { defaultThumbnail, useDefaultThumbnail }