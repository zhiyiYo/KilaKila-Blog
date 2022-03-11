<template>
    <kila-kila-card :icon="['fas', 'tags']" iconColor="#db669f" title="标签">
        <div class="tag-clouds">
            <router-link
                class="tag-item"
                v-for="tag in tagClouds"
                :key="tag.id"
                :to="`/tag/${tag.id}`"
                :style="tag.style"
                >{{ tag.name }}</router-link
            >
        </div>
    </kila-kila-card>
</template>

<script>
import KilaKilaCard from "./KilaKilaCard";
import { mapState } from "../store/map";
import wordCloud from "../utils/wordCloud";
import { computed } from "@vue/runtime-core";

export default {
    name: "KilaKilaTagCard",
    components: {
        KilaKilaCard,
    },
    setup() {
        let { tagCounts } = mapState("tagAbout");
        let tagClouds = computed(() => wordCloud(tagCounts.value));
        return { tagClouds };
    },
};
</script>

<style scoped>
.tag-clouds {
    font-size: 14px;
    box-sizing: border-box;
}

.tag-item {
    text-decoration: none;
    display: inline-block;
    transition: all 0.4s;
    padding: 0 4px;
    overflow-wrap: break-word;
    line-height: 2;
}

.tag-item:hover {
    color: #1892ff !important;
}
</style>