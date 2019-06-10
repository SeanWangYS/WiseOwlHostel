USE woh;

UPDATE woh.customers
SET password = '123456',surname = 'WANG',name = 'Frida',phone = '0916123123',gender = 'F'
WHERE email = 'test2@gmail.com';
