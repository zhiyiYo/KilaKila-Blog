<template>
    <vue-easy-lightbox
        moveDisabled
        :visible="isVisible"
        :imgs="images"
        :index="index"
        @hide="handleHide"
    ></vue-easy-lightbox>
</template>

<script>
import { reactive, ref } from "vue";
import VueEasyLightbox from "vue-easy-lightbox";

export default {
    name: "KilaKilaLightBox",
    components: { VueEasyLightbox },
    props: {
        container: {
            type: String,
            default: ".post-body .article-content",
        },
    },
    setup(props) {
        let isVisible = ref(false);
        let images = reactive(getImages());
        let index = ref(0);

        function getImages() {
            let images = [];
            let articleElement = document.querySelector(props.container);
            if (!articleElement) return images;

            // 获取所有图片
            let imageElements = Array.from(
                articleElement.querySelectorAll("img")
            );
            for (const imageElement of imageElements) {
                let src = imageElement.src;
                let title = imageElement.title || "";
                let alt = imageElement.alt || "";
                images.push({ title, src, alt });
            }

            return images;
        }

        function addImageClickedListener() {
            let imageElements = Array.from(
                document.querySelectorAll(props.container + " img")
            );
            imageElements.forEach((imageElement, i) => {
                imageElement.addEventListener("click", () => {
                    index.value = i;
                    show();
                });
            });
        }

        function show() {
            isVisible.value = true;
        }

        function handleHide() {
            isVisible.value = false;
        }

        return {
            images,
            isVisible,
            index,
            handleHide,
            show,
            addImageClickedListener,
        };
    },
};
</script>

<style scoped>
:deep(.vel-img) {
    box-shadow: 0 5px 20px 2px rgba(0, 0, 0, 0.35);
}
</style>