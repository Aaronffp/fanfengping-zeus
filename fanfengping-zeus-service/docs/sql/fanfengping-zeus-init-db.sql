-- ----------------------------
-- Create Database fanfengping_zeus（宙斯）
-- ----------------------------
CREATE SCHEMA IF NOT EXISTS fanfengping_zeus DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
CREATE USER 'zeus'@'localhost' IDENTIFIED BY 'Zeus123456';
GRANT ALL PRIVILEGES ON `fanfengping_zeus`.* TO 'zeus'@'localhost' IDENTIFIED BY 'Zeus123456';
FLUSH PRIVILEGES ;

