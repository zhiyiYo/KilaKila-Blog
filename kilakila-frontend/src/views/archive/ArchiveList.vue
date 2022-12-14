<template>
    <div id="archive-list">
        <!-- 页头 -->
        <kila-kila-header />

        <!-- 二次元封面 -->
        <kila-kila-wife-cover>
            <h1>归档</h1>
        </kila-kila-wife-cover>

        <div class="container">
            <!-- 侧边栏 -->
            <kila-kila-side-bar />

            <!-- 归档 -->
            <div class="archive-body">
                <div class="archive-card">
                    <el-timeline class="timeline">
                        <el-timeline-item center :timestamp="`历史文章 - ${articleCount}`" placement="top"
                            class="root-item">
                        </el-timeline-item>

                        <el-timeline-item v-for="archive in archives" :key="archive.year" class="year-item"
                            :timestamp="archive.year + ''" placement="top">
                            <div v-for="article in archive.articles" :key="article.id" class="article-item">
                                <router-link :to="`/article/${article.id}`" class="article-thumbail-link"><img
                                        :src="article.thumbnail" @error.once="useDefaultThumbnail" alt="缩略图"
                                        class="article-thumbnail" />
                                </router-link>

                                <div class="article-info">
                                    <router-link :to="`/article/${article.id}`"
                                        class="article-title">{{ article.title }}
                                    </router-link>
                                    <div class="article-meta-data">
                                        <span><font-awesome-icon :icon="['fas', 'calendar-days']"
                                                class="article-meta-data-icon" />发表于
                                            {{ article.createTime }}</span>
                                        <span><font-awesome-icon :icon="['fas', 'eye']"
                                                class="article-meta-data-icon" />{{
                                                        article.viewCount
                                                }}次围观</span>
                                    </div>
                                </div>
                            </div>
                        </el-timeline-item>
                    </el-timeline>
                </div>

                <!-- 分页 -->
                <el-pagination background layout="prev, pager, next" :total="articleCount" :page-size="pageSize"
                    id="pagination" @current-change="onCurrentPageChanged" v-if="articleCount > 0" />
            </div>
        </div>

        <!-- 页脚 -->
        <kila-kila-footer />

        <!-- 回到顶部 -->
        <kila-kila-back-to-top />
    </div>
</template>

<script>
import { mapState } from "../../store/map";
import { getArchiveList } from "../../api/archive";
import { computed, reactive } from "vue";
import { defaultThumbnail, useDefaultThumbnail } from "../../utils/thumbnail";

export default {
    name: "ArchiveList",
    setup() {
        window.scrollTo({ top: 0 });
        let pageSize = 10;
        let archives = reactive([]);
        let { articleCountInfo } = mapState("adminAbout");
        let articleCount = computed(() =>
            parseInt(articleCountInfo.value.article)
        );
        onCurrentPageChanged(1);

        function onCurrentPageChanged(pageNum) {
            getArchiveList(pageNum, pageSize).then((data) => {
                data.rows.forEach((archive) => {
                    archive.articles.forEach((article) => {
                        article.createTime = article.createTime.split(" ")[0];
                        article.thumbnail =
                            article.thumbnail || defaultThumbnail;
                    });
                });

                archives.splice(0, archives.length, ...data.rows);

                // 多插入一个啥也没有的节点
                archives.push({ year: "", articles: [] });
            });
        }

        return {
            articleCount,
            pageSize,
            archives,
            onCurrentPageChanged,
            useDefaultThumbnail,
        };
    },
};
</script>

<style lang="less" scoped>
#archive-list {
    height: 100%;
    width: 100%;
}

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

    h1 {
        width: 100%;
        text-align: center;
        position: absolute;
        text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
        font-size: 40px;
        color: white;
        line-height: 1.5;
        margin-bottom: 15px;
        padding: 0 30px;
        box-sizing: border-box;
    }
}

.archive-body {
    width: 74%;
    height: 100%;

    .archive-card {
        background: white;
        border-radius: 8px;
        box-shadow: var(--card-box-shadow);
        padding: 50px 24px;
    }

    :deep(#pagination) {
        margin-top: 20px;
        justify-content: center;

        &>button {
            box-shadow: var(--card-box-shadow);
            background: white;
            border-radius: 8px;
            height: 35px;
            width: 35px;
        }

        li {
            box-shadow: var(--card-box-shadow);
            background-color: white;
            border-radius: 8px;
            margin: 0 6px;
            height: 35px;
            width: 35px;
        }

        li.active {
            color: white;
            background: var(--theme-color);
            font-weight: normal;
        }
    }
}

:deep(.timeline) {
    margin-top: 20px;

    .root-item {
        .el-timeline-item {
            padding-bottom: 20px;
        }

        .el-timeline-item__node {
            border: 5px solid var(--theme-color);
            background: transparent;
            width: 20px;
            height: 20px;
            left: -5px;
            top: -7px;
        }

        .el-timeline-item__tail {
            top: calc(50% - 15px);
            height: calc(50% + 15px);
            border-left: 2px solid #4ba9fc;
        }

        .el-timeline-item__wrapper {
            transform: translateY(-11px);

            .el-timeline-item__timestamp {
                font-size: 24px;
                color: var(--text-color);
            }
        }
    }

    .year-item {
        .el-timeline-item__node {
            border: 3px solid #ff7242;
            background: white;
            width: 12px;
            height: 12px;
            left: -1px;
            top: 0;
        }

        .el-timeline-item__wrapper {
            transform: translateY(-6px);

            .el-timeline-item__timestamp {
                font-size: 20px;
                color: var(--text-color);
            }
        }

        .el-timeline-item__tail {
            border-left: 2px solid #9eccf5;
        }
    }

    .year-item:nth-last-child(2) {
        padding-bottom: 0;
    }

    .year-item:last-child {
        visibility: hidden;
    }
}

.article-item {
    display: flex;
    justify-content: center;
    align-content: center;
    padding: 10px 0;

    .article-thumbail-link {
        height: 80px;
        width: 80px;
        overflow: hidden;
        border-radius: 6px;

        .article-thumbnail {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: all 0.4s ease;
        }
    }

    .article-thumbnail:hover {
        transform: scale(1.1);
    }

    .article-info {
        flex: 1;
        padding-left: 16px;
        word-break: break-all;
        display: inline-block;
        align-self: center;

        .article-title {
            color: var(--text-color);
            font-size: 15px;
            text-decoration: none;
            transition: color 0.4s;
            overflow: hidden;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            line-height: 1.5;
            -webkit-line-clamp: 2;

            &:hover {
                color: var(--theme-color);
            }
        }

        .article-meta-data {
            font-size: 13px;
            color: rgb(133, 133, 133);
            box-sizing: border-box;
            line-height: 30px;
            overflow: hidden;
            -webkit-line-clamp: 1;
            display: -webkit-box;
            -webkit-box-orient: vertical;

            .article-meta-data-icon {
                margin-right: 5px;
            }

            span {
                margin-right: 20px;
            }
        }
    }
}

@media screen and (max-width: 900px) {
    .archive-body {
        width: 100%;
    }
}
</style>