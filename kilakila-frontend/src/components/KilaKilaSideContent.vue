<template>
    <div class="side-content">
        <kila-kila-admin-card></kila-kila-admin-card>
    </div>
</template>

<script>
import KilaKilaAdminCard from "./KilaKilaAdminCard.vue";

export default {
    components: { KilaKilaAdminCard },
    name: "KilaKilaSideContent",
    setup() {},
};
</script>

<style>
@media screen and (max-width: 1180px) {
    .side-content {
        display: none;
    }
}
</style>