<template>
    <div class="admin-menu">
        <kila-kila-admin-card />
    </div>
</template>

<script>
import KilaKilaAdminCard from "./KilaKilaAdminCard";
export default {
    name: "KilaKilaAdminMenu",
    components: {
        KilaKilaAdminCard,
    },
};
</script>

<style scoped>
.admin-menu {
    text-shadow: none !important;
    padding: 0 4%;
}
.admin-card {
    box-shadow: none;
    width: 100%;
    padding: 0;
}
</style>