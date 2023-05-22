/*
 Navicat MySQL Data Transfer

 Source Server         : docker-mariadb
 Source Server Type    : MySQL
 Source Server Version : 100906
 Source Host           : localhost:3306
 Source Schema         : security

 Target Server Type    : MySQL
 Target Server Version : 100906
 File Encoding         : 65001

 Date: 22/05/2023 17:42:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jwt_user
-- ----------------------------
DROP TABLE IF EXISTS `jwt_user`;
CREATE TABLE `jwt_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色',
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'ip地址',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '域名',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '账户可用, 0-禁用, 1-可用',
  `first_login` tinyint(1) NULL DEFAULT 1 COMMENT '首次登录, 0-首次, 1-非首次',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_username`(`username` ASC) USING BTREE COMMENT '用户名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jwt_user
-- ----------------------------
INSERT INTO `jwt_user` VALUES (1, 'admin', '$2a$10$W9HjB4ctxxgF0whxz7Xy7O.w/oe5lFgCDJ2dwk89uH.1Y5r2NrisW', NULL, 'default', NULL, NULL, 1, 0, '2023-05-22 17:41:17', '2023-05-22 17:41:17');
INSERT INTO `jwt_user` VALUES (2, 'zhangsan', '$2a$10$/YjI7NpXIM9MdynF6VuqBuTdXKtEKet.2GqeC97ELtwIAbnoJBYla', NULL, 'default', NULL, NULL, 1, 0, '2023-05-22 17:41:30', '2023-05-22 17:41:30');
INSERT INTO `jwt_user` VALUES (3, 'root', '$2a$10$E4UEZa2S2TABI.vPC5.kT.W1OxrXMv6W1c0Dug9xEHlyGDW1sGbHC', NULL, 'default', NULL, NULL, 1, 0, '2023-05-22 17:41:40', '2023-05-22 17:41:40');
INSERT INTO `jwt_user` VALUES (5, 'xx', '$2a$10$WfJmPCEXhVVVSrn7JE3sTuFeUNWop.nnAWS50H2eJGWhJ9Y/ggZ.q', NULL, '', NULL, NULL, 1, 0, '2023-05-22 17:42:11', '2023-05-22 17:42:11');

SET FOREIGN_KEY_CHECKS = 1;
