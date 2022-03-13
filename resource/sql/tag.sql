CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '标签名',
  `create_by` bigint(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(200) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 DEFAULT CHARSET = utf8mb4 COMMENT = '标签表';
INSERT INTO
  `tag`
VALUES
  (1, 'Spring Boot', NULL, NULL, NULL, NULL, '0'),
  (2, 'MyBatis', NULL, NULL, NULL, NULL, '0'),
  (3, 'MyBatis-Plus', NULL, NULL, NULL, NULL, '0'),
  (4, 'Redis', NULL, NULL, NULL, NULL, '0'),
  (5, 'PyQt5', NULL, NULL, NULL, NULL, '0'),
  (6, 'MySQL', NULL, NULL, NULL, NULL, '0');