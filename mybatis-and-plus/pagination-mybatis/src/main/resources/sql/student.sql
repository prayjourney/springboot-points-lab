/*
 Navicat Premium Data Transfer

 Source Server         : mysql-local
 Source Server Type    : MySQL
 Source Server Version : 100315
 Source Host           : localhost:3306
 Source Schema         : myschool

 Target Server Type    : MySQL
 Target Server Version : 100315
 File Encoding         : 65001

 Date: 27/06/2022 23:37:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stId` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `stAge` int NOT NULL COMMENT '年龄',
  `stPhoneNo` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '000000' COMMENT '手机',
  `stGender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `stClass` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级',
  `stHome` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `stParentsId` int NULL DEFAULT NULL COMMENT '父母id',
  PRIMARY KEY (`stId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (100, 'admin', 22, '000000', '男', '二年1班', '中央大街', 0);
INSERT INTO `student` VALUES (101, '太乙真人', 1098, '13312346767', '男', '高三2班', '乾元山金光洞', 0);
INSERT INTO `student` VALUES (102, '度厄真人', 996, '13367679000', '男', '高二5班', '九顶铁刹山八宝云光洞', 0);
INSERT INTO `student` VALUES (103, '慈航真人', 1022, '18798326183', '女', '高三8班', '普陀山紫竹林', 0);
INSERT INTO `student` VALUES (104, '姜子牙', 63, '000000', '男', '初三1班', '镐京中央大街324号', 0);
INSERT INTO `student` VALUES (105, '多宝道人', 2800, '13186986711', '男', '高三5班', '东海蓬莱岛紫芝崖碧游宫', 0);
INSERT INTO `student` VALUES (106, '赵公明', 720, '17129847835', '男', '高三2班', '峨嵋山罗浮洞', 0);
INSERT INTO `student` VALUES (107, '金灵圣母', 1022, '15189673478', '女', '高三1班', '普陀山紫竹林', 0);
INSERT INTO `student` VALUES (108, '月游星君', 700, '000000', '女', '初三2班', NULL, 0);
INSERT INTO `student` VALUES (109, '文殊广法天尊', 1125, '000000', '男', '高三1班', '五龙山云霄洞', 0);
INSERT INTO `student` VALUES (110, '邓婵玉', 26, '18922226731', '女', '三年6班', NULL, 0);
INSERT INTO `student` VALUES (111, '云霄', 928, '000000', '女', '高二2班', '三仙岛', 0);
INSERT INTO `student` VALUES (112, '琼霄', 927, '000000', '女', '高二2班', '三仙岛', 0);
INSERT INTO `student` VALUES (113, '碧霄', 919, '000000', '女', '高二2班', '三仙岛', 0);
INSERT INTO `student` VALUES (114, '杨戬', 27, '13187779888', '男', '初二5班', '都江堰灌江口二郎庙', 0);
INSERT INTO `student` VALUES (115, '孙悟空', 599, '13187986728', '男', '1班', '东海花果山', 0);
INSERT INTO `student` VALUES (116, '哪吒', 15, '000000', '男', '三年5班', '陈塘关', 104);
INSERT INTO `student` VALUES (117, '金吒', 17, '000000', '男', '五年2班', '陈塘关', 104);
INSERT INTO `student` VALUES (118, '木吒', 16, '000000', '男', '四年1班', '陈塘关', 104);
INSERT INTO `student` VALUES (119, '黄天霸', 19, '000000', '男', '初一2班', '朝歌', 105);

SET FOREIGN_KEY_CHECKS = 1;
