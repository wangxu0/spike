CREATE DATABASE spike;

use spike;

-- 创建库存表
CREATE TABLE spike(
`spike_id`  BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` VARCHAR(120) NOT NULL COMMENT '商品名称',
`number` INT NOT NULL COMMENT '库存数量',
`start_time` DATETIME NOT NULL COMMENT  '秒杀开启时间',
`end_time` DATETIME NOT NULL COMMENT '秒杀结束时间',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间', /*mysql5中只允许一个列设置为TIMESTAMP并默认*/
PRIMARY KEY (spike_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='库存表';


-- 创建秒杀成功明细表
CREATE TABLE success_spike(
`spike_id` BIGINT NOT NULL COMMENT '商品id',
`user_phone` VARCHAR(50) NOT NULL COMMENT '用户手机号',
`state` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1秒杀成功，0秒杀结束，-1重复秒杀，-2秒杀出错，-3秒杀地址被篡改',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (spike_id, user_phone),
KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';


-- 数据初始化
INSERT INTO spike(name, bumber, start_time, end_time)
VALUES
  ('1元秒杀库里同款战靴', 100, '2017-2-27 00:00:00', '2017-2-27 12:00:00'),
  ('2元秒杀情人节玫瑰花束', 1000, '2017-2-13 00:00:00', '2017-2-14 00:00:00'),
  ('100元秒杀iPhone7 plus', 10, '2017-2-16 00:00:00', '2017-2-16 23:59:59'),
  ('200元秒杀Macbook', 5, '2017-2-16 00:00:00', '2017-2-16 23:59:59'),
  ('500元秒杀小米平衡车', 50, '2017-2-16 00:00:00', '2017-2-16 23:59:59'),
  ('1万元秒杀特斯拉电动车', 3, '2017-2-16 00:00:00', '2017-2-16 23:59:59');