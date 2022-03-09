<template>
    <div class="wife-cover" :style="wifeCoverStyle">
        <slot></slot>
    </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { ixiaowai } from "../api/wife";

export default {
    name: "KilaKilaWifeCover",
    setup() {
        let wifeCoverStyle = reactive({
            backgroundImage: "",
        });
        ixiaowai().then((response) => {
            wifeCoverStyle.backgroundImage = `url(${response.data.imgurl})`;
        });

        return { wifeCoverStyle };
    },
};
</script>

<style scoped>
.wife-cover {
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    height: 400px;
    width: 100%;
    animation: fadeUpInCover 1s;
}

.wife-cover::before {
    width: 100%;
    height: 400px;
    background-color: rgba(0, 0, 0, 0.5);
    content: "";
    position: absolute;
}

.side-content {
    width: 26%;
    margin-right: 20px;
}

@keyframes fadeUpInCover {
    0% {
        -webkit-transform: translateY(-70px);
        transform: translateY(-70px);
        opacity: 0%;
    }
    100% {
        -webkit-transform: translateY(0);
        transform: translateY(0);
        opacity: 100%;
    }
}
</style>