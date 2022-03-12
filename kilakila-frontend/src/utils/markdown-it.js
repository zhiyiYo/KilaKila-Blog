import MarkdownIt from "markdown-it";
import emoji from "markdown-it-emoji"
import deflist from "markdown-it-deflist"
import abbr from "markdown-it-abbr"
import footnote from "markdown-it-footnote"
import ins from "markdown-it-ins"
import mark from "markdown-it-mark"
import taskLists from "markdown-it-task-lists"
import container from "markdown-it-container"
import toc from "markdown-it-toc-done-right"
import mermaid from "@DatatracCorporation/markdown-it-mermaid"


var config = {
    html: true,
    xhtmlOut: true,
    breaks: true,
    langPrefix: 'lang-',
    linkify: false,
    typographer: true,
    quotes: '“”‘’'
}
let markdownIt = new MarkdownIt(config)



markdownIt.use(emoji)
    .use(deflist)
    .use(abbr)
    .use(footnote)
    .use(ins)
    .use(mark)
    .use(taskLists)
    .use(container)
    .use(container, 'hljs-left')
    .use(container, 'hljs-center')
    .use(container, 'hljs-right')
    .use(toc)
    .use(mermaid)


export default markdownIt