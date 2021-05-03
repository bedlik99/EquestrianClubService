USE
`DataForWebService`;
    
CREATE TABLE `user_code`(
    `id` int(11) NOT NULL auto_increment,
	`user_id` int(11) NOT NULL,
    `invite_code` varchar(10) DEFAULT NULL,
    
    PRIMARY KEY (id)

) ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=latin1;

INSERT INTO `user_code` (user_id, invite_code)
VALUES
(1,1,null),(2,2,null),(3,3,null),(4,4,null),(5,5,null),(6,6,null),(7,7,null),(8,8,null),(9,9,null);

 ALTER TABLE `user`
    ADD CONSTRAINT `FK_USER_ID` foreign key (`id`)
    references `user_code` (`user_id`);






