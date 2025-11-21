

CREATE TABLE crm_customer (
  id int unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
  name varchar(10) NOT NULL COMMENT '姓名',
  phone char(11) NOT NULL UNIQUE COMMENT '手机号',
  gender tinyint unsigned NOT NULL COMMENT '性别, 1:男, 2:女',
  age tinyint unsigned DEFAULT NULL COMMENT '年龄',
  qq varchar(15) DEFAULT NULL COMMENT 'QQ号码',
  description varchar(200) DEFAULT NULL COMMENT '备注说明',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '修改时间'
) COMMENT '客户表';

INSERT INTO crm_customer VALUES 
	(1,'唐玄奘','18800000001',1,34,'82938298323','备注说明信息','2023-06-03 20:47:38','2023-06-03 21:48:44'),
	(2,'孙悟空','18800000002',1,22,'23245453','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(3,'猪八戒','18800000003',1,32,'34657453453','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(4,'沙僧','18800000004',1,45,'1345432424','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(5,'白龙马','18800000005',1,23,'9674532424','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(6,'牛魔王','18800000006',1,68,'23436475645','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(7,'白骨精','18800000007',2,19,'345345345345','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(8,'葫芦娃','18800000008',1,12,'34534524786','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(9,'琵琶精','18800000009',2,18,'34547652288','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(10,'太上老君','18800000010',1,88,'547644447676','备注说明信息','2023-06-03 20:47:38','2023-06-03 20:47:38'),
	(11,'如来佛','18909091212',1,98,'23232312','如来佛','2023-06-03 21:52:06','2023-06-05 23:12:03'),
	(12,'张翠山','13209123456',1,34,'2784398432','铁画银钩','2023-06-06 21:31:16','2023-06-06 21:38:31'),
    (13,'宋远桥','13567801290',1,45,'7273878237','武当大弟子','2023-06-06 22:55:50','2023-06-06 22:55:50'),
    (14,'俞岱岩','13429091212',1,34,'429091212','武当弟子','2023-06-06 22:56:27','2023-06-06 22:56:27'),
    (15,'殷梨亭','15309091212',1,39,'309091212','武当弟子','2023-06-06 22:56:54','2023-06-06 22:56:54'),
    (16,'莫山山','15609091212',2,22,'609091212','墨池苑书痴','2023-06-06 22:58:04','2023-06-06 22:58:04');


CREATE TABLE crm_operate_log (
  id int unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
  url varchar(100) DEFAULT NULL COMMENT '请求URL地址',
  target_class varchar(100) DEFAULT NULL COMMENT '目标类类名',
  target_method varchar(50) DEFAULT NULL COMMENT '目标方法名',
  method_params varchar(500) DEFAULT NULL COMMENT '方法运行参数',
  method_return varchar(1000) DEFAULT NULL COMMENT '方法运行返回值, json格式字符串',
  client_browser varchar(300) DEFAULT NULL COMMENT '客户端浏览器信息',
  client_platform varchar(100) DEFAULT NULL COMMENT '客户端操作系统名称',
  operate_time datetime DEFAULT NULL COMMENT '操作时间',
  cost_time bigint DEFAULT NULL COMMENT '操作耗时'
) COMMENT 'CRM系统日志记录表';

INSERT INTO crm_operate_log VALUES 
  (1,'http://localhost:8080/customers','com.itheima.controller.CustomerController','update','[Customer(id=11, name=如来佛, phone=18909091212, gender=1, age=98, qq=23232312, description=如来佛, createTime=2023-06-03T21:52:06, updateTime=2023-06-03T21:52:06)]','{\"code\":1,\"msg\":\"success\"}','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36','\"Windows\"','2023-06-05 23:12:03',8);


