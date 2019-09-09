/*
 Navicat Premium Data Transfer

 Source Server         : testFuli
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : rm-uf6i0q77mo39o5vs2ro.mysql.rds.aliyuncs.com:3306
 Source Schema         : fuliserver

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 04/06/2019 10:15:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grain_article_award
-- ----------------------------
DROP TABLE IF EXISTS `grain_article_award`;
CREATE TABLE `grain_article_award`  (
  `award_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '奖励ID',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '员工ID',
  `employee_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工名称',
  `award_grain_value` double(11, 2) NULL DEFAULT NULL COMMENT '员工奖励颗粒值',
  `award_grain_time` datetime(0) NULL DEFAULT NULL COMMENT '奖励获取时间',
  `article_id` bigint(20) NULL DEFAULT NULL COMMENT '文章ID',
  `created` int(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` int(11) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`award_id`) USING BTREE,
  UNIQUE INDEX `idx_employeeId_articleId`(`employee_id`, `article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
