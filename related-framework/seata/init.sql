-- 1. 订单库 (seata_order)sql
CREATE TABLE `orders` ( `id` bigint(20) NOT NULL AUTO_INCREMENT, `user_id` varchar(255) DEFAULT NULL, `product_id` varchar(255) DEFAULT NULL, `count` int(11) DEFAULT NULL, `money` decimal(10,2) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB;
-- 2. 库存库 (seata_stock)
CREATE TABLE `stock` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `product_id` varchar(255) DEFAULT NULL,
                         `total` int(11) DEFAULT NULL,
                         `used` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- 初始化一条库存数据
INSERT INTO stock (product_id, total, used) VALUES ('prod-1001', 100, 0);
-- 3. 账户库 (seata_account)
CREATE TABLE `account` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `user_id` varchar(255) DEFAULT NULL,
                           `total` decimal(10,2) DEFAULT NULL,
                           `used` decimal(10,2) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- 初始化一条账户数据
INSERT INTO account (user_id, total, used) VALUES ('user-2001', 1000.00, 0.00);


-- 4. 在所有三个数据库中都执行以下SQL创建undo_log表！
-- 注意：这个表名和结构是Seata规定的，不要修改
CREATE TABLE `undo_log` (
                            `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
                            `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
                            `context` varchar(128) NOT NULL COMMENT 'undo_log context, such as serialization',
                            `rollback_info` longblob NOT NULL COMMENT 'rollback info',
                            `log_status` int(11) NOT NULL COMMENT '0:normal status, 1:defense status',
                            `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
                            `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
                            UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB COMMENT='AT transaction mode undo table';