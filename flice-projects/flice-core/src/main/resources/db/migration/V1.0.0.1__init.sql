
CREATE TABLE `Comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created` tinyblob,
  `owner_id` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `` (`owner_id`),
  KEY `FK_n38k0lxv5gd37x9jkokejluc2` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Community` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `created` tinyblob,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ix3il794cllbsb77crngcdfj1` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `created` tinyblob,
  `name` varchar(255) DEFAULT NULL,
  `community_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6vl5e2vmy8gij3u1jgquvw6td` (`community_id`),
  KEY `FK_jh41cjhrk6lervksja2qdexdr` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `UserCommunity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `favorite` bit(1) DEFAULT NULL,
  `community_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g486aaqqnruaeeo65226pys61` (`community_id`),
  KEY `FK_ajqan2hr17dio32hn5uxssorj` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `UserTopic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `favorite` bit(1) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5glol9ykehch4wcfltknavu68` (`topic_id`),
  KEY `FK_7f3y3mcmsw11m7yga152mphms` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE Comment ADD CONSTRAINT `FK_gwrw905k51p92f40d8hadue07` FOREIGN KEY (`owner_id`) REFERENCES `User` (`id`);
ALTER TABLE Comment ADD CONSTRAINT `FK_n38k0lxv5gd37x9jkokejluc2` FOREIGN KEY (`topic_id`) REFERENCES `Topic` (`id`);
ALTER TABLE Community ADD CONSTRAINT `FK_ix3il794cllbsb77crngcdfj1` FOREIGN KEY (`owner_id`) REFERENCES `User` (`id`);
ALTER TABLE Topic ADD CONSTRAINT `FK_6vl5e2vmy8gij3u1jgquvw6td` FOREIGN KEY (`community_id`) REFERENCES `Community` (`id`);
ALTER TABLE Topic ADD CONSTRAINT `FK_jh41cjhrk6lervksja2qdexdr` FOREIGN KEY (`owner_id`) REFERENCES `User` (`id`);
ALTER TABLE UserCommunity ADD CONSTRAINT `FK_ajqan2hr17dio32hn5uxssorj` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);
ALTER TABLE UserCommunity ADD CONSTRAINT `FK_g486aaqqnruaeeo65226pys61` FOREIGN KEY (`community_id`) REFERENCES `Community` (`id`);
ALTER TABLE UserTopic ADD CONSTRAINT `FK_5glol9ykehch4wcfltknavu68` FOREIGN KEY (`topic_id`) REFERENCES `Topic` (`id`);
ALTER TABLE UserTopic ADD CONSTRAINT `FK_7f3y3mcmsw11m7yga152mphms` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

