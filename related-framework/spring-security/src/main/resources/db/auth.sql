-- testdb.account definition

CREATE TABLE `account` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户标识',
                           `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户',
                           `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户表';


-- testdb.rela_role_account definition

CREATE TABLE `rela_role_account` (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色账户关联标识',
                                     `role_id` bigint(20) NOT NULL COMMENT '角色标识',
                                     `account_id` bigint(20) NOT NULL COMMENT '账户标识',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色账户关联标表';


-- testdb.`role` definition

CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色标识',
                        `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';