-- DROP DATABASE IF EXISTS woh;
-- CREATE DATABASE woh DEFAULT CHARACTER SET utf8mb4 ;

USE woh;
-- DROP TABLE IF EXISTS rooms;
-- CREATE TABLE `rooms` (
--   `id` int(10) unsigned NOT NULL,
--   `name` varchar(50) NOT NULL,
--   `name_title` varchar(50) NOT NULL,
--   `unit_price` double NOT NULL,
--   `room_type` varchar(10) NOT NULL,
--   `MAX` int(11) NOT NULL,
--   `stock` int(11) NOT NULL,
--   `Balcony` varchar(10) DEFAULT 'Yes',   
--   `photo_url` varchar(200) DEFAULT NULL,
--   `description` varchar(500) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; 


-- CREATE TABLE `customers` (
--   `email` varchar(50) NOT NULL,
--   `password` varchar(30) NOT NULL,
--   `surname` varchar(20) NOT NULL,
--   `name` varchar(40) NOT NULL,
--   `phone` varchar(20) NOT NULL,
--   `gender` char(1) NOT NULL,
--   PRIMARY KEY (`email`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `woh`.`stock_of_rooms` (
  `date` DATE NOT NULL,
  `room_id` INT NOT NULL,
  `stock` INT NOT NULL,
  PRIMARY KEY (`date`, `room_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_email` varchar(50) NOT NULL,
  `order_date` date NOT NULL,
  `order_time` time NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `payment_type` varchar(20) NOT NULL,
  `payment_note` varchar(100) NOT NULL DEFAULT '',
  `status` int(11) NOT NULL DEFAULT '0',
  `customer_country` varchar(60) DEFAULT NULL,
  `customer_city` varchar(60) DEFAULT NULL,
  `customer_booking_estimated_arrival` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkey_orders_to_customers_idx` (`customer_email`),
  CONSTRAINT `fkey_orders_to_customers` FOREIGN KEY (`customer_email`) REFERENCES `customers` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `order_items` (
  `order_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  `number` int(10) unsigned NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`order_id`,`room_id`),
  KEY `fkey_order_items_to_rooms_idx` (`room_id`),
  KEY `fkey_order_items_to_orders_idx` (`order_id`),
  CONSTRAINT `fkey_order_items_to_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkey_order_items_to_rooms` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

