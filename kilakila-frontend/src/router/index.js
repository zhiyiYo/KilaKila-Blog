import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue"
import Register from "../views/Register.vue"
import ArticleDetails from "../views/article/ArticleDetails"
import ArticleEdit from "../views/article/ArticleEdit"
import Settings from "../views/Settings"
import CategoryList from "../views/category/CategoryList"
import CategoryDetails from "../views/category/CategoryDetails"
import TagList from "../views/tag/TagList"
import TagDetails from "../views/tag/TagDetails"
import { getUserInfo } from "../utils/storage"

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/login",
        name: "Login",
        component: Login,
    },
    {
        path: "/register",
        name: "Register",
        component: Register,
    },
    {
        path: "/article/add",
        name: "ArticleAdd",
        component: ArticleEdit,
        meta: {
            needAuthentication: true
        }
    },
    {
        path: "/article/:id",
        name: "ArticleDetails",
        component: ArticleDetails,
        props: true
    },
    {
        path: "/article/:id/edit",
        name: "ArticleEdit",
        component: ArticleEdit,
        props: true,
        meta: {
            needAuthentication: true
        }
    },
    {
        path: "/category",
        name: "CategoryList",
        component: CategoryList,
        props: true
    },
    {
        path: "/category/:id",
        name: "CategoryDetails",
        component: CategoryDetails,
        props: true
    },
    {
        path: "/tag",
        name: "TagList",
        component: TagList,
        props: true
    },
    {
        path: "/tag/:id",
        name: "TagDetails",
        component: TagDetails,
        props: true
    },
    {
        path: "/user/settings",
        name: "UserSettings",
        component: Settings,
        props: true,
        meta: {
            needLogin: true
        }
    },

];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});


router.beforeEach((to, from, next) => {
    if (to.meta.needAuthentication) {
        let isAdmin = getUserInfo() ? getUserInfo().isAdmin : false;
        if (isAdmin) {
            next()
        } else {
            next({ name: "Login" })
        }
    } else if (to.meta.needLogin) {
        let userInfo = getUserInfo()
        if (userInfo) {
            next()
        } else {
            next({ name: "Login" })
        }
    } else {
        next()
    }
})

export default router;
