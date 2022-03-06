<template>
    <div class="home">
        <kila-kila-header />

        <!-- 封面 -->
        <kila-kila-cover
            imgUrl="assets/image/home-cover.jpg"
            title="✨ Kila Kila Blog ✨"
            content="雄关漫道真如铁，而今迈步从头越"
        ></kila-kila-cover>
        <div class="container">
            <!-- 侧边栏 -->
            <kila-kila-side-content />

            <!-- 发表的文章 -->
            <div class="post-article-list">
                <kila-kila-post-article-card
                    v-for="(article, index) in postArticleList"
                    :key="article.id"
                    :article="article"
                    :reverse="index % 2 == 1"
                />
            </div>
        </div>

        <!-- 滚动到顶部按钮 -->
        <kila-kila-back-to-top />
    </div>
</template>

<script>
import { reactive, ref } from "vue";
import KilaKilaCover from "../components/KilaKilaCover";
import KilaKilaSideContent from "../components/KilaKilaSideContent";
import KilaKilaHeader from "../components/KilaKilaHeader";
import KilaKilaBackToTop from "../components/KilaKilaBackToTop";
import KilaKilaPostArticleCard from "../components/KilaKilaPostArticleCard";
import { getPostArticleList } from "../api/article";
import store from "../store";

export default {
    name: "Home",
    components: {
        KilaKilaHeader,
        KilaKilaCover,
        KilaKilaSideContent,
        KilaKilaBackToTop,
        KilaKilaPostArticleCard,
    },
    setup() {
        store.dispatch("adminAbout/getAdminInfo");
        store.dispatch("adminAbout/getArticleCount");

        let postArticleList = reactive([]);
        let articleCount = ref(0);

        getPostArticleList(0, 10).then((data) => {
            console.log(data);
            articleCount.value = data.total;
            data.rows.forEach((article) => {
                article.createTime = article.createTime.split(" ")[0];
                article.thumbnail = article.thumbnail || defaultThumbnail;
            });

            postArticleList.push(...data.rows);
        });

        return { postArticleList, articleCount };
    },
};
</script>

<style scoped>
.home {
    height: 100%;
    width: 100%;
    background-color: #f3f6f7;
}

.container {
    padding: 40px 15px;
    background: transparent;
    max-width: 1300px;
    margin: 0 auto;
    display: flex;
}

.post-article-list {
    width: 74%;
    padding-left: 20px;
}

.post-article-list .post-article-card {
    margin-top: 20px;
}

.post-article-list .post-article-card:nth-child(1) {
    margin-top: 0;
}
</style>