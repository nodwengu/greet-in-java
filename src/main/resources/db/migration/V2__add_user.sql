create table user (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username varchar(20),
    greetCount double
);

insert into user (username, greetCount) values ('Thando', 4);
insert into user (username, greetCount) values ('James', 3);
insert into user (username, greetCount) values ('Kiro', 2);
insert into user (username, greetCount) values ('Zan', 1);