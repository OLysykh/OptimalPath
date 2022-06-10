CREATE TABLE `cities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `latitude` double,
  `longitude` double,
  `standartTariff` int 
);

CREATE TABLE `airlines` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `citiesId` int
);

CREATE TABLE `classTypes` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `typeName` varchar(255),
  `citiesId` int
);

CREATE TABLE `clients` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `firstName` varchar(255),
  `lastName` varchar(255),
  `passportNum` varchar(255),
  `phoneNum` varchar(255)
);

CREATE TABLE `animals` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `typeOfAnimal` varchar(255),
  `ticketsId` int
);

CREATE TABLE `tickets` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `airlinesId` int,
  `citiesId` int,
  `classTypesId` int,
  `clientsId` int,
  `destinationCity` varchar(255),
  `seatsNum` varchar(255),
  `price` int
);

ALTER TABLE `airlines` ADD FOREIGN KEY (`citiesId`) REFERENCES `cities` (`id`);

ALTER TABLE `classTypes` ADD FOREIGN KEY (`citiesId`) REFERENCES `cities` (`id`);

ALTER TABLE `animals` ADD FOREIGN KEY (`ticketsId`) REFERENCES `tickets` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`airlinesId`) REFERENCES `airlines` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`citiesId`) REFERENCES `cities` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`classTypesId`) REFERENCES `classTypes` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`clientsId`) REFERENCES `clients` (`id`);

INSERT INTO cities (name, latitude, longitude)
VALUES ('Kyiv','50.45','30.51'),
('Dnipro','48.28', '35.01'),
('Vinnytsia','49.14','28.28'),
('Lutsk','50.44','25.19'),
('Lviv','49.50','24.00'),
('Khmelnytskiy','49.25','27.00'),
('Uzhhorod','48.37','22.17'),
('Kalush','49.00','24.22'),
('Ivano-Frankivsk','48.55','24.42'),
('Chernivtsi','48.17','25.56'),
('Kryvyi Rih','47.54','33.22'),
('Odesa','46.28','30.43'),
('Mykolaiv','46.57','31.59'),
('Kherson','46.39','32.37'),
('Simferopol','44.57','34.06'),
('Sevastopol','44.35','33.31'),
('Kharkiv','49.58','36.15'),
('Chernihiv','51.30','31.17'),
('Sumy','50.55','34.48'),
('Luhansk','48.34','39.19'),
('Donetsk','48.01','37.48'),
('Zaporizhzhia','47.49','35.11'),
('Mariupol','47.05','37.32');

INSERT INTO classTypes (typeName, citiesId)
VALUES ('Business','1'),
('Ekonom','1'),
('Business','2'),
('Ekonom','2'),
('Business','3'),
('Ekonom','3'),
('Business','4'),
('Ekonom','4'),
('Business','5'),
('Ekonom','5'),
('Business','6'),
('Ekonom','6'),
('Business','7'),
('Ekonom','7'),
('Business','8'),
('Ekonom','8'),
('Business','9'),
('Ekonom','9'),
('Business','10'),
('Ekonom','10'),
('Business','11'),
('Ekonom','11'),
('Business','12'),
('Ekonom','12'),
('Business','13'),
('Ekonom','13'),
('Business','14'),
('Ekonom','14'),
('Business','15'),
('Ekonom','15'),
('Business','16'),
('Ekonom','16'),
('Business','17'),
('Ekonom','17'),
('Business','18'),
('Ekonom','18'),
('Business','19'),
('Ekonom','19'),
('Business','20'),
('Ekonom','20'),
('Business','21'),
('Ekonom','21'),
('Business','22'),
('Ekonom','22'),
('Business','23'),
('Ekonom','23');

INSERT INTO airlines (name, citiesId)
VALUES ('Wizzair','1'),
('Ryanair','2'),
('Wizzair','3'),
('Ryanair','4'),
('Wizzair','5'),
('Ryanair','6'),
('Wizzair','7'),
('Ryanair','8'),
('Wizzair','9'),
('Ryanair','10'),
('Wizzair','11'),
('Ryanair','12'),
('Wizzair','13'),
('Ryanair','14'),
('Ryanair','15'),
('Wizzair','16'),
('Ryanair','17'),
('Wizzair','18'),
('Ryanair','19'),
('Wizzair','20'),
('Ryanair','21'),
('Ryanair','22'),
('Wizzair','23');

INSERT INTO clients (firstName, lastName, passportNum, phoneNum)
VALUES ('Peter','Vasilishvili', 'CE280564', "+38067101010"),
('Mark','Arthurs', 'VK280565', "+38067101011"),
('Debbi','Robertson', 'PS280566', "+38067221012"),
('David','Goldmen', 'RB2805657', "+38067251011"),
('Nelli','Hardman', 'VB2805658', "+38067821035"),
('Lilia','Flower', 'OP2805659', "+38069701041"),
('Alon','Freeman', 'PT2805653', "+38067102311");

INSERT INTO tickets (airlinesId, citiesId, classTypesId, clientsId, price)
VALUES ('1','1','2','1','400'),
('2','2','3','2','200'),
('3','3','4','3','400'),
('4','4','5','4','200'),
('5','5','6','5','50');

INSERT INTO animals (typeOfAnimal, ticketsId)
VALUES ('Cat', 1),
('Dog', 2),
('Rabbit', 3),
('Chinchilla', 4),
('Mouse', 5);

use optimalpath;
CREATE TABLE `userpass` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user` varchar(255),
  `pass` varchar(255)
);

INSERT INTO userpass (user, pass)
VALUES ('roma', 'roma'),
('alex', 'alex'),
('taras', 'taras'),
('olga', 'olga'),
('yura', 'yura');


