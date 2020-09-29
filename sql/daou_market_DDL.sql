-- Daou market DDL
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_num` bigint(20) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `password` char(30) DEFAULT NULL,
  `department` char(20) DEFAULT NULL,
  `image` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `item`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `title` char(100) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `status` char(1) DEFAULT 'S',
  `delflag` tinyint(1) DEFAULT 0,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_item_user` (`user_id`),
  CONSTRAINT `fk_item_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `search`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `search` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `keyword` varchar(30) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_search_user1` (`user_id`),
  CONSTRAINT `fk_search_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `point`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_point_user1` (`user_id`),
  CONSTRAINT `fk_point_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL,
  `picture` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_image_item1` (`item_id`),
  CONSTRAINT `fk_image_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `chatroom`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `chatroom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL,
  `seller_id` bigint(20) NOT NULL,
  `buyer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chatroom_user2` (`buyer_id`),
  KEY `fk_chatroom_item1` (`item_id`),
  KEY `fk_chatroom_user1` (`seller_id`),
  CONSTRAINT `fk_chatroom_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chatroom_user1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chatroom_user2` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chatroom_id` bigint(20) NOT NULL,
  `seller_id` bigint(20) NOT NULL,
  `buyer_id` bigint(20) NOT NULL,
  `content` text DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `receive_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_message_chatroom1` (`chatroom_id`),
  KEY `fk_message_user1` (`seller_id`),
  KEY `fk_message_user2` (`buyer_id`),
  CONSTRAINT `fk_message_chatroom1` FOREIGN KEY (`chatroom_id`) REFERENCES `chatroom` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user2` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Table `favorite`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `fk_favorite_user1` (`user_id`),
  KEY `fk_favorite_item1` (`item_id`),
  CONSTRAINT `fk_favorite_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;