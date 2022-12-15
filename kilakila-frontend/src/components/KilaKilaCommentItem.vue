<template>
    <div :class="commentClass">
        <!-- 头像 -->
        <div class="comment-item-avatar">
            <img :src="comment.avatar" @error.once="fallbackAvatar" alt="用户头像" />
        </div>

        <!-- 评论内容 -->
        <div class="comment-item-body">
            <!-- 评论头 -->
            <div class="comment-item-header">
                <!-- 评论信息 -->
                <div class="comment-item-info">
                    <a @click.prevent="scrollToAnchor">#{{ floorNumber }}楼
                    </a>
                    <span>{{ comment.createTime }}</span>
                    <span>{{ comment.userName }}</span>
                </div>

                <!-- 评论操作 -->
                <div class="comment-item-actions">
                    <!-- 表情 -->
                    <span class="smile-face">
                        <svg xmlns="http://www.w3.org/2000/svg" height="16" viewBox="0 0 16 16" version="1.1"
                            width="16">
                            <path fill-rule="evenodd"
                                d="M1.5 8a6.5 6.5 0 1113 0 6.5 6.5 0 01-13 0zM8 0a8 8 0 100 16A8 8 0 008 0zM5 8a1 1 0 100-2 1 1 0 000 2zm7-1a1 1 0 11-2 0 1 1 0 012 0zM5.32 9.636a.75.75 0 011.038.175l.007.009c.103.118.22.222.35.31.264.178.683.37 1.285.37.602 0 1.02-.192 1.285-.371.13-.088.247-.192.35-.31l.007-.008a.75.75 0 111.222.87l-.614-.431c.614.43.614.431.613.431v.001l-.001.002-.002.003-.005.007-.014.019a1.984 1.984 0 01-.184.213c-.16.166-.338.316-.53.445-.63.418-1.37.638-2.127.629-.946 0-1.652-.308-2.126-.63a3.32 3.32 0 01-.715-.657l-.014-.02-.005-.006-.002-.003v-.002h-.001l.613-.432-.614.43a.75.75 0 01.183-1.044h.001z">
                            </path>
                        </svg>
                    </span>

                    <!-- 更多操作 -->
                    <span class="more-actions" @click="showMoreActionsMenu">
                        <svg xmlns="http://www.w3.org/2000/svg" role="img" height="16" width="16" viewBox="0 0 16 16"
                            version="1.1">
                            <path
                                d="M8 9a1.5 1.5 0 100-3 1.5 1.5 0 000 3zM1.5 9a1.5 1.5 0 100-3 1.5 1.5 0 000 3zm13 0a1.5 1.5 0 100-3 1.5 1.5 0 000 3z">
                            </path>
                        </svg>
                    </span>

                    <el-dropdown ref="moreActionsMenu" trigger="contextmenu" @command="handleMenuCommand">
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item command="reply">回复</el-dropdown-item>
                                <el-dropdown-item command="update" v-if="canModify">修改</el-dropdown-item>
                                <el-dropdown-item command="delete" v-if="canModify">删除</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </div>
            <div class="comment-item-content" v-html="content"></div>
        </div>
    </div>
</template>

<script>
import { reactive, computed, ref } from "vue";
import { fallbackAvatar } from "../utils/avatar";
import markdownIt from "../utils/markdown-it";
import { getUserInfo } from "../utils/storage";


export default {
    name: "KilaKilaCommentItem",
    props: {
        comment: {
            type: Object,
            require: true
        },
        floorNumber: {
            type: Number,
            require: true
        }
    },
    emits: ["reply", "update", "delete"],
    setup(props, context) {
        let commentClass = reactive(["comment-item"])
        if (props.comment.isAdmin) {
            commentClass.push("comment-item-admin")
        }

        let content = computed(() => markdownIt.render(props.comment.content));
        let moreActionsMenu = ref();
        let userInfo = getUserInfo();
        let canModify = userInfo ? ref(userInfo.isAdmin || props.comment.createBy == userInfo.id) : ref(false);

        function scrollToAnchor(event) {
            event.target.scrollIntoView({ behavior: "smooth" })
        }

        function showMoreActionsMenu() {
            moreActionsMenu.value.handleOpen();
        }

        function handleMenuCommand(command) {
            context.emit(command, props.comment);
        }

        return {
            commentClass,
            content,
            moreActionsMenu,
            canModify,
            scrollToAnchor,
            showMoreActionsMenu,
            handleMenuCommand
        }
    }
}
</script>

<style lang="less" scoped>
.comment-item {
    display: flex;
    padding: 15px 0 15px 0;
    position: relative;

    .comment-item-avatar {
        img {
            height: 40px;
            width: 40px;
            transition: all 0.6s ease-out;
            box-shadow: 0 0 0 1px rgba(27, 31, 36, 0.15);
            border-radius: 50%;

            &:hover {
                transform: rotate(360deg);
            }
        }
    }

    .comment-item-body {
        border: 1px solid #d0d7de;
        border-radius: 6px;
        width: 100%;
        margin-left: 20px;
        z-index: 1;
        flex: 1;
        min-width: 0;

        .comment-item-header {
            display: flex;
            justify-content: space-between;
            font-size: 13px;
            color: var(--text-color);
            color: #586069;
            border-top-left-radius: 6px;
            border-top-right-radius: 6px;
            background-color: #f6f8fa;
            border-bottom: 1px solid #d0d7de;
            padding: 9px 20px;
            white-space: nowrap;
            text-overflow: ellipsis;
            position: relative;

            .comment-item-info {
                a {
                    color: var(--text-color);
                    text-decoration: none;
                    transition: all .3s ease;
                    position: relative;
                    cursor: pointer;

                    &::after {
                        content: "";
                        display: block;
                        width: 0;
                        height: 1px;
                        position: absolute;
                        left: 0;
                        bottom: -2px;
                        background: var(--theme-color);
                        transition: all 0.3s ease-in-out;
                    }

                    &:hover {
                        color: var(--theme-color);

                        &::after {
                            width: 100%;
                        }
                    }
                }

                span {
                    margin: 0 3px;
                }
            }

            .comment-item-actions {
                span {
                    color: #57606a;
                    padding: 0px 4px;
                    cursor: pointer;
                    font-size: 13px;

                    &:hover {
                        color: #0969da;
                    }

                    svg {
                        fill: currentColor;
                    }
                }
            }

            /*气泡三角形*/
            &::before,
            &::after {
                top: 11px;
                right: 100%;
                left: -16px;
                width: 0;
                height: 0;
                content: " ";
                border-style: solid solid outset;
                position: absolute;
                display: block;
                pointer-events: none;
                border-color: transparent #d0d7de transparent transparent;
                border-width: 8px
            }

            &::after {
                border-color: transparent;
                margin-top: 1px;
                margin-left: 2px;
                border-width: 7px;
                border-right-color: #f6f8fa;
            }

            /* 弹出菜单 */
            :deep(.el-dropdown) {
                left: -13px;
                top: 10px;
            }
        }

        :deep(.comment-item-content) {
            padding: 16px;
            font-size: 14px;
            background-color: white;
            border-bottom-left-radius: 6px;
            border-bottom-right-radius: 6px;

            a {
                color: var(--theme-color);
                position: relative;
                text-decoration: none;

                &:after {
                    content: "";
                    display: block;
                    width: 0;
                    height: 1px;
                    position: absolute;
                    left: 0;
                    bottom: -2px;
                    background: var(--theme-color);
                    transition: all 0.3s ease-in-out;
                }

                &:hover {
                    &::after {
                        width: 100%;
                    }
                }
            }

            p {
                margin: 0 0;

                /* 代码块 */
                &>code {
                    color: #c7254e;
                    font-family: consolas !important;
                    vertical-align: middle;
                    margin: 0 3px;
                    background-color: #f9f2f4 !important;
                    font-size: 13px !important;
                    border-radius: 3px !important;
                    border: 1px solid #f9f2f4 !important;
                    padding: 0.2em 0.3em !important;
                    word-break: break-all;
                }
            }

            pre {
                margin: 10px 0;
                white-space: pre;
                position: relative;
                border-radius: 7px;
                color: #bababa;
                background-color: #282c34;
                font-size: 14px;
                padding: 0;

                code {
                    border: none;
                    border-radius: 7px;
                    font-family: Consolas, Monaco, "Andale Mono", "Ubuntu Mono",
                        monospace !important;
                    line-height: 21px;
                }

            }

            img {
                margin: 10px 0;
                max-width: 100% !important;
                max-height: 400px;
            }

            /* 引用 */
            blockquote {
                padding: 0 1em;
                color: #57606a;
                border: none;
                border-left: 0.25em solid #d0d7de;
                margin: 10px 0;

                p {
                    line-height: 1.8;

                    a {
                        color: #57606a;
                        transition: all 0.3s ease;

                        &:hover {
                            color: var(--theme-color);
                        }
                    }
                }
            }
        }
    }

    // 连接线
    &::before {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 90px;
        display: block;
        z-index: 0;
        width: 2px;
        content: "";
        background-color: #d8dee4;
    }

    &:first-child {
        padding-top: 0;
    }

    &:last-child {
        padding-bottom: 0;
    }
}

.comment-item-admin {
    .comment-item-body {
        border: 1px solid #bbdfff;

        .comment-item-header {
            background-color: #ddf4ff;
            border-bottom: 1px solid #bbdfff;

            &::before,
            &::after {
                border-color: transparent #bbdfff transparent transparent;
            }

            &::after {
                border-right-color: #ddf4ff;
            }
        }
    }
}
</style>