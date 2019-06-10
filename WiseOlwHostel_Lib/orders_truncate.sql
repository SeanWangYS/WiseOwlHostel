-- DROP TABLE order_items;
SELECT * FROM orders;
SELECT * FROM order_items;

SET FOREIGN_KEY_CHECKS=0; --關閉FKey檢查

TRUNCATE TABLE order_items;
TRUNCATE TABLE orders;

SET FOREIGN_KEY_CHECKS=1; --重新開啟FKey檢查
SELECT * FROM orders;
SELECT * FROM order_items;
