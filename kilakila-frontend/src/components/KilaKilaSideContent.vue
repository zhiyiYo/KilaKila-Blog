<template>
    <div class="side-content">
        <kila-kila-admin-card />
        <kila-kila-hot-article-card />
        <kila-kila-category-card />
        <kila-kila-tag-card />
    </div>
</template>

<script>
import KilaKilaAdminCard from "./KilaKilaAdminCard";
import KilaKilaHotArticleCard from "./KilaKilaHotArticleCard";
import KilaKilaCategoryCard from "./KilaKilaCategoryCard";
import KilaKilaTagCard from "./KilaKilaTagCard";

export default {
    name: "KilaKilaSideContent",
    components: {
        KilaKilaAdminCard,
        KilaKilaHotArticleCard,
        KilaKilaCategoryCard,
        KilaKilaTagCard,
    },
};
</script>

<style scoped>
</style>