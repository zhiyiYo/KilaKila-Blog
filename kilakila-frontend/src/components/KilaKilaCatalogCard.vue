<template>
    <div class="catalog-card">
        <div class="catalog-card-header">
            <div>
                <span
                    ><font-awesome-icon
                        :icon="['fas', 'bars-staggered']"
                        class="catalog-icon"
                /></span>
                <span>目录</span>
            </div>
            <span class="progress">{{ progress }}</span>
        </div>

        <div class="catalog-content">
            <div
                v-for="title in titles"
                :key="title.id"
                @click="scrollToView(title.scrollTop)"
                :class="[
                    'catalog-item',
                    currentTitle.id == title.id ? 'active' : 'not-active',
                ]"
                :style="{ marginLeft: title.level * 20 + 'px' }"
                v-show="
                    title.parentId ? titleTree[title.parentId][title.id] : true
                "
                :title="title.rawName"
            >
                {{ title.name }}
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, ref } from "vue";
import KilaKilaCard from "./KilaKilaCard";

export default {
    name: "KilaKilaCatalog",
    components: { KilaKilaCard },
    setup() {
        let { titles, titleMap, titleTree } = getTitles();
        titles = reactive(titles);
        titleTree = reactive(titleTree);
        let currentTitle = reactive({});
        let progress = ref(0);

        // 获取目录的标题
        function getTitles() {
            let titles = [];
            let titleTree = {};
            let levels = ["h1", "h2", "h3"];

            let articleElement = document.querySelector(
                ".post-body .article-content"
            );
            if (!articleElement) {
                return { titles, titleTree };
            }

            let elements = Array.from(articleElement.querySelectorAll("*"));

            // 调整标签等级
            let tagNames = new Set(
                elements.map((el) => el.tagName.toLowerCase())
            );
            for (let i = levels.length - 1; i >= 0; i--) {
                if (!tagNames.has(levels[i])) {
                    levels.splice(i, 1);
                }
            }

            let lastNode;
            let serialNumbers = levels.map(() => 0);
            for (let i = 0; i < elements.length; i++) {
                const element = elements[i];
                let tagName = element.tagName.toLowerCase();
                let level = levels.indexOf(tagName);
                if (level == -1) continue;

                let id = tagName + "-" + element.innerText + "-" + i;

                if (!lastNode) {
                    lastNode = {
                        parentId: null,
                        id: id,
                        level: level,
                    };

                    titleTree[id] = {};
                } else {
                    // 遇到子标题
                    if (lastNode.level < level) {
                        titleTree[lastNode.id][id] = false;
                        lastNode.parentId = lastNode.id;
                    }
                    // 遇到上一级标题
                    else if (lastNode.level > level) {
                        titleTree[id] = {};
                        lastNode.parentId = null;
                        serialNumbers.fill(0, level + 1);
                    }
                    // 遇到平级
                    else {
                        if (lastNode.parentId) {
                            titleTree[lastNode.parentId][id] = false;
                        } else {
                            titleTree[id] = {};
                            lastNode.parentId = null;
                        }
                    }

                    lastNode.id = id;
                    lastNode.level = level;
                }

                serialNumbers[level] += 1;
                let serialNumber = serialNumbers.slice(0, level + 1).join(".");

                titles.push({
                    id: id,
                    parentId: lastNode.parentId,
                    name: serialNumber + ". " + element.innerText,
                    rawName: element.innerText,
                    scrollTop: element.offsetTop,
                    level: level,
                });
            }

            let titleMap = Object.fromEntries(titles.map((i) => [i.id, i]));

            return { titles, titleMap, titleTree };
        }

        // 监听滚动事件并更新样式
        window.addEventListener("scroll", function () {
            progress.value =
                parseInt(
                    (window.scrollY / document.documentElement.scrollHeight) *
                        100
                ) + "%";

            let visibleTitleIds = [];

            for (let i = titles.length - 1; i >= 0; i--) {
                const title = titles[i];
                if (title.scrollTop <= window.scrollY) {
                    if (currentTitle.id === title.id) return;

                    Object.assign(currentTitle, title);

                    // 展开节点
                    setChildrenVisible(title.id, true);
                    visibleTitleIds.push(title.id);

                    // 展开父节点
                    let parentId = currentTitle.parentId;
                    while (parentId) {
                        setChildrenVisible(parentId, true);
                        visibleTitleIds.push(parentId);
                        parentId = titleMap[parentId].parentId;
                    }

                    // 折叠其余节点
                    for (const pid in titleTree) {
                        if (!visibleTitleIds.includes(pid)) {
                            setChildrenVisible(pid, false);
                        }
                    }

                    return;
                }
            }
        });

        // 设置子节点的可见性
        function setChildrenVisible(id, isVisible) {
            let children = titleTree[id];
            for (const cid in children) {
                children[cid] = isVisible;
            }
        }

        // 滚动到指定的位置
        function scrollToView(scrollTop) {
            window.scrollTo({ top: scrollTop, behavior: "smooth" });
        }

        return { titles, titleTree, currentTitle, progress, scrollToView };
    },
};
</script>

<style lang="less" scoped>
.catalog-card {
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
    padding: 20px 24px;
    width: 100%;
    margin-top: 25px;
    box-sizing: border-box;
}

.catalog-card-header {
    text-align: left !important;
    margin-bottom: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.catalog-icon {
    font-size: 18px;
    margin-right: 10px;
    color: dodgerblue;
}

.catalog-card-header div > span {
    font-size: 17px;
    color: #4c4948;
}

.progress {
    color: #a9a9a9;
    font-style: italic;
    font-size: 140%;
}

.catalog-content {
    max-height: calc(100vh-120px);
    overflow: hidden;
}

.catalog-item {
    color: #666261;
    margin: 5px 0;
    line-height: 28px;
    cursor: pointer;
    transition: all 0.2s ease-in-out;
    font-size: 14px;
    padding: 2px 6px;
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;

    &:hover {
        color: #1892ff;
    }
}

.active {
    background-color: #1892ff;
    color: white;

    &:hover {
        background-color: #0c82e9;
        color: white;
    }
}
</style>