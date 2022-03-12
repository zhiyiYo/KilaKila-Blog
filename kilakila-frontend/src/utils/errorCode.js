const errorCode = new Map()
errorCode.set(401, "需要登录后操作")
errorCode.set(404, "请求的资源不存在哦")
errorCode.set(403, "前辈权限不足，无法执行当前操作")
errorCode.set(403, "前辈权限不足，无法执行当前操作")
errorCode.set(500, "服务器出现异常啦")
errorCode.set(501, "用户名被占用啦")
errorCode.set(502, "手机号已存在")
errorCode.set(503, "邮箱地址被占用啦")
errorCode.set(504, "用户名或密码错误")

export default errorCode