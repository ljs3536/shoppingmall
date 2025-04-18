/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.5.28-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: shoppingmall
-- ------------------------------------------------------
-- Server version	10.5.28-MariaDB-ubu2004

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Sequence structure for `category_seq`
--

DROP SEQUENCE IF EXISTS `category_seq`;
CREATE SEQUENCE `category_seq` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 50 nocache nocycle ENGINE=InnoDB;
DO SETVAL(`category_seq`, 101, 0);

--
-- Sequence structure for `model_train_log_seq`
--

DROP SEQUENCE IF EXISTS `model_train_log_seq`;
CREATE SEQUENCE `model_train_log_seq` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 50 nocache nocycle ENGINE=InnoDB;
DO SETVAL(`model_train_log_seq`, 101, 0);

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FKix170nytunweovf2v9137mx2o` (`member_id`),
  CONSTRAINT `FKix170nytunweovf2v9137mx2o` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'전자제품'),(2,'생활용품'),(3,'패션'),(4,'화장품');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'2025-04-18 01:06:10.226075','2025-04-18 01:06:10.226075','702f86e7-436b-432b-844b-cffd09fb2078_640px-Rotluchs2.jpg','/upload/images/PRODUCT/1','image/jpeg','PRODUCT','',1),(2,'2025-04-18 01:06:34.209610','2025-04-18 01:06:34.209610','1f8c07dc-8bbf-4d03-b242-038ed39808ee_201604131460701467_1.jpg','/upload/images/PRODUCT/2','image/jpeg','PRODUCT','',2),(3,'2025-04-18 01:07:15.489241','2025-04-18 01:07:15.489241','80958d16-7793-4266-9f5e-dbc6932add25_20180831112400062756.jpg','/upload/images/PRODUCT/3','image/jpeg','PRODUCT','',3),(4,'2025-04-18 01:07:35.190176','2025-04-18 01:07:35.190176','9e5bacf3-f97e-4463-bf3a-c282cfd934f4_2018083111240156567.jpg','/upload/images/PRODUCT/4','image/jpeg','PRODUCT','',4),(5,'2025-04-18 01:07:55.881111','2025-04-18 01:07:55.881111','352d9646-8a23-4f53-bf18-f365ebe3f54a_BIMGAM0000376668_20211105112431304132.jpg','/upload/images/PRODUCT/5','image/jpeg','PRODUCT','',5),(6,'2025-04-18 01:08:16.915252','2025-04-18 01:08:16.915252','0d420370-ac22-4265-b835-a9585911574a_BIMGAM0000376790_20211105162454042413.jpg','/upload/images/PRODUCT/6','image/jpeg','PRODUCT','',6),(7,'2025-04-18 01:08:35.425042','2025-04-18 01:08:35.425042','4fcc9827-c3f0-48fe-9ad9-7a1c88e29386_BIMGMO0000002334.jpg','/upload/images/PRODUCT/7','image/jpeg','PRODUCT','',7),(8,'2025-04-18 01:08:52.691371','2025-04-18 01:08:52.691371','c439acad-b956-4fdb-b489-c25319206f6c_BIMGRP0000364504_2020110413241935_700hq7951i.jpg','/upload/images/PRODUCT/8','image/jpeg','PRODUCT','',8),(9,'2025-04-18 01:09:12.902861','2025-04-18 01:09:12.902861','30c492c5-9580-4268-ad98-75b72ad0f2cb_BIMGRP0000376795_20211105162459372975.jpg','/upload/images/PRODUCT/9','image/jpeg','PRODUCT','',9),(10,'2025-04-18 01:09:43.308609','2025-04-18 01:09:43.308609','a7fe7d0f-75b1-4781-ac9b-9fff25e391a7_images (1).jpg','/upload/images/PRODUCT/10','image/jpeg','PRODUCT','',10),(11,'2025-04-18 01:18:29.806252','2025-04-18 01:18:29.806252','ab33d474-ece0-46d9-b967-f890ec8af037_images.jpg','/upload/images/PRODUCT/11','image/jpeg','PRODUCT','',11),(12,'2025-04-18 01:20:09.252456','2025-04-18 01:20:09.252456','98826ae0-29b5-4a11-8cba-018737cbc5a2_news-p.v1.20220810.813acc63f5eb4b34bb0c171e48777c91_P1.jpg','/upload/images/PRODUCT/12','image/jpeg','PRODUCT','',12),(13,'2025-04-18 01:20:33.622976','2025-04-18 01:20:33.622976','73b3b4dc-d9e2-46a9-8bf7-1ed63c974f4a_nYC8N9SjHPUQ_ZJy5fwaUwok5lGkeLCrZt_X8t-OMU7ut36_I04sfrkExVBegCejJYthGmALmZAuYBuLEJdRfA.webp','/upload/images/PRODUCT/13','image/webp','PRODUCT','',13),(14,'2025-04-18 04:06:17.727929','2025-04-18 04:06:17.727929','b9b4d8de-d237-41a8-a559-f268b773807a_pop_24_01.jpg','/upload/images/PRODUCT/14','image/jpeg','PRODUCT','',14),(15,'2025-04-18 04:06:41.036597','2025-04-18 04:06:41.036597','2dfbaf30-78ac-4beb-a2a7-69ccf446f7dc_201604131460701467_1.jpg','/upload/images/PRODUCT/15','image/jpeg','PRODUCT','',15),(16,'2025-04-18 04:07:03.199457','2025-04-18 04:07:03.199457','ba2f53a0-18f2-4f96-bd28-8717c0b77d68_20180831112400062756.jpg','/upload/images/PRODUCT/16','image/jpeg','PRODUCT','',16),(17,'2025-04-18 04:07:27.579607','2025-04-18 04:07:27.579607','f3494093-0c3c-4d92-827c-1da12cd445c5_2018083111240156567.jpg','/upload/images/PRODUCT/17','image/jpeg','PRODUCT','',17),(18,'2025-04-18 04:07:53.497757','2025-04-18 04:07:53.497757','4c9c8a9c-618b-4fbb-a968-7bc877e48178_BIMGAM0000376668_20211105112431304132.jpg','/upload/images/PRODUCT/18','image/jpeg','PRODUCT','',18),(19,'2025-04-18 04:08:15.017164','2025-04-18 04:08:15.017164','637a9b5b-67b8-49df-ab9b-adabca085dbd_BIMGMO0000002334.jpg','/upload/images/PRODUCT/19','image/jpeg','PRODUCT','',19),(20,'2025-04-18 04:08:34.977587','2025-04-18 04:08:34.977587','3ee2437a-c2eb-4076-9e81-a186fe41cc4d_pop_24_02.jpg','/upload/images/PRODUCT/20','image/jpeg','PRODUCT','',20),(21,'2025-04-18 04:09:14.477867','2025-04-18 04:09:14.477867','aa3c91dd-a1e7-40c4-97f6-14074d50dc94_BIMGRP0000364504_2020110413241935_700hq7951i.jpg','/upload/images/PRODUCT/21','image/jpeg','PRODUCT','',21),(22,'2025-04-18 04:09:36.328320','2025-04-18 04:09:36.328320','331bd766-6f89-4826-bbfe-418873723f4d_BIMGRP0000376795_20211105162459372975.jpg','/upload/images/PRODUCT/22','image/jpeg','PRODUCT','',22),(23,'2025-04-18 04:09:58.269245','2025-04-18 04:09:58.269245','c8f2959d-3dcc-4841-a33d-37a27dc86b3f_images.jpg','/upload/images/PRODUCT/23','image/jpeg','PRODUCT','',23),(24,'2025-04-18 04:10:20.113369','2025-04-18 04:10:20.113369','d0e41aae-ad5b-4d5a-b3fa-420310039e84_다운로드.png','/upload/images/PRODUCT/24','image/png','PRODUCT','',24),(25,'2025-04-18 04:10:37.588749','2025-04-18 04:10:37.588749','453f8c7b-0821-4492-9636-89cee90f38bf_다운로드.jpg','/upload/images/PRODUCT/25','image/jpeg','PRODUCT','',25),(26,'2025-04-18 04:10:55.179094','2025-04-18 04:10:55.179094','7d59bf02-5dd6-4873-863a-ebb506efc50c_unnamed.jpg','/upload/images/PRODUCT/26','image/jpeg','PRODUCT','',26),(27,'2025-04-18 04:11:13.139281','2025-04-18 04:11:13.139281','0ee01466-8aee-4316-8b1d-f8b48e63cd81_unnamed (1).jpg','/upload/images/PRODUCT/27','image/jpeg','PRODUCT','',27),(28,'2025-04-18 04:11:32.038601','2025-04-18 04:11:32.038601','f17974e8-b576-444d-9269-e12f69f4e49b_Prosopocoilus_astacoides_(7271483300).jpg','/upload/images/PRODUCT/28','image/jpeg','PRODUCT','',28),(29,'2025-04-18 04:11:51.680773','2025-04-18 04:11:51.680773','1b42d424-49e2-4ad2-baa2-de219146c731_pop_guide.jpg','/upload/images/PRODUCT/29','image/jpeg','PRODUCT','',29),(30,'2025-04-18 04:12:07.642795','2025-04-18 04:12:07.642795','e7bdb0b8-1f9f-4af4-b415-07e2c930c6e4_2018083111240156567.jpg','/upload/images/PRODUCT/30','image/jpeg','PRODUCT','',30);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `cell_no` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `login_id` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `real_address` varchar(255) NOT NULL,
  `region` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','BUYER','SELLER') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UKenfm5patwjqulw8k4wwuo6f60` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2025-04-17 02:15:33.421582','2025-04-17 02:15:33.421582',33,'010-6247-0557','ljs@whisker.kr','남','test','테스트관리자','$2a$10$4BBI0z9KrXF46nQ/ZoZfJucFzN/i01/a0CRsDdWeX2CUd0qR7ESYi','서울 중구','서울','ADMIN','테스트관리자'),(2,'2025-04-17 07:17:53.992685','2025-04-17 07:17:53.992685',20,'010-7895-4114','rrrr@naver.com','여','testseller1','테스트판매자1','$2a$10$MlWksVnr0ay598McZmtJA.RIipphaN48ApT9n6VJc.vOuw2Ppc7RC','대전광역시 서구 중앙동로 133','대전','SELLER','테스트판매자1'),(3,'2025-04-17 07:18:37.028452','2025-04-17 07:18:37.028452',50,'010-8879-6665','yhtt66@naver.com','남','testseller11','테스트판매자11','$2a$10$63UBcPJek9uA0I9kju9gMewmih/a2MMZsJdCEPx8EpT2gGkQm9Lju','광주광역시 중구','광주','SELLER','테스트판매자11'),(4,'2025-04-17 07:38:49.386380','2025-04-17 07:38:49.386380',14,'010-8879-6665','rqwhwrh@naver.com','남','testuser1','테스트구매자1','$2a$10$zp6/ASWmRzn/4eyYD3mU0.beImC2fX.GFfjG2hTCs/G1h/UhC92Vy','강원 강릉시 하슬라로 15 고속버스터미널','강릉','BUYER','테스트구매자1'),(5,'2025-04-18 01:04:23.665463','2025-04-18 01:04:23.665463',28,'010-9872-5547','ggrr4@naver.com','남','testseller12','테스트판매자12','$2a$10$0bS2QNQjTupZkfhsZc0IKeWTuZZfoQBNNV765ohc757lDq.P7SBUu','광주광역시 중구','광주','SELLER','테스트판매자12'),(6,'2025-04-18 01:05:19.056480','2025-04-18 01:05:19.056480',64,'010-7854-8887','etfffv@naver.com','여','testseller13','테스트판매자13','$2a$10$NWGLUvT4OsjvWzi0.P7N.OgEjcvVexKaQKbsU4UJMNIhUjdYRAXYe','서울특별시 광진구','서울','SELLER','테스트판매자13');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_config`
--

DROP TABLE IF EXISTS `model_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_config` (
  `model_config_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `model_name` varchar(255) NOT NULL,
  `model_type` enum('PREDICT','RECOMMEND') NOT NULL,
  PRIMARY KEY (`model_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_config`
--

LOCK TABLES `model_config` WRITE;
/*!40000 ALTER TABLE `model_config` DISABLE KEYS */;
INSERT INTO `model_config` VALUES (1,'2025-04-17 07:39:58.167722','2025-04-17 07:40:53.705616','','content추천모델','content','RECOMMEND'),(2,'2025-04-17 07:40:12.880410','2025-04-17 07:40:12.880410','\0','collaborative추천모델','collaborative','RECOMMEND'),(3,'2025-04-17 07:40:21.398899','2025-04-17 07:40:21.398899','\0','svd추천모델','svd','RECOMMEND'),(4,'2025-04-17 07:40:35.361525','2025-04-17 07:40:35.361525','\0','xgb_classifier추천모델','xgb_classifier','RECOMMEND'),(5,'2025-04-17 07:40:44.082767','2025-04-17 07:40:44.082767','\0','knn추천모델','knn','RECOMMEND'),(6,'2025-04-17 07:40:53.392934','2025-04-17 07:40:53.392934','\0','item2vec추천모델','item2vec','RECOMMEND'),(7,'2025-04-17 07:41:21.163018','2025-04-17 07:41:21.163018','\0','xgb_timeseries예측모델','xgb_timeseries','PREDICT'),(8,'2025-04-17 07:41:27.590628','2025-04-17 07:41:27.590628','\0','prophet예측모델','prophet','PREDICT'),(9,'2025-04-17 07:41:35.310003','2025-04-17 07:41:35.310003','\0','arima예측모델','arima','PREDICT'),(10,'2025-04-17 07:41:43.410987','2025-04-17 07:41:43.410987','\0','sarimax예측모델','sarimax','PREDICT');
/*!40000 ALTER TABLE `model_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_train_log`
--

DROP TABLE IF EXISTS `model_train_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_train_log` (
  `id` bigint(20) NOT NULL,
  `executed_at` datetime(6) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `model_name` varchar(255) DEFAULT NULL,
  `success` bit(1) NOT NULL,
  `type` enum('PREDICT','RECOMMEND') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_train_log`
--

LOCK TABLES `model_train_log` WRITE;
/*!40000 ALTER TABLE `model_train_log` DISABLE KEYS */;
INSERT INTO `model_train_log` VALUES (1,'2025-04-18 03:04:45.927911','{\n  \"message\": \"Content-based filtering model trained and saved.\"\n}\n','content','','RECOMMEND'),(2,'2025-04-18 03:04:52.635366','{\n  \"message\": \"Collaborative filtering model trained and saved.\"\n}\n','collaborative','','RECOMMEND'),(3,'2025-04-18 03:04:58.126185','{\n  \"message\": \"SVD recommendation model trained and saved.\"\n}\n','svd','','RECOMMEND');
/*!40000 ALTER TABLE `model_train_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  `status` enum('CANCELLED','DELIVERED','PENDING','PROCESSING','REVIEWED','SHIPPED') DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date_time` datetime(6) DEFAULT NULL,
  `modified_date_time` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `order_request` varchar(255) DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `shopping_fee` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKpktxwhj3x9m4gth5ff6bkqgeb` (`member_id`),
  CONSTRAINT `FKpktxwhj3x9m4gth5ff6bkqgeb` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2025-04-18 01:06:10.211633','2025-04-18 01:06:10.211633','노트북1','노트북1',11200000,50,1,2,2),(2,'2025-04-18 01:06:34.206631','2025-04-18 01:06:34.206631','스마트폰1','스마트폰1',1900000,100,1,2,2),(3,'2025-04-18 01:07:15.482489','2025-04-18 01:07:15.482489','헤드폰1','헤드폰1',2150000,20,1,2,2),(4,'2025-04-18 01:07:35.158579','2025-04-18 01:07:35.158579','노트북2','노트북2',1200000,30,1,2,2),(5,'2025-04-18 01:07:55.871059','2025-04-18 01:07:55.871059','스마트폰2','스마트폰2',900000,500,1,2,2),(6,'2025-04-18 01:08:16.871518','2025-04-18 01:08:16.871518','헤드폰2','헤드폰2',50000,80,1,2,2),(7,'2025-04-18 01:08:35.421010','2025-04-18 01:08:35.421010','볼펜1','볼펜1',2000,500,2,2,2),(8,'2025-04-18 01:08:52.687144','2025-04-18 01:08:52.687144','볼펜2','볼펜2',4000,1000,2,2,2),(9,'2025-04-18 01:09:12.898763','2025-04-18 01:09:12.898763','볼펜3','볼펜3',12000,500,2,2,2),(10,'2025-04-18 01:09:43.304778','2025-04-18 01:09:43.304778','가위1','가위1',3000,3800,2,2,2),(11,'2025-04-18 01:18:29.802800','2025-04-18 01:18:29.802800','물티슈1','물티슈1',120000,80,1,2,2),(12,'2025-04-18 01:20:09.249283','2025-04-18 01:20:09.249283','물티슈2','물티슈2',20000,80,2,3,3),(13,'2025-04-18 01:20:33.620581','2025-04-18 01:20:33.620581','물티슈3','물티슈3',320000,80,2,3,3),(14,'2025-04-18 04:06:17.671250','2025-04-18 04:06:17.671250','휴지1','휴지1',50000,500,2,5,5),(15,'2025-04-18 04:06:41.031139','2025-04-18 04:06:41.031139','핸드크림1','핸드크림1',12000,120,4,5,5),(16,'2025-04-18 04:07:03.190412','2025-04-18 04:07:03.190412','선크림1','선크림1',30000,300,4,5,5),(17,'2025-04-18 04:07:27.551217','2025-04-18 04:07:27.551217','핸드크림2','핸드크림2',50000,500,4,5,5),(18,'2025-04-18 04:07:53.491860','2025-04-18 04:07:53.491860','선크림2','선크림2',22000,220,4,5,5),(19,'2025-04-18 04:08:15.012672','2025-04-18 04:08:15.012672','핸드크림3','핸드크림3',56000,60,4,5,5),(20,'2025-04-18 04:08:34.966955','2025-04-18 04:08:34.966955','선크림3','선크림3',10000,1000,4,5,5),(21,'2025-04-18 04:09:14.466027','2025-04-18 04:09:14.466027','운동화1','운동화1',120000,120,3,6,6),(22,'2025-04-18 04:09:36.321002','2025-04-18 04:09:36.321002','청바지1','청바지1',50000,500,3,6,6),(23,'2025-04-18 04:09:58.265936','2025-04-18 04:09:58.265936','운동화2','운동화2',20000,200,3,6,6),(24,'2025-04-18 04:10:20.109378','2025-04-18 04:10:20.109378','청바지2','청바지2',150000,150,3,6,6),(25,'2025-04-18 04:10:37.584089','2025-04-18 04:10:37.584089','코트1','코트1',2000000,200,3,6,6),(26,'2025-04-18 04:10:55.175811','2025-04-18 04:10:55.175811','코트2','코트2',250000,250,3,6,6),(27,'2025-04-18 04:11:13.135596','2025-04-18 04:11:13.135596','코트3','코트3',8000000,80,3,6,6),(28,'2025-04-18 04:11:32.029954','2025-04-18 04:11:32.029954','가디건1','가디건1',100000,100,3,6,6),(29,'2025-04-18 04:11:51.668579','2025-04-18 04:11:51.668579','가디건2','가디건2',150000,150,3,6,6),(30,'2025-04-18 04:12:07.629774','2025-04-18 04:12:07.629774','가디건3','가디건3',80000,500,3,6,6);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-18  4:18:44
