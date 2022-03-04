<template>
    <div class="admin-card">
        <el-avatar
            :size="50"
            :src="adminInfo.avatar"
            class="avatar"
        ></el-avatar>
        <h3>{{ adminInfo.nickName }}</h3>
        <p>{{ adminInfo.signature }}</p>

        <!-- 发表的文章信息 -->
        <div class="article-info-container">
            <div class="article-info">
                <p>文章</p>
                <p>{{ articleCountInfo.article }}</p>
            </div>
            <div class="article-info">
                <p>分类</p>
                <p>{{ articleCountInfo.category }}</p>
            </div>
            <div class="article-info">
                <p>标签</p>
                <p>{{ articleCountInfo.tag }}</p>
            </div>
        </div>

        <!-- 在 github 上 follow -->
        <el-button type="primary" class="follow-button" @click="gotoGithub"
            ><font-awesome-icon
                :icon="['fab', 'github']"
                class="github-icon"
            />Follow Me</el-button
        >

        <!-- 社交软件图标 -->
        <div class="social-icon-container">
            <el-tooltip
                class="box-item"
                effect="dark"
                content="Twitter"
                placement="bottom"
                ><a href="">
                    <img
                        src="../assets/image/twitter.svg"
                        alt="twitter"
                        class="social-icon"
                    />
                </a>
            </el-tooltip>
            <el-tooltip
                class="box-item"
                effect="dark"
                content="GitHub"
                placement="bottom"
            >
                <a :href="adminInfo.githubUrl">
                    <img
                        src="../assets/image/github.svg"
                        alt="github"
                        class="social-icon"
                    />
                </a>
            </el-tooltip>
            <el-tooltip
                class="box-item"
                effect="dark"
                content="QQ"
                placement="bottom"
            >
                <img
                    src="../assets/image/QQ.svg"
                    alt="QQ"
                    class="social-icon"
                />
            </el-tooltip>
            <el-tooltip
                class="box-item"
                effect="dark"
                content="Email"
                placement="bottom"
            >
                <a :href="'mailto:' + adminInfo.email">
                    <img
                        src="../assets/image/email.svg"
                        alt="email"
                        class="social-icon"
                    />
                </a>
            </el-tooltip>
        </div>
    </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { getAdminInfo } from "../api/user";
import { getArticleCount } from "../api/article";

export default {
    name: "KilaKilaAdminCard",
    setup() {
        let adminInfo = reactive({
            nickName: "",
            signature: "",
            avatar: require("@/assets/image/avatar.jpg"),
            githubUrl: "https://github.com/zhiyiYo",
        });
        let articleCountInfo = reactive({
            article: 0,
            category: 0,
            tag: 0,
        });

        getAdminInfo().then((data) => {
            delete data.avatar;
            Object.assign(adminInfo, data);
        });

        getArticleCount().then((data) => {
            Object.assign(articleCountInfo, data);
        });

        function gotoGithub() {
            location.href = adminInfo.githubUrl;
        }

        return { adminInfo, articleCountInfo, gotoGithub };
    },
};
</script>

<style scoped>
@import url(../assets/css/material-icons.css);

.admin-card {
    background: white;
    border-radius: 15px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
    text-align: center;
    height: 400px;
    width: 20%;
    padding: 20px;
}

.avatar {
    width: 120px;
    height: 120px;
    transition: transform 0.5s ease-out;
    -webkit-transition: transform 0.6s ease-out;
    -moz-transition: transform 0.5s ease-out;
    -ms-transition: transform 0.5s ease-out;
    -o-transition: transform 0.5s ease-out;
}

.avatar:hover {
    -webkit-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
}

h3 {
    margin: 0px;
}

.article-info-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0% 8%;
    margin-bottom: 10px;
    margin-top: 0;
}

.article-info {
    margin: 0 10px;
}

.social-icon-container {
    margin-top: 20px;
}

.social-icon {
    margin: 0px 3%;
}

.follow-button {
    display: block;
    margin: 0 auto;
    width: 100%;
}

.github-icon {
    margin-right: 10px;
}
</style>