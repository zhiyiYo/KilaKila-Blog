CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `status` char(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int(1) DEFAULT '0' COMMENT 'del_flag',
  `create_by` bigint(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(200) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET = utf8mb4 COMMENT = '角色表';
INSERT INTO
  `role`
VALUES
  (
    1,
    '管理员',
    'admin',
    '0',
    0,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL
  ),
  (
    2,
    '普通用户',
    'normal_user',
    '0',
    0,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL
  );