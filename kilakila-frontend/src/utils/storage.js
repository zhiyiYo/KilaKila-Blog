/**
 * 获取令牌
 * @returns token
 */
function getToken() {
    return localStorage.getItem('token')
}

/**
 * 设置令牌
 * @param {string} token 令牌
 */
function setToken(token) {
    localStorage.setItem('token', token)
}


/**
 * 获取用户信息
 * @returns userInfo
 */
function getUserInfo() {
    return JSON.parse(localStorage.getItem('userInfo'))
}


/**
 * 设置用户信息
 * @param {object} userInfo 用户信息
 */
function setUserInfo(userInfo) {
    localStorage.setItem('userInfo', JSON.stringify(userInfo))
}


export { getToken, setToken, getUserInfo, setUserInfo }