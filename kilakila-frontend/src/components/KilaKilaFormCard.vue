<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <h2>✨ Kila Kila Blog ✨</h2>
            </div>
        </template>

        <div class="card-body">
            <p class="title">{{ title }}</p>

            <slot>表单内容</slot>
        </div>
    </el-card>
</template>

<script>
export default {
    name: "KilaKilaFormCard",
    props: {
        title: {
            type: String,
            require: true,
        },
    },
};
</script>

<style scoped>
.card-header {
    justify-content: space-between;
    align-items: center;
    text-align: center;
    height: 35px;
}

.card-header h2 {
    font-family: "Kanit";
    margin-top: 3px;
}

.el-card__header {
    padding-top: 0px;
}

.card-body {
    padding: 0px 20px;
}

.card-body h3,
.card-body p {
    text-align: center;
}

.card-body h3 {
    margin-top: 0px;
}

.text-muted {
    color: #627d98;
}

.text-sm {
    font-size: 0.7875rem !important;
}

.box-card {
    width: 461px;
    border: 1px solid transparent;
    border-radius: 10px;
    box-shadow: none !important;
    margin: 0 auto;
}

.m-2 {
    margin-top: 10px;
}

.m-3 {
    margin-top: 15px;
}

.title {
    font-size: 20px;
    color: #627d98;
    margin-top: 0px;
}

@media screen and (max-width: 500px) {
    .box-card {
        width: 80%;
    }
}
</style>