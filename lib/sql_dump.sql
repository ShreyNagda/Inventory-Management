DROP SCHEMA IF EXISTS `inventorymanagement`;
CREATE DATABASE  IF NOT EXISTS `inventorymanagement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `inventorymanagement`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `name` varchar(45) NOT NULL,
  `username` varchar(55) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`username`)
);
INSERT INTO `users` VALUES ('Admin','admin','1234','admin');
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cName` varchar(45) NOT NULL,
  `pId` int NOT NULL,
  `quantity` int NOT NULL,
  `amount` int NOT NULL,
  `delivery_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_idx` (`pId`),
  CONSTRAINT `product` FOREIGN KEY (`pId`) REFERENCES `products` (`id`)
)
