CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `role_access`;
CREATE TABLE `role_access` (
  `role_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `access_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '权限id',
  PRIMARY KEY (`role_id`, `access_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8mb4;
INSERT INTO
  `role_access`
VALUES
  (1, 1),(1, 2),(1, 3),(1, 4),(2, 4)