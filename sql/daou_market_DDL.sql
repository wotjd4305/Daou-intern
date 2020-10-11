-- Daou market DDL
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `emp_num` int DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `password` char(64) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  `image` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `item`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `item` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `status` char(1) DEFAULT 'S',
  `delflag` tinyint(1) DEFAULT 0,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_item_user` (`user_id`),
  CONSTRAINT `fk_item_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `search`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `search` (
  `search_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `keyword` varchar(30) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`search_id`),
  KEY `fk_search_user1` (`user_id`),
  CONSTRAINT `fk_search_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `point`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `point` (
  `point_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `date` date DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`point_id`),
  KEY `fk_point_user1` (`user_id`),
  CONSTRAINT `fk_point_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `image` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL,
  `picture` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `fk_image_item1` (`item_id`),
  CONSTRAINT `fk_image_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `chatroom`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `chatroom` (
  `chatroom_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL,
  `seller_id` int NOT NULL,
  `buyer_id` int NOT NULL,
  PRIMARY KEY (`chatroom_id`),
  KEY `fk_chatroom_user2` (`buyer_id`),
  KEY `fk_chatroom_item1` (`item_id`),
  KEY `fk_chatroom_user1` (`seller_id`),
  CONSTRAINT `fk_chatroom_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chatroom_user1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chatroom_user2` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chatroom_id` bigint(20) NOT NULL,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `content` text DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `receive_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `fk_message_chatroom1` (`chatroom_id`),
  KEY `fk_message_user1` (`sender_id`),
  KEY `fk_message_user2` (`receiver_id`),
  CONSTRAINT `fk_message_chatroom1` FOREIGN KEY (`chatroom_id`) REFERENCES `chatroom` (`chatroom_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `favorite`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `favorite` (
  `favorite_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`favorite_id`),
  KEY `fk_favorite_user1` (`user_id`),
  KEY `fk_favorite_item1` (`item_id`),
  CONSTRAINT `fk_favorite_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;