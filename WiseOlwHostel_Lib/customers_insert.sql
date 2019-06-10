USE woh;
TRUNCATE TABLE customers;
INSERT INTO customers
( email,password,surname,name,phone,gender)
VALUES('SeanWang@gmail.com','123456','Wang','Sean','0916221209','M');

INSERT INTO customers
( email,password,surname,name,phone,gender)
VALUES('FridaWang@gmail.com','123456','Wang','Frida','0916333888','F');


INSERT INTO customers
( email,password,surname,name,phone,gender)
VALUES(?,?,?,?,?,?);