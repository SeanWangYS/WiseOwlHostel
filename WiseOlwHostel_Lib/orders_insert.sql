/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sean
 * Created: 2019/4/12
 */

INSERT INTO orders
(id,customer_email,order_date,order_time,start_date,end_date,
payment_type,status,
customer_country,customer_city,customer_booking_estimated_arrival)
VALUES
(?,?,?,?,?,?,?,?,?,?,?);

INSERT INTO order_items
(order_id,room_id,`number`,price)
VALUES(?,?,?,?);


