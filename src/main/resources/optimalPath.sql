Use optimalPath;

CREATE TABLE `cities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `latitude` double,
  `longitude` double
);

CREATE TABLE `airlines` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `citiesId` int
);

CREATE TABLE `classTypes` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `typeName` varchar(255),
  `citiesId` int,
  `price` int
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
  `typeOfAnimal` varchar(255)
);

CREATE TABLE `tickets` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `animalsId` int,
  `airlineId` int,
  `citiesId` int,
  `classTypeId` int,
  `clientsId` int,
  `seatsNum` int
);

ALTER TABLE `airline` ADD FOREIGN KEY (`citiesId`) REFERENCES `cities` (`id`);

ALTER TABLE `classType` ADD FOREIGN KEY (`citiesId`) REFERENCES `cities` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`animalsId`) REFERENCES `animals` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`airlineId`) REFERENCES `airline` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`citiesId`) REFERENCES `cities` (`id`);

ALTER TABLE `tickets` ADD FOREIGN KEY (`classTypeId`) REFERENCES `classType` (`id`);

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

INSERT INTO classTypes (typeName, citiesId, price)
VALUES ('Business','1', '400'),
('Ekonom','1', '200'),
('Business','2', '200'),
('Ekonom','2', '50'),
('Business','3', '200'),
('Ekonom','3', '50'),
('Business','4', '400'),
('Ekonom','4', '200'),
('Business','5', '400'),
('Ekonom','5', '200'),
('Business','6', '200'),
('Ekonom','6', '50'),
('Business','7', '200'),
('Ekonom','7', '50'),
('Business','8', '400'),
('Ekonom','8', '200'),
('Business','9', '400'),
('Ekonom','9', '200'),
('Business','10', '200'),
('Ekonom','10', '50'),
('Business','11', '200'),
('Ekonom','11', '50'),
('Business','12', '400'),
('Ekonom','12', '200'),
('Business','13', '400'),
('Ekonom','13', '200'),
('Business','14', '200'),
('Ekonom','14', '50'),
('Business','15', '200'),
('Ekonom','15', '50'),
('Business','16', '400'),
('Ekonom','16', '200'),
('Business','17', '400'),
('Ekonom','17', '200'),
('Business','18', '200'),
('Ekonom','18', '50'),
('Business','19', '200'),
('Ekonom','19', '50'),
('Business','20', '400'),
('Ekonom','20', '200'),
('Business','21', '400'),
('Ekonom','21', '200'),
('Business','22', '200'),
('Ekonom','22', '50'),
('Business','23', '200'),
('Ekonom','23', '50');

INSERT INTO clients (firstName, lastName, passportNum, phoneNum)
VALUES ('Peter','Vasilishvili', 'CE280564', "+38067101010"),
('Mark','Arthurs', 'VK280565', "+38067101011"),
('Debbi','Robertson', 'PS280566', "+38067221012"),
('David','Goldmen', 'RB2805657', "+38067251011"),
('Nelli','Hardman', 'VB2805658', "+38067821035"),
('Lilia','Flower', 'OP2805659', "+38069701041"),
('Alon','Freeman', 'PT2805653', "+38067102311");

INSERT INTO animals (typeOfAnimal)
VALUES ('Cat'),
('Dog'),
('Rabbit'),
('Chinchilla'),
('Mouse');

INSERT INTO tickets (animalsId, airlinesId, citiesId, classTypesId, clientsId)
VALUES ('1','1','2','1','1'),
('2','2','3','2','2'),
('3','3','4','3','3'),
('4','4','5','4','4'),
('5','5','6','5','5');







