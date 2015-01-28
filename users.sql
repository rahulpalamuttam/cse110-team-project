-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: localhost    Database: telecom
-- ------------------------------------------------------
-- Server version	5.6.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `password` varchar(60) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `reg_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'jjl112@ucsd.edu','$2y$12$64W7Q59WLgY48JJa6azysucK1bkf6tbcs0zmve8HKndrSFeScLs6m','Lee','Jeongjoon','2015-01-21'),(3,'test@ucsd.edu','$2y$12$UGzPW.1ghE/lKMemUU8hqe35VdQsSrAa9MOXMLAk6e5SWnkSILxSW','Test','User','2015-01-21'),(4,'hello@ucsd.edu','$2y$12$uYtlQxmgRcUfC5HZiw0xN.ZIQhqJYXHyopF7V2GNxyWB/5sSZljJC','hello','hello','2015-01-21'),(5,'tests@ucsd.edu','$2y$12$iZxHXZfM8m8SY27yCM47Q.pU4taMbAvfyCCAtpbzCmAa7ZQUEd4HW','test','user','2015-01-21'),(6,'email@ucsd.edu','$2y$12$OOuvSU5fA5EjdgwvZ9C2rOSjGJW5AFE7yK7g7m9zlg7w/ybziAr6.','hello','hello','2015-01-21'),(7,'testssss@ucsd.edu','$2y$12$ErjJm1TsIR8xXjFc6hvWseemd26VvMd4gsWbJdwaTqEjRtMF9cHRW','hello','hello','2015-01-21'),(8,'abc@ucsd.edu','$2y$12$FSaT95449AZvvndwrKige.y34SCaBuwQTZNsqLYhFwgLuye8wR8ZK','abc','abc','2015-01-21'),(9,'hah@ucsd.edu','$2y$12$DpngbDPM1fS5d62BgnKNsOkq6V1DC84ZmzpoZ./vrKM/1AnQbj/VS','haha','haha','2015-01-21'),(10,'asdfasdf@ucsd.edu','$2y$12$sR55Vk09VRXYu/f5m5xgn.i6eWz6Bv75j8R.Yl/LZrN5Yis2OLM5K','hello','hello','2015-01-21'),(11,'asdfasdf@ucsds.edu','$2y$12$G8jwEacxHhWtjlaqTuqkCegMAo4WOR6n6OIhgH8dEMA09rHAZciTy','asdfg','asdfg','2015-01-21'),(12,'asdfasdddf@ucsd.edu','$2y$12$89cWMO/WAKa6OTy0XmcLC.X2uS4mUIFwhvRj33SCHmG/OkErf2zaa','asdfg','asdfg','2015-01-21'),(13,'asdfasdfee@asdfi.asdf','$2y$12$QOGnPvLfKgb.k4Wm9r/a4.wG.nuadqQLxSbMDdJY5SWKDwAxBEyhG','asdfg','asdfg','2015-01-21'),(14,'lani@ucsd.edu','$2y$12$3csM9sQ.h/4IvcIYqd8/3O5lz05xITSuwfJh9Qlpips78th./07LO','lani','lani','2015-01-21'),(15,'helloworld@ucsd.edu','hello','hello','hello','2015-01-27');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-27 21:49:11
