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
`password` char(80) NOT NULL,
`first_name` varchar(50) NOT NULL,
`last_name` varchar(50) NOT NULL,
`email` varchar(50) NOT NULL,
`enabled` tinyint(1) NOT NULL,

PRIMARY KEY (`id`)
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

INSERT INTO `user` (username,password,first_name,last_name,email,enabled)
VALUES
('john','$2a$04$czqQfg2CQOKkr6cl1/Q.EuhM5Eb0/I1w8BnK4o/y.t/oz9U53rwtm','John','Doe','john@luv2code.com',1),
('mary','$2a$04$czqQfg2CQOKkr6cl1/Q.EuhM5Eb0/I1w8BnK4o/y.t/oz9U53rwtm','Mary','Public','mary@luv2code.com',1),
('susan','$2a$04$czqQfg2CQOKkr6cl1/Q.EuhM5Eb0/I1w8BnK4o/y.t/oz9U53rwtm','Susan','Adams','susan@luv2code.com',1);



--
-- Table structure for table `role`
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

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
                               `user_id` int(11) NOT NULL,
                               `role_id` int(11) NOT NULL,

                               PRIMARY KEY (`user_id`,`role_id`),

                               KEY `FK_ROLE_idx` (`role_id`),

                               CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
                                   REFERENCES `user` (`id`)
                                   ON DELETE NO ACTION ON UPDATE NO ACTION,

                               CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
                                   REFERENCES `roles` (`id`)
                                   ON DELETE NO ACTION ON UPDATE NO ACTION
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
(3, 3)