CREATE TABLE if not exists `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE if not exists `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE if not exists `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX IF NOT EXISTS `USERS_index` ON `USERS` (`email`);

ALTER TABLE `RENTALS` ADD CONSTRAINT `fk_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);
# ALTER TABLE `USERS` ADD FOREIGN KEY (`id`) REFERENCES `RENTALS` (`owner_id`);

ALTER TABLE `MESSAGES` ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
# ALTER TABLE `USERS` ADD FOREIGN KEY (`id`) REFERENCES `MESSAGES` (`user_id`);

ALTER TABLE `MESSAGES` ADD CONSTRAINT  `fk_rental_id` FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);
# ALTER TABLE `RENTALS` ADD FOREIGN KEY (`id`) REFERENCES `MESSAGES` (`rental_id`);
