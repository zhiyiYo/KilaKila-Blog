/**
 * 生成线性递增颜色的词云
 * @param {Array<object>} words 词语数据列表，每个元素是包含 `id`、`name`、 `count` 属性的对象
 * @param {object} config 词云的配置，有以下可用的配置项
 *        * {String} lowColor: 最低频率对应的字体颜色，比如 `'#999999'`
 *        * {Array} colorSteps: rgb 颜色数组
 *        * {number} lowSize: 最低频率对应的字体大小，以 em 为单位
 *        * {number} sizeStep: 字体大小增长的步长，以 em 为单位
 *        * {number} maxSize: 字体的最大尺寸，以 em 为单位
 *
 * @return {Array<object>} clouds 词云
 */
function linearColorWordCloud(words, config) {
    let defaultConfig = {
        lowColor: "#999999",
        colorSteps: [0, 1, 2],
        lowSize: 1.1,
        sizeStep: 0.4 / 15,
        maxSize: 2
    }

    config = Object.assign(defaultConfig, config)

    // 获取的最高频率和最低频率
    let lowCount = 1
    let highCount = 1
    words.forEach((word) => {
        lowCount = Math.min(lowCount, word.count)
        highCount = Math.max(highCount, word.count)
    })

    // 获取颜色
    let [lr, lg, lb] = hexToRgb(config.lowColor)

    let clouds = []
    for (let word of words) {
        let step = word.count - lowCount

        // 计算字体颜色
        let r = Math.min(Math.max(step * config.colorSteps[0] + lr, 0), 255)
        let g = Math.min(Math.max(step * config.colorSteps[1] + lb, 0), 255)
        let b = Math.min(Math.max(step * config.colorSteps[2] + lg, 0), 255)

        // 计算字体大小
        let fontSize = Math.min(config.maxSize, config.lowSize + config.sizeStep * step)

        // 保存字体和颜色
        clouds.push({ ...word, style: { color: rgbToHex(r, g, b), fontSize: fontSize + 'em' } })
    }

    return clouds
}

/**
 * 生成随机颜色的词云
 * @param {Array<object>} words 词语数据列表，每个元素是包含 `id`、`name`、 `count` 属性的对象
 * @param {object} config 词云的配置，有以下可用的配置项
 *        * {Array<string>} colors: 词云颜色列表，比如 `['#999999', '#1892ff']`
 *        * {number} lowSize: 最低频率对应的字体大小，以 em 为单位
 *        * {number} sizeStep: 字体大小增长的步长，以 em 为单位
 *        * {number} maxSize: 字体的最大尺寸，以 em 为单位
 *
 * @return {Array<object>} clouds 词云
 */
function randomColorWordCloud(words, config) {
    let defaultConfig = {
        colors: [
            '#c78289', '#48ac75', '#8b25b5',
            '#4ec844', '#509e91', '#1e3d24',
            '#be679d', '#a6b0c7', '#1f737f',
            '#796955', '#65075b', '#c63440',
            '#1a8d7a', '#838214', '#c24fc8',
            '#7d8896', '#4222c8', '#4b896d'
        ],
        lowSize: 1.2,
        sizeStep: 0.06,
        maxSize: 2.5
    }
    config = Object.assign(defaultConfig, config)

    let clouds = []
    let colorCount = config.colors.length
    for (const word of words) {
        let color = config.colors[Math.floor(Math.random() * colorCount)]
        let fontSize = Math.min(config.maxSize, config.lowSize + (word.count - 1) * config.sizeStep)
        clouds.push({ ...word, style: { color, fontSize: fontSize + 'em' } })
    }

    return clouds
}


/**
 * 将 RGB 数组装换为 16 进制字符串
 * @param {number} r 红色通道灰度值
 * @param {number} g 绿色通道灰度值
 * @param {number} b 蓝色通道灰度值
 */
function rgbToHex(r, g, b) {
    var hex = ((r << 16) | (g << 8) | b).toString(16);
    return "#" + new Array(Math.abs(hex.length - 7)).join("0") + hex;
}

/**
 * 将 16 进制颜色字符串装换为 RGB 数组
 * @param {String} hex 十六进制颜色字符串，比如 `'#4c96ff'`
 * @returns rgb
 */
function hexToRgb(hex) {
    var rgb = [];
    for (var i = 1; i < 7; i += 2) {
        rgb.push(parseInt("0x" + hex.slice(i, i + 2)));
    }
    return rgb;
}


export { linearColorWordCloud, randomColorWordCloud }