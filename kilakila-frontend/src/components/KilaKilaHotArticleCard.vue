<template>
    <kila-kila-card
        :icon="['fas', 'fire']"
        iconColor="#f01111"
        title="热门文章"
    >
        <div
            v-for="article in hotArticles"
            :key="article.id"
            class="hot-article-item"
        >
            <router-link
                :to="`/article/${article.id}`"
                class="hot-article-thumbail-link"
                ><img
                    :src="article.thumbnail"
                    @error.once="useDefaultThumbnail"
                    alt="缩略图"
                    class="hot-article-thumbnail"
                />
            </router-link>

            <div class="hot-article-info">
                <router-link
                    :to="`/article/${article.id}`"
                    class="hot-article-title"
                    >{{ article.title }}
                </router-link>
                <div class="hot-article-meta-data">
                    {{ article.createTime }}
                    <span>{{ article.viewCount }}次围观</span>
                </div>
            </div>
        </div>
    </kila-kila-card>
</template>

<script>
import { getHotArticleList } from "../api/article";
import { reactive } from "@vue/reactivity";
import { defaultThumbnail, useDefaultThumbnail } from "../utils/thumbnail";

export default {
    name: "KilaKilaHotArticleCard",
    setup() {
        let hotArticles = reactive([]);

        getHotArticleList().then((data) => {
            data.forEach((article) => {
                article.createTime = article.createTime.split(" ")[0];
                article.thumbnail = article.thumbnail || defaultThumbnail;
            });

            hotArticles.push(...data);
        });

        return { hotArticles, useDefaultThumbnail };
    },
};
</script>

<style scoped>
.hot-article-item {
    display: flex;
    justify-content: center;
    align-content: center;
    padding: 8px 0;
}

.hot-article-thumbail-link {
    height: 58px;
    width: 58px;
    overflow: hidden;
}

.hot-article-thumbnail {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: all 0.4s ease;
}

.hot-article-thumbnail:hover {
    transform: scale(1.1);
}

.hot-article-info {
    flex: 1;
    padding-left: 10px;
    word-break: break-all;
    display: inline-block;
    align-self: center;
}

.hot-article-title {
    color: var(--text-color);
    font-size: 13.5px;
    text-decoration: none;
    transition: color 0.4s;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    line-height: 1.5;
    -webkit-line-clamp: 2;
}

.hot-article-title:hover {
    color: var(--theme-color);
}

.hot-article-meta-data {
    font-size: 12px;
    color: rgb(133, 133, 133);
    box-sizing: border-box;
    line-height: 24px;
    overflow: hidden;
    -webkit-line-clamp: 1;
    display: -webkit-box;
    -webkit-box-orient: vertical;
}

.hot-article-meta-data span {
    margin-left: 4%;
}
</style>