-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: inventory_management_system
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category_table`
--

DROP TABLE IF EXISTS `category_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_table` (
  `category_id` varchar(10) NOT NULL,
  `category_name` varchar(45) DEFAULT NULL,
  `data_structure` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_table`
--

LOCK TABLES `category_table` WRITE;
/*!40000 ALTER TABLE `category_table` DISABLE KEYS */;
INSERT INTO `category_table` VALUES ('001','Beverages','s'),('002','Bread/Bakery','s'),('003','Canned/Jarred Goods','s'),('004','Dairy','s'),('005','Dry/Baking Goods','q'),('006','Frozen Foods','q'),('007','Meat','q'),('008','Produce','l'),('009','Cleaners','l'),('010','Paper Goods','l'),('011','Personal Care','l'),('226','Vegetables','l'),('463','stationary','q'),('525','Snacks','q'),('592','Fruits','q'),('836','Sweeties','s');
/*!40000 ALTER TABLE `category_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_table`
--

DROP TABLE IF EXISTS `goods_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_table` (
  `goods_id` varchar(10) NOT NULL,
  `goods_name` varchar(45) DEFAULT NULL,
  `buying_price` double DEFAULT NULL,
  `selling_price` double DEFAULT NULL,
  `profit` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `datetime_added` datetime DEFAULT NULL,
  `category_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category_table` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_table`
--

LOCK TABLES `goods_table` WRITE;
/*!40000 ALTER TABLE `goods_table` DISABLE KEYS */;
INSERT INTO `goods_table` VALUES ('123','All-purpose',12.99,13.89,1039.2,60,'2023-03-20 18:28:21','009'),('126','Fornty',12.98,15.99,1168.2,12,'2023-03-29 11:25:17','003'),('153','Soda',11,11.09,121,80,'2023-03-27 07:51:23','001'),('229','Coffee',99,9,1089,19,'2023-03-27 10:29:06','001'),('272','Shampoo',8,8,48,2,'2023-03-20 18:13:06','011'),('407','Cola Cola',8.9,88,890,94,'2023-03-19 10:54:17','001'),('453','Laundry detergent',22.99,25.99,436.80999999999995,16,'2023-03-20 18:23:49','009'),('456','Bel Cola',2.5,3,30,152,'2023-03-19 13:11:34','001'),('467','Frozen Beef',69.87,70.89,13974,188,'2023-03-29 11:23:34','006'),('502','Malt',5,6,400,75,'2023-03-19 10:58:24','001'),('565','Cabbage',12.9,25.12,1548,44,'2023-03-31 07:18:19','226'),('581','Sandwich bag',2.99,3.2,35.88,10,'2023-03-20 18:19:22','010'),('615','Sporting Waves Cream',90,99,90000,416,'2023-03-27 10:31:26','011'),('682','Pencils',2.39,3.3,478,200,'2023-03-31 18:36:07','463'),('744','Center Filled',0.1,0.2,100,1000,'2023-04-04 09:14:01','836'),('754','Ideal milk',8,10,400,50,'2023-03-31 14:30:36','004'),('759','Ginger Bread',45.21,50.22,30.059998,20,'2023-04-03 14:44:03','002'),('866','exercise book',1.21,2.88,167,100,'2023-04-04 08:29:01','463'),('933','Beef',13.99,14.45,139.9,154,'2023-03-19 13:10:35','007'),('948','Tomtom',12.96,18.12,1296,62,'2023-03-31 07:17:47','836'),('949','Orange',1.55,2.77,344.1,66,'2023-03-29 13:45:15','592'),('953','Pear',3.99,4.5,11.97,63,'2023-03-29 14:04:59','592'),('981','Mango',4.7,5.98,141,5,'2023-03-29 13:47:01','592');
/*!40000 ALTER TABLE `goods_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(55) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `date_registered` date DEFAULT NULL,
  `telephone_number` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('111827','Eric Asomani Ofori','12345','Admin','Male','2023-03-31','0203950577'),('123456','Miki Johnson','work','Non-Admin','Male','2023-03-12','2321356785'),('152568','Alexenda Baiden','12345','Non-Admin','Male','2023-03-12','9898754321'),('160991','Lorreta Addo','12345','Non-Admin','Female','2023-03-06','9987331244'),('202378','John Cole','12345','Non-Admin','Male','2023-03-11','222113543'),('2222','Jessica Gogovi','js','Admin','Female','2023-03-01','897643214'),('2468','Data Structure','structures','Admin','Male','2023-04-06','1010100101'),('331679','Nana Adjoa Bentum','nana','Non-Admin','Female','2023-03-12','22221353235'),('412975','Eric Hoddie','12345','Non-Admin','Male','2023-03-13','2222222222222'),('413940','BEREWONO JOHN KYEENAATUO','12345','Admin','Male','2023-04-03','0540364164'),('425996','Emmanuel Adjei','12345','Admin','Male','2023-04-03','0540791479'),('539158','@MrQuamina','12345','Non-Admin','Male','2023-03-12','3456781290'),('582160','Greenhood Vanessa','12345','Non-Admin','Female','2023-03-12','2123456785'),('590588','Douglas Costa','12345','Non-Admin','Male','2023-03-12','1902344236'),('623487','Elon James','elon','Admin','Male','2022-12-31','0110011100'),('631407','Micheal Hoddie','12345','Non-Admin','Male','2023-03-12','1234590432'),('680699','Richmond Wick','12345','Non-Admin','Male','2023-03-01','0811123289'),('712597','Oscar Jnr','12345','Admin','Male','2023-03-16','11111111'),('739573','Scott McColl','scott','Non-Admin','Male','2023-03-11','989876747'),('747093','Stephen Commodore','12345','Non-Admin','Male','2023-03-12','3129087009'),('801498','Zidane Sani','12345','Non-Admin','Male','2023-03-28','3290876451'),('8642','vendor','vendor','Non-Admin','Female','2023-03-19','523534523'),('873144','Akaadom','12345','Admin','Male','2023-03-26','0450791479'),('889126','Jomphia Essuman','2727','Admin','Male','2023-03-18','999999999'),('919040','John Cartor','12345','Non-Admin','Male','2023-03-18','0999999912'),('937073','Christian ChatGTP','12345','Non-Admin','Male','2023-03-30','7778654321'),('945654','Stephen Commodore','12345','Non-Admin','Male','2023-04-03','0552823554'),('950940','Rose Rosa','12345','Non-Admin','Female','2023-03-12','908765432');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_issued_goods`
--

DROP TABLE IF EXISTS `view_issued_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view_issued_goods` (
  `user_id` varchar(10) DEFAULT NULL,
  `receipt_id` varchar(10) NOT NULL,
  `goods_id` varchar(10) DEFAULT NULL,
  `datetime_of_good_sold` datetime DEFAULT NULL,
  `quantity_bought` int DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `client_name` varchar(45) DEFAULT NULL,
  KEY `user_id_idx` (`user_id`),
  KEY `goods_id_idx` (`goods_id`),
  CONSTRAINT `goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods_table` (`goods_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_issued_goods`
--

LOCK TABLES `view_issued_goods` WRITE;
/*!40000 ALTER TABLE `view_issued_goods` DISABLE KEYS */;
INSERT INTO `view_issued_goods` VALUES ('2222','79988','759','2023-03-29 14:49:05',2,198,'John Forson'),('2222','79988','153','2023-03-29 14:49:05',6,66.54,'John Forson'),('2222','79988','126','2023-03-29 14:49:05',88,1407.12,'John Forson'),('2222','90201','467','2023-03-29 17:18:35',8,567.12,'Eric Forson'),('2222','90201','949','2023-03-29 17:18:35',80,221.6,'Eric Forson'),('2222','99846','126','2023-03-29 20:15:53',10,159.9,'Eric Jnr'),('2222','25334','949','2023-03-30 03:15:57',50,138.5,'Jomphisco Essuman'),('2222','25334','953','2023-03-30 03:15:57',20,90,'Jomphisco Essuman'),('2222','25334','981','2023-03-30 03:15:57',15,89.7,'Jomphisco Essuman'),('123456','14808','126','2023-03-30 03:24:35',8,127.92,'Emma Adjei'),('123456','14808','759','2023-03-30 03:24:36',2,198,'Emma Adjei'),('123456','14808','456','2023-03-30 03:24:36',15,45,'Emma Adjei'),('123456','84240','467','2023-03-30 03:27:39',2,141.78,'Emma Adjei'),('123456','84240','123','2023-03-30 03:27:39',15,208.35,'Emma Adjei'),('123456','84240','272','2023-03-30 03:27:39',3,24,'Emma Adjei'),('123456','84240','229','2023-03-30 03:27:40',10,90,'Emma Adjei'),('123456','84240','949','2023-03-30 03:27:40',16,44.32,'Emma Adjei'),('123456','84240','953','2023-03-30 03:27:40',5,22.5,'Emma Adjei'),('123456','84240','615','2023-03-30 03:27:40',5,495,'Emma Adjei'),('123456','84240','581','2023-03-30 03:27:40',1,3.2,'Emma Adjei'),('123456','84240','456','2023-03-30 03:27:41',10,30,'Emma Adjei'),('2222','33186','948','2023-03-31 07:19:49',20,362.4,'Ebenezer Ogoe'),('2222','33186','565','2023-03-31 07:19:50',25,628,'Ebenezer Ogoe'),('2222','76895','953','2023-03-31 07:28:58',2,9,'Jason Scott'),('2222','76895','949','2023-03-31 07:28:58',2,5.54,'Jason Scott'),('2222','76895','456','2023-03-31 07:28:58',2,6,'Jason Scott'),('2222','57880','453','2023-03-31 07:29:45',3,77.97,'Jason Scott'),('2222','57880','123','2023-03-31 07:29:45',5,69.45,'Jason Scott'),('2222','57880','981','2023-03-31 07:29:45',6,35.88,'Jason Scott'),('2222','62871','126','2023-03-31 07:30:39',2,31.98,'Jason Scott'),('2222','62871','153','2023-03-31 07:30:39',3,33.27,'Jason Scott'),('2222','62871','467','2023-03-31 07:30:39',2,141.78,'Jason Scott'),('2222','62871','502','2023-03-31 07:30:39',5,30,'Jason Scott'),('2222','62871','615','2023-03-31 07:30:40',2,198,'Jason Scott'),('2222','62871','581','2023-03-31 07:30:40',1,3.2,'Jason Scott'),('2222','66797','126','2023-03-31 08:55:59',1,15.99,'Eric James'),('2222','66797','153','2023-03-31 08:55:59',1,11.09,'Eric James'),('2222','66797','272','2023-03-31 08:55:59',1,8,'Eric James'),('2222','66797','229','2023-03-31 08:56:00',1,9,'Eric James'),('2222','66797','407','2023-03-31 08:56:00',1,88,'Eric James'),('2222','66797','565','2023-03-31 08:56:00',1,25.12,'Eric James'),('2222','66797','759','2023-03-31 08:56:00',1,99,'Eric James'),('2222','66797','948','2023-03-31 08:56:00',1,18.12,'Eric James'),('2222','66797','615','2023-03-31 08:56:01',10,990,'Eric James'),('2222','91716','565','2023-03-31 12:43:37',50,1256,'Jomphia Essuman'),('2222','91716','933','2023-03-31 12:43:39',5,72.25,'Jomphia Essuman'),('2222','91716','759','2023-03-31 12:43:40',1,99,'Jomphia Essuman'),('2222','91716','948','2023-03-31 12:43:40',10,181.2,'Jomphia Essuman'),('2222','53856','153','2023-03-31 14:14:56',109,1208.81,'Adams'),('2222','51985','126','2023-03-31 14:15:22',67,1071.33,'Adams'),('2222','51985','153','2023-03-31 14:15:23',2,22.18,'Adams'),('2222','51985','933','2023-03-31 14:15:23',1,14.45,'Adams'),('2222','51985','981','2023-03-31 14:15:23',9,53.82,'Adams'),('123456','53282','126','2023-03-31 14:48:20',2,31.98,''),('123456','53282','153','2023-03-31 14:48:20',2,22.18,''),('123456','53282','759','2023-03-31 14:48:21',9,891,''),('2222','92345','866','2023-03-31 17:36:52',10,299.9,'Solomon Eshun'),('2222','92345','153','2023-03-31 17:36:52',1,11.09,'Solomon Eshun'),('2222','92345','948','2023-03-31 17:36:52',7,126.84,'Solomon Eshun'),('2222','92345','407','2023-03-31 17:36:52',5,440,'Solomon Eshun'),('2222','13040','153','2023-04-01 00:02:27',7,77.63,''),('2222','52289','272','2023-04-03 13:21:37',3,24,''),('2222','30547','272','2023-04-03 13:22:58',1,8,'Abi'),('2222','93459','615','2023-04-04 08:43:55',600,59400,'Steve Morre'),('2222','14599','615','2023-04-04 09:08:55',600,59400,'Steve Morre'),('889126','95620','123','2023-04-05 22:13:16',121,1680.69,'Wkkkkk'),('889126','95620','272','2023-04-05 22:13:17',2,16,'Wkkkkk');
/*!40000 ALTER TABLE `view_issued_goods` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-06 23:23:33
