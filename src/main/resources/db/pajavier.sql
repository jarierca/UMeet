-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: umeet
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
INSERT INTO `categories` VALUES (1,'Stephanie',1),(2,'Nona',2),(3,'Urban',3),(4,'Lera',4),(5,'Tressie',5);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `channels`
--

LOCK TABLES `channels` WRITE;
/*!40000 ALTER TABLE `channels` DISABLE KEYS */;
INSERT INTO `channels` VALUES (1,'Macejkovic',1),(2,'Graham',2),(3,'Deckow',3),(4,'Kiehn',4),(5,'Prosacco',5);
/*!40000 ALTER TABLE `channels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,'aceptado',1,2),(2,'aceptado',2,3),(3,'invitado',3,1),(4,'invitado',4,5),(5,'invitado',5,2),(16,'aceptado',6,1);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'ipsum','Qui qui aut deleniti temporibus suscipit et quis. Quidem qui dolores magni quisquam voluptatem quo ratione. Et eum perferendis rerum est quis. Enim est rerum debitis blanditiis beatae.',1,1,NULL),(2,'similique','Sed earum amet nulla repellat qui ratione occaecati. Assumenda voluptatem sunt tenetur quod. Rerum doloremque qui dolor sit. Mollitia dolores repudiandae ut nihil exercitationem hic molestias.',2,2,NULL),(3,'iste','Beatae et non voluptatum vero molestiae numquam mollitia. Iusto cupiditate nisi a eum et quia id. Odio quod vitae mollitia voluptas voluptatem itaque facilis.',3,3,NULL),(4,'odit','In exercitationem voluptatum hic velit et cumque consequatur doloribus. Beatae commodi rerum pariatur enim in ea itaque. Quo eum quis commodi deserunt. Qui quos adipisci voluptates libero.',4,4,NULL),(5,'ut','Odio fugiat est ut placeat impedit. Fugiat quibusdam accusantium consequatur consequatur. Beatae adipisci repellat fuga quam voluptas non. Ipsum ab illum dolorem.',5,5,NULL),(6,'ut','Odio fugiat est ut placeat impedit. Fugiat quibusdam accusantium consequatur consequatur. Beatae adipisci repellat fuga quam voluptas non. Ipsum ab illum dolorem.',NULL,5,1),(8,'Perico palotes','Texto mensaje privado',NULL,4,3),(9,'ipsum','Texto invent',NULL,1,2),(10,'Nombre editado 4.0','Nuevo texto de chat',1,1,NULL),(11,'registro','Nuevo mensaje que me invento',1,6,NULL),(12,'registro','Nuevo mensaje de chat',1,6,NULL),(13,'registro','Aritz observandome',1,6,NULL),(14,'registro','hola que hase',1,6,NULL),(15,'registro','Aritz observandome',1,6,NULL),(16,'registro','hola marcos',1,6,NULL),(17,'registro','Hola javier',1,6,NULL),(18,'registro','Hola jorge',1,6,NULL),(19,'registro','Probando errores',1,6,NULL),(20,'registro','holalgtrjswkgewsrklgje',1,6,NULL),(21,'registro','trurui',1,6,NULL),(22,'registro','esto explota hasta mas que el vocaln',1,6,NULL),(23,'registro','ujydridrt',1,6,NULL),(24,'registro','lkdhdhd',1,6,NULL),(25,'registro','hljherdhedrhedr',1,6,NULL),(26,NULL,'hola amigooooo',NULL,6,1),(27,'registro','hola amiguito',NULL,6,1),(28,'registro','que me comentas',NULL,6,1),(29,'registro','cuentame cosas',NULL,6,1),(30,'registro','ppplsss',NULL,6,1),(31,'registro','cosicas',NULL,6,1),(32,'registro','asas',NULL,6,1),(33,'registro','gfsdfg',NULL,6,1),(34,'registro','Nuevo texto de chat',NULL,6,1),(35,'registro','Holiii',NULL,6,1),(36,'registro','holi aranxa',NULL,6,1),(48,'registro','grfedañklsylñmkeulkewnsljkyelktenlyelykewlkywelkñgmjelkgrñsmeslñkrgmhrselñkhgmlskemhlkesmhlkmeshlkemshlkemshlkemsrhlkemshlkemsrlhkemslhkemsrlhñemslh',1,6,NULL),(49,'registro','reñly melyje ipy we4pyu 4jpu5ewo4upwi4er5 upiw4j 5pujw4po 6ujpi 4jwoiy6uj rw4juo4r6wj bour4j6 wr4 6u oirw4yj wery e yh4reu jyuroj uwr ',1,6,NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages_files`
--

LOCK TABLES `messages_files` WRITE;
/*!40000 ALTER TABLE `messages_files` DISABLE KEYS */;
INSERT INTO `messages_files` VALUES (1,'sjeo','nihil','http://lockman.info/',1),(2,'auqj','quidem','http://www.howe.com/',2),(3,'rxht','unde','http://www.fadeljohns.info/',3),(4,'gusm','eum','http://feil.org/',4),(5,'lxje','harum','http://kessler.com/',5);
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
INSERT INTO `servers` VALUES (1,'C:/zzUpload/avatar/avatar-stock.png','Saepe amet dolores quidem ex ut. Dolorum quaerat veniam nulla aspernatur.','Blake Gulgowski','activo'),(2,'C:/zzUpload/avatar/avatar-stock.png','Beatae dicta dolore fuga earum necessitatibus. Aut voluptas perspiciatis ut est optio. At architecto qui quam aut eius. Aliquam ut et sunt numquam.','Don Rempel','activo'),(3,'C:/zzUpload/avatar/avatar-stock.png','Quis aperiam quia debitis maxime harum doloremque nam beatae. Ea ipsa non excepturi quibusdam quisquam perferendis perferendis quisquam. Eveniet vel consequatur delectus beatae similique.','Kaitlin Gislason IV','bloqueado'),(4,'C:/zzUpload/avatar/avatar-stock.png','Dolore fugit magni maiores ab voluptate. Temporibus pariatur repudiandae aut. Sint rerum repudiandae sunt nemo pariatur tempora sapiente. Rerum et ipsum sint aut quia dolorem incidunt. Modi labore non ut sunt veritatis illum vel.','Leonora Haag','activo'),(5,'C:/zzUpload/avatar/avatar-stock.png','Est qui itaque ut qui dolor nemo illo laborum. Reiciendis a maiores ipsam dolore. Quo rerum enim cum repudiandae molestiae error. Et mollitia nulla eaque aperiam libero.','Mrs. Eva Dare Jr.','bloqueado');
/*!40000 ALTER TABLE `servers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_server_role`
--

LOCK TABLES `user_server_role` WRITE;
/*!40000 ALTER TABLE `user_server_role` DISABLE KEYS */;
INSERT INTO `user_server_role` VALUES (61,1,1,1),(62,2,2,2),(63,3,3,3),(64,2,4,4),(65,2,5,5),(66,2,1,6),(69,2,4,6),(70,2,5,6);
/*!40000 ALTER TABLE `user_server_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'C:/zzUpload/avatar/avatar-stock.png','Email@editado.com','Nombre editado 4.0','Reinger','Ocupado','cboyer'),(2,'C:/zzUpload/avatar/avatar-stock.png','cdach@example.net','Jerrell O\'Keefe','Mueller','conectado','talon58'),(3,'C:/zzUpload/avatar/avatar-stock.png','price.annetta@example.com','Prof. Fletcher Schmidt Sr.','Witting','conectado','ernser.mason'),(4,'C:/zzUpload/avatar/avatar-stock.png','turner.dubuque@example.org','Annette McClure','Quitzon','ocupado','davion05'),(5,'C:/zzUpload/avatar/avatar-stock.png','muller.nathan@example.org','Mr. Nathanael Weber I','Toy','desconectado','reinger.bobby'),(6,'C:/zzUpload/avatar/avatar-stock.png','email@inventado.com','registro','$2a$10$dfSXSbQ/XLqCp08N4QKUTONccIjW/ghEP7eddd0bTksms4TyrDKqi','conectado','1');
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

-- Dump completed on 2021-09-22 12:09:20
