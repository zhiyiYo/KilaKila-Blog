<template>
    <div class="card">
        <div class="card-header">
            <div>
                <span><font-awesome-icon :icon="['fas', 'box-archive']" class="header-icon" /></span>
                <span>归档</span>
            </div>
            <router-link to="/archive/" class="header-more-link" title="查看更多"><font-awesome-icon
                    :icon="['fas', 'angle-right']" class="header-more-icon" /></router-link>
        </div>
        <div class="card-body archive-list">
            <router-link v-for="archive in archiveCounts" :key="archive.date" :to="`/archive/${archive.date}`"
                class="archive-item">
                <span class="archive-date">{{ archive.date.split("/")[0] }} 年
                    {{ archive.date.split("/")[1] }} 月
                </span>
                <span class="archive-count">{{ archive.count }}</span>
            </router-link>
        </div>
    </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { getArchiveCountList } from "../api/archive";

export default {
    name: "KilaKilaArchiveCard",
    setup() {
        let archiveCounts = reactive([]);

        getArchiveCountList(1, 100).then((data) => {
            archiveCounts.push(...data.rows);
        });

        return { archiveCounts };
    },
};
</script>

<style scoped>
.card {
    background: white;
    border-radius: 8px;
    box-shadow: var(--card-box-shadow);
    padding: 20px 24px;
    width: 100%;
    margin-top: 25px;
    box-sizing: border-box;
}

.card-header {
    text-align: left !important;
    margin-bottom: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header-icon {
    font-size: 18px;
    margin-right: 10px;
    color: dodgerblue;
}

.header-more-link {
    transition: all 0.2s;
}

.header-more-link:hover {
    animation: moveLinkAni 1s infinite;
}

.header-more-icon {
    font-size: 18px;
    color: var(--text-color);
}

.card-header span {
    font-size: 17px;
    color: var(--text-color);
}

.archive-item {
    display: flex;
    justify-content: space-between;
    text-decoration: none;
    padding: 10px 10px;
    color: var(--text-color);
    font-size: 14px;
    transition: all 0.4s;
    border-radius: 4px;
}

.archive-item:hover {
    background-color: var(--theme-color);
    color: white;
    padding: 10px 17px;
}

.archive-date {
    flex: 1;
    overflow: hidden;
    text-overflow: ellipsis;
}

@keyframes moveLinkAni {

    0%,
    100% {
        -webkit-transform: translateX(0);
        -moz-transform: translateX(0);
        -o-transform: translateX(0);
        -ms-transform: translateX(0);
        transform: translateX(0);
    }

    50% {
        -webkit-transform: translateX(3px);
        -moz-transform: translateX(3px);
        -o-transform: translateX(3px);
        -ms-transform: translateX(3px);
        transform: translateX(3px);
    }
}
</style>