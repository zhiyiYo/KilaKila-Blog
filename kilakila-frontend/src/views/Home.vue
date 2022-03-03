<template>
    <div class="home">
        <kila-kila-cover
            imgUrl="assets/image/home-cover.jpg"
            title="✨ Kila Kila Blog ✨"
            content="雄关漫道真如铁，而今迈步从头越"
        ></kila-kila-cover>
    </div>
</template>

<script>
import KilaKilaCover from "../components/KilaKilaCover.vue";
export default {
    name: "Home",
    components: {
        KilaKilaCover,
    },
};
</script>

<style scoped>
.home {
    height: 100%;
    width: 100%;
}
</style>