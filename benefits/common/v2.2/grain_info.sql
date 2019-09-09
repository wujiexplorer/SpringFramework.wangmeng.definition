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

 Date: 04/06/2019 10:16:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grain_info
-- ----------------------------
DROP TABLE IF EXISTS `grain_info`;
CREATE TABLE `grain_info`  (
  `grain_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '颗粒号',
  `enterpr_id` bigint(20) NULL DEFAULT NULL COMMENT '企业ID',
  `enterpr_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态（1:审核中；2:通过；3:冻结;4:不通过）',
  `receive_value` double(11, 2) NULL DEFAULT NULL COMMENT '转入积分总额',
  `cumulation_read_quantity` int(11) NULL DEFAULT NULL COMMENT '累计阅读量',
  `left_grain_value` double(11, 2) NULL DEFAULT NULL COMMENT '剩余颗粒值',
  `typeList` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限设置返回',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `established_time` datetime(0) NULL DEFAULT NULL COMMENT '开通时间',
  `service_status` tinyint(4) NULL DEFAULT NULL COMMENT '服务状态（1：正常；2：暂停；3：待定）',
  `created` int(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` int(11) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`grain_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
