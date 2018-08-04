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


INSERT INTO zeus_code_type(valid, code, name, operator, note) VALUES
  (1, 100, '消息', '范丰平', 'HTTP状态码。这一类型的状态码，代表请求已被接受，需要继续处理。这类响应是临时响应，只包含状态行和某些可选的响应头信息，并以空行结束。由于 HTTP/1.0 协议中没有定义任何 1xx 状态码，所以除非在某些试验条件下，服务器禁止向此类客户端发送 1xx 响应。'),
  (1, 200, '成功', '范丰平', 'HTTP状态码。这一类型的状态码，代表请求已成功被服务器接收、理解、并接受。'),
  (1, 300, '重定向', '范丰平', 'HTTP状态码。这类状态码代表需要客户端采取进一步的操作才能完成请求。通常，这些状态码用来重定向，后续的请求地址（重定向目标）在本次响应的 Location 域中指明。当且仅当后续的请求所使用的方法是 GET 或者 HEAD 时，用户浏览器才可以在没有用户介入的情况下自动提交所需要的后续请求。客户端应当自动监测无限循环重定向（例如：A->A，或者A->B->C->A），因为这会导致服务器和客户端大量不必要的资源消耗。按照 HTTP/1.0 版规范的建议，浏览器不应自动访问超过5次的重定向。'),
  (1, 400, '请求错误', '范丰平', 'HTTP状态码。这类的状态码代表了客户端看起来可能发生了错误，妨碍了服务器的处理。除非响应的是一个 HEAD 请求，否则服务器就应该返回一个解释当前错误状况的实体，以及这是临时的还是永久性的状况。这些状态码适用于任何请求方法。浏览器应当向用户显示任何包含在此类错误响应中的实体内容。如果错误发生时客户端正在传送数据，那么使用TCP的服务器实现应当仔细确保在关闭客户端与服务器之间的连接之前，客户端已经收到了包含错误信息的数据包。如果客户端在收到错误信息后继续向服务器发送数据，服务器的TCP栈将向客户端发送一个重置数据包，以清除该客户端所有还未识别的输入缓冲，以免这些数据被服务器上的应用程序读取并干扰后者。'),
  (1, 500, '服务器错误', '范丰平', 'HTTP状态码。这类状态码代表了服务器在处理请求的过程中有错误或者异常状态发生，也有可能是服务器意识到以当前的软硬件资源无法完成对请求的处理。除非这是一个HEAD 请求，否则服务器应当包含一个解释当前错误状态以及这个状况是临时的还是永久的解释信息实体。浏览器应当向用户展示任何在当前响应中被包含的实体。'),
  (1, 600, '是否有效/显示/删除/性别', '范丰平', '是否有效/显示/删除/性别'),
  (1, 610, '性别', '范丰平', '性别'),
  (1, 611, '学历', '范丰平', '学历'),
  (1, 612, '证件类型', '范丰平', '证件类型'),
  (1, 613, '学历', '范丰平', '婚姻状态'),
  (1, 630, '缺陷状态', '范丰平', '缺陷状态'),
  (1, 631, '优先级', '范丰平', '优先级'),
  (1, 632, '严重等级', '范丰平', '严重等级'),
  (1, 633, '产生原因', '范丰平', '产生原因'),
  (1, 650, '审批状态', '范丰平', '审批状态'),
  (1, 900, '层级', '范丰平', '菜单层级/目录层级。数字越小，层级越高'),
  (1, 999, '未知', '范丰平', '未知');









