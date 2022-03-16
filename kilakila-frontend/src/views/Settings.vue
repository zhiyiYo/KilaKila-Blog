<template>
    <div id="settings">
        <!-- 页头 -->
        <kila-kila-header />

        <!-- 二次元封面 -->
        <kila-kila-wife-cover>
            <h1>个人中心</h1>
        </kila-kila-wife-cover>

        <!-- 个人信息 -->
        <div class="user-info-card">
            <!-- 用户封面 -->
            <div class="user-cover">
                <img src="../assets/image/user-cover.jpg" alt="封面" />
            </div>

            <!-- 用户头像 -->
            <div class="user-avatar">
                <img :src="avatar" alt="头像" />
            </div>

            <!-- 用户信息 -->
            <div class="user-info">
                <div class="user-info-item">
                    <span class="user-info-label">用户名</span>
                    <span class="user-info-value">{{ nickName }}</span>
                </div>
                <div class="user-info-item">
                    <span class="user-info-label">性别</span>
                    <span class="user-info-value">{{
                        sex == "0" ? "男" : "女"
                    }}</span>
                </div>
                <div class="user-info-item">
                    <span class="user-info-label">个性签名</span>
                    <span class="user-info-value">{{ signature }}</span>
                </div>
                <div class="user-info-item">
                    <span class="user-info-label">电子邮箱</span>
                    <span class="user-info-value">{{ email }}</span>
                </div>
                <div class="user-info-item">
                    <span class="user-info-label">手机号码</span>
                    <span class="user-info-value">{{
                        phonenumber || "未知"
                    }}</span>
                </div>
            </div>

            <div class="card-footer">
                <el-button type="danger" @click="handleLogout"
                    >退出登录</el-button
                >
            </div>
        </div>

        <!-- 页脚 -->
        <kila-kila-footer />
    </div>
</template>

<script>
import { reactive, toRefs } from "@vue/reactivity";
import { getUserInfo } from "../utils/storage";
import { logout } from "../api/user";
import router from "../router";

export default {
    name: "Settings",
    setup() {
        let userInfo = reactive(getUserInfo());
        function handleLogout() {
            logout().then(() => {
                router.replace("/login");
            });
        }
        return { ...toRefs(userInfo), handleLogout };
    },
};
</script>

<style lang="less" scoped>
.wife-cover {
    display: flex;
    align-items: center;
    justify-content: center;

    h1 {
        text-align: center;
        position: absolute;
        width: 100%;
        text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
        font-size: 40px;
        color: white;
        line-height: 1.5;
        margin-bottom: 15px;
        padding: 0 30px;
        overflow: hidden;
        display: -webkit-box;
        text-overflow: ellipsis;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
        box-sizing: border-box;
    }
}

.user-info-card {
    background: white;
    border-radius: 8px;
    box-shadow: var(--card-box-shadow);
    box-sizing: border-box;
    width: 60%;
    margin: 30px auto 20px;
    position: relative;

    .user-cover {
        height: 240px;
        overflow: hidden;

        img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }
    }

    .container {
        display: flex;
    }

    .user-avatar {
        height: 130px;
        width: 130px;
        margin-left: 35px;
        padding: 5px;
        transform: translateY(-50%);
        background: white;
        border: transparent;
        border-radius: 50%;

        img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
        }
    }

    .user-info {
        border-radius: 15px;
        margin: -50px 30px 20px;

        .user-info-item {
            border-bottom: 1px solid #ebebeb;
            padding: 15px 0;
            line-height: 36px;
            margin-left: 5%;

            .user-info-label {
                position: absolute;
                font-size: 17px;
                font-weight: bold;
                color: var(--text-color);
            }

            .user-info-value {
                padding: 0 64px 0 180px;
                font-size: 16px;
                overflow: hidden;
                display: -webkit-box;
                text-overflow: ellipsis;
                -webkit-line-clamp: 1;
                -webkit-box-orient: vertical;
            }
        }
    }
    .card-footer {
        padding: 1rem 2rem;
        background-color: rgba(0, 0, 0, 0.02);
        border-top: 1px solid rgba(0, 0, 0, 0);
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        text-align: end;
    }
}
</style>