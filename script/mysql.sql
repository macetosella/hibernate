delimiter $$

CREATE TABLE `category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(45) NOT NULL,
  `cat_description` varchar(45) NOT NULL,
  `cat_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`cat_id`),
  KEY `fk_to_myseft_idx` (`cat_parent`),
  CONSTRAINT `fk_to_myseft` FOREIGN KEY (`cat_parent`) REFERENCES `category` (`cat_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

