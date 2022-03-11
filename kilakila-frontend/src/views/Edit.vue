<template>
    <div class="edit">
        <!-- 页头 -->
        <kila-kila-header />

        <!-- 二次元封面 -->
        <kila-kila-wife-cover>
            <h1>新随笔</h1>
        </kila-kila-wife-cover>

        <!-- 编辑表单 -->
        <div class="edit-card">
            <h1 class="kila-kila-blog-title">✨ Kila Kila Blog ✨</h1>
            <el-form
                ref="ruleFormRef"
                :model="ruleForm"
                :rules="rules"
                label-width="60px"
                class="edit-ruleForm"
            >
                <!-- 标题 -->
                <el-form-item prop="title" label="标题">
                    <el-input
                        v-model="ruleForm.title"
                        class="w-50 m-3"
                        size="large"
                        placeholder="给博客起一个好标题很重要哦"
                    ></el-input>
                </el-form-item>

                <!-- 摘要 -->
                <el-form-item prop="summary" label="摘要">
                    <el-input
                        v-model="ruleForm.summary"
                        class="w-50 m-3"
                        size="large"
                        type="textarea"
                        placeholder="简要介绍一下这篇博客吧"
                    ></el-input>
                </el-form-item>

                <div class="inline-form-row">
                    <!-- 分类 -->
                    <el-form-item
                        label="分类"
                        prop="category"
                        inline
                        style="width: 35%"
                    >
                        <el-select-v2
                            v-model="ruleForm.category"
                            :options="categories"
                            placeholder="给博客分个类吧"
                            style="width: 100%; vertical-align: middle"
                            allow-create
                            filterable
                            clearable
                        />
                    </el-form-item>

                    <!-- 标签 -->
                    <el-form-item
                        label="标签"
                        prop="tag"
                        inline
                        style="width: 60%"
                    >
                        <el-select-v2
                            v-model="ruleForm.tags"
                            :options="tags"
                            placeholder="来贴几个标签吧"
                            style="width: 100%; vertical-align: middle"
                            multiple
                            allow-create
                            filterable
                            clearable
                        />
                    </el-form-item>
                </div>

                <!-- 内容 -->
                <el-form-item prop="content" label="内容">
                    <mavon-editor
                        v-model="ruleForm.content"
                        id="mavon"
                        codeStyle="atom-one-dark"
                        :autofocus="false"
                        @imgAdd="onImageAdded"
                        ref="mavonRef"
                    />
                </el-form-item>

                <!-- 缩略图 -->
                <el-form-item prop="thumbnail" label="缩略图">
                    <el-upload
                        class="upload-demo"
                        drag
                        action=""
                        :limit="1"
                        :http-request="uploadThumbnail"
                        :before-remove="beforeRemoveThumbnail"
                    >
                        <el-icon class="el-icon--upload"
                            ><upload-filled
                        /></el-icon>
                        <div class="el-upload__text">
                            拖拽文件或 <em>点击上传</em>
                        </div>
                    </el-upload>
                </el-form-item>

                <!-- 按钮 -->
                <el-form-item>
                    <el-button
                        type="primary"
                        @click="submitForm()"
                        color="#1892ff"
                        class="el-button"
                        >立刻发布</el-button
                    >
                    <el-button class="el-button">存为草稿</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </div>

        <!-- 页脚 -->
        <kila-kila-footer />
    </div>
</template>

<script>
import { ref, reactive, computed, nextTick } from "vue";
import { mapState } from "../store/map";
import KilaKilaHeader from "../components/KilaKilaHeader.vue";
import KilaKilaWifeCover from "../components/KilaKilaWifeCover.vue";
import KilaKilaFooter from "../components/KilaKilaFooter.vue";
import { UploadFilled } from "@element-plus/icons-vue";
import { uploadImage } from "../api/image";

export default {
    name: "Edit",
    components: {
        KilaKilaHeader,
        KilaKilaWifeCover,
        KilaKilaFooter,
        UploadFilled,
    },
    setup() {
        let content = ref("");
        let mavonRef = ref();
        let ruleFormRef = ref();
        let { categoryCounts } = mapState("categoryAbout");
        let { tagCounts } = mapState("tagAbout");
        let categories = computed(() => {
            return categoryCounts.value.map((i) => ({
                value: i.id,
                label: i.name,
            }));
        });
        let tags = computed(() => {
            return tagCounts.value.map((i) => ({
                value: i.id,
                label: i.name,
            }));
        });

        let ruleForm = reactive({
            title: "",
            summary: "",
            content: "",
            category: "",
            tags: [],
            thumbnail: "",
        });
        let rules = reactive({
            title: [
                {
                    required: true,
                    message: "文章标题不能为空",
                    trigger: "change",
                },
            ],
            content: [
                {
                    required: true,
                    message: "文章内容不能为空",
                    trigger: "change",
                },
            ],
            category: [
                {
                    required: true,
                    message: "分类不能为空",
                    trigger: "change",
                },
            ],
        });

        function onImageAdded(pos, file) {
            uploadImage(file).then((url) => {
                mavonRef.value.$img2Url(pos, url);
            });
        }

        function uploadThumbnail(file) {
            uploadImage(file.file).then((url) => {
                ruleForm.thumbnail = url;
            });
        }

        function beforeRemoveThumbnail(file, fileList) {
            ruleForm.thumbnail = "";
        }

        nextTick(() => {
            window.scrollTo({ top: 0 });
        });

        return {
            content,
            ruleForm,
            ruleFormRef,
            rules,
            categories,
            tags,
            onImageAdded,
            mavonRef,
            uploadThumbnail,
            beforeRemoveThumbnail,
        };
    },
    props: ["id"],
};
</script>

<style lang="less" scoped>
.wife-cover {
    display: flex;
    align-items: center;
    justify-content: center;

    h1 {
        font-size: 35px;
        font-weight: normal;
        color: white;
        line-height: 1.5;
        margin-bottom: 15px;
        position: absolute;
        text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
    }
}

.edit-card {
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
    padding: 10px 30px;
    width: 70%;
    margin: 50px auto 0;
    box-sizing: border-box;
    animation: fadeInUp 1s;

    .kila-kila-blog-title {
        text-align: center;
        font-size: 26px;
        font-family: "Kanit";
        color: #4679fa;
    }

    .inline-form-row {
        display: flex;
        justify-content: space-between;
    }

    #mavon {
        width: 100%;
        max-height: 600px;
    }

    .el-button {
        transition: all 0.4s;
    }
}

@keyframes fadeInUp {
    from {
        transform: translateY(50px);
        opacity: 0;
    }

    to {
        transform: translateX(0);
        opacity: 1;
    }
}
</style>