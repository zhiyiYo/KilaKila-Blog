<template>
    <header>
        <router-link class="header-title" to="/"
            >✨ Kila Kila Blog ✨</router-link
        >
        <div class="header-menu">
            <div v-for="item in menuItems" :key="item.name">
                <router-link :to="item.href" class="header-menu-item">
                    <font-awesome-icon
                        :icon="item.icon"
                        class="header-icon"
                    />{{ item.name }}
                </router-link>
            </div>
        </div>

        <div id="header-menu-button" @click="drawer = !drawer">
            <font-awesome-icon :icon="['fas', 'bars']" />
        </div>

        <el-drawer
            v-model="drawer"
            direction="ltr"
            :show-close="false"
            :size="350"
        >
            <kila-kila-admin-menu />
        </el-drawer>
    </header>
</template>

<script>
import { reactive, ref } from "@vue/reactivity";
import KilaKilaAdminMenu from "./KilaKilaAdminMenu";

export default {
    name: "KilaKilaHeader",
    components: {
        KilaKilaAdminMenu,
    },
    setup() {
        // TODO:管理员可以看到新博客
        let menuItems = reactive([
            { name: "首页", icon: ["fab", "fort-awesome"], href: "/" },
            { name: "归档", icon: ["fas", "box-archive"], href: "/" },
            { name: "分类", icon: ["fas", "folder"], href: "/" },
            { name: "标签", icon: ["fas", "tags"], href: "/" },
        ]);

        let drawer = ref(false);
        return { menuItems, drawer };
    },
};
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Kanit:900);

header {
    position: absolute;
    top: 0;
    height: 60px;
    width: 100%;
    z-index: 9999;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 3%;
    box-sizing: border-box;
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
    --text-color: #eeeeee;
    --text-hover-color: white;
}

.header-title {
    color: var(--text-color);
    font-size: 23px;
    font-family: "Kanit";
    text-decoration: none;
    transition: all 0.2s;
}

.header-title:hover {
    color: var(--text-hover-color);
}

.header-icon {
    margin-right: 7px;
}

.header-menu {
    display: flex;
    position: relative;
}

.header-menu-item {
    color: var(--text-color);
    text-decoration: none;
    font-size: 15px;
    margin-left: 25px;
    position: relative;
    padding-bottom: 5px;
    transition: color 0.4s ease-in-out;
}

.header-menu-item:hover {
    color: var(--text-hover-color);
}

.header-menu-item:after {
    content: "";
    width: 0;
    height: 3px;
    background: #80c8f8;
    position: absolute;
    bottom: 0;
    left: 50%;
    transition: all 0.4s ease-in-out;
    border-radius: 2px;
}

.header-menu-item:hover:after {
    left: 0%;
    width: 100%;
}

#header-menu-button {
    display: none;
    color: var(--text-color);
    cursor: pointer;
    position: relative;
    font-size: 20px;
    margin: 0;
}

#header-menu-button:hover {
    color: var(--text-hover-color);
}

@media screen and (max-width: 1180px) {
    .header-menu {
        display: none;
    }

    #header-menu-button {
        display: inline-block;
    }
}
</style>