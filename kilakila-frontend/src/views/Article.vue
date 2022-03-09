<template>
    <div class="article">
        <!-- 页头 -->
        <kila-kila-header />

        <!-- 二次元封面 -->
        <kila-kila-wife-cover>
            <div class="article-info">
                <h1 class="article-title">{{ articleDetails.title }}</h1>
                <div class="article-meta-data-wrap">
                    <span class="article-meta-data">
                        <font-awesome-icon :icon="['fas', 'calendar-days']" />
                        发表于 {{ articleDetails.createTime }}
                    </span>
                    <span class="article-meta-data-divider">|</span>
                    <span class="article-meta-data">
                        <font-awesome-icon :icon="['fas', 'box-archive']" />
                        分类 {{ articleDetails.categoryName }}
                    </span>
                    <span class="article-meta-data-divider">|</span>
                    <span class="article-meta-data">
                        <font-awesome-icon :icon="['fas', 'eye']" />
                        阅读量 {{ articleDetails.viewCount }}
                    </span>
                </div>
            </div>
        </kila-kila-wife-cover>

        <div class="container">
            <!-- 侧边栏 -->
            <div class="side-content">
                <kila-kila-admin-card />
                <kila-kila-hot-article-card />
            </div>

            <!-- 文章内容 -->
            <div class="post-body">
                <div
                    class="article-content"
                    v-html="articleDetails.content"
                    v-highlight
                ></div>
            </div>
        </div>

        <!-- 回到顶部 -->
        <kila-kila-back-to-top />

        <!-- 页脚 -->
        <kila-kila-footer />
    </div>
</template>

<script>
import KilaKilaHeader from "../components/KilaKilaHeader";
import KilaKilaFooter from "../components/KilaKilaFooter";
import KilaKilaWifeCover from "../components/KilaKilaWifeCover";
import KilaKilaAdminCard from "../components/KilaKilaAdminCard";
import KilaKilaHotArticleCard from "../components/KilaKilaHotArticleCard";
import KilaKilaBackToTop from "../components/KilaKilaBackToTop";
import { getArticleDetails } from "../api/article";
import { reactive, nextTick } from "vue";
import { mavonEditor } from "mavon-editor";
import { buildHljsLineNumber, highlightCode } from "../utils/hljs";
import buildCopyButton from "../utils/copyButton";

export default {
    name: "Article",
    components: {
        KilaKilaWifeCover,
        KilaKilaHeader,
        KilaKilaFooter,
        KilaKilaAdminCard,
        KilaKilaHotArticleCard,
        KilaKilaBackToTop,
    },
    setup(props) {
        window.scrollTo({ top: 0 });

        let articleDetails = reactive({});
        getArticleDetails(props.id).then((data) => {
            Object.assign(articleDetails, data);
            articleDetails.content = mavonEditor
                .getMarkdownIt()
                .render(data.content);

            nextTick(() => {
                buildHljsLineNumber();
                buildCopyButton();
            });
        });

        // TODO:增加文章阅读量

        return { articleDetails };
    },
    props: ["id"],
};
</script>

<style lang="less" scoped>
.container {
    padding: 40px 15px;
    max-width: 1300px;
    margin: 0 auto;
    display: flex;
    animation: fadeInUp 1s;
}

.wife-cover {
    display: flex;
    align-items: center;
    justify-content: center;
}

.side-content {
    width: 26%;
    margin-right: 20px;
}

.article-info {
    text-align: center;
    position: absolute;
    width: 100%;
    text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
}

.article-title {
    font-size: 35px;
    font-weight: normal;
    color: white;
    line-height: 1.5;
    margin-bottom: 15px;
    overflow: hidden;
    display: -webkit-box;
    text-overflow: ellipsis;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
}

.article-meta-data-wrap {
    display: flex;
    justify-content: center;
}

.article-meta-data {
    font-size: 14px;
    color: white;
    box-sizing: border-box;
    line-height: 24px;
    overflow: hidden;
    -webkit-line-clamp: 1;
    display: -webkit-box;
    -webkit-box-orient: vertical;
}

.article-meta-data-divider {
    color: white;
    margin: 3px 8px;
    font-size: 14px;
}

.post-body {
    width: 74%;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
    padding: 30px 40px;
    box-sizing: border-box;

    :deep(.article-content) {
        img {
            max-width: 100%;
            margin: 0 auto 20px;
        }

        h1 code,
        h2 code,
        h3 code,
        h4 code,
        h5 code,
        h6 code,
        p > code,
        li > code,
        table code {
            color: #c7254e;
            line-height: 1.2;
            font-family: consolas !important;
            vertical-align: middle;
            margin: 0 3px;
            background-color: #f9f2f4 !important;
            font-size: 14px !important;
            padding: 0.2em 0.3em !important;
            border-radius: 3px !important;
            border: 1px solid #f9f2f4 !important;
        }

        p {
            color: #4c4948;
            font-size: 15px;
        }

        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            overflow: hidden;
            -webkit-line-clamp: 4;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            color: #1f2d3d;
            transition: all 0.2s ease-out;
        }

        h4,
        h5,
        h6 {
            font-size: 16px;
            padding-left: 30px;

            &:hover {
                padding-left: 40px;
            }

            &::before {
                margin-left: -34px;
                content: "🌟";
            }
        }

        h1 {
            font-size: 25px;
            padding-left: 19px;

            &:hover {
                padding-left: 36px;
            }

            &::before {
                margin-left: -24px;
                content: "\1F4AB";
                margin-right: 2px;
            }
        }

        h2 {
            padding-left: 19px;
            font-size: 22px;

            &:hover {
                padding-left: 36px;
            }

            &::before {
                margin-left: -24px;
                content: "✨";
                margin-right: 2px;
            }
        }

        h3 {
            padding-left: 30px;
            font-size: 17px;

            &:hover {
                padding-left: 40px;
            }

            &::before {
                margin-left: -34px;
                content: "🌟";
            }
        }

        /* 代码样式 */
        pre {
            white-space: pre;
            position: relative;
            border-radius: 7px;
            color: #bababa;
            background-color: #282c34;
            font-size: 14px;
            padding: 0;

            code {
                border: none;
                border-radius: 7px;
                font-family: Consolas, Monaco, "Andale Mono", "Ubuntu Mono",
                    monospace !important;
                line-height: 21px;
            }
        }
    }
}

@media screen and (max-width: 900px) {
    .side-content {
        display: none;
    }

    .post-body {
        width: 100%;
    }
}

@keyframes fadeInUp {
    from {
        margin-top: 50px;
        opacity: 0;
    }

    to {
        margin-top: 0;
        opacity: 1;
    }
}
</style>