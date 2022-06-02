CREATE TABLE `cities_departure` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `city_name` varchar(255)
);

CREATE TABLE `entities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `distance` int UNIQUE,
  `cities_departure_id` int,
  `cities_arrival_id` int
);

CREATE TABLE `cities_arrival` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `city_name` varchar(255)
);

CREATE TABLE `clients` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `passport_num` varchar(255),
  `phone_num` varchar(255)
);

CREATE TABLE `planes` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `type` varchar(255),
  `company_name` varchar(255),
  `clients_id` int
);

CREATE TABLE `ticket_orders` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `type` varchar(255),
  `name` varchar(255),
  `clients_id` int,
  `ticket_orders_id` int
);

ALTER TABLE `entities` ADD FOREIGN KEY (`cities_departure_id`) REFERENCES `cities_departure` (`id`);

ALTER TABLE `entities` ADD FOREIGN KEY (`cities_arrival_id`) REFERENCES `cities_arrival` (`id`);

ALTER TABLE `planes` ADD FOREIGN KEY (`clients_id`) REFERENCES `clients` (`id`);

ALTER TABLE `ticket_orders` ADD FOREIGN KEY (`clients_id`) REFERENCES `clients` (`id`);

ALTER TABLE `ticket_orders` ADD FOREIGN KEY (`ticket_orders_id`) REFERENCES `cities_departure` (`id`);

INSERT INTO cities_departure (city_name)
VALUES ('Kyiv'),
('Lviv');
	
INSERT INTO cities_arrival (city_name)
VALUES ('Dnipro'),
('Odessa');

INSERT INTO entities (distance, cities_departure_id, cities_arrival_id)
VALUES ('485','1','1'),
('500','1','2')