CREATE TABLE vocabulary (
word_id INT PRIMARY KEY AUTO_INCREMENT,
word_type varchar(4),
word_en varchar(50),
word_kr varchar(50),
phonetic varchar(50),
description varchar(100),
example varchar(30)
) default charset=utf8;


CREATE TABLE users_word (id INT PRIMARY KEY AUTO_INCREMENT,username VARCHAR(100) NOT NULL, word_id int) default charset=utf8;
