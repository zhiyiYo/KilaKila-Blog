<template>
    <div class="register">
        <kila-kila-form-card title="你来啦">
            <el-form
                ref="ruleFormRef"
                :model="ruleForm"
                :rules="rules"
                label-width="0px"
                class="demo-ruleForm"
            >
                <!-- 用户名 -->
                <el-form-item prop="userName">
                    <el-input
                        v-model="ruleForm.userName"
                        class="w-50 m-3"
                        size="large"
                        placeholder="用户名"
                    >
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <User />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <!-- 昵称 -->
                <el-form-item prop="nickName">
                    <el-input
                        v-model="ruleForm.nickName"
                        class="w-50 m-3"
                        size="large"
                        placeholder="昵称"
                    >
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <Postcard />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <!-- 邮箱 -->
                <el-form-item prop="email">
                    <el-input
                        v-model="ruleForm.email"
                        class="w-50 m-3"
                        type="email"
                        size="large"
                        placeholder="邮箱"
                    >
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <Promotion />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <!-- 密码 -->
                <el-form-item prop="password">
                    <el-input
                        v-model="ruleForm.password"
                        class="w-50 m-2"
                        size="large"
                        type="password"
                        placeholder="密码"
                    >
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <Lock />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <!-- 确认密码 -->
                <el-form-item prop="ensurePassword">
                    <el-input
                        v-model="ruleForm.ensurePassword"
                        class="w-50 m-2"
                        size="large"
                        type="password"
                        placeholder="确认密码"
                    >
                        <template #prefix>
                            <el-icon class="el-input__icon">
                                <Lock />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <!-- 注册按钮 -->
                <el-form-item>
                    <kila-kila-button @click="submitForm(ruleFormRef)"
                        >注册</kila-kila-button
                    >
                </el-form-item>
            </el-form>
        </kila-kila-form-card>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { User, Lock, Postcard, Promotion } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import KilaKilaButton from "../components/KilaKilaButton";
import KilaKilaFormCard from "../components/KilaKilaFormCard";
import { register } from "../api/user";
import { md5Encryption } from "../utils/encrypt";
import router from "../router/index";

export default {
    name: "Login",
    components: {
        User,
        Lock,
        Postcard,
        Promotion,
        KilaKilaFormCard,
        KilaKilaButton,
    },
    setup() {
        let ruleFormRef = ref();

        let ruleForm = reactive({
            userName: "",
            nickName: "",
            email: "",
            password: "",
            ensurePassword: "",
        });

        let rules = reactive({
            userName: [
                {
                    required: true,
                    message: "用户名不能为空",
                    trigger: "change",
                },
            ],
            nickName: [
                { required: true, message: "昵称不能为空", trigger: "change" },
            ],
            email: [
                {
                    required: true,
                    message: "邮箱地址不能为空",
                    trigger: "change",
                },
                {
                    type: "email",
                    message: "请输入正确的邮箱地址",
                    trigger: "blur",
                },
            ],
            password: [
                { required: true, message: "密码不能为空", trigger: "change" },
            ],
        });

        function submitForm(form) {
            if (!form) return;

            form.validate((valid) => {
                if (!valid) {
                    ElMessage.error("请正确填写前辈的信息");
                    return;
                }

                if (ruleForm.password !== ruleForm.ensurePassword) {
                    ElMessage.error("两次密码不一致");
                    return;
                }

                let password = md5Encryption(ruleForm.password);
                register(
                    ruleForm.userName,
                    ruleForm.nickName,
                    ruleForm.email,
                    password
                ).then((data) => {
                    ElMessage.success("注册用户成功 ✨");
                    setTimeout(() => {
                        router.push("/login");
                    }, 2000);
                });
            });
        }

        return {
            rules,
            ruleForm,
            ruleFormRef,
            submitForm,
        };
    },
};
</script>

<style>
.register {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background: url("../assets/image/login-background.jpg");
    background-size: cover;
}
</style>