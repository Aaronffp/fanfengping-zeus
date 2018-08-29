-- ----------------------------
-- Create Database fanfengping_zeus（宙斯）
-- ----------------------------
CREATE SCHEMA IF NOT EXISTS fanfengping_zeus DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
CREATE USER 'fanfengping'@'localhost' IDENTIFIED BY 'fanfengping';
GRANT ALL PRIVILEGES ON `fanfengping_zeus`.* TO 'fanfengping'@'localhost' IDENTIFIED BY 'fanfengping';
FLUSH PRIVILEGES ;



-- ----------------------------
-- Table structure for code_type(系统编码类型信息)
-- ----------------------------
DROP TABLE IF EXISTS `code_type`;
CREATE TABLE `fanfengping_zeus`.`code_type` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '系统主键',
    `valid` INT NOT NULL DEFAULT 0 COMMENT '是否有效（1，有效；0，无效。默认无效）',
    `code` INT NOT NULL DEFAULT '000' COMMENT '类型编码',
    `name` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '编码类型名称',
    `operator` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT 'system' COMMENT '更新人',
    `note` VARCHAR(400) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '备注',
    `ctime` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `utime` DATETIME NOT NULL DEFAULT NOW() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `code_type_unique_code` (`code` ASC) COMMENT '系统编码类型信息表/类型编码 唯一键'
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统编码类型信息表';


-- ----------------------------
-- Table structure for code_detail(系统编码详情信息)
-- ----------------------------
DROP TABLE IF EXISTS `code_detail`;
CREATE TABLE `fanfengping_zeus`.`code_detail` (
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
  UNIQUE INDEX `code_detail_unique_type_code` (`type_id` ASC, `code` ASC) COMMENT '系统编码详情信息表/类型ID-详情编码 唯一键'
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统编码详情信息表';


INSERT INTO code_type(valid, code, name, operator, note) VALUES
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



INSERT INTO code_detail(type_id, valid, code, name, operator, note) VALUES
  (1, 1, 100, '客户端应当继续发送请求', '范丰平', '这个临时响应是用来通知客户端它的部分请求已经被服务器接收，且仍未被拒绝。客户端应当继续发送请求的剩余部分，或者如果请求已经完成，忽略这个响应。服务器必须在请求完成后向客户端发送一个最终响应。'),
  (2, 1, 200, '请求已成功', '范丰平', '请求已成功，请求所希望的响应头或数据体将随此响应返回。出现此状态码是表示正常状态。'),
  (2, 1, 201, '请求已经被实现', '范丰平', '请求已经被实现，而且有一个新的资源已经依据请求的需要而建立，且其 URI 已经随Location 头信息返回。假如需要的资源无法及时建立的话，应当返回 ''202 Accepted''。'),
  (2, 1, 202, '服务器已接受请求，但尚未处理', '范丰平', '服务器已接受请求，但尚未处理。正如它可能被拒绝一样，最终该请求可能会也可能不会被执行。在异步操作的场合下，没有比发送这个状态码更方便的做法了。'),
  (3, 1, 300, '重定向有多种选择', '范丰平', '被请求的资源有一系列可供选择的回馈信息，每个都有自己特定的地址和浏览器驱动的商议信息。用户或浏览器能够自行选择一个首选的地址进行重定向。'),
  (3, 1, 301, '请求被永久重置', '范丰平', '被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个 URI 之一。如果可能，拥有链接编辑功能的客户端应当自动把请求的地址修改为从服务器反馈回来的地址。除非额外指定，否则这个响应也是可缓存的。'),
  (3, 1, 302, '请求被暂时重置', '范丰平', '请求的资源临时从不同的 URI响应请求。由于这样的重定向是临时的，客户端应当继续向原有地址发送以后的请求。只有在Cache-Control或Expires中进行了指定的情况下，这个响应才是可缓存的。'),
  (3, 1, 305, '请求必须指定代理', '范丰平', '被请求的资源必须通过指定的代理才能被访问。Location 域中将给出指定的代理所在的 URI 信息，接收者需要重复发送一个单独的请求，通过这个代理才能访问相应资源。只有原始服务器才能建立305响应。'),
  (4, 1, 400, '请求语义错误', '范丰平', '1、语义有误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求。2、请求参数有误。'),
  (4, 1, 401, '请求未认证', '范丰平', '当前请求需要用户验证。该响应必须包含一个适用于被请求资源的 WWW-Authenticate 信息头用以询问用户信息。客户端可以重复提交一个包含恰当的 Authorization 头信息的请求。如果当前请求已经包含了 Authorization 证书，那么401响应代表着服务器验证已经拒绝了那些证书。如果401响应包含了与前一个响应相同的身份验证询问，且浏览器已经至少尝试了一次验证，那么浏览器应当向用户展示响应中包含的实体信息，因为这个实体信息中可能包含了相关诊断信息。参见RFC 2617。'),
  (4, 1, 403, '请求被拒绝', '范丰平', '服务器已经理解请求，但是拒绝执行它。与401响应不同的是，身份验证并不能提供任何帮助，而且这个请求也不应该被重复提交。如果这不是一个 HEAD 请求，而且服务器希望能够讲清楚为何请求不能被执行，那么就应该在实体内描述拒绝的原因。当然服务器也可以返回一个404响应，假如它不希望让客户端获得任何信息。'),
  (4, 1, 404, '请求失败', '范丰平', '请求失败，请求所希望得到的资源未被在服务器上发现。没有信息能够告诉用户这个状况到底是暂时的还是永久的。假如服务器知道情况的话，应当使用410状态码来告知旧资源因为某些内部的配置机制问题，已经永久的不可用，而且没有任何可以跳转的地址。404这个状态码被广泛应用于当服务器不想揭示到底为何请求被拒绝或者没有其他适合的响应可用的情况下。出现这个错误的最有可能的原因是服务器端没有这个页面。'),
  (4, 1, 405, '请求方法不支持', '范丰平', '请求行中指定的请求方法不能被用于请求相应的资源。该响应必须返回一个Allow 头信息用以表示出当前资源能够接受的请求方法的列表。鉴于 PUT，DELETE 方法会对服务器上的资源进行写操作，因而绝大部分的网页服务器都不支持或者在默认配置下不允许上述请求方法，对于此类请求均会返回405错误。'),
  (4, 1, 406, '无法接收', '范丰平', '请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体。除非这是一个 HEAD 请求，否则该响应就应当返回一个包含可以让用户或者浏览器从中选择最合适的实体特性以及地址列表的实体。实体的格式由 Content-Type 头中定义的媒体类型决定。浏览器可以根据格式及自身能力自行作出最佳选择。但是，规范中并没有定义任何作出此类自动选择的标准。'),
  (4, 1, 408, '请求超时', '范丰平', '请求超时。客户端没有在服务器预备等待的时间内完成一个请求的发送。客户端可以随时再次提交这一请求而无需进行任何更改。'),
  (4, 1, 413, '请求实体太大', '范丰平', '服务器拒绝处理当前请求，因为该请求提交的实体数据大小超过了服务器愿意或者能够处理的范围。此种情况下，服务器可以关闭连接以免客户端继续发送此请求。'),
  (4, 1, 414, '请求网址过长', '范丰平', '请求的URI 长度超过了服务器能够解释的长度，因此服务器拒绝对该请求提供服务。这比较少见，通常的情况包括：本应使用POST方法的表单提交变成了GET方法，导致查询字符串（Query String）过长；重定向URI “黑洞”，例如每次重定向把旧的 URI 作为新的 URI 的一部分，导致在若干次重定向后 URI 超长；客户端正在尝试利用某些服务器中存在的安全漏洞攻击服务器。这类服务器使用固定长度的缓冲读取或操作请求的 URI，当 GET 后的参数超过某个数值后，可能会产生缓冲区溢出，导致任意代码被执行[1]。没有此类漏洞的服务器，应当返回414状态码。'),
  (4, 1, 415, '不支持的媒体类型', '范丰平', '对于当前请求的方法和所请求的资源，请求中提交的实体并不是服务器中所支持的格式，因此请求被拒绝。'),
  (4, 1, 421, '请求连接数超出', '范丰平', '从当前客户端所在的IP地址到服务器的连接数超过了服务器许可的最大范围。通常，这里的IP地址指的是从服务器上看到的客户端地址（比如用户的网关或者代理服务器地址）。在这种情况下，连接数的计算可能涉及到不止一个终端用户。'),
  (5, 1, 500, '服务器未知错误', '范丰平', '服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。一般来说，这个问题都会在服务器端的源代码出现错误时出现。'),
  (5, 1, 501, '服务器无法识别请求', '范丰平', '服务器不支持当前请求所需要的某个功能。当服务器无法识别请求的方法，并且无法支持其对任何资源的请求。'),
  (5, 1, 502, '无效网关', '范丰平', '作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。'),
  (5, 1, 503, '服务器维护或过载', '范丰平', '由于临时的服务器维护或者过载，服务器当前无法处理请求。这个状况是临时的，并且将在一段时间以后恢复。如果能够预计延迟时间，那么响应中可以包含一个 Retry-After 头用以标明这个延迟时间。如果没有给出这个 Retry-After 信息，那么客户端应当以处理500响应的方式处理它。'),
  (5, 1, 504, '网关超时', '范丰平', '作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应。'),
  (5, 1, 505, '服务器不支持或拒绝请求HTTP版本', '范丰平', '服务器不支持，或者拒绝支持在请求中使用的 HTTP 版本。这暗示着服务器不能或不愿使用与客户端相同的版本。响应中应当包含一个描述了为何版本不被支持以及服务器支持哪些协议的实体。'),
  (6, 1, 1, '有效/显示/删除', '范丰平', '有效/显示/删除'),
  (6, 1, 0, '无效/不显示/不删除', '范丰平', '无效/不显示/不删除'),
  (7, 1, 1, '男', '范丰平', '性别'),
  (7, 1, 0, '女', '范丰平', '性别'),
  (8, 1, 100, '博士', '范丰平', '学历'),
  (8, 1, 101, '研究生', '范丰平', '学历'),
  (8, 1, 102, '本科', '范丰平', '学历'),
  (8, 1, 103, '大专', '范丰平', '学历'),
  (8, 1, 104, '高中', '范丰平', '学历'),
  (8, 1, 105, '初中', '范丰平', '学历'),
  (9, 1, 100, '身份证', '范丰平', '证件类型'),
  (9, 1, 101, '驾驶证', '范丰平', '证件类型'),
  (9, 1, 102, '军人证', '范丰平', '证件类型'),
  (9, 1, 103, '学生证', '范丰平', '证件类型'),
  (9, 1, 104, '户口本', '范丰平', '证件类型'),
  (10, 1, 100, '未婚', '范丰平', '婚姻状态'),
  (10, 1, 101, '已婚', '范丰平', '婚姻状态'),
  (10, 1, 102, '离异', '范丰平', '婚姻状态'),
  (10, 1, 103, '丧偶', '范丰平', '婚姻状态'),
  (11, 1, 100, '新建', '范丰平', '缺陷状态'),
  (11, 1, 101, '修复中', '范丰平', '缺陷状态'),
  (11, 1, 102, '提测', '范丰平', '缺陷状态'),
  (11, 1, 103, '测试中', '范丰平', '缺陷状态'),
  (11, 1, 104, '通过', '范丰平', '缺陷状态'),
  (11, 1, 105, '重新开启', '范丰平', '缺陷状态'),
  (12, 1, 100, '紧急', '范丰平', '优先级'),
  (12, 1, 101, '高', '范丰平', '优先级'),
  (12, 1, 102, '中', '范丰平', '优先级'),
  (12, 1, 103, '低', '范丰平', '优先级'),
  (13, 1, 101, '崩溃', '范丰平', '严重等级'),
  (13, 1, 102, '非常严重', '范丰平', '严重等级'),
  (13, 1, 103, '严重', '范丰平', '严重等级'),
  (13, 1, 104, '中等', '范丰平', '严重等级'),
  (13, 1, 105, '一般', '范丰平', '严重等级'),
  (13, 1, 106, '轻微', '范丰平', '严重等级'),
  (14, 1, 100, '需求原因', '范丰平', '产生原因'),
  (14, 1, 101, '需求变更', '范丰平', '产生原因'),
  (14, 1, 102, '系统设计', '范丰平', '产生原因'),
  (14, 1, 103, '功能错误', '范丰平', '产生原因'),
  (14, 1, 104, '逻辑错误', '范丰平', '产生原因'),
  (14, 1, 105, '显示错误', '范丰平', '产生原因'),
  (14, 1, 106, '数据错误', '范丰平', '产生原因'),
  (14, 1, 107, '网络异常', '范丰平', '产生原因'),
  (14, 1, 108, '接口错误', '范丰平', '产生原因'),
  (14, 1, 109, '性能问题', '范丰平', '产生原因'),
  (14, 1, 110, '其他问题', '范丰平', '产生原因'),
  (15, 1, 100, '通过', '范丰平', '审批状态'),
  (15, 1, 101, '驳回', '范丰平', '审批状态'),
  (15, 1, 102, '审批中', '范丰平', '审批状态'),
  (16, 1, 1, '一级', '范丰平', '层级'),
  (16, 1, 2, '二级', '范丰平', '层级'),
  (16, 1, 3, '三级', '范丰平', '层级'),
  (16, 1, 4, '四级', '范丰平', '层级'),
  (17, 1, 999, '未知', '范丰平', '未知');




-- ----------------------------
-- shortcut（首页快捷网站信息）
-- ----------------------------
DROP TABLE IF EXISTS `shortcut`;
CREATE TABLE `fanfengping_zeus`.`shortcut` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '系统主键',
    `dis` INT NOT NULL DEFAULT 0 COMMENT '是否显示（1，显示；0，不显示。默认不显示）',
    `disorder` INT NOT NULL DEFAULT 200 COMMENT '显示顺序（数字小，则优先显示）',
    `name` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT '个人博客' COMMENT '网站名称',
    `icon` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '网站图标地址',
    `url` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL DEFAULT 'http://www.cnblogs.com/fengpingfan/' COMMENT '网站访问地址',
    `manager` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT '范丰平' COMMENT '网站管理者',
    `mobile` VARCHAR(15) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '手机号码',
    `operator` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL DEFAULT 'system' COMMENT '更新人',
    `note` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL DEFAULT '' COMMENT '备注',
    `ctime` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `utime` DATETIME NOT NULL DEFAULT NOW() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `shortcut_uindex_url` (`url` ASC) COMMENT '快捷网站信息表/网站访问地址 唯一键'
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '快捷网站信息表';


INSERT INTO shortcut(dis, disorder, name, icon, url, manager, mobile, operator, note) VALUES
  ('1', '10', '个人网站', 'http://www.fanfengping.com/images/personal.png', 'http://www.fanfengping.com/', '范丰平', '15213149225', '范丰平', ''),
  ('1', '20', '个人博客', 'http://www.fanfengping.com/images/personal.png', 'http://www.cnblogs.com/fengpingfan/', '范丰平', '15213149225', '范丰平', ''),
  ('1', '30', '宙斯平台', 'http://www.fanfengping.com/images/personal.png', 'http://www.fanfengping.com/zeus/', '范丰平', '15213149225', '范丰平', '');





-- ----------------------------
-- Table structure for user(用户列表)
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `uuid` varchar(50) NOT NULL COMMENT 'UUID',
  `name` varchar(50) DEFAULT '规划用户' NOT NULL COMMENT '用户名称',
  `mobile` varchar(15) DEFAULT '' NOT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT '' NOT NULL COMMENT '用户邮箱',
  `account` varchar(50) DEFAULT '' NOT NULL COMMENT '用户账号（字母数字下划线）',
  `passwd` varchar(50) DEFAULT '' NOT NULL COMMENT '用户密码',
  `valid` int DEFAULT 0 NOT NULL COMMENT '是否启用（默认注销。1，启用；0，注销）',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `user_account_uindex` (`account`) COMMENT '用户表/用户账号 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';

INSERT INTO user(uuid, name, mobile, email, account, passwd, valid, operator, ctime, utime) VALUES
  (REPLACE(UUID(), '-', ''), '超级管理员', '15213149225', '263869564@qq.com', 'system', 'zeus123456', '1', '范丰平', NOW(), NOW()),
  (REPLACE(UUID(), '-', ''), '管理员', '15213149225', '263869564@qq.com', 'admin', 'zeus123456', '1', '范丰平', NOW(), NOW()),
  (REPLACE(UUID(), '-', ''), '普通用户', '15213149225', '263869564@qq.com', 'user', 'zeus123456', '1', '范丰平', NOW(), NOW()),
  (REPLACE(UUID(), '-', ''), '访客', '15213149225', '263869564@qq.com', 'guest', 'zeus123456', '1', '范丰平', NOW(), NOW()),
  (REPLACE(UUID(), '-', ''), '匿名用户', '15213149225', '263869564@qq.com', 'anon', 'zeus123456', '1', '范丰平', NOW(), NOW());



-- ----------------------------
-- Table structure for group(分组表，分组等级：god > admin > user > guest > anon)
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `code` varchar(50) DEFAULT 'anon' NOT NULL COMMENT '分组代码',
  `name` varchar(50) DEFAULT '匿名组' NOT NULL COMMENT '分组名称',
  `desc` varchar(100) DEFAULT '匿名组' NULL COMMENT '分组描述',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `group_uindex_code` (`code`) COMMENT '分组表/分组代码 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分组表';

INSERT INTO `group`(code, name, `desc`, operator, ctime, utime) VALUES
  ('god', '超管组', '拥有系统所有权限', 'system', NOW(), NOW()),
  ('admin', '管理组', '拥有除系统设置外的所有权限', 'system', NOW(), NOW()),
  ('user', '用户组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('guest', '访客组', '拥有除登录用户外的所有读取权限', 'system', NOW(), NOW()),
  ('anon', '匿名组', '仅可访问系统公共资源', 'system', NOW(), NOW()),
  ('business', '业务组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('demand', '需求组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('product', '产品组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('schema', '架构组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('develop', '研发组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('manultest', '功能测试组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('apitest', '自动化测试组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('autotest', '自动化测试组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('perftest', '性能组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('linux', '运维组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('service', '客服组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('support', '技术支持组', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW());



-- ----------------------------
-- Table structure for role(角色表，角色等级：god > admin > user > guest > anon)
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `code` varchar(50) DEFAULT 'anon' NOT NULL COMMENT '角色代码',
  `name` varchar(50) DEFAULT '匿名用户' NOT NULL COMMENT '角色名称',
  `desc` varchar(100) DEFAULT '匿名用户' NULL COMMENT '角色描述',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `role_uindex_code` (`code`) COMMENT '角色表/角色代码 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色表';

INSERT INTO role(code, name, `desc`, operator, ctime, utime) VALUES
  ('god', '超级管理员', '拥有系统所有权限', 'system', NOW(), NOW()),
  ('admin', '管理员', '拥有除系统设置外的所有权限', 'system', NOW(), NOW()),
  ('user', '普通用户', '拥有除系统设置/管理外的所有权限', 'system', NOW(), NOW()),
  ('guest', '访客用户', '拥有除登录用户外的所有读取权限', 'system', NOW(), NOW()),
  ('anon', '匿名用户', '仅可访问系统公共资源', 'system', NOW(), NOW());


-- ----------------------------
-- Table structure for permission(授权类型表，授权等级：all > delete > update > create > reseach > forbidden)
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `type` int NOT NULL DEFAULT 1 COMMENT '授权类型（1，菜单；2，按钮）',
  `code` varchar(50) DEFAULT 'read' NOT NULL COMMENT '授权代码',
  `name` varchar(50) DEFAULT '只读权限' NOT NULL COMMENT '授权名称',
  `desc` varchar(100) DEFAULT '只读权限' NULL COMMENT '授权描述',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `permission_uindex_code` (`code`) COMMENT '授权表/授权代码 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='授权表';

INSERT INTO permission(type, code, name, `desc`, operator, ctime, utime) VALUES
  (1, 'all', '所有', '系统所有/操作权限', 'system', NOW(), NOW()),
  (1, 'delete', '删除', '删除数据/删除按钮', 'system', NOW(), NOW()),
  (1, 'update', '更新', '更新数据/修改按钮', 'system', NOW(), NOW()),
  (1, 'create', '创建', '创建数据/新增按钮', 'system', NOW(), NOW()),
  (1, 'reseach', '查询', '查询数据/查询按钮', 'system', NOW(), NOW()),
  (1, 'forbidden', '拒绝', '拒绝访问/操作按钮', 'system', NOW(), NOW());



-- ----------------------------
-- Table structure for user_and_group(用户分组表)
-- ----------------------------
DROP TABLE IF EXISTS `user_and_group`;
CREATE TABLE `user_and_group`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `group_id` int NOT NULL COMMENT '分组ID',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `user_and_group_uindex` (`user_id`, `group_id`) COMMENT '用户分组表/用户ID-分组ID 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户分组表';


INSERT INTO user_and_group(user_id, group_id, operator, ctime, utime) VALUES
  (1, 1, 'system', NOW(), NOW()),
  (2, 2, 'system', NOW(), NOW()),
  (3, 3, 'system', NOW(), NOW()),
  (4, 4, 'system', NOW(), NOW()),
  (5, 5, 'system', NOW(), NOW());



-- ----------------------------
-- Table structure for user_and_role(用户角色表)
-- ----------------------------
DROP TABLE IF EXISTS `user_and_role`;
CREATE TABLE `user_and_role`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `user_and_role_uindex` (`user_id`, `role_id`) COMMENT '用户角色表/用户ID-角色ID 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户角色表';

INSERT INTO user_and_role(user_id, role_id, operator, ctime, utime) VALUES
  (1, 1, 'system', NOW(), NOW()),
  (2, 2, 'system', NOW(), NOW()),
  (3, 3, 'system', NOW(), NOW()),
  (4, 4, 'system', NOW(), NOW()),
  (5, 5, 'system', NOW(), NOW());


-- ----------------------------
-- Table structure for group_and_role(分组角色表)
-- ----------------------------
DROP TABLE IF EXISTS `group_and_role`;
CREATE TABLE `group_and_role`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `group_id` int NOT NULL COMMENT '分组ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `group_and_role_uindex` (`group_id`, `role_id`) COMMENT '分组角色表/分组ID-角色ID 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分组角色表';


INSERT INTO group_and_role(group_id, role_id, operator, ctime, utime) VALUES
  (1, 1, 'system', NOW(), NOW()),
  (2, 2, 'system', NOW(), NOW()),
  (3, 3, 'system', NOW(), NOW()),
  (4, 4, 'system', NOW(), NOW()),
  (5, 5, 'system', NOW(), NOW());



-- ----------------------------
-- Table structure for role_and_permission(角色授权表)
-- ----------------------------
DROP TABLE IF EXISTS `role_and_permission`;
CREATE TABLE `role_and_permission`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色ID',
  `perm_id` int NOT NULL COMMENT '授权ID',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间',
  UNIQUE INDEX `role_and_permission_uindex` (`role_id`, `perm_id`) COMMENT '角色授权表/角色ID-授权ID 唯一键'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色授权表';

INSERT INTO role_and_permission(role_id, perm_id, operator, ctime, utime) VALUES
  (1, 1, 'system', NOW(), NOW()),
  (2, 2, 'system', NOW(), NOW()),
  (3, 3, 'system', NOW(), NOW()),
  (4, 5, 'system', NOW(), NOW()),
  (5, 6, 'system', NOW(), NOW());





-- ----------------------------
-- Table structure for menu(菜单列表)
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
  `id` int PRIMARY KEY NOT NULL COMMENT '系统主键' AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '规划菜单' NOT NULL COMMENT '菜单名称',
  `icon` varchar(50) DEFAULT 'home' NOT NULL COMMENT '菜单图标',
  `path` varchar(150) DEFAULT '' NOT NULL COMMENT '菜单路径（配置路径即可）',
  `level` int DEFAULT 999 NOT NULL COMMENT '菜单级别（1，一级菜单；2，二级菜单；3，三级菜单）',
  `menu_id` int DEFAULT 999 NOT NULL COMMENT '上级菜单ID',
  `dis` int DEFAULT 0 NOT NULL COMMENT '是否显示（默认不显示。1，显示；0，不显示。）',
  `disorder` int DEFAULT 999 NOT NULL COMMENT '显示顺序（数字小，则优先显示）',
  `operator` varchar(50) DEFAULT 'system' NOT NULL COMMENT '操作人',
  `ctime` datetime DEFAULT NOW() NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT now() NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='菜单表';


INSERT INTO menu(title, icon, path, level, menu_id, dis, disorder, operator, ctime, utime) VALUES
  ('首页', 'home', '/index', 1, 0, 1, 10, 'system', NOW(), NOW()),
  ('项目管理', 'flag', '', 1, 0, 0, 20, 'system', NOW(), NOW()),
  ('发布管理', 'tags', '', 1, 0, 0, 30, 'system', NOW(), NOW()),
  ('开发管理', 'code', '', 1, 0, 0, 40, 'system', NOW(), NOW()),
  ('测试管理', 'balance-scale', '', 1, 0, 0, 50, 'system', NOW(), NOW()),
  ('报表管理', 'line-chart', '', 1, 0, 0, 60, 'system', NOW(), NOW()),
  ('数据管理', 'database', '', 1, 0, 1, 70, 'system', NOW(), NOW()),
  ('自动化', 'spinner', '', 1, 0, 0, 80, 'system', NOW(), NOW()),
  ('知识库', 'wikipedia-w', '', 1, 0, 0, 90, 'system', NOW(), NOW()),
  ('工具库', 'database', '', 1, 0, 0, 100, 'system', NOW(), NOW()),
  ('运维管理', 'linux', '', 1, 0, 0, 110, 'system', NOW(), NOW()),
  ('系统管理', 'cog', '', 1, 0, 1, 120, 'system', NOW(), NOW()),
  ('系统设置', 'cogs', '', 1, 0, 1, 130, 'system', NOW(), NOW());


INSERT INTO menu(title, icon, path, level, menu_id, dis, disorder, operator, ctime, utime) VALUES
  ('项目立项', 'flag-o', '', 2, 2, 1, 10, 'system', NOW(), NOW()),                  -- 项目管理
  ('项目需求', 'eye', '', 2, 2, 1, 10, 'system', NOW(), NOW()),                     -- 项目管理

  ('变更管理', 'exchange', '', 2, 3, 1, 10, 'system', NOW(), NOW()),                -- 发布管理
  ('发布管理', 'tag', '', 2, 3, 1, 10, 'system', NOW(), NOW()),                     -- 发布管理

  ('版本管理', 'gitlab', '', 2, 4, 1, 10, 'system', NOW(), NOW()),                  -- 开发管理
  ('系统设计', 'coffee', '', 2, 4, 1, 10, 'system', NOW(), NOW()),                  -- 开发管理
  ('接口文档', 'sliders', '', 2, 4, 1, 10, 'system', NOW(), NOW()),                 -- 开发管理
  ('用户故事', 'user-circle', '', 2, 4, 1, 10, 'system', NOW(), NOW()),             -- 开发管理
  ('开发任务', 'tasks', '', 2, 4, 1, 20, 'system', NOW(), NOW()),                   -- 开发管理

  ('用例管理', 'suitcase', '', 2, 5, 1, 10, 'system', NOW(), NOW()),                -- 测试管理
  ('缺陷管理', 'bug', '', 2, 5, 1, 30, 'system', NOW(), NOW()),                     -- 测试管理

  ('发布报表', 'line-chart', '', 2, 6, 1, 30, 'system', NOW(), NOW()),              -- 报表管理
  ('项目报表', 'area-chart', '', 2, 6, 1, 30, 'system', NOW(), NOW()),              -- 报表管理
  ('团队报表', 'bar-chart', '', 2, 6, 1, 30, 'system', NOW(), NOW()),               -- 报表管理
  ('用例分布', 'pie-chart', '', 2, 6, 1, 30, 'system', NOW(), NOW()),               -- 报表管理
  ('缺陷分布', 'pie-chart', '', 2, 6, 1, 30, 'system', NOW(), NOW()),               -- 报表管理

  ('数据库列表', 'list', '/page1', 2, 7, 1, 10, 'system', NOW(), NOW()),            -- 数据管理
  ('表结构比对', 'compress', '/page2', 2, 7, 1, 20, 'system', NOW(), NOW()),        -- 数据管理
  ('数据字典', 'book', '/page3', 2, 7, 1, 30, 'system', NOW(), NOW()),              -- 数据管理

  ('挡板管理', 'puzzle-piece', '', 2, 8, 1, 20, 'system', NOW(), NOW()),            -- 自动化
  ('接口测试', 'plug', '', 2, 8, 1, 20, 'system', NOW(), NOW()),                    -- 自动化
  ('性能测试', 'thermometer', '', 2, 8, 1, 20, 'system', NOW(), NOW()),             -- 自动化
  ('JMeter测试', 'plug', '', 2, 8, 1, 20, 'system', NOW(), NOW()),                  -- 自动化
  ('WEB测试', 'desktop', '', 2, 8, 1, 20, 'system', NOW(), NOW()),                  -- 自动化
  ('APP测试', 'android', '', 2, 8, 1, 20, 'system', NOW(), NOW()),                  -- 自动化

  ('知识库', 'wikipedia-w', '', 1, 9, 0, 70, 'system', NOW(), NOW()),               -- 知识库

  ('MD5加密', 'barcode', '/page3', 2, 10, 1, 30, 'system', NOW(), NOW()),           -- 工具库
  ('AES加密', 'barcode', '/page3', 2, 10, 1, 30, 'system', NOW(), NOW()),           -- 工具库
  ('数据生成', 'database', '/page3', 10, 6, 1, 30, 'system', NOW(), NOW()),         -- 工具库

  ('应用管理', 'android', '/page3', 2, 11, 1, 30, 'system', NOW(), NOW()),          -- 运维管理
  ('服务管理', 'rss-square', '/page3', 2, 11, 1, 30, 'system', NOW(), NOW()),       -- 运维管理
  ('环境管理', 'server', '/page3', 2, 11, 1, 30, 'system', NOW(), NOW()),           -- 运维管理
  ('监控管理', 'binoculars', '/page3', 2, 11, 1, 30, 'system', NOW(), NOW()),       -- 运维管理

  ('用户管理', 'users', '/user', 2, 12, 1, 10, 'system', NOW(), NOW()),             -- 系统管理
  ('团队管理', 'handshake-o', '/team', 2, 12, 0, 20, 'system', NOW(), NOW()),       -- 系统管理
  ('产品线管理', 'product-hunt', '/line', 2, 12, 0, 30, 'system', NOW(), NOW()),    -- 系统管理
  ('小组管理', 'group', '/group', 2, 12, 0, 40, 'system', NOW(), NOW()),            -- 系统管理
  ('权限管理', 'gavel', '/permission', 12, 3, 0, 50, 'system', NOW(), NOW()),       -- 系统管理

  ('菜单设置', 'th-list', '/menu', 2, 13, 1, 10, 'system', NOW(), NOW()),           -- 系统设置
  ('编码设置', 'barcode', '/code', 2, 13, 0, 20, 'system', NOW(), NOW());           -- 系统设置













