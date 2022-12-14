<template>
    <!-- 侧边栏 -->
    <div id="side-bar">
        <slot>
            <kila-kila-admin-card />
            <kila-kila-hot-article-card />
            <kila-kila-category-card />
            <kila-kila-tag-card />
            <kila-kila-archive-card />
        </slot>
    </div>
</template>

<script>
export default {
    name: "KilaKilaSideBar"
}
</script>

<style lang="less" scoped>
#side-bar {
    width: 26%;
    margin-right: 20px;
}

@media screen and (max-width: 900px) {
    #side-bar {
        display: none;
    }
}
</style>