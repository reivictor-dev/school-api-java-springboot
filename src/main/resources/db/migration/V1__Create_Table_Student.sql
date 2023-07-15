CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(80) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `age` bigint(10) NOT NULL,
  `weight` decimal(65,1) NOT NULL,
  `height` decimal(65,2) NOT NULL,
  PRIMARY KEY (`id`)
);