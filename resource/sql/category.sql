CREATE DATABASE /*!32312 IF NOT EXISTS*/`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '分类名',
  `pid` bigint(200) DEFAULT '-1' COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(200) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 DEFAULT CHARSET = utf8mb4 COMMENT = '分类表';
insert into
  `category`(
    `id`,
    `name`,
    `pid`,
    `description`,
    `status`,
    `create_by`,
    `create_time`,
    `update_by`,
    `update_time`,
    `del_flag`
  )
values
  (
    1,
    'java',
    -1,
    'Java 天下第一',
    '0',
    NULL,
    NULL,
    NULL,
    NULL,
    0
  ),(
    2,
    'PHP',
    -1,
    'PHP 是世界上最好的语言',
    '0',
    NULL,
    NULL,
    NULL,
    NULL,
    0
  );