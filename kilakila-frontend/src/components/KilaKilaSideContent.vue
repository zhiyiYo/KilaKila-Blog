<template>
    <div class="side-content">
        <kila-kila-admin-card />
        <kila-kila-hot-article-card />
        <kila-kila-category-card />
        <kila-kila-tag-card />
        <kila-kila-archive-card />
    </div>
</template>

<script>
import KilaKilaAdminCard from "./KilaKilaAdminCard";
import KilaKilaHotArticleCard from "./KilaKilaHotArticleCard";
import KilaKilaCategoryCard from "./KilaKilaCategoryCard";
import KilaKilaTagCard from "./KilaKilaTagCard";
import KilaKilaArchiveCard from "./KilaKilaArchiveCard";

export default {
    name: "KilaKilaSideContent",
    components: {
        KilaKilaAdminCard,
        KilaKilaHotArticleCard,
        KilaKilaCategoryCard,
        KilaKilaTagCard,
        KilaKilaArchiveCard,
    },
};
</script>

<style scoped>
</style>