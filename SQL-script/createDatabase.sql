DROP DATABASE  IF EXISTS `DataForWebService`;

CREATE DATABASE  IF NOT EXISTS `DataForWebService`;
USE `DataForWebService`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`username` varchar(50) NOT NULL,
`password` varchar(80) NOT NULL,
`first_name` varchar(50) NOT NULL,
`last_name` varchar(50) NOT NULL,
`email` varchar(50) NOT NULL,
UNIQUE KEY `PRIVATE_NAME` (`username`),
PRIMARY KEY (`id`),

CONSTRAINT `FK_USER_ID` foreign key (`id`) references `user_code` (`user_id`) 
ON DELETE NO ACTION ON UPDATE NO ACTION
    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: admin
--

INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES
('john','$2a$04$czqQfg2CQOKkr6cl1/Q.EuhM5Eb0/I1w8BnK4o/y.t/oz9U53rwtm','John','Doe','john@luv2code.com'),
('mary','$2a$04$czqQfg2CQOKkr6cl1/Q.EuhM5Eb0/I1w8BnK4o/y.t/oz9U53rwtm','Mary','Public','mary@luv2code.com'),
('susan','$2a$04$czqQfg2CQOKkr6cl1/Q.EuhM5Eb0/I1w8BnK4o/y.t/oz9U53rwtm','Susan','Adams','susan@luv2code.com'),
('rafix', '$2a$10$/RyWIXF266CNf9EWEbLjsefeY/UC6/3m0ZOP.ueJM1/uIgW/vxEJW', 'Rafal', 'Trocki', 'troc.raf@pmail.com'),
('kasia', '$2a$10$WDTsv4TiKAQb68Tk6Oy64eH6HmN647vM40KCoLIUBeWu.mOJSSX6G', 'Katarzyna', 'Wielka', 'wielka.kat@op.com'),
('wik', '$2a$10$ULgUuPc3WBDNVuBjvucuG.ubPpJOIcQigvJaGa0OUSjkY.pE5cTqq', 'Wiktor', 'Grocz', 'grocz.tor@re.de'),
('marek', '$2a$10$hIHDq7f7wvmVbr3I/Oagzuj4dlw1Ace9AXY2HwbyPMWWQehdRplDy', 'Marek', 'Kielek', 'kiel.mar@u2.com'),
('pete', '$2a$10$e2vcdVACBLY.epILku9GleWMumHBDFI44hP2bWN.rdZ3UWYrhyuAK', 'Pete', 'Parker', 'par.pete@po.mail.com'),
('klysz', '$2a$10$dkUCHWJ/5Z0K0PAwM6AS7.R608jQtP69U6RmqVQIcIM2d3B1Ns.dK', 'Jakub', 'Klysz', 'kl.kub@tugmail.dk');


--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(50) DEFAULT NULL,
                         PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (name)
VALUES
('CUSTOMER'),('MANAGER'),('DEVELOPER');


DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
                             `user_id` int(11) NOT NULL,
                             `role_id` int(11) NOT NULL,

                             KEY `FK_ROLE_idx` (`role_id`),

                             CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
                                 REFERENCES `roles` (`id`)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION,

                              CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
                                 REFERENCES `user`  (`id`)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION,

                             PRIMARY KEY (`user_id`,`role_id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `user_role` (user_id,role_id)
VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1);

-- create table if not exists persistent_logins (
--                                                  username varchar(100) not null,
--                                                  series varchar(64) primary key,
--                                                  token varchar(64) not null,
--                                                  last_used timestamp not null
-- );

