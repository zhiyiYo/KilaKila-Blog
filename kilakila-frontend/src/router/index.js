import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue"
import Register from "../views/Register.vue"
import ArticleDetails from "../views/article/ArticleDetails"
import ArticleEdit from "../views/article/ArticleEdit"
import Settings from "../views/Settings"
import ArchiveList from "../views/archive/ArchiveList"
import ArchiveDetails from "../views/archive/ArchiveDetails"
import CategoryList from "../views/category/CategoryList"
import CategoryDetails from "../views/category/CategoryDetails"
import TagList from "../views/tag/TagList"
import TagDetails from "../views/tag/TagDetails"
import PageNotFound from "../views/PageNotFound"
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
        path: "/archive",
        name: "ArchiveList",
        component: ArchiveList,
    },
    {
        path: "/archive/:year/:month",
        name: "ArchiveDetails",
        component: ArchiveDetails,
        props: true
    },
    {
        path: "/category",
        name: "CategoryList",
        component: CategoryList,
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
        meta: {
            needLogin: true
        }
    },
    {
        path: '/:pathMatch(.*)*',
        component: PageNotFound
    }

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
