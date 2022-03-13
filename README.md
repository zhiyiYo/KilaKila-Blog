# ✨ Kila Kila Blog ✨
一个基于 SpringBoot 和 Vue3 的博客系统，取名灵感来自 aiko 的第 18 首单曲 [キラキラ](https://www.youtube.com/watch?v=S0bXDRY1DGM)。

# 快速开始
1. 运行 `resource/sql/kila_kila_blog.sql`，创建数据库
2. 修改 `kilakila-backend/blog/src/main/resources/application.yml` 中的配置，主要有以下两项：
   1. `spring.datasource` 中的 `url`、`username` 和 `password` 改成你自己的
   2. `spring.redis` 的 `password` 改成你自己的，没有设置 redis 密码的话可以去掉该项配置

3. 在 IDEA 中导入 kilakila-backend 项目，启动 `BlogApplication`
4. 进入 kilakila-frontend 目录，打开终端，运行以下命令：
   ```shell
   npm install
   npm run serve
   ```
5. 在浏览器中打开网页 [http://localhost:8080/](http://localhost:8080/)，开启博客之旅~

# FAQs
* **管理员的用户名和密码是啥呀？**

    用户名：zhiyi，密码：1234，数据库中的其他普通账户的密码也是 1234。