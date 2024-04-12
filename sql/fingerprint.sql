/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : fingerprint

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-12-16 17:26:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');
INSERT INTO `admin` VALUES ('2', 'root', 'root');

-- ----------------------------
-- Table structure for `clock`
-- ----------------------------
DROP TABLE IF EXISTS `clock`;
CREATE TABLE `clock` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clock
-- ----------------------------
INSERT INTO `clock` VALUES ('5', '6', '失败', '2019-12-14 10:10:39');
INSERT INTO `clock` VALUES ('6', '6', '失败', '2019-12-14 10:12:23');
INSERT INTO `clock` VALUES ('7', '6', '成功', '2019-12-14 10:12:42');
INSERT INTO `clock` VALUES ('8', '7', '成功', '2019-12-14 10:14:00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `age` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `img` varchar(500) DEFAULT NULL,
  `local` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'user1', '111', '男', '24', '张小三', 'http://127.0.0.1:83/user/937d5fd051fe4ca5848cc51a8a1eb1ac.bmp', 'd:/coin/file/fingerprint/user/937d5fd051fe4ca5848cc51a8a1eb1ac.bmp');
INSERT INTO `user` VALUES ('7', 'user2', '111', '女', '45', '李艳红', 'http://127.0.0.1:83/user/2d42d72cf68944629b4292c1a6d32603.bmp', 'd:/coin/file/fingerprint/user/2d42d72cf68944629b4292c1a6d32603.bmp');
INSERT INTO `user` VALUES ('8', 'user3', '111', '男', '34', '赵辉何', 'http://127.0.0.1:83/user/a91f55b0cf8346f4b2fcb3b494fc2448.bmp', 'd:/coin/file/fingerprint/user/a91f55b0cf8346f4b2fcb3b494fc2448.bmp');
