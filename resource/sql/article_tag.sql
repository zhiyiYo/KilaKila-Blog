CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `article_id` bigint(200) NOT NULL COMMENT '文章 ID',
  `tag_id` bigint(200) NOT NULL COMMENT '标签 ID',
  PRIMARY KEY (`article_id`, `tag_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 DEFAULT CHARSET = utf8mb4 COMMENT = '文章-标签表';
INSERT INTO
  `article_tag`
VALUES
  (1, 1),(2, 2),(2, 3),(2, 4),(3, 5);