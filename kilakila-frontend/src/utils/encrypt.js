import md5 from 'js-md5'


/**
 * 密码 MD5 加密
 * @param {string} plainText 明文
 * @return {string} ciphertext 加密后的密码
 */
function md5Encryption(plainText, salt='zhiyiYo') {
    return md5(plainText + salt)
}


export {md5Encryption}