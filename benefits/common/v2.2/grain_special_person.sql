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

 Date: 04/06/2019 10:16:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grain_special_person
-- ----------------------------
DROP TABLE IF EXISTS `grain_special_person`;
CREATE TABLE `grain_special_person`  (
  `special_person_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '某个公司的特殊员工ID',
  `employee_id` bigint(20) NULL DEFAULT NULL COMMENT '员工ID',
  `employee_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工工号',
  `employee_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工名称',
  `single_award_grain_value` double(11, 2) NULL DEFAULT NULL COMMENT '单次奖励颗粒值',
  `enterpr_id` bigint(20) NULL DEFAULT NULL COMMENT '企业ID',
  `created` int(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` int(11) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`special_person_id`) USING BTREE,
  UNIQUE INDEX `idx_employeeNo_employeeName`(`employee_no`, `employee_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
