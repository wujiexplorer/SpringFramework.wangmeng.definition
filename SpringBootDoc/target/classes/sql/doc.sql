/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.37-community : Database - javadoc_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`javadoc_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `javadoc_db`;

/*Table structure for table `md_attachment` */

CREATE TABLE `md_attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) NOT NULL DEFAULT '',
  `file_path` varchar(2000) NOT NULL DEFAULT '',
  `file_size` double NOT NULL DEFAULT '0',
  `http_path` varchar(2000) NOT NULL DEFAULT '',
  `file_ext` varchar(50) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  `create_at` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `md_attachment` */

insert  into `md_attachment`(`attachment_id`,`book_id`,`document_id`,`file_name`,`file_path`,`file_size`,`http_path`,`file_ext`,`create_time`,`create_at`) values (1,1,1,'1','1',1,'1','1','2017-09-06 19:36:35',1);

/*Table structure for table `md_books` */

CREATE TABLE `md_books` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(500) NOT NULL DEFAULT '',
  `identify` varchar(100) NOT NULL DEFAULT '',
  `order_index` int(11) NOT NULL DEFAULT '0',
  `description` varchar(2000) NOT NULL DEFAULT '',
  `label` varchar(500) NOT NULL DEFAULT '',
  `privately_owned` int(11) NOT NULL DEFAULT '0',
  `private_token` varchar(500) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `editor` varchar(50) NOT NULL DEFAULT '',
  `doc_count` int(11) NOT NULL DEFAULT '0',
  `comment_status` varchar(20) NOT NULL DEFAULT 'open',
  `comment_count` int(11) NOT NULL DEFAULT '0',
  `cover` varchar(1000) NOT NULL DEFAULT '',
  `theme` varchar(255) NOT NULL DEFAULT 'default',
  `create_time` datetime NOT NULL,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `modify_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `identify` (`identify`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `md_books` */

insert  into `md_books`(`book_id`,`book_name`,`identify`,`order_index`,`description`,`label`,`privately_owned`,`private_token`,`status`,`editor`,`doc_count`,`comment_status`,`comment_count`,`cover`,`theme`,`create_time`,`member_id`,`modify_time`,`version`) values (2,'支付宝','alipay',0,'支付宝','',0,'',0,'markdown',1,'closed',0,'/uploads/201709/cover_14e1c2b307e5e567_small.jpg','default','2017-09-06 19:36:35',1,'2017-09-06 19:37:07',1504697794),(3,'微信支付','weixinpay',0,'微信','',0,'',0,'markdown',1,'closed',0,'/uploads/201709/cover_14e1c2c2fd5582d8_small.jpg','default','2017-09-06 19:37:24',1,'2017-09-06 19:38:15',1504697843),(4,'银联支付','unionpay',0,'银联支付','',0,'',0,'markdown',1,'closed',0,'/uploads/201709/cover_14e1c2c9d469a449_small.jpg','default','2017-09-06 19:37:53',1,'2017-09-06 19:38:44',1504697872);

/*Table structure for table `md_document_history` */

CREATE TABLE `md_document_history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) NOT NULL DEFAULT '',
  `action_name` varchar(255) NOT NULL DEFAULT '',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `document_name` varchar(500) NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `markdown` longtext,
  `content` longtext,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `modify_time` datetime NOT NULL,
  `modify_at` int(11) NOT NULL DEFAULT '0',
  `version` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`history_id`),
  KEY `md_document_history_document_id` (`document_id`),
  KEY `md_document_history_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `md_document_history` */

/*Table structure for table `md_documents` */

CREATE TABLE `md_documents` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_name` varchar(500) NOT NULL DEFAULT '',
  `identify` varchar(100) DEFAULT 'null',
  `book_id` int(11) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `order_sort` int(11) NOT NULL DEFAULT '0',
  `markdown` longtext,
  `release` longtext,
  `content` longtext,
  `create_time` datetime NOT NULL,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `modify_time` datetime NOT NULL,
  `modify_at` int(11) NOT NULL DEFAULT '0',
  `version` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`document_id`),
  KEY `md_documents_identify` (`identify`),
  KEY `md_documents_book_id` (`book_id`),
  KEY `md_documents_parent_id` (`parent_id`),
  KEY `md_documents_order_sort` (`order_sort`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `md_documents` */

insert  into `md_documents`(`document_id`,`document_name`,`identify`,`book_id`,`parent_id`,`order_sort`,`markdown`,`release`,`content`,`create_time`,`member_id`,`modify_time`,`modify_at`,`version`) values (2,'空白文档','',2,0,0,'','','','2017-09-06 19:36:35',1,'2017-09-06 19:36:35',0,1504697794),(3,'空白文档','',3,0,0,'','','','2017-09-06 19:37:24',1,'2017-09-06 19:37:24',0,1504697843),(4,'空白文档','',4,0,0,'','','','2017-09-06 19:37:53',1,'2017-09-06 19:37:53',0,1504697872);

/*Table structure for table `md_label` */

CREATE TABLE `md_label` (
  `label_id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(50) NOT NULL DEFAULT '',
  `book_number` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`label_id`),
  UNIQUE KEY `label_name` (`label_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `md_label` */

/*Table structure for table `md_logs` */

CREATE TABLE `md_logs` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `category` varchar(255) NOT NULL DEFAULT 'operate',
  `content` longtext NOT NULL,
  `original_data` longtext NOT NULL,
  `present_data` longtext NOT NULL,
  `create_time` datetime NOT NULL,
  `user_agent` varchar(500) NOT NULL DEFAULT '',
  `ip_address` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `md_logs` */

/*Table structure for table `md_member_token` */

CREATE TABLE `md_member_token` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `token` varchar(150) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `is_valid` tinyint(1) NOT NULL DEFAULT '0',
  `valid_time` datetime DEFAULT NULL,
  `send_time` datetime NOT NULL,
  PRIMARY KEY (`token_id`),
  KEY `md_member_token_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `md_member_token` */

/*Table structure for table `md_members` */

CREATE TABLE `md_members` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL DEFAULT '',
  `auth_method` varchar(50) NOT NULL DEFAULT 'local',
  `description` varchar(2000) NOT NULL DEFAULT '',
  `email` varchar(30) NOT NULL DEFAULT '',
  `phone` varchar(20) DEFAULT 'null',
  `avatar` varchar(200) NOT NULL DEFAULT '',
  `role` int(11) NOT NULL DEFAULT '1',
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `create_at` int(11) NOT NULL DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `account` (`account`),
  UNIQUE KEY `email` (`email`),
  KEY `md_members_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `md_members` */

insert  into `md_members`(`member_id`,`account`,`password`,`auth_method`,`description`,`email`,`phone`,`avatar`,`role`,`status`,`create_time`,`create_at`,`last_login_time`) values (1,'admin','96e79218965eb72c92a549dd5a330112','local','','345849402@qq.com','','/images/headimgurl.jpg',0,0,'2017-09-06 19:35:25',0,NULL);

/*Table structure for table `md_migrations` */

CREATE TABLE `md_migrations` (
  `migration_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL DEFAULT '',
  `statements` longtext,
  `status` varchar(255) NOT NULL DEFAULT 'update',
  `create_time` datetime NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`migration_id`),
  UNIQUE KEY `version` (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `md_migrations` */

/*Table structure for table `md_options` */

CREATE TABLE `md_options` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `option_title` varchar(500) NOT NULL DEFAULT '',
  `option_name` varchar(80) NOT NULL DEFAULT '',
  `option_value` longtext,
  `remark` longtext,
  PRIMARY KEY (`option_id`),
  UNIQUE KEY `option_name` (`option_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `md_options` */

insert  into `md_options`(`option_id`,`option_title`,`option_name`,`option_value`,`remark`) values (1,'是否启用注册','ENABLED_REGISTER','true',''),(2,'是否启用文档历史','ENABLE_DOCUMENT_HISTORY','true',''),(3,'是否启用验证码','ENABLED_CAPTCHA','true',''),(4,'启用匿名访问','ENABLE_ANONYMOUS','true',''),(5,'站点名称','SITE_NAME','小柒2012','');

/*Table structure for table `md_relationship` */

CREATE TABLE `md_relationship` (
  `relationship_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `book_id` int(11) NOT NULL DEFAULT '0',
  `role_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`relationship_id`),
  UNIQUE KEY `member_id` (`member_id`,`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `md_relationship` */

insert  into `md_relationship`(`relationship_id`,`member_id`,`book_id`,`role_id`) values (2,1,2,0),(3,1,3,0),(4,1,4,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
