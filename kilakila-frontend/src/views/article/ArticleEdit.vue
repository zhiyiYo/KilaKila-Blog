<template>
    <div id="arctile-edit">
        <!-- 页头 -->
        <kila-kila-header />

        <!-- 二次元封面 -->
        <kila-kila-wife-cover>
            <h1>{{ title }}</h1>
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
                    <kila-kila-uploader
                        @uploaded="handleThumbnailUploaded"
                        @aboutToUpload="handleAboutToUploadThumbnail"
                        @removed="handleRemoveThumbnail"
                        ref="uploaderRef"
                    />
                </el-form-item>

                <!-- 按钮 -->
                <el-form-item>
                    <el-button
                        type="primary"
                        @click="submitForm(ruleFormRef, false)"
                        color="#1892ff"
                        class="el-button"
                        id="submit-button"
                        >立刻发布</el-button
                    >
                    <el-button
                        class="el-button"
                        id="draft-button"
                        @click="submitForm(ruleFormRef, true)"
                        >存为草稿</el-button
                    >
                    <el-button @click="cancelSubmit">取消</el-button>
                </el-form-item>
            </el-form>
        </div>

        <!-- 页脚 -->
        <kila-kila-footer />
    </div>
</template>

<script>
import { ref, reactive, computed, nextTick } from "vue";
import { mapState } from "../../store/map";
import { ElMessage, ElMessageBox } from "element-plus";
import { addArticle, editArticle, getArticleDetails } from "../../api/article";
import { uploadImage } from "../../api/image";
import router from "../../router";
import bus from "../../utils/bus";
import markdownIt from "../../utils/markdown-it";

export default {
    name: "ArticleEdit",
    setup(props) {
        let isInEditMode = props.id ? true : false;
        let content = ref("");
        let mavonRef = ref();
        let ruleFormRef = ref();
        let uploaderRef = ref();
        let title = computed(() => (isInEditMode ? "编辑博客" : "新随笔"));
        let { categoryCounts } = mapState("categoryAbout");
        let { tagCounts } = mapState("tagAbout");
        let categories = computed(() => {
            return categoryCounts.value.map((i) => ({
                value: i.name,
                label: i.name,
            }));
        });
        let tags = computed(() => {
            return tagCounts.value.map((i) => ({
                value: i.name,
                label: i.name,
            }));
        });

        let ruleForm = reactive({
            id: undefined,
            title: "",
            summary: "",
            content: "",
            category: "",
            tags: [],
            thumbnail: "",
            isDraft: false,
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

        // 如果有传入文章 id 需要先获取文章信息
        if (isInEditMode) {
            getArticleDetails(props.id).then((data) => {
                Object.assign(ruleForm, data);
                ruleForm.category = data.categoryName;
                ruleForm.tags = data.tags.map((t) => t.name);
                if (data.thumbnail) {
                    uploaderRef.value.setImageUrl(data.thumbnail);
                    uploaderRef.value.isSuccessLabelVisible = true;
                }
            });
        }

        function onImageAdded(pos, file) {
            uploadImage(file).then((url) => {
                mavonRef.value.$img2Url(pos, url);
            });
        }

        function handleThumbnailUploaded(url) {
            ruleForm.thumbnail = url;
            document.getElementById("submit-button").disabled = false;
            document.getElementById("draft-button").disabled = false;
        }

        function handleAboutToUploadThumbnail() {
            document.getElementById("submit-button").disabled = true;
            document.getElementById("draft-button").disabled = true;
        }

        function handleRemoveThumbnail(file) {
            ruleForm.thumbnail = "";
        }

        function submitForm(form, isDraft) {
            if (!validateForm(form)) return;

            ruleForm.isDraft = isDraft;
            generateSummary();

            let name = isDraft ? "草稿" : "博客";
            if (!isInEditMode) {
                addArticle(ruleForm).then((id) => {
                    ElMessage.success(name + "保存成功啦");
                    setTimeout(() => {
                        router.push("/article/" + id);
                    }, 1500);
                });
            } else {
                editArticle(ruleForm).then((id) => {
                    ElMessage.success(name + "编辑成功啦");
                    bus.emit("articlePosted");
                    setTimeout(() => {
                        router.push("/article/" + id);
                    }, 1500);
                });
            }
        }

        function cancelSubmit() {
            ElMessageBox.confirm(
                "前辈已经想好要取消这篇博客的发表了吗？",
                "一条友善的提示",
                {
                    confirmButtonText: "你在教我做事？",
                    cancelButtonText: "我再想想",
                    type: "warning",
                }
            ).then(() => {
                router.push("/");
            });
        }

        function validateForm(form) {
            if (!form) return false;

            form.validate((valid) => {
                if (!valid) {
                    ElMessage.error("必填字段不能为空哦");
                    return false;
                }
            });

            return true;
        }

        function generateSummary() {
            if (ruleForm.summary) {
                return;
            }

            let html = markdownIt.render(ruleForm.content);
            ruleForm.summary = html.replace(/<[^>]+>/g, "").slice(0, 150);
        }

        nextTick(() => {
            window.scrollTo({ top: 0 });
        });

        return {
            title,
            content,
            ruleForm,
            ruleFormRef,
            uploaderRef,
            rules,
            categories,
            tags,
            onImageAdded,
            mavonRef,
            handleThumbnailUploaded,
            handleAboutToUploadThumbnail,
            handleRemoveThumbnail,
            submitForm,
            cancelSubmit,
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
    box-shadow: var(--card-box-shadow);
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

#submit-button:disabled,
#draft-button:disabled {
    cursor: not-allowed;
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