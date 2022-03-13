CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `root_id` bigint(20) DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint(20) DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint(20) DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 31 DEFAULT CHARSET = utf8mb4 COMMENT = '评论表';
INSERT INTO
  `comment`
VALUES
  (
    1,
    1,
    -1,
    '吃到了一颗好吃的糖，想跟你的嘴巴分享',
    -1,
    -1,
    1,
    '2022-01-29 07:59:22',
    1,
    '2022-01-29 07:59:22',
    0
  ),(
    2,
    1,
    -1,
    '抱歉不如抱我',
    -1,
    -1,
    1,
    '2022-01-29 08:01:24',
    1,
    '2022-01-29 08:01:24',
    0
  ),(
    3,
    1,
    -1,
    '躲得过初一躲不过你',
    -1,
    -1,
    1,
    '2022-01-29 16:07:24',
    1,
    '2022-01-29 16:07:24',
    0
  ),(
    4,
    1,
    -1,
    '打算搬家，搬到你心里',
    -1,
    -1,
    1,
    '2022-01-29 16:12:09',
    1,
    '2022-01-29 16:12:09',
    0
  ),(
    5,
    1,
    -1,
    '黑虎掠过秃鹰',
    -1,
    -1,
    1,
    '2022-01-29 18:19:56',
    1,
    '2022-01-29 18:19:56',
    0
  ),(
    6,
    1,
    -1,
    '龙卷风摧毁停车场',
    -1,
    -1,
    1,
    '2022-01-29 22:13:52',
    1,
    '2022-01-29 22:13:52',
    0
  ),(
    7,
    1,
    2,
    '马尾甩苍蝇',
    1,
    2,
    1,
    '2022-01-29 22:18:40',
    1,
    '2022-01-29 22:18:40',
    0
  ),(
    8,
    1,
    2,
    '哈哈哈哈',
    1,
    9,
    1,
    '2022-01-29 22:29:15',
    1,
    '2022-01-29 22:29:15',
    0
  ),(
    9,
    1,
    2,
    '不愧是你',
    1,
    10,
    3,
    '2022-01-29 22:29:55',
    1,
    '2022-01-29 22:29:55',
    0
  );