<template>
    <div class="home">
        <kila-kila-cover
            imgUrl="assets/image/home-cover.jpg"
            title="✨ Kila Kila Blog ✨"
            content="雄关漫道真如铁，而今迈步从头越"
        ></kila-kila-cover>
        <div class="container">
            <kila-kila-side-content></kila-kila-side-content>
        </div>
    </div>
</template>

<script>
import KilaKilaCover from "../components/KilaKilaCover";
import KilaKilaSideContent from "../components/KilaKilaSideContent";
export default {
    name: "Home",
    components: {
        KilaKilaCover,
        KilaKilaSideContent,
    },
};
</script>

<style scoped>
.home {
    height: 100%;
    width: 100%;
}

.container {
    padding: 50px;
}
</style>