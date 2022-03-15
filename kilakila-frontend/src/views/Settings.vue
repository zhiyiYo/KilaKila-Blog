<template>
    <div id="settings">
        <!-- 页头 -->
        <kila-kila-header />

        <!-- 二次元封面 -->
        <kila-kila-wife-cover>
            <h1>个人中心</h1>
        </kila-kila-wife-cover>

        <!-- 个人信息 -->
        <div class="user-info-card"></div>

        <!-- 页脚 -->
        <kila-kila-footer />
    </div>
</template>

<script>
export default {
    name: "Settings",
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
    padding: 20px 24px;
    box-sizing: border-box;
    width: 60%;
    margin: 50px auto 10px;
}
</style>