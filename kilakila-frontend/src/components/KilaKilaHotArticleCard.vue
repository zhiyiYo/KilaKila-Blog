<template>
    <kila-kila-card
        :icon="['fas', 'fire']"
        iconColor="#f01111"
        title="热门文章"
    >
        <div
            v-for="article in hotArticleList"
            :key="article.id"
            class="article-item"
        >
            <router-link
                :to="`/article/${article.id}`"
                class="article-thumbail-link"
                ><img
                    :src="article.thumbnail"
                    @error.once="useDefaultThumbnail"
                    alt="缩略图"
                    class="article-thumbnail"
                />
            </router-link>

            <div class="article-info">
                <router-link
                    :to="`/article/${article.id}`"
                    class="article-title"
                    >{{ article.title }}
                </router-link>
                <div class="article-createTime">
                    {{ article.createTime }}
                    <span>{{ article.viewCount }}次围观</span>
                </div>
            </div>
        </div>
    </kila-kila-card>
</template>

<script>
import KilaKilaCard from "./KilaKilaCard";
import { getHotArticleList } from "../api/article";
import { reactive } from "@vue/reactivity";

export default {
    name: "KilaKilaHotArticleCard",
    components: {
        KilaKilaCard,
    },
    setup() {
        let hotArticleList = reactive([]);
        let defaultThumbnail = require("@/assets/image/article-thumbnail.jpg");

        getHotArticleList().then((data) => {
            data.forEach((article) => {
                article.createTime = article.createTime.split(" ")[0];
                article.thumbnail = article.thumbnail || defaultThumbnail;
            });

            hotArticleList.push(...data);
        });

        function useDefaultThumbnail(event) {
            console.log(666);
            event.target.src = defaultThumbnail;
        }

        return { hotArticleList, useDefaultThumbnail };
    },
};
</script>

<style scoped>
.article-item {
    display: flex;
    justify-content: center;
    align-content: center;
    padding: 8px 0;
}

.article-thumbail-link {
    height: 58px;
    width: 58px;
    overflow: hidden;
}

.article-thumbnail {
    width: 100%;
    height: 100%;
    align-items: center;
    object-fit: cover;
    transition: all 0.4s ease;
}

.article-thumbnail:hover {
    transform: scale(1.1);
}

.article-info {
    flex: 1;
    padding-left: 10px;
    word-break: break-all;
    display: inline-block;
    align-self: center;
}

.article-title {
    color: #4c4948;
    font-size: 13.3px;
    text-decoration: none;
    transition: color 0.4s;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    line-height: 1.5;
    -webkit-line-clamp: 2;
}

.article-title:hover {
    color: #1892ff;
}

.article-createTime {
    font-size: 12px;
    color: rgb(133, 133, 133);
    box-sizing: border-box;
    line-height: 24px;
    overflow: hidden;
    -webkit-line-clamp: 1;
    display: -webkit-box;
    -webkit-box-orient: vertical;
}

.article-createTime span {
    margin-left: 4%;
}

.header-icon {
    animation: floatAni 2s infinite;
}

@keyframes floatAni {
    0%,
    100% {
        -webkit-transform: translateY(0);
        transform: translateY(0);
    }

    50% {
        -webkit-transform: translateY(-30px);
        transform: translateY(-30px);
    }
}
</style>