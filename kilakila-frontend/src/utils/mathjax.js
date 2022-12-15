/**
 * 异步载入 MathJax 脚本
 */
export function injectMathJax() {
    if (!window.MathJax) {
        const script = document.createElement('script')
        script.src = 'https://cdn.bootcdn.net/ajax/libs/mathjax/3.2.0/es5/tex-chtml.js'
        script.async = true
        document.head.appendChild(script)
    }
}

/**
 * 初始化 MathJax
 * @param options 自定义 MathJax 配置
 * @param callback 当 MathJax 脚本载入完毕时
 */
export function initMathJax(options = {}, callback) {
    injectMathJax()
    const defaultConfig = {
        tex: {
            inlineMath: [['$', '$']],
            displayMath: [['$$', '$$']],
            processEnvironments: true,
            processRefs: true,
        },
        options: {
            skipHtmlTags: ['noscript', 'style', 'textarea', 'pre', 'code'],
            ignoreHtmlClass: 'tex2jax_ignore',
        },
        startup: {
            pageReady: () => {
                callback && callback()
            },
        },
        svg: {
            fontCache: 'global',
        },
    }
    const mergeConfig = Object.assign({}, defaultConfig, options)
    window.MathJax = mergeConfig
}

/**
 * 渲染指定容器中的数学公式
 * @param {string} el 需要被渲染的容器
 * @returns Promise
 */
export function renderByMathjax(el) {
    if (!window.MathJax || !window.MathJax.version) {
        return
    }

    el = [...document.querySelectorAll(el)]

    return new Promise((resolve, reject) => {
        window.MathJax.typesetPromise(el)
            .then(() => {
                resolve(void 0)
            })
            .catch((err) => reject(err))
    })
}
