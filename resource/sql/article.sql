CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '文章内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint(20) DEFAULT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint(200) DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 8 DEFAULT CHARSET = utf8mb4 COMMENT = '文章表';
INSERT INTO
  `article`
VALUES
  (
    1,
    '测试 Kila Kila Blog 的博客样式',
    '# 字符效果\n\n* ~~删除线~~ <s>删除线（开启识别HTML标签时）</s>\n\n* *斜体字* _斜体字_\n* **粗体** __粗体__\n* ***粗斜体*** ___粗斜体___\n\n* 上标：X<sub>2</sub>，下标：O<sup>2</sup>\n\n* ==高亮==\n\n* `Inline Code`\n\n> 引用：如果想要插入空白换行（即 `<br>` 标签），在插入处先键入两个以上的空格然后回车即可\n\n\n#  超链接\n* [普通链接](https://www.cnblogs.com/zhiyiYo/)\n* [*斜体链接*](https://www.cnblogs.com/zhiyiYo/)\n* [**粗体链接**](https://www.cnblogs.com/zhiyiYo/)\n\n# 脚注\n这是一个简单的脚注 [^1] 而这是一个更长的脚注 [^bignote].\n\n[^1]: 这是第一个脚注.\n\n[^bignote]: 这是一个非常长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长的脚注.\n\n\n# 图像\n下面是一张我家硝子的壁纸?:\n![硝子1](https://i.niupic.com/images/2022/03/11/9Wl7.jpg)\n再来一张好了?：\n![硝子2](https://i.niupic.com/images/2022/03/12/9Wme.jpg)\n\n# 代码\n\n## 行内代码\n在 VS Code 中按下 <kbd>Alt</kbd> + <kbd>T</kbd> + <kbd>R</kbd> 执行命令：`npm install marked`\n\n## 代码片\n\n### Python 代码\n```python\nclass Animal:\n    \"\"\" 动物类 \"\"\"\n\n    def __init__(self, age: int, name: str):\n        self.age = age\n        self.name = name\n\n    def getInfo(self) -> str:\n        \"\"\" 返回信息 \"\"\"\n        return f\'age: {self.age}; name: {self.name}\'\n\n\nclass Dog(Animal):\n    \"\"\" 狗狗类 \"\"\"\n\n    def __init__(self, age: int, name: str, gender=\'female\', color=\'white\'):\n        super().__init__(age, name)\n        self.gender = gender\n        self.__color = color\n\n    def bark(self):\n        \"\"\" 狗叫 \"\"\"\n        print(\'lololo\')\n\n    @property\n    def color(self):\n        return self.__color\n\n    @color.setter\n    def color(self, color: str):\n        if color not in [\'red\', \'white\', \'black\']:\n            raise ValueError(\'颜色不符合要求\')\n        self.__color = color\n\n\nif __name__ == \'__main__\':\n    dog = Dog(16, \'啸天\', gender=\'male\')\n    # 狗叫\n    dog.bark()\n    # 设置狗狗毛色\n    dog.color = \'blue\'\n```\n\n### HTML 代码\n\n```html\n<!DOCTYPE html>\n<html>\n    <head>\n        <mate charest=\"utf-8\" />\n        <title>Hello world!</title>\n    </head>\n    <body>\n        <h1>Hello world!</h1>\n    </body>\n</html>\n```\n\n\n\n# 列表\n\n## 无序列表\n\n* 福建\n  * 厦门\n  * 福州\n* 浙江\n* 江苏\n## 有序列表\n\n1. 动物\n   1. 人类\n   2. 犬类\n2. 植物\n3. 微生物\n\n## 任务列表\n\n- [x] 预习计算机网络\n- [ ] 复习现代控制理论\n- [ ] 刷现代控制理论历年卷\n    - [ ] 2019 年期末试卷\n    - [ ] 2020 年期末试卷\n\n\n\n# 表格\n\n| 项目   |  价格 | 数量  |\n| ------ | ----: | :---: |\n| 计算机 | $1600 |   5   |\n| 手机   |   $12 |  12   |\n| 管线   |    $1 |  234  |\n\n----\n\n# 特殊符号\n\n&copy; &  &uml; &trade; &iexcl; &pound;\n&amp; &lt; &gt; &yen; &euro; &reg; &plusmn; &para; &sect; &brvbar; &macr; &laquo; &middot;\n\nX&sup2; Y&sup3; &frac34; &frac14;  &times;  &divide;   &raquo;\n\n18&ordm;C  &quot;  &apos;\n\n# Emoji表情 :satisfied:\n* 马：:horse:\n* 星星：:star2:\n* 笑脸：:smile:\n* 跑步：:running:\n\n# 数学公式\n\n行间公式：\n$$\\sin(\\alpha)^{\\theta}=\\sum_{i=0}^{n}(x^i + \\cos(f))$$\n行内公式 $E=mc^2$\n\n# 绘图\n## 时序图\n\n```mermaid\nsequenceDiagram\n\n  Alice->>John: Hello John, how are you?\n  loop Healthcheck\n      John->>John: Fight against hypochondria\n  end\n  Note right of John: Rational thoughts!\n       John-->>Alice: Great!\n       John->>Bob   : How about you?\n       Bob-->>John  : Jolly good!\n```\n\n\n## 状态图\n\n```mermaid\nstateDiagram\n\n    [*]-->Active\n    state Active {\n        [*]-->NumLockOff\n        NumLockOff-->NumLockOn : EvNumLockPressed\n        NumLockOn-->NumLockOff : EvNumLockPressed\n        --\n        [*]-->CapsLockOff\n        CapsLockOff-->CapsLockOn : EvCapsLockPressed\n        CapsLockOn-->CapsLockOff : EvCapsLockPressed\n        --\n        [*]-->ScrollLockOff\n        ScrollLockOff-->ScrollLockOn : EvCapsLockPressed\n        ScrollLockOn-->ScrollLockOff : EvCapsLockPressed\n            }\n```',
    '这篇博客用来测试 Kila Kila Blog 的正文样式',
    1,
    'https://i.niupic.com/images/2022/03/11/9Wl7.jpg',
    '0',
    '0',
    42,
    '1',
    1,
    '2022-01-12 21:16:07',
    NULL,
    '2022-03-12 21:16:07',
    0
  ),(
    2,
    '对 python 中 @property 装饰器的一点思考',
    '# 前言\npython 中提供了 `@property` 装饰器，可以将一个成员函数变为成员变量来访问，之前只是觉得这东西应该就只是为了 `setter` 和 `getter` 而存在的，但是学了 Vue 的计算属性后对这个装饰器有了新的见解。\n\n# 计算属性\n在 Vue 中提供了计算属性 `computed`，避免在模板中写出很长的表达式。仔细想想，用了 `@property` 装饰器的方法不就是计算属性的 `get()` 吗，然后 `@property.setter` 不就是 `set()` 吗？有了 `get()` 之后，当一个变量发生变化时，计算属性也随之发生变化，这样在更新变量时没必要手动在代码中更新计算属性的值，代码看起来就更加简洁了。比如，有以下代码：\n```python\nclass ImageViewer:\n\n    def __init__(self):\n        self.imagePaths = [\'キラキラ.png\', \'恋をしたのは.png\']\n        self.index = 0\n\n    def next(self):\n        if self.index >= len(self.imagePaths)-1:\n            return\n\n        self.index += 1\n\n    def previous(self):\n        if self.index <= 0\n            return\n\n        self.index -= 1\n\n    @property\n    def imagePath(self):\n        return self.imagePaths[self.index]\n```\n有了 `@property` 之后，每次访问到的 `imagePath` 都会是最新的，没必要在 `next()` 方法和 `previous()` 中再写一行更新 `imagePath` 的代码，我们只需维护好 `index` 即可，以上~',
    'python 中提供了 `@property` 装饰器，可以将一个成员函数变为成员变量来访问，之前只是觉得这东西应该就只是为了 `setter` 和 `getter` 而存在的，但是学了 Vue 的计算属性后对这个装饰器有了新的见解。',
    2,
    'https://i.niupic.com/images/2022/03/12/9Wme.jpg',
    '0',
    '0',
    18,
    '1',
    1,
    '2022-02-12 22:32:45',
    NULL,
    '2022-03-12 22:32:45',
    0
  ),(
    3,
    '如何在 IDEA 中配置 Easy Code 的 MybatisPlus 实体类模板',
    '# 前言\nEasy Code 是一个很强大的插件，可以根据数据库的表结构生成对应的实体类、Mapper 接口等代码，省去了编写没有技术含量的代码的工作，极大提升敲代码的效率。但是如果仅仅使用预置的模板可能无法满足我们的需求，这时候就可以自己去定制模板。下面给出实体类的模板。\n\n# 实现流程\n\n我们希望实体类满足以下需求：\n\n* 使用 Lombok 的 `@Data`、`@NoArgsConstructor` 和 `@AllArgsConstructor` 注解\n* 使用 `@TableName` 注解让实体类应该和数据库的表名对应\n* 实现 `Serializable` 接口\n* 在主键字段上使用 `@TableId` 注解\n\n对应的模板如下所示：\n\n```java\n##导入宏定义\n$!{define.vm}\n\n##保存文件（宏定义）\n#save(\"/entity\", \".java\")\n\n##包路径（宏定义）\n#setPackageSuffix(\"entity\")\n\n##自动导入包（全局变量）\n$!{autoImport.vm}\nimport java.io.Serializable;\n\nimport com.baomidou.mybatisplus.annotation.IdType;\nimport com.baomidou.mybatisplus.annotation.TableId;\nimport com.baomidou.mybatisplus.annotation.TableName;\nimport lombok.Data;\nimport lombok.AllArgsConstructor;\nimport lombok.NoArgsConstructor;\n\n##表注释（宏定义）\n#tableComment(\"表实体类\")\n@SuppressWarnings(\"serial\")\n@Data\n@AllArgsConstructor\n@NoArgsConstructor\n@TableName(\"$!{tableInfo.obj.name}\")\npublic class $!{tableInfo.name} implements Serializable {\n\n    private static final long serialVersionUID = $!tool.serial();\n\n#foreach($column in $tableInfo.fullColumn)\n    #if(${column.comment})/**\n     * ${column.comment}\n     */#end\n    \n  #if($column.name.equals(\'id\'))\n  @TableId(type = IdType.AUTO)\n  #end\n  private $!{tool.getClsNameByFullName($column.type)} $!{column.name}; \n    \n#end\n\n}\n\n```\n\n',
    '前言\nEasy Code 是一个很强大的插件，可以根据数据库的表结构生成对应的实体类、Mapper 接口等代码，省去了编写没有技术含量的代码的工作，极大提升敲代码的效率。但是如果仅仅使用预置的模板可能无法满足我们的需求，这时候就可以自己去定制模板。下面给出实体类的模板。\n实现流程\n我们希望实体类满足',
    3,
    'https://i.niupic.com/images/2022/03/11/9Wl4.jpg',
    '0',
    '0',
    8,
    '1',
    1,
    '2022-03-13 13:24:53',
    NULL,
    '2022-03-13 13:24:53',
    0
  );