import './index.less'
import ClipboardJS from 'clipboard'
import $ from "jquery"


function buildCopyButton() {
    let $pres = $('.post-body .article-content pre');
    if (!$pres.length) return;

    $pres.each(function () {
        var t = $(this).children("code").text();

        // 创建按钮
        var btn = $('<span class="copy">复制</span>').attr(
            "data-clipboard-text",
            t
        );

        $(this).prepend(btn);

        var c = new ClipboardJS(btn[0]);
        c.on("success", function () {
            btn.addClass("copyed").text("复制成功");
            setTimeout(function () {
                btn.text("复制").removeClass("copyed");
            }, 1000);
        });
        c.on("error", function () {
            btn.text("复制失败");
        });
    });
}


export default buildCopyButton;