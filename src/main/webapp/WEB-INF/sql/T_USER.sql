drop table if exists T_USER;
CREATE TABLE `T_USER` (
`id` int(10) NOT NULL AUTO_INCREMENT, 
`username` VARCHAR(20) NOT NULL,
`password` VARCHAR(20) NOT NULL,
`email` VARCHAR(50),
`insertDate` timestamp DEFAULT CURRENT_TIMESTAMP,
`insertBy` VARCHAR(20) default 'admin',
`updateDate` timestamp DEFAULT CURRENT_TIMESTAMP,
`updateBy` VARCHAR(20) default 'admin',
PRIMARY KEY(`id`)
); 
alter table T_USER AUTO_INCREMENT=1;

/*初始化admin用户*/
insert into T_USER (id, username, password, email) values (1, 'admin', 'admin', 'admin@admin.com');
insert into T_USER (id, username, password, email) values (2, 'test', 'test', 'test@test.com');
/*以上初始化和建库脚步*/

select * from T_USER;


