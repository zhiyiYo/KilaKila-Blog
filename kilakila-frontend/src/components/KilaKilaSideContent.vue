<template>
    <div class="side-content">
        <kila-kila-admin-card />
        <kila-kila-hot-article-card />
    </div>
</template>

<script>
import KilaKilaAdminCard from "./KilaKilaAdminCard";
import KilaKilaHotArticleCard from "./KilaKilaHotArticleCard";

export default {
    components: { KilaKilaAdminCard, KilaKilaHotArticleCard },
    name: "KilaKilaSideContent",
    setup() {},
};
</script>

<style scoped>
.side-content {
    width: 26%;
}

@media screen and (max-width: 900px) {
    .side-content {
        display: none;
    }
}
</style>