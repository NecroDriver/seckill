-- 数据库初始化脚本
-- 创建数据库
create database seckill;
-- 使用数据库
use seckill;
-- 创建秒杀数据库
create table seckill(
  seckill_id bigint not null AUTO_INCREMENT COMMENT '商品库存id',
  name VARCHAR(120) not null COMMENT '商品名称',
  number int not NULL COMMENT '库存数量',
  start_time TIMESTAMP not NULL DEFAULT '1970-01-02 00:00:00' COMMENT '秒杀开始时间',
  end_time TIMESTAMP NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '秒杀结束时间',
  create_time TIMESTAMP not NULL default current_timestamp COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET =utf8 COMMENT='秒杀库存表';

-- 初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀IPHONE8',100,'2017-09-10 00:00:00','2017-09-11 00:00:00'),
  ('500元秒杀IPAD2',200,'2017-09-10 00:00:00','2017-09-11 00:00:00'),
  ('300元秒杀米6',300,'2017-09-10 00:00:00','2017-09-11 00:00:00'),
  ('200元秒杀红米2',400,'2017-09-10 00:00:00','2017-09-11 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_kill(
  seckill_id BIGINT not null COMMENT '商品商品id',
  user_phone bigint not null COMMENT '用户手机号',
  state TINYINT not null DEFAULT -1 COMMENT '状态标识：-1无效 0成功 1已付款 2已发货',
  create_time TIMESTAMP not NULL default current_timestamp COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),/*联合主键*/
  key idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET =utf8 COMMENT='秒杀库存表';

-- 连接数据库控制台
-- mysql -uroot -p

-- 为什么手写DDL
-- 记录每次上线的DDL的代码
-- 上线v1.1
ALTER TABLE seckill
    DROP INDEX idx_create_time,
  ADD INDEX idx_c_s(create_time,start_time);

-- 上线v1.2
-- DDL