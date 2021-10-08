-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: umeet
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (3,'Urban',3),(4,'Lera',4),(5,'Tressie',5),(6,'dsfgsfdgs',17),(7,'cat1',18),(8,'cat2',18),(9,'cat1',19);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `channels`
--

LOCK TABLES `channels` WRITE;
/*!40000 ALTER TABLE `channels` DISABLE KEYS */;
INSERT INTO `channels` VALUES (3,'Deckow',3),(4,'Kiehn',4),(5,'Prosacco',5),(6,'fdsgfsdgsdfg',6),(7,'CANAL',7),(8,'canal2',8),(9,'canal11',9);
/*!40000 ALTER TABLE `channels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (12,'invitado',9,2),(13,'aceptado',9,1),(14,'aceptado',9,11),(16,'aceptado',9,12),(17,'aceptado',9,4),(18,'invitado',9,3),(20,'invitado',9,5),(21,'invitado',9,6),(22,'invitado',9,8);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (3,'iste','Beatae et non voluptatum vero molestiae numquam mollitia. Iusto cupiditate nisi a eum et quia id. Odio quod vitae mollitia voluptas voluptatem itaque facilis.',3,3,NULL),(4,'odit','In exercitationem voluptatum hic velit et cumque consequatur doloribus. Beatae commodi rerum pariatur enim in ea itaque. Quo eum quis commodi deserunt. Qui quos adipisci voluptates libero.',4,4,NULL),(5,'ut','Odio fugiat est ut placeat impedit. Fugiat quibusdam accusantium consequatur consequatur. Beatae adipisci repellat fuga quam voluptas non. Ipsum ab illum dolorem.',5,5,NULL),(6,'ut','Odio fugiat est ut placeat impedit. Fugiat quibusdam accusantium consequatur consequatur. Beatae adipisci repellat fuga quam voluptas non. Ipsum ab illum dolorem.',NULL,5,1),(7,'Aran','hola',NULL,9,1),(8,'Aran','otra',NULL,9,4),(9,'Aran','dafdsfasdfsdafdfa',6,9,NULL),(10,'Aran','dgsdfdsfdsf',6,9,NULL),(11,'Aran','sd',3,9,NULL),(12,'Aran','adios',NULL,9,1),(13,'Aran','we',7,9,NULL),(14,'Aran','prueba',NULL,9,1),(15,'Aran','aaa',NULL,9,1),(16,'Aran','Hola',NULL,9,12),(17,'Prueba3','as',NULL,12,9),(18,'Aran','asas',4,9,NULL),(19,'Aran','holaaa',7,9,NULL),(20,'Prueba3','adios',7,12,NULL),(21,'Aran','oolaaaaaaaa',NULL,9,12),(22,'Prueba3','dededede',NULL,12,9),(23,'Aran','sdsd',NULL,9,12),(24,'Prueba3','g',NULL,12,9),(25,'Aran','ehhhh',NULL,9,1),(26,'Aran','holil',NULL,9,1),(27,'Aran','2.jpg',NULL,9,1),(29,'Aran','hhh',NULL,9,13),(30,'Arann','hh',NULL,13,9),(31,'Aran','dgdg',NULL,9,13),(32,'Arann','jhfhj',NULL,13,9),(33,'Aran','dgdg',NULL,9,13),(34,'Arann','`p',NULL,13,9),(35,'Arann','`p',NULL,13,9),(36,'Aran','hhhh',NULL,9,13),(37,'Arann','p',NULL,13,9),(38,'Arann','jjj',NULL,13,9),(39,'Aran','jh',NULL,9,13),(40,'Arann','ffff',NULL,13,9),(41,'Aran','ff',NULL,9,13),(42,'Aran','sfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff',6,9,NULL),(43,'Aran','rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr',6,9,NULL),(44,'Aran','fggdgd',NULL,9,13),(45,'Aran','ssss',NULL,9,1),(46,'Aran','dddd',NULL,9,1),(47,'Aran','sfsfsfs',NULL,9,1),(48,'Aran','jj',NULL,9,1),(49,'Aran','ggg',NULL,9,13),(50,'Aran','kjbjlvljhihuigñu',NULL,9,1),(51,'Aran','hjghf',NULL,9,1),(52,'Aran','GGGG+JHGJG',NULL,9,1),(53,'Aran','dgdg',NULL,9,1),(54,'Aran','kjguigli',NULL,9,1),(55,'Aran','hfhf',NULL,9,1),(56,'Aran','fff',NULL,9,1),(57,'Aran','hyyh',NULL,9,1),(58,'Aran','dsdsdsd',NULL,9,1),(59,'Aran','rf',NULL,9,1),(60,'Aran','fw',9,9,NULL),(61,'Aran','dgdgdgdf??',NULL,9,1),(62,'Aran','fsfsfsfs',5,9,NULL),(63,'Arann','dgdgddgd',3,13,NULL),(64,'Marcos','dgdgdgd',3,11,NULL),(65,'Aran','tetete',3,9,NULL),(66,'Aran','ufl',NULL,9,1),(67,'Aran','ganbaru',4,9,NULL),(68,'Aran','151734-ilustracion-juego_de_pc-dios_de_la_guerra-juego_de_aventura_de_accion-demonio-x750.jpg',NULL,9,13),(69,'Aran','descarga.jfif',NULL,9,1),(70,'Aran','ddd',NULL,9,1),(71,'Aran','151734-ilustracion-juego_de_pc-dios_de_la_guerra-juego_de_aventura_de_accion-demonio-x750.jpg',NULL,9,1),(72,'Aran','?',NULL,9,1),(74,'Aran','jhvjhv',NULL,9,1),(75,'Aran','Best-Video-Game-Wallpapers-HD-Free-Download.jpg',NULL,9,1),(76,'Aran','77ec380c9b1860955a475d0002b0d0af (1).gif',NULL,9,1),(77,'Aran','nueva.jpg',NULL,9,1),(78,'Aran','g?',NULL,9,1),(79,'Aran','hjhjhjhjh',NULL,9,1),(80,'Aran','hkjkjk',NULL,9,1),(81,'Aran','',NULL,9,1),(82,'Aran','images.png',NULL,9,1),(83,'Aran','nueva.jpg',NULL,9,1),(84,'Aran','dlihgiodshgoñs',5,9,NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages_files`
--

LOCK TABLES `messages_files` WRITE;
/*!40000 ALTER TABLE `messages_files` DISABLE KEYS */;
INSERT INTO `messages_files` VALUES (1,NULL,'fichero','C:/zzUpload/file/user/9/910e259d-0080-45ec-a88c-f492e8c5d046_2.jpg',27),(2,NULL,'fichero','C:/zzUpload/file/user/9/b6604970-d392-4aa0-a691-a70e354b46b4_151734-ilustracion-juego_de_pc-dios_de_la_guerra-juego_de_aventura_de_accion-demonio-x750.jpg',68),(3,NULL,'fichero','C:/zzUpload/file/user/9/c6de6828-8b93-41d3-a7b1-ed4cd87b78f8_descarga.jfif',69),(4,NULL,'fichero','C:/zzUpload/file/user/9/82fe33e2-4ddc-42d8-a6fc-68453c6e0b92_151734-ilustracion-juego_de_pc-dios_de_la_guerra-juego_de_aventura_de_accion-demonio-x750.jpg',71),(5,NULL,'fichero','C:/zzUpload/file/user/9/6cfdab12-7b85-4617-b3a7-90af20b88b78_Best-Video-Game-Wallpapers-HD-Free-Download.jpg',75),(6,NULL,'fichero','C:/zzUpload/file/user/9/c78ad04f-0edd-453c-84a3-5751f0ef0323_77ec380c9b1860955a475d0002b0d0af (1).gif',76),(7,NULL,'fichero','C:/zzUpload/file/user/9/dfce3206-5c75-4fe9-b40a-9a5907dfe282_nueva.jpg',77),(8,NULL,'fichero','C:/zzUpload/file/user/9/55337aca-8e40-4525-8e82-fa185cce65d2_',81),(9,NULL,'fichero','C:/zzUpload/file/user/9/49b7875b-d153-4cdd-8730-f7bce50a65b0_images.png',82),(10,NULL,'fichero','C:/zzUpload/file/user/9/7e9ec675-2f4d-43c5-a144-4f44a6cf3c14_nueva.jpg',83);
/*!40000 ALTER TABLE `messages_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'todos','Creador'),(2,'lectura','Usuario'),(3,'muchos','Moderador');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `servers`
--

LOCK TABLES `servers` WRITE;
/*!40000 ALTER TABLE `servers` DISABLE KEYS */;
INSERT INTO `servers` VALUES (3,'C:/zzUpload/avatar/servers/Kaitlin-Gislason-IV.png','Wellcome to the best server online. You can have fun with your friends!','Kaitlin Gislason IV','bloqueado'),(4,'C:/zzUpload/avatar/servers/Leonora-Haag.png','The official Leonora Haag server! Join us!!','Leonora Haag','activo'),(5,'C:/zzUpload/avatar/servers/Mrs.-Eva-Dare-Jr..png','This server connect with 500.000 + anime fans','Mrs. Eva Dare Jr.','bloqueado'),(6,'C:/zzUpload/avatar/servers/Destiny-Time.png','Come to the dark side....we have cookies','Destiny Time','activo'),(7,'C:/zzUpload/avatar/servers/Pepe\'s-Server.png','Your name it\'s Pepe? This is your place','Pepe\'s Server','activo'),(8,'C:/zzUpload/avatar/servers/The-secret\'s-haul.png','All the worlds\'s conspirations, have a look...','The trunk of secrets','activo'),(9,'C:/zzUpload/avatar/servers/Spiderman\'s-Lovers.png','If you want to marry Spiderman, this is your place, males only!','Spiderman\'s Lovers','activo'),(10,'C:/zzUpload/avatar/servers/Dark-Side-of-the-Moon.png','Here is a compilation of some of the most scary stories you\'ll ever read','Dark Side of the Moon','activo'),(17,'C:/zzUpload/avatar/server-stock.png','','fsdfdgdfsgfd',NULL),(18,'C:/zzUpload/avatar/server-stock.png','descripcion server','Server',NULL),(19,'C:/zzUpload/avatar/servers/nuevo-servido.png','desc','nuevo servido',NULL),(24,'C:/zzUpload/avatar/servers/Dont-touch-my-fries.png','Descubre todo sobre las patatas fritas','Dont touch my fries',NULL),(25,'C:/zzUpload/avatar/servers/Noodle-Daddy.png','Only for Food\'s lovers','Noodle Daddy',NULL),(26,'C:/zzUpload/avatar/servers/Netflix-and-Chill.png','Recomendaciones de las mejores series y películas de netflix','Netflix and Chill',NULL),(27,'C:/zzUpload/avatar/servers/Winter\'s-Inferno.png','We love Santa, no Satan!','Winter\'s Inferno',NULL),(28,'C:/zzUpload/avatar/servers/Naruto\'s-lovers.png','For Naruto\'s lovers!','Naruto\'s lovers',NULL),(29,'C:/zzUpload/avatar/servers/龙的传人.png','龙的传人龙的传人龙的传人龙的传人龙的传人龙的传人龙的传人','龙的传人',NULL),(30,'C:/zzUpload/avatar/servers/█▀█-█▄█-▀█▀-█▬█-█-▀█▀.png','No sabemos ni para que estamos','█▀█ █▄█ ▀█▀ █▬█ █ ▀█▀',NULL),(31,'C:/zzUpload/avatar/servers/꧁龙的传人꧂.png','龙的传人龙的传人龙的传人龙的传人龙的传人','꧁龙的传人꧂',NULL),(34,'C:/zzUpload/avatar/servers/♔??????♔.png','Holi','♔??????♔',NULL);
/*!40000 ALTER TABLE `servers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_server_role`
--

LOCK TABLES `user_server_role` WRITE;
/*!40000 ALTER TABLE `user_server_role` DISABLE KEYS */;
INSERT INTO `user_server_role` VALUES (17,2,4,12),(31,2,8,9),(32,2,3,13),(36,2,5,9),(42,2,3,11),(55,1,34,9),(56,2,4,9),(58,2,3,9),(59,2,9,9);
/*!40000 ALTER TABLE `user_server_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`,`avatar`,`email`,`nick_name`,`pass`,`status`,`username`,`codigo`) VALUES (1,'C:/zzUpload/avatar/usuarios/7.jpg','ellie.kautzer@example.net','Darren Johns','$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','Conectado','cboyer',NULL),(2,'C:/zzUpload/avatar/usuarios/4.jpg','hola@example.org','Florinda Bonita','$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','conectado','anita',NULL),(3,'C:/zzUpload/avatar/usuarios/3.jpg','price.annetta@example.com','Prof. Fletcher Schmidt Sr.','$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','conectado','ernser.mason',NULL),(4,'C:/zzUpload/avatar/usuarios/2.jpg','turner.dubuque@example.org','Annette McClure','$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','ocupado','davion05',NULL),(5,'C:/zzUpload/avatar/usuarios/5.jpg','muller.nathan@example.org','Mr. Nathanael Weber I','$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','desconectado','reinger.bobby',NULL),(6,'C:/zzUpload/avatar/usuarios/6.jpg','1@example.org','Aitor Tilla','$2a$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','desconectado','Aitor',NULL),(7,'C:/zzUpload/avatar/usuarios/3.jpg','2@example.org','Inés Tornudo','$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','conectado','Ines',NULL),(8,'C:/zzUpload/avatar/usuarios/1.jpg','8@example.org','Aitor Menta','$2a$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','desconectado','Aitor2',NULL),(9,'C:/zzUpload/avatar/users/Aran.png','C:/zzUpload/avatar/usuarios/3.jpg','Aran','$2a$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','ausente','Aran',NULL),(10,'C:/zzUpload/avatar/usuarios/3.jpg','u@example.org','Elder Bar','$2a$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','conectado','Elder',NULL),(11,'C:/zzUpload/avatar/usuarios/3.jpg','hh@example.org','Marcos','$2a$10$kSvecF18MLZwK80DAqMZw.C3G8Sz02q389hBq4ipFdJCB6iDdCUA6','desconectado','Marcos',NULL),(12,'C:/zzUpload/avatar/users/Prueba4.png','prueba@prueb.com','Prueba3','$2a$10$QCVPmuNnZt2iJM./ajGHn.pwtI.o5dY/QCiN0MiuoX2iWIjePkhR2','ausente','Prueba4',NULL),(13,'C:/zzUpload/avatar/avatar-stock.png','Aranzazu_90@hotmail.es','Arann','$2a$10$EUWjNZB9EHwi7VFsah5Wbu664r3DhCSYB70a1323M7Nz83cO1j1s2','desconectado','Arann',NULL),(14,'C:/zzUpload/avatar/usuarios/3.jpg','e@example.org','Lola','$2a$10$EUWjNZB9EHwi7VFsah5Wbu664r3DhCSYB70a1323M7Nz83cO1j1s2','ocupado','Lola',NULL);
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

-- Dump completed on 2021-10-08  8:40:39
