CREATE DATABASE
/*!32312 IF NOT EXISTS*/
`kila_kila_blog`;
USE `kila_kila_blog`;
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
INSERT INTO
  `user_role`
VALUES
  (1, 1),(2, 2),(3, 2),(4, 2),(5, 2);