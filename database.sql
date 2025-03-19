CREATE DATABASE shoppingmall;

CREATE USER 'mallAdmin'@'%' IDENTIFIED BY 'qwe123!@#';

GRANT ALL PRIVILEGES ON shopppingmall.* to 'mallAdmin'@'%';
