import request from '../utils/request'

/**
 * 用户登录
 * @param {string} userName 用户名
 * @param {string} password 密码
 * @returns promise
 */
function login(userName, password) {
    return request({
        url: "/login",
        method: "post",
        data: { userName, password },
    })
}

/**
 * 用户注销
 * @returns promise
 */
function logout() {
    return request({
        url: "/logout",
        method: "post",
        needAuthentication: true
    })
}

/**
 * 用户注册
 * @param {string} userName 用户名
 * @param {string} nickName 昵称
 * @param {string} email 邮箱
 * @param {string} password 密码
 * @returns promise
 */
function register(userName, nickName, email, password) {
    return request({
        url: "/user/register",
        method: "post",
        data: { userName, nickName, email, password },
    })
}


/**
 * 获取管理员信息
 */
function getAdminInfo() {
    return request.get("/user/adminInfo")
}


/**
 * 获取用户信息
 */
function getUserInfo() {
    return request.get("/user/userInfo")
}


export { login, logout, register, getAdminInfo, getUserInfo }