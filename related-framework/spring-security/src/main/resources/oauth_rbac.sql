-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: oauth_rbac
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户标识',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','$2a$10$kAs3b8hfZqHUJ1zV1xEq7OekX3/6FTWyecJkCjXnl4mpm4OG8j5h6'),(2,'user','$2a$10$4q5T3GPi3R6heBPQid1f0ORxz/XlMlCstrxO7JsPtl1oPx9LDq0N6'),(3,'ziming','$2a$10$cwZJ/7pAiCL4pq65719Ghu1qaOFNFC.L7yZYOwysX86Bqy5zq4Psi');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_resource`
--

DROP TABLE IF EXISTS `base_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `base_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统资源标识',
  `resource_name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `api_url` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Api',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_resource`
--

LOCK TABLES `base_resource` WRITE;
/*!40000 ALTER TABLE `base_resource` DISABLE KEYS */;
INSERT INTO `base_resource` VALUES (1,'系统资源表新增','/baseResource'),(2,'需要认证接口','/security/authentication');
/*!40000 ALTER TABLE `base_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rela_role_account`
--

DROP TABLE IF EXISTS `rela_role_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rela_role_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色账户关联标识',
  `role_id` bigint(20) NOT NULL COMMENT '角色标识',
  `account_id` bigint(20) NOT NULL COMMENT '账户标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色账户关联标表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rela_role_account`
--

LOCK TABLES `rela_role_account` WRITE;
/*!40000 ALTER TABLE `rela_role_account` DISABLE KEYS */;
INSERT INTO `rela_role_account` VALUES (1,1,1),(2,3,2);
/*!40000 ALTER TABLE `rela_role_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rela_role_resource`
--

DROP TABLE IF EXISTS `rela_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rela_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色系统资源关联标识',
  `role_id` bigint(20) NOT NULL COMMENT '资源名称',
  `resource_id` bigint(20) DEFAULT NULL COMMENT 'Api',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色系统资源关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rela_role_resource`
--

LOCK TABLES `rela_role_resource` WRITE;
/*!40000 ALTER TABLE `rela_role_resource` DISABLE KEYS */;
INSERT INTO `rela_role_resource` VALUES (1,1,1),(2,1,2),(3,2,1),(4,3,2);
/*!40000 ALTER TABLE `rela_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色标识',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'超级管理员'),(2,'管理员'),(3,'普通用户');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `role_account_resource`
--

DROP TABLE IF EXISTS `role_account_resource`;
/*!50001 DROP VIEW IF EXISTS `role_account_resource`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `role_account_resource` AS SELECT 
 1 AS `account_id`,
 1 AS `username`,
 1 AS `password`,
 1 AS `role_id`,
 1 AS `role_name`,
 1 AS `resource_id`,
 1 AS `resource_name`,
 1 AS `api_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'oauth_rbac'
--

--
-- Final view structure for view `role_account_resource`
--

/*!50001 DROP VIEW IF EXISTS `role_account_resource`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `role_account_resource` AS select `a`.`id` AS `account_id`,`a`.`username` AS `username`,`a`.`password` AS `password`,`r`.`id` AS `role_id`,`r`.`role_name` AS `role_name`,`br`.`id` AS `resource_id`,`br`.`resource_name` AS `resource_name`,`br`.`api_url` AS `api_url` from ((((`account` `a` join `rela_role_account` `rra` on((`a`.`id` = `rra`.`account_id`))) join `role` `r` on((`r`.`id` = `rra`.`role_id`))) join `rela_role_resource` `rrr` on((`r`.`id` = `rrr`.`role_id`))) join `base_resource` `br` on((`rrr`.`resource_id` = `br`.`id`))) */
/*!50002 WITH CASCADED CHECK OPTION */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-14 11:02:17
