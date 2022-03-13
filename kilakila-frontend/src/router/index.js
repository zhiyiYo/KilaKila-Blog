import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue"
import Register from "../views/Register.vue"
import Article from "../views/Article.vue"
import Edit from "../views/Edit"
import Settings from "../views/Settings"
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
        component: Edit,
        meta: {
            needAuthentication: true
        }
    },
    {
        path: "/article/:id",
        name: "ArticleDetails",
        component: Article,
        props: true
    },
    {
        path: "/article/:id/edit",
        name: "ArticleEdit",
        component: Edit,
        props: true,
        meta: {
            needAuthentication: true
        }
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
