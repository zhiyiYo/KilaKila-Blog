<template>
    <div class="card">
        <div class="card-header">
            <span :style="headerIconStyle"
                ><font-awesome-icon :icon="icon" class="header-icon"
            /></span>
            <span>{{ title }}</span>
        </div>
        <div class="card-body">
            <slot>卡片体</slot>
        </div>
    </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
export default {
    name: "KilaKilaCard",
    props: {
        title: {
            type: String,
            require: true,
        },
        icon: {
            type: Array,
            require: true,
        },
        iconColor: {
            type: String,
            require: false,
            default: "#4c4948",
        },
    },
    setup(props) {
        let headerIconStyle = reactive({
            color: props.iconColor,
        });

        return { headerIconStyle };
    },
};
</script>

<style scoped>
.card {
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
    padding: 20px 24px;
    width: 100%;
    margin-top: 25px;
    box-sizing: border-box;
}

.card-header {
    text-align: left !important;
    margin-bottom: 20px;
}

.header-icon {
    font-size: 18px;
    margin-right: 10px;
}

.card-header span {
    font-size: 17px;
    color: #4c4948;
}
</style>