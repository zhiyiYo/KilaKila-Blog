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
    name: "KilaKilaSideContent",
    components: { KilaKilaAdminCard, KilaKilaHotArticleCard },
};
</script>

<style scoped>
</style>