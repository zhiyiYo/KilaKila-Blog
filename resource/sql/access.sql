CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `access`;
CREATE TABLE `access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_name` varchar(64) NOT NULL COMMENT '权限名',
  `permission` varchar(100) NOT NULL COMMENT '权限标识',
  `status` char(1) DEFAULT '0' COMMENT '权限状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8mb4 COMMENT = '权限表';
INSERT INTO
  `access`
VALUES
  (
    1,
    '发表博客',
    'article:add',
    '0',
    NULL,
    NULL,
    NULL,
    NULL,
    '0',
    'NULL'
  ),(
    2,
    '删除博客',
    'article:delete',
    '0',
    NULL,
    NULL,
    NULL,
    NULL,
    '0',
    'NULL'
  ),(
    3,
    '编辑博客',
    'article:edit',
    '0',
    NULL,
    NULL,
    NULL,
    NULL,
    '0',
    'NULL'
  ),(
    4,
    '查看博客',
    'article:view',
    '0',
    NULL,
    NULL,
    NULL,
    NULL,
    '0',
    'NULL'
  );