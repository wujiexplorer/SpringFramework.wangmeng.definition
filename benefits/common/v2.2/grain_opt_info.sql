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

 Date: 04/06/2019 10:16:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grain_opt_info
-- ----------------------------
DROP TABLE IF EXISTS `grain_opt_info`;
CREATE TABLE `grain_opt_info`  (
  `opt_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '颗粒号操作ID',
  `parent_opt_id` bigint(20) NULL DEFAULT NULL COMMENT '颗粒号父操作ID（开始时为0）',
  `grain_id` bigint(20) NULL DEFAULT NULL COMMENT '颗粒号',
  `enterpr_id` bigint(20) NULL DEFAULT NULL COMMENT '企业ID',
  `enterpr_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '转入时间',
  `receive_credit_value` double(11, 2) NULL DEFAULT NULL COMMENT '转入积分（普通积分）',
  `created` int(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` int(11) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`opt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
