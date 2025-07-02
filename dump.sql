-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: shoppingmall
-- ------------------------------------------------------
-- Server version	11.7.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm` (
  `alarm_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `target` enum('ADMIN','ALL','BUYER','PERSONAL','SELLER') DEFAULT NULL,
  `target_member_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`alarm_id`),
  KEY `FKpa27574ryy2s7o0fcnty6w15m` (`target_member_id`),
  CONSTRAINT `FKpa27574ryy2s7o0fcnty6w15m` FOREIGN KEY (`target_member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,'2025-05-02 09:12:39.123742','2025-05-02 09:12:39.123742','arima [predict] → success','2025-05-02 09:12:39.104002',_binary '\0','ADMIN',NULL,'모델 학습 결과 도착'),(2,'2025-05-02 09:24:13.748259','2025-05-02 09:24:13.748259','sarimax [predict] → success','2025-05-02 09:24:13.737258',_binary '\0','ADMIN',NULL,'모델 학습 결과 도착'),(3,'2025-05-08 14:28:46.220295','2025-05-08 15:36:51.620004','content [recommend] → success','2025-05-08 14:28:46.199774',_binary '','ADMIN',4,'모델 학습 결과 도착'),(4,'2025-05-08 14:44:41.619223','2025-05-08 14:44:41.619223','content [recommend] → success','2025-05-08 14:44:41.588708',_binary '\0','ADMIN',4,'모델 학습 결과 도착'),(5,'2025-05-13 08:57:36.672983','2025-05-13 08:57:36.672983','content [recommend] → success','2025-05-13 08:57:36.627979',_binary '\0','ADMIN',4,'모델 학습 결과 도착');
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FKix170nytunweovf2v9137mx2o` (`member_id`),
  CONSTRAINT `FKix170nytunweovf2v9137mx2o` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (5,'2025-04-02 10:04:45.447571','2025-04-02 10:04:45.447571',1),(6,'2025-04-04 16:02:43.095852','2025-04-04 16:02:43.095852',5);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `cart_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (18,'2025-04-04 16:09:05.599360','2025-04-04 16:13:22.329212',11,6,7),(20,'2025-04-04 16:51:44.290995','2025-04-04 16:51:44.290995',3,6,17),(21,'2025-04-04 16:51:56.664860','2025-04-04 16:51:56.664860',2,6,16),(22,'2025-04-04 16:52:00.919461','2025-04-04 16:52:00.919461',1,6,15),(31,'2025-04-24 17:19:15.177060','2025-04-24 17:19:15.177060',1,5,3),(32,'2025-04-24 17:19:22.140140','2025-04-24 17:19:22.140140',1,5,5);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (152,'화장품'),(153,'식품'),(154,'생활용품'),(155,'전자제품'),(156,'패션');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_seq`
--

DROP TABLE IF EXISTS `category_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_seq`
--

LOCK TABLES `category_seq` WRITE;
/*!40000 ALTER TABLE `category_seq` DISABLE KEYS */;
INSERT INTO `category_seq` VALUES (251,1,9223372036854775806,1,50,0,0,0);
/*!40000 ALTER TABLE `category_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `image_type` enum('MEMBER','PRODUCT') DEFAULT NULL,
  `is_main` bit(1) NOT NULL,
  `reference_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FKcwj2qe954vyxgwfv4uu096gay` (`reference_id`),
  CONSTRAINT `FKcwj2qe954vyxgwfv4uu096gay` FOREIGN KEY (`reference_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'2025-03-28 11:33:14.534121','2025-03-28 11:33:14.534121','2e9c42f9-9a2f-4c38-8cd4-be7bbac76f3a_test.jpg','C:\\upload\\images\\PRODUCT\\42e9c42f9-9a2f-4c38-8cd4-be7bbac76f3a_test.jpg','image/jpeg','PRODUCT',_binary '',4),(3,'2025-03-31 10:36:48.868997','2025-03-31 14:49:52.169188','bb83142d-35cb-4055-8d61-0777130e548f_status.jpg','C:\\upload\\images\\PRODUCT\\5','image/jpeg','PRODUCT',_binary '',5),(6,'2025-03-31 14:32:53.594774','2025-03-31 14:32:53.594774','8cf1c04d-c2f2-439d-a663-bada8618de79_test.jpg','C:\\upload\\images\\PRODUCT\\6','image/jpeg','PRODUCT',_binary '',6),(7,'2025-03-31 14:32:53.601774','2025-03-31 14:32:53.601774','28d78b8c-51ac-4fcb-b3e1-65b393637629_LIMS중복 (1).png','C:\\upload\\images\\PRODUCT\\6','image/png','PRODUCT',_binary '\0',6),(8,'2025-03-31 14:32:53.605859','2025-03-31 14:32:53.605859','0eda47b2-838b-43e6-8c04-16967d47c286_student.jpg','C:\\upload\\images\\PRODUCT\\6','image/jpeg','PRODUCT',_binary '\0',6),(9,'2025-03-31 16:33:00.951207','2025-03-31 16:33:00.951207','6513cc67-f218-4b18-bae8-21337cf2a4de_지원사업 시행지침.jpg','C:\\upload\\images\\PRODUCT\\4','image/jpeg','PRODUCT',_binary '\0',4),(13,'2025-04-01 10:44:38.897802','2025-04-01 10:44:38.897802','94358e4e-2f37-42d3-addb-ca4c8d0258e2_사업자등록증 (1).jpg','C:\\upload\\images\\PRODUCT\\1','image/jpeg','PRODUCT',_binary '',1),(14,'2025-04-01 10:44:45.632047','2025-04-01 10:44:45.632047','6a5ec8cc-e65f-4a31-8b81-edb20b71309a_CMS중복 (1).png','C:\\upload\\images\\PRODUCT\\2','image/png','PRODUCT',_binary '',2),(15,'2025-04-01 10:44:52.556032','2025-04-01 10:44:52.556032','e211aefe-073e-4757-99ef-2d33dcaf8368_스크린샷_21-3-2025_9340_kcl.re.kr.jpeg','C:\\upload\\images\\PRODUCT\\3','image/jpeg','PRODUCT',_binary '',3),(16,'2025-04-02 17:09:54.600249','2025-04-02 17:09:54.600249','fc53e936-1ac6-4379-b944-9c48fd603136_201604131460701467_1.jpg','C:\\upload\\images\\PRODUCT\\7','image/jpeg','PRODUCT',_binary '',7),(17,'2025-04-02 17:10:18.477151','2025-04-02 17:10:18.477151','c4faf090-d2c0-44f1-840c-1cd0f242e84e_news-p.v1.20220810.813acc63f5eb4b34bb0c171e48777c91_P1.jpg','C:\\upload\\images\\PRODUCT\\8','image/jpeg','PRODUCT',_binary '\0',8),(18,'2025-04-02 17:21:42.013892','2025-04-02 17:21:42.013892','4b1d7a19-fe60-453d-9039-0b5b7d82bfde_BIMGMO0000002334.jpg','C:\\upload\\images\\PRODUCT\\9','image/jpeg','PRODUCT',_binary '\0',9),(19,'2025-04-02 17:22:18.325014','2025-04-02 17:22:18.325014','cf315e3f-c204-4910-afa0-01aeec74d27a_BIMGAM0000376668_20211105112431304132.jpg','C:\\upload\\images\\PRODUCT\\10','image/jpeg','PRODUCT',_binary '\0',10),(20,'2025-04-02 17:22:37.505162','2025-04-02 17:22:37.505162','6ef41bf3-5540-47a5-901e-98ca761c2391_nYC8N9SjHPUQ_ZJy5fwaUwok5lGkeLCrZt_X8t-OMU7ut36_I04sfrkExVBegCejJYthGmALmZAuYBuLEJdRfA.webp','C:\\upload\\images\\PRODUCT\\11','image/webp','PRODUCT',_binary '\0',11),(21,'2025-04-02 17:22:59.430336','2025-04-02 17:22:59.430336','84d402a4-cfb1-41bb-952c-cc5f30dd544d_news-p.v1.20220810.813acc63f5eb4b34bb0c171e48777c91_P1.jpg','C:\\upload\\images\\PRODUCT\\12','image/jpeg','PRODUCT',_binary '\0',12),(22,'2025-04-02 17:37:28.981475','2025-04-02 17:37:28.981475','5d8b28d8-9888-4cc4-a640-3eb07873743b_news-p.v1.20220810.813acc63f5eb4b34bb0c171e48777c91_P1.jpg','C:\\upload\\images\\PRODUCT\\13','image/jpeg','PRODUCT',_binary '',13),(23,'2025-04-02 17:37:46.778655','2025-04-02 17:37:46.778655','3cf44298-895d-4149-b518-8f0340042d2c_images.jpg','C:\\upload\\images\\PRODUCT\\14','image/jpeg','PRODUCT',_binary '',14),(24,'2025-04-02 17:38:03.114661','2025-04-02 17:38:03.114661','0ba133d3-a2bb-4980-afc0-976eb02941c7_BIMGRP0000364504_2020110413241935_700hq7951i.jpg','C:\\upload\\images\\PRODUCT\\15','image/jpeg','PRODUCT',_binary '',15),(25,'2025-04-02 17:38:23.291768','2025-04-02 17:38:23.291768','3c0e11e8-a777-4d99-9fd2-38ceb58a1393_BIMGMO0000002334.jpg','C:\\upload\\images\\PRODUCT\\16','image/jpeg','PRODUCT',_binary '',16),(26,'2025-04-02 17:39:31.089159','2025-04-02 17:39:31.089159','585f256d-ec41-46e9-96a5-66c5a567d5dc_201604131460701467_1.jpg','C:\\upload\\images\\PRODUCT\\17','image/jpeg','PRODUCT',_binary '',17),(27,'2025-04-08 14:15:29.093700','2025-04-08 14:15:29.093700','a099903c-c7fe-4b2e-ad66-78799ceba278_nYC8N9SjHPUQ_ZJy5fwaUwok5lGkeLCrZt_X8t-OMU7ut36_I04sfrkExVBegCejJYthGmALmZAuYBuLEJdRfA.webp','C:\\upload\\images\\PRODUCT\\18','image/webp','PRODUCT',_binary '',18),(28,'2025-04-08 14:15:44.621931','2025-04-08 14:15:44.621931','4a8e2833-4750-48e4-a8c4-ce592e39f36b_pop_guide.jpg','C:\\upload\\images\\PRODUCT\\19','image/jpeg','PRODUCT',_binary '',19),(29,'2025-04-08 14:16:03.936743','2025-04-08 14:16:03.936743','e740fcaf-f98e-4a44-ae69-92ccc060a22a_images (1).jpg','C:\\upload\\images\\PRODUCT\\20','image/jpeg','PRODUCT',_binary '',20),(30,'2025-04-08 14:16:28.983278','2025-04-08 14:16:28.983278','afd1b28a-c122-4e3f-86de-0bcf82f14c2b_BIMGRP0000376795_20211105162459372975.jpg','C:\\upload\\images\\PRODUCT\\21','image/jpeg','PRODUCT',_binary '',21),(31,'2025-04-10 09:13:41.596724','2025-04-10 09:13:41.596724','ec7483fa-f4a2-4b58-a1a2-19b103cbe678_20180831112400062756.jpg','C:\\upload\\images\\PRODUCT\\8','image/jpeg','PRODUCT',_binary '',8),(32,'2025-04-10 09:13:58.495132','2025-04-10 09:13:58.495132','de543b66-55b8-4693-80ee-2a86046c4b66_images.jpg','C:\\upload\\images\\PRODUCT\\9','image/jpeg','PRODUCT',_binary '',9),(33,'2025-04-10 09:14:06.252951','2025-04-10 09:14:06.252951','284f5961-9072-421c-b106-bcd8f8f05000_img_equip_01.png','C:\\upload\\images\\PRODUCT\\25','image/png','PRODUCT',_binary '',25),(34,'2025-04-10 09:14:11.344454','2025-04-10 09:14:11.344454','985c59da-b145-450a-b54a-97f6e4d90bf9_img_equip_05.png','C:\\upload\\images\\PRODUCT\\26','image/png','PRODUCT',_binary '',26),(35,'2025-04-10 09:14:16.668989','2025-04-10 09:14:16.668989','5b73fc6f-ec85-4c57-b5c8-df6232134ef5_img_equip_08.png','C:\\upload\\images\\PRODUCT\\28','image/png','PRODUCT',_binary '',28),(36,'2025-04-10 09:14:22.042804','2025-04-10 09:14:22.042804','597a162e-0fdd-499c-a8f8-06d80a06b65c_img_equip_07.png','C:\\upload\\images\\PRODUCT\\27','image/png','PRODUCT',_binary '',27),(37,'2025-04-10 09:14:28.320515','2025-04-10 09:14:28.320515','f450e8b6-05d0-4ae2-9ad5-2beee4ab2346_img_equip_06.png','C:\\upload\\images\\PRODUCT\\29','image/png','PRODUCT',_binary '',29),(38,'2025-04-10 09:14:34.512848','2025-04-10 09:14:34.512848','ee98543c-1e7e-4901-9d11-3035c812b623_img_equip_04.png','C:\\upload\\images\\PRODUCT\\30','image/png','PRODUCT',_binary '',30),(39,'2025-04-10 09:15:06.602718','2025-04-10 09:15:06.602718','ff4a326e-10a7-4e75-ade7-00709382c5dc_2-1.png','C:\\upload\\images\\PRODUCT\\10','image/png','PRODUCT',_binary '',10),(40,'2025-04-10 09:15:16.433724','2025-04-10 09:15:16.433724','13e71ca9-1acc-402d-9519-eaa8831015cd_2-2.png','C:\\upload\\images\\PRODUCT\\11','image/png','PRODUCT',_binary '',11),(41,'2025-04-10 09:15:28.094960','2025-04-10 09:15:28.094960','6c6e92fc-df56-45c3-9450-f8d090097f61_식품용 기구 검사신청서 미리보기2.png','C:\\upload\\images\\PRODUCT\\12','image/png','PRODUCT',_binary '',12),(42,'2025-04-10 09:15:43.889039','2025-04-10 09:15:43.889039','89b47c46-8d8d-4f89-b026-d8e6560a6f28_img_lab_01.png','C:\\upload\\images\\PRODUCT\\22','image/png','PRODUCT',_binary '',22),(43,'2025-04-10 09:15:49.853020','2025-04-10 09:15:49.853020','cc69eaa1-8a4d-48e1-9b37-c21f2786ccf3_img_lab_02.png','C:\\upload\\images\\PRODUCT\\23','image/png','PRODUCT',_binary '',23),(44,'2025-04-10 09:15:56.999761','2025-04-10 09:15:56.999761','3c979efc-e1e8-4f93-8c42-e155474e3c4a_img_lab_03.png','C:\\upload\\images\\PRODUCT\\24','image/png','PRODUCT',_binary '',24);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `cell_no` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `login_id` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `real_address` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','BUYER','SELLER') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2025-03-27 14:07:01.825942','2025-03-27 14:07:01.825942',33,'010-6247-0557','ljs@whisker.kr','남','testuser1','테스트유저1','$2a$10$4Enlh.y.plmAd6Y0hTuGV.n/IiygKDErLzxubMxqP6g6N0LcVzhjO','대전광역시 서구 둔산동','대전','BUYER','테스트유저1'),(2,'2025-03-27 14:07:55.083174','2025-03-27 14:07:55.083174',55,'010-6247-0557','ljs@whisker.kr','남','testseller1','테스트셀러1','$2a$10$Xm.CWAMRFHuwgRvQW39azO.GpixnVQzdQNZxhqqWvNYCdtetF4R.G','광주광역시 중구','광주','SELLER','테스트셀러1'),(3,'2025-03-27 14:08:37.797088','2025-03-27 14:08:37.797088',53,'010-7895-4114','qwe@naver.com','여','testseller2','테스트셀러2','$2a$10$/XXAA4Q133s6kFBwvsv5re6jMHtHgdKo38w8o/.78bxU.PvCAnZqG','강원 강릉시 하슬라로 15 고속버스터미널','강릉','SELLER','테스트셀러2'),(4,'2025-03-27 14:10:07.448665','2025-03-27 14:10:07.448665',20,'010-7785-2222','ljs@whisker.kr','남','test','테스트','$2a$10$vEaQ3nIJUZ11pYDzIw2IMuLse1AN55QXfxbP4K2ONkqgbKDBLSmFe','서울특별시','서울','ADMIN','테스트'),(5,'2025-04-02 16:15:08.112252','2025-04-02 16:15:08.112252',12,'010-4452-1578','gwrrr@naver.com','남','testuser11','테스트유저11','$2a$10$1TA4kfsKbPlNn4m2XK5qSOCq.HunUKhY08tSh.J2f502lGROVwcv6','대전광역시 서구 도안동','대전','BUYER','테스트유저11'),(6,'2025-04-02 16:17:28.643476','2025-04-18 15:28:45.120868',10,'010-7785-2222','rwhwhrr@naver.com','남','testseller11','테스트판매자11','$2a$10$UIdu/hQpV3VutsjvHnh/8uaD2jNEIA5lnr6NgnNdHrOfPkQIE13r2','경기 화성시 남양읍 남양리 2000','경기도','SELLER','테스트판매자11'),(7,'2025-04-02 16:18:04.165926','2025-04-02 16:18:04.165926',64,'010-7785-2222','rqwhwrh@naver.com','여','testseller12','테스트판매자12','$2a$10$z25U0EY3vVD3wCG9ZNY2K.76IuOsM/X/hrtcqDgzUPPpLC2myn262','대전광역시 서구 중앙동','대전','SELLER','테스트판매자12'),(8,'2025-04-02 16:21:27.996406','2025-04-02 16:21:27.996406',22,'010-6247-0557','w3333@naver.com','남','testseller13','테스트판매자13','$2a$10$Q4TFq7KwzxadC3lIu5/iC./k5EpqCysx614Amo6pV.OIWjyHBcbQC','대전광역시 서구 중앙동로 133','대전','SELLER','테스트판매자13'),(9,'2025-04-02 16:22:02.345655','2025-04-02 16:22:02.345655',66,'010-8798-8554','rrrr@naver.com','여','testuser12','테스트구매자12','$2a$10$pgZdSh6FyvIJ00XkxHgrkuuAD.jEfdiH9MRS8Qv/ap2K5LxUUSVQK','대전광역시 중구 중촌동','대전','BUYER','테스트구매자12'),(10,'2025-04-02 16:22:44.513290','2025-04-02 16:22:44.513290',96,'010-8798-8554','yhtt66@naver.com','여','testuser13','테스트구매자13','$2a$10$l2JJtMgdIDNvVVFzT7nxN.LFBESUm2/Qxzu.DoaK0WaltyG81xb2m','대전광역시 서구 중앙동로 133','대전','BUYER','테스트구매자13'),(11,'2025-04-02 16:23:11.410882','2025-04-02 16:23:11.410882',77,'010-7785-2222','rrrr@naver.com','남','testseller14','테스트판매자14','$2a$10$UmQtXjGldm9iRdhFKAqfC.KJGLvOgIqeVVBiqctkVxvVpdTlfVugq','대전광역시 서구 중앙동로 133','대전','SELLER','테스트판매자14'),(12,'2025-04-11 17:34:30.690600','2025-04-11 17:34:30.690600',60,'010-6247-0557','ljs@whisker.kr','여','testuser2','testuser2','$2a$10$erdNCoP2JyRlAMOt.k8WM.sYKyXHJLxfXz/HE2AJhQQR76ThxiETe','서울특별시','서울','BUYER','테스트유저2');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_config`
--

DROP TABLE IF EXISTS `model_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_config` (
  `model_config_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `model_name` varchar(255) NOT NULL,
  `model_type` enum('PREDICT','RECOMMEND') NOT NULL,
  PRIMARY KEY (`model_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_config`
--

LOCK TABLES `model_config` WRITE;
/*!40000 ALTER TABLE `model_config` DISABLE KEYS */;
INSERT INTO `model_config` VALUES (1,'2025-04-14 14:28:25.893539','2025-04-14 14:43:14.924739',_binary '','content기반추천모델','content','RECOMMEND'),(2,'2025-04-14 14:29:40.677468','2025-04-14 14:29:40.677468',_binary '\0','xgb기반예측모델','xgb_timeseries','PREDICT'),(3,'2025-04-14 14:29:54.388368','2025-04-14 14:41:27.903166',_binary '\0','prophet기반예측모델','prophet','PREDICT'),(4,'2025-04-14 14:41:56.897906','2025-04-14 14:41:56.897906',_binary '\0','arima기반예측모델','arima','PREDICT'),(5,'2025-04-14 14:42:06.517545','2025-04-14 14:42:06.517545',_binary '\0','sarimax기반예측모델','sarimax','PREDICT'),(6,'2025-04-14 14:42:22.085162','2025-04-14 14:42:22.085162',_binary '\0','collaborative기반추천모델','collaborative','RECOMMEND'),(7,'2025-04-14 14:42:32.349208','2025-04-14 14:42:32.349208',_binary '\0','svd기반추천모델','svd','RECOMMEND'),(8,'2025-04-14 14:42:46.493986','2025-04-14 14:42:46.493986',_binary '\0','xgb기반추천모델','xgb_classifier','RECOMMEND'),(9,'2025-04-14 14:42:57.861997','2025-04-14 14:42:57.861997',_binary '\0','knn기반추천모델','knn','RECOMMEND'),(10,'2025-04-14 14:43:05.525133','2025-04-14 14:43:05.525133',_binary '\0','item2vec기반추천모델','item2vec','RECOMMEND');
/*!40000 ALTER TABLE `model_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_train_log`
--

DROP TABLE IF EXISTS `model_train_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_train_log` (
  `id` bigint(20) NOT NULL,
  `executed_at` datetime(6) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `model_name` varchar(255) DEFAULT NULL,
  `success` bit(1) NOT NULL,
  `type` enum('PREDICT','RECOMMEND') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_train_log`
--

LOCK TABLES `model_train_log` WRITE;
/*!40000 ALTER TABLE `model_train_log` DISABLE KEYS */;
INSERT INTO `model_train_log` VALUES (1,'2025-04-14 16:08:48.740515','{\n  \"message\": \"Content-based filtering model trained and saved.\"\n}\n','content',_binary '','RECOMMEND'),(2,'2025-04-14 16:10:01.764043','{\n  \"message\": \"xgb_timeseries time series model trained and saved.\"\n}\n','xgb_timeseries',_binary '','PREDICT'),(52,'2025-04-15 10:31:53.722775','Connection refused: no further information: localhost/127.0.0.1:5000','content',_binary '\0','RECOMMEND'),(53,'2025-04-15 10:31:53.829317','Connection refused: no further information: localhost/127.0.0.1:5000','xgb_timeseries',_binary '\0','RECOMMEND'),(54,'2025-04-15 10:31:53.843428','Connection refused: no further information: localhost/127.0.0.1:5000','prophet',_binary '\0','RECOMMEND'),(55,'2025-04-15 10:31:53.857019','Connection refused: no further information: localhost/127.0.0.1:5000','arima',_binary '\0','RECOMMEND'),(56,'2025-04-15 10:31:53.871731','Connection refused: no further information: localhost/127.0.0.1:5000','sarimax',_binary '\0','RECOMMEND'),(57,'2025-04-15 10:31:53.884733','Connection refused: no further information: localhost/127.0.0.1:5000','collaborative',_binary '\0','RECOMMEND'),(58,'2025-04-15 10:31:53.897733','Connection refused: no further information: localhost/127.0.0.1:5000','svd',_binary '\0','RECOMMEND'),(59,'2025-04-15 10:31:53.908730','Connection refused: no further information: localhost/127.0.0.1:5000','xgb_classifier',_binary '\0','RECOMMEND'),(60,'2025-04-15 10:31:53.917731','Connection refused: no further information: localhost/127.0.0.1:5000','knn',_binary '\0','RECOMMEND'),(61,'2025-04-15 10:31:53.927733','Connection refused: no further information: localhost/127.0.0.1:5000','item2vec',_binary '\0','RECOMMEND'),(102,'2025-04-22 17:35:24.458895','{\n  \"message\": \"Content-based filtering model trained and saved.\"\n}\n','content',_binary '','RECOMMEND'),(103,'2025-04-22 17:35:24.667492','500 Internal Server Error from POST http://localhost:5000/recommend/train','xgb_timeseries',_binary '\0','RECOMMEND'),(104,'2025-04-22 17:35:24.683497','500 Internal Server Error from POST http://localhost:5000/recommend/train','prophet',_binary '\0','RECOMMEND'),(105,'2025-04-22 17:35:24.695517','500 Internal Server Error from POST http://localhost:5000/recommend/train','arima',_binary '\0','RECOMMEND'),(106,'2025-04-22 17:35:24.709116','500 Internal Server Error from POST http://localhost:5000/recommend/train','sarimax',_binary '\0','RECOMMEND'),(107,'2025-04-22 17:35:28.033625','{\n  \"message\": \"Collaborative filtering model trained and saved.\"\n}\n','collaborative',_binary '','RECOMMEND'),(108,'2025-04-22 17:35:31.130693','{\n  \"message\": \"SVD recommendation model trained and saved.\"\n}\n','svd',_binary '','RECOMMEND'),(109,'2025-04-22 17:35:33.815797','500 Internal Server Error from POST http://localhost:5000/recommend/train','xgb_classifier',_binary '\0','RECOMMEND'),(110,'2025-04-22 17:35:37.521225','{\n  \"message\": \"k-NN recommendation model trained and saved.\"\n}\n','knn',_binary '','RECOMMEND'),(111,'2025-04-22 17:35:42.003505','{\n  \"message\": \"Item2Vec model trained and saved.\"\n}\n','item2vec',_binary '','RECOMMEND'),(112,'2025-04-23 17:36:11.493958','{\"message\":\"Content-based filtering model trained and saved.\"}\n','content',_binary '','RECOMMEND'),(113,'2025-04-23 17:36:11.709265','500 Internal Server Error from POST http://localhost:5000/recommend/train','xgb_timeseries',_binary '\0','RECOMMEND'),(114,'2025-04-23 17:36:11.740795','500 Internal Server Error from POST http://localhost:5000/recommend/train','prophet',_binary '\0','RECOMMEND'),(115,'2025-04-23 17:36:11.756535','500 Internal Server Error from POST http://localhost:5000/recommend/train','arima',_binary '\0','RECOMMEND'),(116,'2025-04-23 17:36:11.834777','500 Internal Server Error from POST http://localhost:5000/recommend/train','sarimax',_binary '\0','RECOMMEND'),(117,'2025-04-23 17:36:14.954206','{\"message\":\"Collaborative filtering model trained and saved.\"}\n','collaborative',_binary '','RECOMMEND'),(118,'2025-04-23 17:36:22.760408','{\"message\":\"SVD recommendation model trained and saved.\"}\n','svd',_binary '','RECOMMEND'),(119,'2025-04-23 17:36:25.699734','500 Internal Server Error from POST http://localhost:5000/recommend/train','xgb_classifier',_binary '\0','RECOMMEND'),(120,'2025-04-23 17:36:29.973738','{\"message\":\"k-NN recommendation model trained and saved.\"}\n','knn',_binary '','RECOMMEND'),(121,'2025-04-23 17:36:33.637019','{\"message\":\"Item2Vec model trained and saved.\"}\n','item2vec',_binary '','RECOMMEND'),(122,'2025-04-24 14:53:03.616091','Connection prematurely closed BEFORE response','content',_binary '\0','RECOMMEND'),(123,'2025-04-24 14:53:03.699093','java.lang.InterruptedException','xgb_timeseries',_binary '\0','RECOMMEND'),(124,'2025-04-24 14:53:03.709423','java.lang.InterruptedException','prophet',_binary '\0','RECOMMEND'),(125,'2025-04-24 14:53:03.719421','java.lang.InterruptedException','arima',_binary '\0','RECOMMEND'),(152,'2025-04-24 15:09:57.154773','{\"message\":\"xgb_timeseries time series model trained and saved.\"}\n','xgb_timeseries',_binary '','PREDICT'),(202,'2025-04-24 17:02:38.840334','{\n  \"message\": \"Content-based filtering model trained and saved.\"\n}\n','content',_binary '','RECOMMEND'),(252,'2025-04-28 08:33:41.680536','Connection refused: no further information: localhost/127.0.0.1:6000','content',_binary '\0','RECOMMEND'),(253,'2025-04-28 08:33:41.722489','Connection refused: no further information: localhost/127.0.0.1:6000','xgb_timeseries',_binary '\0','RECOMMEND'),(254,'2025-04-28 08:33:41.731487','Connection refused: no further information: localhost/127.0.0.1:6000','prophet',_binary '\0','RECOMMEND'),(255,'2025-04-28 08:33:41.737999','Connection refused: no further information: localhost/127.0.0.1:6000','arima',_binary '\0','RECOMMEND'),(256,'2025-04-28 08:33:41.745999','Connection refused: no further information: localhost/127.0.0.1:6000','sarimax',_binary '\0','RECOMMEND'),(257,'2025-04-28 08:33:41.752999','Connection refused: no further information: localhost/127.0.0.1:6000','collaborative',_binary '\0','RECOMMEND'),(258,'2025-04-28 08:33:41.759511','Connection refused: no further information: localhost/127.0.0.1:6000','svd',_binary '\0','RECOMMEND'),(259,'2025-04-28 08:33:41.766510','Connection refused: no further information: localhost/127.0.0.1:6000','xgb_classifier',_binary '\0','RECOMMEND'),(260,'2025-04-28 08:33:41.773510','Connection refused: no further information: localhost/127.0.0.1:6000','knn',_binary '\0','RECOMMEND'),(261,'2025-04-28 08:33:41.780511','Connection refused: no further information: localhost/127.0.0.1:6000','item2vec',_binary '\0','RECOMMEND'),(302,'2025-04-29 17:58:01.719594','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(303,'2025-04-29 17:59:16.225006','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(304,'2025-04-30 08:49:29.738824','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(305,'2025-04-30 08:51:11.534161','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(306,'2025-04-30 08:52:49.663970','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(307,'2025-04-30 08:56:00.623079','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(308,'2025-04-30 08:56:26.860725','xgb_timeseries모델 학습 요청','xgb_timeseries',_binary '','PREDICT'),(309,'2025-04-30 08:57:54.471003','prophet모델 학습 요청','prophet',_binary '','PREDICT'),(310,'2025-04-30 11:13:46.906582','content모델 학습 요청','content',_binary '','RECOMMEND'),(311,'2025-04-30 11:13:46.913157','xgb_timeseries모델 학습 요청','xgb_timeseries',_binary '','RECOMMEND'),(312,'2025-04-30 11:13:46.916672','prophet모델 학습 요청','prophet',_binary '','RECOMMEND'),(313,'2025-04-30 11:13:46.921263','arima모델 학습 요청','arima',_binary '','RECOMMEND'),(314,'2025-04-30 11:13:46.924897','sarimax모델 학습 요청','sarimax',_binary '','RECOMMEND'),(315,'2025-04-30 11:13:46.928731','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(316,'2025-04-30 11:13:46.935235','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(317,'2025-04-30 11:13:46.939981','xgb_classifier모델 학습 요청','xgb_classifier',_binary '','RECOMMEND'),(318,'2025-04-30 11:13:46.947941','knn모델 학습 요청','knn',_binary '','RECOMMEND'),(319,'2025-04-30 11:13:46.956784','item2vec모델 학습 요청','item2vec',_binary '','RECOMMEND'),(352,'2025-04-30 13:40:14.807884','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(353,'2025-04-30 13:53:27.673881','모델 학습 결과: success','collaborative',_binary '','RECOMMEND'),(402,'2025-05-02 09:12:39.103002','모델 학습 결과: success','arima',_binary '','PREDICT'),(452,'2025-05-02 09:24:13.737258','모델 학습 결과: success','sarimax',_binary '','PREDICT'),(502,'2025-05-08 14:28:46.105297','모델 학습 결과: success','content',_binary '','RECOMMEND'),(552,'2025-05-08 14:44:41.462907','모델 학습 결과: success','content',_binary '','RECOMMEND'),(602,'2025-05-09 08:29:05.764027','content모델 학습 요청','content',_binary '','RECOMMEND'),(603,'2025-05-09 08:29:06.574344','xgb_timeseries모델 학습 요청','xgb_timeseries',_binary '','RECOMMEND'),(604,'2025-05-09 08:29:06.605346','prophet모델 학습 요청','prophet',_binary '','RECOMMEND'),(605,'2025-05-09 08:29:06.623837','arima모델 학습 요청','arima',_binary '','RECOMMEND'),(606,'2025-05-09 08:29:06.637205','sarimax모델 학습 요청','sarimax',_binary '','RECOMMEND'),(607,'2025-05-09 08:29:06.650853','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(608,'2025-05-09 08:29:06.663858','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(609,'2025-05-09 08:29:06.677770','xgb_classifier모델 학습 요청','xgb_classifier',_binary '','RECOMMEND'),(610,'2025-05-09 08:29:06.691837','knn모델 학습 요청','knn',_binary '','RECOMMEND'),(611,'2025-05-09 08:29:06.703378','item2vec모델 학습 요청','item2vec',_binary '','RECOMMEND'),(652,'2025-05-13 08:57:36.434811','모델 학습 결과: success','content',_binary '','RECOMMEND'),(653,'2025-05-13 08:56:53.134540','xgb_timeseries모델 학습 요청','xgb_timeseries',_binary '','RECOMMEND'),(654,'2025-05-13 08:56:53.146540','prophet모델 학습 요청','prophet',_binary '','RECOMMEND'),(655,'2025-05-13 08:56:53.156544','arima모델 학습 요청','arima',_binary '','RECOMMEND'),(656,'2025-05-13 08:56:53.168539','sarimax모델 학습 요청','sarimax',_binary '','RECOMMEND'),(657,'2025-05-13 08:56:53.179544','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(658,'2025-05-13 08:56:53.189057','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(659,'2025-05-13 08:56:53.203056','xgb_classifier모델 학습 요청','xgb_classifier',_binary '','RECOMMEND'),(660,'2025-05-13 08:56:53.217249','knn모델 학습 요청','knn',_binary '','RECOMMEND'),(661,'2025-05-13 08:56:53.228643','item2vec모델 학습 요청','item2vec',_binary '','RECOMMEND'),(662,'2025-05-14 09:05:06.633633','content모델 학습 요청','content',_binary '','RECOMMEND'),(663,'2025-05-14 09:05:06.695067','xgb_timeseries모델 학습 요청','xgb_timeseries',_binary '','RECOMMEND'),(664,'2025-05-14 09:05:06.707234','prophet모델 학습 요청','prophet',_binary '','RECOMMEND'),(665,'2025-05-14 09:05:06.721832','arima모델 학습 요청','arima',_binary '','RECOMMEND'),(666,'2025-05-14 09:05:06.737157','sarimax모델 학습 요청','sarimax',_binary '','RECOMMEND'),(667,'2025-05-14 09:05:06.759696','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(668,'2025-05-14 09:05:06.770202','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(669,'2025-05-14 09:05:06.777619','xgb_classifier모델 학습 요청','xgb_classifier',_binary '','RECOMMEND'),(670,'2025-05-14 09:05:06.784625','knn모델 학습 요청','knn',_binary '','RECOMMEND'),(671,'2025-05-14 09:05:06.797640','item2vec모델 학습 요청','item2vec',_binary '','RECOMMEND'),(672,'2025-05-15 08:38:29.561661','content모델 학습 요청','content',_binary '','RECOMMEND'),(673,'2025-05-15 08:38:29.609988','xgb_timeseries모델 학습 요청','xgb_timeseries',_binary '','RECOMMEND'),(674,'2025-05-15 08:38:29.619566','prophet모델 학습 요청','prophet',_binary '','RECOMMEND'),(675,'2025-05-15 08:38:29.630685','arima모델 학습 요청','arima',_binary '','RECOMMEND'),(676,'2025-05-15 08:38:29.646557','sarimax모델 학습 요청','sarimax',_binary '','RECOMMEND'),(677,'2025-05-15 08:38:29.677566','collaborative모델 학습 요청','collaborative',_binary '','RECOMMEND'),(678,'2025-05-15 08:38:29.684551','svd모델 학습 요청','svd',_binary '','RECOMMEND'),(679,'2025-05-15 08:38:29.690654','xgb_classifier모델 학습 요청','xgb_classifier',_binary '','RECOMMEND'),(680,'2025-05-15 08:38:29.697050','knn모델 학습 요청','knn',_binary '','RECOMMEND'),(681,'2025-05-15 08:38:29.706671','item2vec모델 학습 요청','item2vec',_binary '','RECOMMEND');
/*!40000 ALTER TABLE `model_train_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_train_log_seq`
--

DROP TABLE IF EXISTS `model_train_log_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_train_log_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_train_log_seq`
--

LOCK TABLES `model_train_log_seq` WRITE;
/*!40000 ALTER TABLE `model_train_log_seq` DISABLE KEYS */;
INSERT INTO `model_train_log_seq` VALUES (751,1,9223372036854775806,1,50,0,0,0);
/*!40000 ALTER TABLE `model_train_log_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  `status` enum('CANCELLED','REVIEWED','DELIVERED','PENDING','PROCESSING','SHIPPED') DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,'2025-03-27 16:07:58.109199','2025-04-09 14:17:38.050199',2,1,2,2,'REVIEWED'),(2,'2025-04-01 11:10:19.045564','2025-04-09 14:18:37.857352',2,2,1,2,'REVIEWED'),(3,'2025-04-01 11:10:19.049563','2025-04-10 13:02:33.800806',1,2,3,2,'REVIEWED'),(4,'2025-04-02 09:20:16.182315','2025-04-03 17:26:32.426394',2,3,1,2,'REVIEWED'),(5,'2025-04-02 09:20:16.185315','2025-04-03 17:26:39.807421',1,3,3,2,'REVIEWED'),(6,'2025-04-02 09:25:14.767386','2025-04-02 09:25:14.767386',1,4,6,2,'PROCESSING'),(7,'2025-04-02 09:36:20.893152','2025-04-02 09:36:20.893152',2,5,4,2,'PROCESSING'),(8,'2025-04-02 09:42:05.005999','2025-04-02 09:42:05.005999',1,6,6,2,'PROCESSING'),(9,'2025-04-02 10:05:33.895650','2025-04-02 10:05:33.895650',1,7,5,2,'PROCESSING'),(10,'2025-04-04 16:09:56.161675','2025-04-10 13:05:24.612542',1,8,4,2,'DELIVERED'),(11,'2025-04-04 16:10:35.368710','2025-04-10 13:05:21.677863',1,9,1,2,'DELIVERED'),(12,'2025-04-04 16:10:35.370698','2025-04-10 13:05:18.917468',1,9,2,2,'DELIVERED'),(13,'2025-04-04 16:10:35.371701','2025-04-10 13:05:15.496471',5,9,6,2,'DELIVERED'),(14,'2025-04-04 17:42:09.972227','2025-04-10 13:05:11.652765',1,10,13,2,'DELIVERED'),(15,'2025-04-04 17:47:47.569524','2025-04-10 13:05:08.981680',2,11,3,2,'DELIVERED'),(16,'2025-04-04 17:47:47.572575','2025-04-10 13:05:05.165473',1,11,13,2,'DELIVERED'),(17,'2025-04-04 17:47:47.573584','2025-04-10 13:05:01.573829',2,11,5,2,'DELIVERED'),(18,'2025-04-04 17:47:47.573584','2025-04-10 13:04:58.804779',1,11,14,2,'DELIVERED'),(19,'2025-04-10 13:01:54.829865','2025-04-10 13:01:54.829865',1,12,10,8,'PROCESSING'),(20,'2025-04-10 13:02:20.772009','2025-04-10 13:02:20.772009',2,13,10,8,'PROCESSING'),(21,'2025-04-10 13:02:20.772994','2025-04-10 13:06:06.537506',1,13,4,2,'REVIEWED'),(22,'2025-04-10 13:03:43.605534','2025-04-10 13:03:43.605534',1,14,25,7,'PROCESSING'),(23,'2025-04-10 13:04:11.152015','2025-04-10 13:04:11.152015',1,15,25,7,'PROCESSING'),(24,'2025-04-10 13:04:11.154015','2025-04-10 13:04:11.154015',1,15,20,8,'PROCESSING');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `order_request` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `shopping_fee` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` int(11) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKpktxwhj3x9m4gth5ff6bkqgeb` (`member_id`),
  CONSTRAINT `FKpktxwhj3x9m4gth5ff6bkqgeb` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2025-03-27 16:07:58.076944','2025-03-27 16:07:58.076944','대전광역시 서구 둔산동',NULL,'알아서 찾아와주세요','010-6247-0557','테스트유저',0,'PROCESSING',40000000,1,NULL),(2,'2025-04-01 11:10:19.012563','2025-04-01 11:10:19.012563','테스트',NULL,'테스트','010-6247-0557','테스트',0,'PROCESSING',44000,1,NULL),(3,'2025-04-02 09:20:16.154319','2025-04-02 09:20:16.154319','테스트장바구니',NULL,'테스트 장바구니 입력','010-6247-0557','테스트유저',0,NULL,44000,1,NULL),(4,'2025-04-02 09:25:14.762397','2025-04-02 09:25:14.762397','대전광역시 서구 둔산동',NULL,'테스트 상품 주문','010-6247-0557','테스트유저',0,NULL,2000,1,NULL),(5,'2025-04-02 09:36:20.775835','2025-04-02 09:36:20.775835','대전광역시 서구 둔산동',NULL,'테스트상품주문','010-6247-0557','테스트',0,NULL,40000,1,NULL),(6,'2025-04-02 09:42:04.974837','2025-04-02 09:42:04.974837','대전광역시 서구 둔산동',NULL,'테스트','010-6247-0557','테스트유저',0,NULL,2000,1,NULL),(7,'2025-04-02 10:05:33.863155','2025-04-02 10:05:33.863155','대전광역시 서구 둔산동',NULL,'테스트','010-6247-0557','테스트유저',0,NULL,25000,1,NULL),(8,'2025-04-04 16:09:56.156170','2025-04-04 16:09:56.156170','대전광역시 서구 둔산동',NULL,'현관앞에 놓고 가주세요','010-6247-0557','테스트유저',0,NULL,20000,5,NULL),(9,'2025-04-04 16:10:35.367696','2025-04-04 16:10:35.367696','테스트주소',NULL,'테스트요청사항입니다','010-7777-7777','테스트유저11',0,NULL,20022000,5,NULL),(10,'2025-04-04 17:42:09.966241','2025-04-04 17:42:09.966241','테스트',NULL,'테스트입력','010-7777-7777','테스트',0,NULL,1000,5,NULL),(11,'2025-04-04 17:47:47.566575','2025-04-04 17:47:47.566575','테스트',NULL,'테스트입력','010-7777-7777','테스트',0,NULL,99000,5,NULL),(12,'2025-04-10 13:01:54.773254','2025-04-10 13:01:54.773254','테스트',NULL,'테스트','010-7777-7777','테스트',0,NULL,120000,1,'DIRECT'),(13,'2025-04-10 13:02:20.770994','2025-04-10 13:02:20.770994','테스트',NULL,'테스트','010-8888-8888','테스트',0,NULL,1440000,1,'CART'),(14,'2025-04-10 13:03:43.602535','2025-04-10 13:03:43.602535','테스트',NULL,'테스트','010-8888-8888','테스트',0,NULL,12000,1,'DIRECT'),(15,'2025-04-10 13:04:11.151025','2025-04-10 13:04:11.151025','테스트',NULL,'테스트','010-8888-8888','테스트',0,NULL,262000,1,'CART');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FKnx0ywvm0wv364mdn51jigfone` (`created_by`),
  KEY `FK917peq5noee6tp2hj1vuqac1a` (`modified_by`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FK917peq5noee6tp2hj1vuqac1a` FOREIGN KEY (`modified_by`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKnx0ywvm0wv364mdn51jigfone` FOREIGN KEY (`created_by`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2025-03-27 14:09:17.576664','2025-04-08 14:09:16.813084','테스트화장품입니다','노트북1',11200000,25,152,2,2),(2,'2025-03-27 14:13:19.525533','2025-04-08 14:09:28.959570','전자제품 테스트 입력','스마트폰1',1900000,9,155,2,2),(3,'2025-03-28 11:25:02.000304','2025-04-08 14:10:20.333754','테스트이미지상품등록테스트','헤드폰1',2150000,6,152,2,2),(4,'2025-03-28 11:33:14.480019','2025-04-10 13:02:20.773993','테스트이미지상품등록테스트','노트북2',1200000,6,152,2,2),(5,'2025-03-31 10:36:48.824463','2025-04-08 14:10:43.573494','테스트이미지상품등록테스트변경수정','스마트폰2',900000,9,154,2,2),(6,'2025-03-31 14:32:53.545778','2025-04-08 14:10:55.703889','테스트이미지상품등록','헤드폰2',50000,193,154,2,2),(7,'2025-04-02 17:09:54.544242','2025-04-08 14:12:49.930102','테스트화장품상품등록','물티슈2',20000,500,152,6,6),(8,'2025-04-02 17:10:18.471151','2025-04-08 14:12:59.122186','테스트상품등록122','물티슈3',320000,100,154,6,6),(9,'2025-04-02 17:21:42.006893','2025-04-08 14:13:20.922984','테스트상품222','휴지1',50000,10000,155,7,7),(10,'2025-04-02 17:22:18.319011','2025-04-10 13:02:20.773993','테스트상품11','운동화1',120000,47,153,8,8),(11,'2025-04-02 17:22:37.500166','2025-04-08 14:13:52.418754','테스트상품','청바지1',50000,100,152,8,8),(12,'2025-04-02 17:22:59.424336','2025-04-08 14:14:03.604344','테스트상품224','운동화2',20000,120,154,8,8),(13,'2025-04-02 17:37:28.924952','2025-04-08 14:11:15.595200','테스트상품등록1','볼펜1',2000,200000,152,2,2),(14,'2025-04-02 17:37:46.772657','2025-04-08 14:11:28.899917','테스트상품등록2','볼펜2',4000,5000,154,2,2),(15,'2025-04-02 17:38:03.110660','2025-04-08 14:11:43.494406','테스트상품등록3','볼펜3',12000,300,155,2,2),(16,'2025-04-02 17:38:23.285242','2025-04-08 14:12:06.533116','테스트상품등록4','가위1',3000,500,153,2,2),(17,'2025-04-02 17:39:31.085163','2025-04-08 14:12:19.896496','테스트 상품 등록 상품','물티슈1',120000,300,154,2,2),(18,'2025-04-08 14:15:29.004399','2025-04-08 14:15:29.004399','청바지2','청바지2',150000,500,156,8,8),(19,'2025-04-08 14:15:44.617393','2025-04-08 14:15:44.617393','코트1','코트1',2000000,2000000,156,8,8),(20,'2025-04-08 14:16:03.930732','2025-04-10 13:04:11.155016','코트2','코트2',250000,249999,156,8,8),(21,'2025-04-08 14:16:28.951828','2025-04-08 14:16:28.951828','코트3','코트3',8000000,8000000,156,8,8),(22,'2025-04-08 14:16:48.708900','2025-04-08 14:16:48.708900','가디건1','가디건1',100000,100000,156,8,8),(23,'2025-04-08 14:16:59.827362','2025-04-08 14:16:59.827362','가디건2','가디건2',150000,150000,156,8,8),(24,'2025-04-08 14:17:10.958242','2025-04-08 14:17:10.958242','가디건3','가디건3',80000,80000,156,8,8),(25,'2025-04-08 14:17:33.063570','2025-04-10 13:04:11.155016','핸드크림1','핸드크림1',12000,11998,152,7,7),(26,'2025-04-08 14:17:41.352090','2025-04-08 14:17:41.352090','선크림1','선크림1',30000,30000,152,7,7),(27,'2025-04-08 14:17:49.159479','2025-04-08 14:17:49.159479','핸드크림2','핸드크림2',50000,50000,152,7,7),(28,'2025-04-08 14:17:57.112742','2025-04-08 14:17:57.112742','선크림2','선크림2',22000,22000,152,7,7),(29,'2025-04-08 14:18:05.875533','2025-04-08 14:18:05.875533','핸드크림3','핸드크림3',56000,56000,152,7,7),(30,'2025-04-08 14:18:14.073699','2025-04-08 14:18:14.073699','선크림3','선크림3',10000,10000,152,7,7);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `content` varchar(1000) NOT NULL,
  `rating` int(11) NOT NULL CHECK (`rating` <= 5 and `rating` >= 0),
  `member_id` bigint(20) NOT NULL,
  `order_item_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FKk0ccx5i4ci2wd70vegug074w1` (`member_id`),
  KEY `FKcpceqmajrln2x7iqc4jua0hu1` (`order_item_id`),
  KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`),
  CONSTRAINT `FKcpceqmajrln2x7iqc4jua0hu1` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`order_item_id`),
  CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKk0ccx5i4ci2wd70vegug074w1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'2025-04-03 16:58:14.605459','2025-04-03 16:58:14.605459','별로입니다',4,1,1,2),(2,'2025-04-03 17:04:08.983848','2025-04-03 17:04:08.983848','잘 받았습니다',5,1,2,1),(4,'2025-04-03 17:26:32.374868','2025-04-03 17:26:32.374868','테스트 리뷰작성',5,1,4,1),(5,'2025-04-03 17:26:39.803910','2025-04-03 17:26:39.803910','테스트 리뷰작성2',5,1,5,3),(6,'2025-04-09 14:17:37.999895','2025-04-09 14:17:37.999895','테스트 리뷰 입력입니다',5,1,1,2),(7,'2025-04-09 14:18:37.822799','2025-04-09 14:18:37.822799','다음에도 구입할게요',4,1,2,1),(8,'2025-04-10 13:02:33.796789','2025-04-10 13:02:33.796789','테스트 리뷰11111',5,1,3,3),(9,'2025-04-10 13:06:06.534508','2025-04-10 13:06:06.534508','최고네요',5,1,21,4);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'shoppingmall'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-02 13:10:36
