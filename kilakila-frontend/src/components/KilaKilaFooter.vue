<template>
    <div id="footer">
        <div class="footer-image"></div>

        <div class="footer-box">
            <!-- 标语 -->
            <div class="footer-text">
                「天若有情天亦老
                <span>
                    <font-awesome-icon
                        :icon="['fas', 'heart-pulse']"
                        class="footer-text-icon"
                    />
                </span>
                人间正道是沧桑」
            </div>

            <!-- 运行时间 -->
            <div>
                <span class="blog-run-time"
                    >网站已经运行了 {{ blogRunTime }}
                </span>
                <span class="smile-face">ღゝ◡╹)ノ♡</span>
            </div>

            <!-- 版权 -->
            <div class="copyright-text">Copyright © 2022 {{ adminName }}</div>
        </div>
    </div>
</template>

<script>
import { ref, computed, onBeforeUnmount } from "vue";
import { useStore } from "vuex";
import { getRunTime } from "../utils/date";
export default {
    name: "KilaKilaFooter",
    setup() {
        let year = ref(new Date().getFullYear());
        let currentTime = ref(new Date().getTime());

        let timer = setInterval(() => {
            currentTime.value = new Date().getTime();
        }, 1000);

        let blogRunTime = computed(() => {
            let startDate = useStore().state.websiteAbout.startDate;
            let { day, hour, min, second } = getRunTime(
                startDate,
                currentTime.value
            );
            return `${day} 天 ${hour} 小时 ${min} 分 ${second} 秒`;
        });

        onBeforeUnmount(() => {
            clearInterval(timer);
        });

        return { year, blogRunTime };
    },
    props: {
        adminName: {
            type: String,
            require: true,
        },
    },
};
</script>

<style scoped>
#footer {
    background: #232323;
    margin-top: 250px;
    padding: 15px 0 45px 0;
    width: 100%;
    color: rgb(133, 133, 133);
    text-align: center;
    position: absolute;
}

.footer-image {
    width: 100%;
    height: 368px;
    background: url("../assets/image/footer.webp") 50% center no-repeat;
    position: absolute;
    top: -293px;
    z-index: 999;
}

.footer-box {
    width: 70%;
    margin: 0 auto;
}

.footer-text {
    font-size: 17px;
    font-family: "Long Cang", sans-serif;
    padding-bottom: 5px;
    border-bottom: 1px dashed #333;
    margin: 0 auto 5px;
}

.footer-text-icon {
    color: red;
    font-size: 16px;
    animation: heartAni 1.4s infinite;
}

.copyright-text,
.blog-run-time,
.smile-face {
    font-size: 12px;
}

.blog-run-time {
    margin-right: 5px;
}

.smile-face {
    animation: faceAni 5s infinite ease-in-out;
    display: inline-block;
}

@keyframes heartAni {
    0%,
    100% {
        transform: scale(1);
    }

    10%,
    30% {
        transform: scale(0.9);
    }

    20%,
    40%,
    50%,
    60%,
    70%,
    80% {
        transform: scale(1.1);
    }
}

@keyframes faceAni {
    2%,
    24%,
    80% {
        -webkit-transform: translate(0, 1.5px) rotate(1.5deg);
        transform: translate(0, 1.5px) rotate(1.5deg);
    }
    4%,
    68%,
    98% {
        -webkit-transform: translate(0, -1.5px) rotate(-0.5deg);
        transform: translate(0, -1.5px) rotate(-0.5deg);
    }
    38%,
    6% {
        -webkit-transform: translate(0, 1.5px) rotate(-1.5deg);
        transform: translate(0, 1.5px) rotate(-1.5deg);
    }
    8%,
    86% {
        -webkit-transform: translate(0, -1.5px) rotate(-1.5deg);
        transform: translate(0, -1.5px) rotate(-1.5deg);
    }
    10%,
    72% {
        -webkit-transform: translate(0, 2.5px) rotate(1.5deg);
        transform: translate(0, 2.5px) rotate(1.5deg);
    }
    12%,
    64%,
    78%,
    96% {
        -webkit-transform: translate(0, -0.5px) rotate(1.5deg);
        transform: translate(0, -0.5px) rotate(1.5deg);
    }
    14%,
    54% {
        -webkit-transform: translate(0, -1.5px) rotate(1.5deg);
        transform: translate(0, -1.5px) rotate(1.5deg);
    }
    16% {
        -webkit-transform: translate(0, -0.5px) rotate(-1.5deg);
        transform: translate(0, -0.5px) rotate(-1.5deg);
    }
    18%,
    22% {
        -webkit-transform: translate(0, 0.5px) rotate(-1.5deg);
        transform: translate(0, 0.5px) rotate(-1.5deg);
    }
    20%,
    36%,
    46% {
        -webkit-transform: translate(0, -1.5px) rotate(2.5deg);
        transform: translate(0, -1.5px) rotate(2.5deg);
    }
    26%,
    50% {
        -webkit-transform: translate(0, 0.5px) rotate(0.5deg);
        transform: translate(0, 0.5px) rotate(0.5deg);
    }
    28% {
        -webkit-transform: translate(0, 0.5px) rotate(1.5deg);
        transform: translate(0, 0.5px) rotate(1.5deg);
    }
    30%,
    40%,
    62%,
    76%,
    88% {
        -webkit-transform: translate(0, -0.5px) rotate(2.5deg);
        transform: translate(0, -0.5px) rotate(2.5deg);
    }
    32%,
    34%,
    66% {
        -webkit-transform: translate(0, 1.5px) rotate(-0.5deg);
        transform: translate(0, 1.5px) rotate(-0.5deg);
    }
    42% {
        -webkit-transform: translate(0, 2.5px) rotate(-1.5deg);
        transform: translate(0, 2.5px) rotate(-1.5deg);
    }
    44%,
    70% {
        -webkit-transform: translate(0, 1.5px) rotate(0.5deg);
        transform: translate(0, 1.5px) rotate(0.5deg);
    }
    48%,
    74%,
    82% {
        -webkit-transform: translate(0, -0.5px) rotate(0.5deg);
        transform: translate(0, -0.5px) rotate(0.5deg);
    }
    52%,
    56%,
    60% {
        -webkit-transform: translate(0, 2.5px) rotate(2.5deg);
        transform: translate(0, 2.5px) rotate(2.5deg);
    }
    58% {
        -webkit-transform: translate(0, 0.5px) rotate(2.5deg);
        transform: translate(0, 0.5px) rotate(2.5deg);
    }
    84% {
        -webkit-transform: translate(0, 1.5px) rotate(2.5deg);
        transform: translate(0, 1.5px) rotate(2.5deg);
    }
    90% {
        -webkit-transform: translate(0, 2.5px) rotate(-0.5deg);
        transform: translate(0, 2.5px) rotate(-0.5deg);
    }
    92% {
        -webkit-transform: translate(0, 0.5px) rotate(-0.5deg);
        transform: translate(0, 0.5px) rotate(-0.5deg);
    }
    94% {
        -webkit-transform: translate(0, 2.5px) rotate(0.5deg);
        transform: translate(0, 2.5px) rotate(0.5deg);
    }
    0%,
    100% {
        -webkit-transform: translate(0, 0) rotate(0);
        transform: translate(0, 0) rotate(0);
    }
}
</style>