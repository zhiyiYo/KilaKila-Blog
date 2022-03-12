let isMathjaxConfig = false

const initMathjaxConfig = () => {
    if (!window.MathJax) {
        return;
    }

    window.MathJax.Hub.Config({
        extensions: ["tex2jax.js"],
        showProcessingMessages: false, //关闭js加载过程信息
        messageStyle: "none", //不显示信息
        jax: ["input/TeX", "output/HTML-CSS"],
        tex2jax: {
            inlineMath: [
                ["$", "$"],
                ["\\(", "\\)"]
            ],
            displayMath: [
                ["$$", "$$"],
                ["\\[", "\\]"]
            ],
            skipTags: ["script", "noscript", "style", "textarea", "pre", "code", "a"]
        },
        "HTML-CSS": {
            availableFonts: ["STIX", "TeX"], //可选字体
            showMathMenu: true
        }
    });

    isMathjaxConfig = true;
};

/**
 * 渲染数学公式
 * @param {string} className 需要提供数学公式支持的标签的类别名
 */
function MathQueue(className) {
    if (!window.MathJax) {
        return
    }
    if (!isMathjaxConfig) {
        initMathjaxConfig()
    }

    window.MathJax.Hub.Queue(['Typeset', window.MathJax.Hub, document.getElementsByClassName(className)])
}


export default MathQueue

