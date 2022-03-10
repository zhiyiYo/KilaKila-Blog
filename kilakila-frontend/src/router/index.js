import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue"
import Register from "../views/Register.vue"
import Article from "../views/Article.vue"

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
        path: "/article/:id",
        name: "Article",
        component: Article,
        props: true
    }

];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

export default router;
