/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sean
 * Created: 2019/4/12
 */
/*SELECT_ORDER_BY_ORDER_ID*/
-- SELECT orders.id as oid, customers.email as cemail, customers.name as cname, customers.surname as csurname, 
-- order_date, order_time, start_date, end_date, status, customer_country, customer_city, customer_booking_estimated_arrival,
-- payment_type, payment_note,
-- room_id, rooms.name as rname, photo_url, price, `number` 
-- FROM orders 
-- LEFT JOIN order_items ON orders.id = order_items.order_id
-- LEFT JOIN customers ON orders.customer_email= customers.email
-- LEFT JOIN rooms ON order_items.room_id = rooms.id
-- WHERE orders.id = '7';

/*SELECT_ORDER_BY_CUSTOMER_EMAIL*/
-- SELECT orders.id, customer_email, order_date, order_time, start_date, end_date, payment_type, status, customer_booking_estimated_arrival
-- --SUM(price* `number`) as total_amount
-- FROM orders LEFT JOIN order_items ON orders.id= order_items.order_id
-- WHERE customer_email='SeanWang@gmail.com'
-- --GROUP BY orders.id
-- ORDER BY order_date desc, order_time desc ;

SELECT orders.customer_email, orders.id, orders.order_date, order_items.room_id, `number`, price, SUM(price*`number`) as amount
FROM orders LEFT JOIN order_items ON orders.id = order_items.order_id
WHERE orders.customer_email='SeanWang@gmail.com'
GROUP BY orders.id
ORDER BY order_date desc, order_time desc;

/*SUM() 括號內只放 相當於一個column 的資訊(以此例而言把price * number 當作一個新的column)，SUM會對整個column內的資訊做加總*/