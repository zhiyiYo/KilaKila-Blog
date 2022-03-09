import "./index.less";
import 'highlight.js/styles/atom-one-dark.css'
import $ from "jquery"
import hljs from 'highlight.js/lib/core';
import javascript from 'highlight.js/lib/languages/javascript';
import vbscript from 'highlight.js/lib/languages/vbscript';
import python from 'highlight.js/lib/languages/python';
import matlab from 'highlight.js/lib/languages/matlab';
import csharp from 'highlight.js/lib/languages/csharp';
import shell from 'highlight.js/lib/languages/shell';
import vhdl from 'highlight.js/lib/languages/vhdl';
import java from 'highlight.js/lib/languages/java';
import css from 'highlight.js/lib/languages/css';
import xml from 'highlight.js/lib/languages/xml';
import sql from 'highlight.js/lib/languages/sql';
import cpp from 'highlight.js/lib/languages/cpp';
import c from 'highlight.js/lib/languages/c';


hljs.registerLanguage('javascript', javascript);
hljs.registerLanguage('vbscript', vbscript);
hljs.registerLanguage('python', python);
hljs.registerLanguage('matlab', matlab);
hljs.registerLanguage('csharp', csharp);
hljs.registerLanguage('shell', shell);
hljs.registerLanguage('vhdl', vhdl);
hljs.registerLanguage('java', java);
hljs.registerLanguage('html', xml);
hljs.registerLanguage('xml', xml);
hljs.registerLanguage('css', css);
hljs.registerLanguage('sql', sql);
hljs.registerLanguage('cpp', cpp);
hljs.registerLanguage('c', c);


function buildHljsLineNumber() {
    let $codes = $('.post-body .article-content pre code');
    if (!$codes.length) {
        return false;
    }

    $.each($codes, (_, code) => {
        if (!$(code).hasClass('hljsln')) {
            $(code).addClass('hljsln');
            $(code).html(addLineNumbersFor($(code).html()));
            var $lastNum = $('span[data-num]:last');
            if (!$lastNum.html()) {
                $lastNum.remove();
            }
        }
    });
}

function addLineNumbersFor(html) {
    var text = html.replace(/<span[^>]*>|<\/span>/g, '');
    if (/\r|\n$/.test(text)) {
        html += '<span class="ln-eof"></span>';
    }
    var num = 1;
    html = html.replace(/\r\n|\r|\n/g, function (a) {
        num++;
        return a + '<span class="ln-num" data-num="' + num + '"></span>';
    });
    html = '<span class="ln-num" data-num="1"></span>' + html;
    html = '<span class="ln-bg"></span>' + html;
    return html;
}


function highlightCode(element) {
    const codeEls = element.querySelectorAll('pre code');
    codeEls.forEach((el) => {
        hljs.highlightElement(el);
    });
}

export { buildHljsLineNumber, highlightCode };