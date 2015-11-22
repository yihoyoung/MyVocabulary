CREATE TABLE vocabulary (
word_id INT PRIMARY KEY AUTO_INCREMENT,
word_type varchar(4),
word_en varchar(30),
word_kr varchar(30),
phonetic varchar(50),
description varchar(100),
example varchar(30),
);



CREATE TABLE users_word (username VARCHAR(100) NOT NULL PRIMARY KEY, ward_id int) ;
