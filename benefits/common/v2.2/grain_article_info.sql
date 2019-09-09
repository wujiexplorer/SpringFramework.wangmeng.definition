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

 Date: 04/06/2019 10:15:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grain_article_info
-- ----------------------------
DROP TABLE IF EXISTS `grain_article_info`;
CREATE TABLE `grain_article_info`  (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `article_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `article_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新文章时间',
  `publish_person` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人',
  `publish_person_id` bigint(20) NULL DEFAULT NULL COMMENT '发布人ID',
  `single_grain_award_value` double(11, 2) NULL DEFAULT NULL COMMENT '单次奖励颗粒值',
  `is_custom` tinyint(4) NULL DEFAULT NULL COMMENT '是否自主创作',
  `is_hot` tinyint(4) NULL DEFAULT NULL COMMENT '文章是否热门',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '文章状态（1：暂停奖励；2：开启奖励；3：未查看；4：已查看；5：已屏蔽；6：待定',
  `verify_status` int(11) NULL DEFAULT NULL COMMENT '文章审核状态（1：未查看；2：已查看；3：已屏蔽)',
  `article_read_time` tinyint(4) NULL DEFAULT NULL COMMENT '文章阅读时间',
  `read_quantity` int(11) NULL DEFAULT NULL COMMENT '文章阅读量',
  `cumulation_award_value` double(11, 2) NULL DEFAULT NULL COMMENT '累计颗粒奖励',
  `created` int(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` int(11) NULL DEFAULT NULL COMMENT '更新时间',
  `grain_id` bigint(20) NULL DEFAULT NULL COMMENT '颗粒号ID',
  `image_path` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
