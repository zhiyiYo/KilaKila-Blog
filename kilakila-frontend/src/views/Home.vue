<template>
    <div class="home">
        <kila-kila-header />
        <kila-kila-cover
            imgUrl="assets/image/home-cover.jpg"
            title="✨ Kila Kila Blog ✨"
            content="雄关漫道真如铁，而今迈步从头越"
        ></kila-kila-cover>
        <div class="container">
            <kila-kila-side-content></kila-kila-side-content>
        </div>
        <kila-kila-back-to-top />
    </div>
</template>

<script>
import KilaKilaCover from "../components/KilaKilaCover";
import KilaKilaSideContent from "../components/KilaKilaSideContent";
import KilaKilaHeader from "../components/KilaKilaHeader";
import KilaKilaBackToTop from "../components/KilaKilaBackToTop";
import store from "../store";

export default {
    name: "Home",
    components: {
        KilaKilaHeader,
        KilaKilaCover,
        KilaKilaSideContent,
        KilaKilaBackToTop,
    },
    setup() {
        store.dispatch("adminAbout/getAdminInfo");
        store.dispatch("adminAbout/getArticleCount");
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
}
</style>