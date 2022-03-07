<template>
    <div class="admin-menu">
        <kila-kila-admin-card />
        <kila-kila-divider />
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
        >
            <!-- 首页 -->
            <el-menu-item index="1">
                <font-awesome-icon
                    :icon="['fab', 'fort-awesome']"
                    class="menu-icon"
                />
                <span>首页</span>
            </el-menu-item>

            <!-- 归档 -->
            <el-sub-menu index="2">
                <template #title>
                    <font-awesome-icon
                        :icon="['fas', 'box-archive']"
                        class="menu-icon"
                    />
                    <span>归档</span>
                </template>
                <el-menu-item index="2-1">🎯 2022 年 1 月（2）</el-menu-item>
                <el-menu-item index="2-2">🎯 2022 年 2 月（3）</el-menu-item>
            </el-sub-menu>

            <!-- 分类 -->
            <el-sub-menu index="3">
                <template #title>
                    <font-awesome-icon
                        :icon="['fas', 'folder']"
                        class="menu-icon"
                    />
                    <span>分类</span>
                </template>
                <el-menu-item
                    v-for="category in $store.state.categoryAbout.categories"
                    :key="category.id"
                    :index="'3-' + category.id"
                    class="category-item"
                    >🗂️
                    <span class="category-name">{{ category.name }}</span>
                    <span class="category-count">{{ category.count }}</span>
                </el-menu-item>
            </el-sub-menu>

            <!-- 标签 -->
            <el-sub-menu index="3">
                <template #title>
                    <font-awesome-icon
                        :icon="['fas', 'tags']"
                        class="menu-icon"
                    />
                    <span>标签</span>
                </template>
                <el-menu-item
                    v-for="category in $store.state.tagAbout.tags"
                    :key="category.id"
                    :index="'3-' + category.id"
                    class="category-item"
                    >🔖
                    <span class="tag-name">{{ category.name }}</span>
                    <span class="tag-count">{{ category.count }}</span>
                </el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
</template>

<script>
import KilaKilaAdminCard from "./KilaKilaAdminCard";
import KilaKilaDivider from "./KilaKilaDivider";
export default {
    name: "KilaKilaAdminMenu",
    components: {
        KilaKilaAdminCard,
        KilaKilaDivider,
    },
};
</script>

<style scoped>
.admin-menu {
    text-shadow: none !important;
    width: 100%;
}

.admin-card {
    box-shadow: none;
    box-sizing: border-box;
    height: 375px;
}

.el-menu {
    padding: 0 10px;
    margin-top: 10px;
}

.menu-icon {
    margin: 0 10px 0 0;
}

:deep(.category-item) {
    display: flex;
    justify-content: space-between;
    text-decoration: none;
    padding-right: 30px;
    color: #4c4948;
    font-size: 14px;
    transition: all 0.4s;
    border-radius: 4px;
}

:deep(.category-item:hover) {
    padding-left: 50px !important;
}

.category-name,
.tag-name {
    flex: 1;
    overflow: hidden;
    text-overflow: ellipsis;
    padding-left: 3px;
}
</style>