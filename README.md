# ✨ Kila Kila Blog ✨
一个基于 SpringBoot 和 Vue3 的博客系统，取名灵感来自 aiko 的第 18 首单曲 [キラキラ](https://www.youtube.com/watch?v=S0bXDRY1DGM)。

## 博客界面
![文章详情](resource/images/博客界面.gif)

## 快速开始
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

## FAQs
* **管理员的用户名和密码是啥呀？**

    用户名：zhiyi，密码：1234，数据库中的其他普通账户的密码也是 1234。


## License
```
MIT License

Copyright (c) 2022 Huang Zhengzhi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```