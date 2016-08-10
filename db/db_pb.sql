/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : 127.0.0.1:3306
Source Database       : db_pb

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2016-08-03 16:59:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `remark` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2345 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_test
-- ----------------------------
INSERT INTO `tb_test` VALUES ('2325', '张三', '胖比');
INSERT INTO `tb_test` VALUES ('2339', '张三子', '就是干Thu Jul 28 14:34:35 CST 2016');
INSERT INTO `tb_test` VALUES ('2340', '张三子', '就是干Thu Jul 28 14:34:43 CST 2016');
INSERT INTO `tb_test` VALUES ('2344', '张三子', '就是干Thu Jul 28 15:07:33 CST 2016');
