/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : h2_blog

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-07 16:42:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `articleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `articleUserName` varchar(255) DEFAULT '1',
  `articleTitle` varchar(255) DEFAULT NULL,
  `articleContent` mediumtext,
  `articleParentCategoryId` int(10) DEFAULT NULL,
  `articleChildCategoryId` int(10) DEFAULT NULL,
  `articleTagIds` varchar(50) DEFAULT NULL,
  `articleViewCount` int(10) DEFAULT '0',
  `articleCommentCount` int(5) DEFAULT '0',
  `articleLikeCount` int(5) DEFAULT '0',
  `articlePostTime` datetime DEFAULT NULL,
  `articleUpdateTime` datetime DEFAULT NULL,
  `articleStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'yuxia', '124', '内容摘要1', '1', '2', '1', '0', '1', '2', '2018-06-06 10:53:47', '2018-07-18 10:53:52', '1');
INSERT INTO `article` VALUES ('2', 'yuxia', '123', '内容摘要2ssssssssssssssssssssssssssssss', '1', '3', '1', '0', '2', '0', '2018-07-03 10:53:58', '2018-07-18 10:54:00', '1');
INSERT INTO `article` VALUES ('3', 'yuxia', '444', '内容摘要3aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '1', '3', '1', '0', '3', '0', '2018-07-05 10:54:05', '2018-07-05 10:54:09', '1');
INSERT INTO `article` VALUES ('4', 'yuxia', '555', '内容摘要4啦啦啦啦啦啦啦阿拉', '1', '3', '1', '0', '4', '0', '2018-07-05 10:54:13', '2018-07-05 10:54:11', '1');
INSERT INTO `article` VALUES ('5', 'yuxia', '123', '内容摘要5', '1', '3', '1', '0', '5', '0', '2018-07-05 10:54:15', '2018-07-05 10:54:16', '1');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categoryId` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `categoryUserName` varchar(255) DEFAULT NULL,
  `categoryPid` int(5) DEFAULT NULL,
  `categoryName` varchar(50) DEFAULT NULL,
  `categoryDescription` varchar(255) DEFAULT NULL,
  `categoryStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'yuxia', '0', '技术', '123123', '1');
INSERT INTO `category` VALUES ('2', 'yuxia', '1', 'java', '123213', '1');
INSERT INTO `category` VALUES ('3', 'yuxia', '1', 'c++', '123213', '1');
INSERT INTO `category` VALUES ('180', 'yuxia', '0', '小说', '213123', '1');
INSERT INTO `category` VALUES ('181', 'yuxia', '180', '玄幻', '123123', '1');
INSERT INTO `category` VALUES ('182', 'yuxia', '180', '神魔', '123123', '1');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `commentPid` int(10) unsigned DEFAULT '0',
  `commentPname` varchar(255) DEFAULT NULL,
  `commentArticleId` int(10) unsigned DEFAULT NULL,
  `commentAuthorName` varchar(50) DEFAULT NULL,
  `commentAuthorEmail` varchar(50) DEFAULT NULL,
  `commentContent` varchar(1000) DEFAULT NULL,
  `commenIp` varchar(50) DEFAULT NULL,
  `commentCreateTime` datetime DEFAULT NULL,
  `commentStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '111', '1', 'lalala', '123@qq.com', '还不错的文章', '192.168.0.111', '2018-06-08 14:18:55', '1');
INSERT INTO `comment` VALUES ('2', '1', '111', '1', 'lalala', '123@qq.com', '还不错的文章', '192.168.0.111', '2018-06-08 14:18:55', '1');
INSERT INTO `comment` VALUES ('4', '1', '111', '1', 'lalala', '123@qq.com', '还不错的文章', '192.168.0.111', '2018-06-08 14:18:55', '1');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagId` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `tagUserName` varchar(255) DEFAULT NULL,
  `tagName` varchar(20) DEFAULT NULL,
  `tagDescription` varchar(255) DEFAULT NULL,
  `tagStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'yuxia', '科技', '很不错的东西', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL DEFAULT '',
  `userPassword` varchar(255) NOT NULL DEFAULT '',
  `userNickName` varchar(255) NOT NULL DEFAULT '',
  `userEmail` varchar(100) DEFAULT '',
  `userAvatar` varchar(255) DEFAULT NULL,
  `userRegisterTime` datetime DEFAULT NULL,
  `userLastLoginTime` datetime DEFAULT NULL,
  `userStatus` int(2) unsigned DEFAULT '1',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('42', '123qwe', '123123', 'qwe', '12@qq.com', 'statics\\images\\1530754799004avatar.jpg', '2018-07-05 09:26:22', '2018-07-05 09:43:03', '1');
INSERT INTO `user` VALUES ('43', 'yuxia', '123123', '雨夏', '123@qq.com', 'statics\\images\\1530755128945avatar.jpg', '2018-07-05 09:37:53', '2018-07-07 16:41:19', '1');
INSERT INTO `user` VALUES ('44', 'lixuan', 'li8618121', 'lixuan', '410199246@qq.com', 'statics\\images\\1530793678329avatar.jpg', '2018-07-05 20:24:38', '2018-07-05 20:37:13', '1');
