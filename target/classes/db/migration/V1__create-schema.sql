CREATE TABLE users (username VARCHAR(100) NOT NULL PRIMARY KEY, encoded_password VARCHAR(255)) default charset=utf8;

CREATE TABLE customers (
id INT PRIMARY KEY AUTO_INCREMENT, 
first_name varchar(30),
last_name varchar(30),
username varchar(100) not null default 'user1') default charset=utf8;


alter table customers add constraint fk_customers_username foreign key (username) references users (username) ON UPDATE CASCADE 
ON DELETE CASCADE;;
