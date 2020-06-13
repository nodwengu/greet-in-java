create table user (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username varchar(20),
    greetCount double
);

insert into user (username, greetCount) values ('thando', 4);
insert into user (username, greetCount) values ('james', 3);
insert into user (username, greetCount) values ('kiro', 2);
insert into user (username, greetCount) values ('zan', 1);