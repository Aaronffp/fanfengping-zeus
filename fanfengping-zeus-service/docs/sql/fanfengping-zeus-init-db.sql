-- ----------------------------
-- Create Database fanfengping_zeus（宙斯）
-- ----------------------------
CREATE SCHEMA IF NOT EXISTS fanfengping_zeus DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
CREATE USER 'zeus'@'localhost' IDENTIFIED BY 'Zeus123456';
GRANT ALL PRIVILEGES ON `fanfengping_zeus`.* TO 'zeus'@'localhost' IDENTIFIED BY 'Zeus123456';
FLUSH PRIVILEGES ;

-- ----------------------------
-- Table structure for zeus_code_type(系统编码类型信息)
-- ----------------------------
DROP TABLE IF EXISTS `zeus_code_type`;
CREATE TABLE `fanfengping_zeus`.`zeus_code_type` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '系统主键',
    `valid` INT NOT NULL DEFAULT 0 COMMENT '是否有效（1，有效；0，无效。默认无效）',
    `code` INT NOT NULL DEFAULT '000' COMMENT '类型编码',
    `name` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '编码类型名称',
    `operator` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT 'system' COMMENT '更新人',
    `note` VARCHAR(400) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '备注',
    `ctime` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `utime` DATETIME NOT NULL DEFAULT NOW() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `code_UNIQUE` (`code` ASC)
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统编码类型信息';

-- ----------------------------
-- Table structure for zeus_code_detail(系统编码详情信息)
-- ----------------------------
DROP TABLE IF EXISTS `zeus_code_detail`;
CREATE TABLE `fanfengping_zeus`.`zeus_code_detail` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '系统主键',
  `type_id` INT NOT NULL DEFAULT 999 COMMENT '类型ID',
  `valid` INT NOT NULL DEFAULT 0 COMMENT '是否有效（1，有效；0，无效。默认无效）',
  `code` INT NOT NULL DEFAULT 999 COMMENT '详情编码',
  `name` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '编码详情名称',
  `operator` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT 'system' COMMENT '更新人',
  `note` VARCHAR(400) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '备注',
  `ctime` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `utime` DATETIME NOT NULL DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_code_unique` (`type_id` ASC, `code` ASC)
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统编码详情信息';
