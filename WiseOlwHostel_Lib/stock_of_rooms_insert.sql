/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Admin
 * Created: 2019/4/1
 */

-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '1', '3');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '2', '3');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '3', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '4', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '5', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '6', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '7', '4');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '8', '6');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-15', '9', '8');
-- 
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '1', '2');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '2', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '3', '0');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '4', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '5', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '6', '0');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '7', '4');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '8', '6');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-16', '9', '8');
-- 
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '1', '3');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '2', '3');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '3', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '4', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '5', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '6', '1');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '7', '4');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '8', '6');
-- INSERT INTO `woh`.`stock_of_rooms` (`date`, `room_id`, `stock`) VALUES ('2019-06-17', '9', '8');

/*========================auto Inser Stock_Of_Rooms ByDate(超精彩的SQL指令) ======================*/

DROP TEMPORARY TABLE temp_date;

CREATE TEMPORARY TABLE temp_date (
`date` DATE NOT NULL
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO temp_date VALUES ('2019-07-24'),('2019-07-25'),('2019-07-26');

INSERT INTO stock_of_rooms (`date`, room_id, stock)
SELECT temp_date.`date`, rooms.id, rooms.stock
 FROM  temp_date JOIN rooms 
    LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id AND temp_date.`date`= stock_of_rooms.`date` 
    WHERE stock_of_rooms.room_id IS null
    ORDER BY temp_date.`date`, rooms.id;

/*===================================================================*/
-- SELECT temp_date.`date`, IFNULL(stock_of_rooms.stock, rooms.stock) as searched_stock,
--     stock_of_rooms.stock as stock1, rooms.stock as stock2,
--     rooms.*
--  FROM  temp_date JOIN rooms 
--     LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id AND temp_date.`date`= stock_of_rooms.date
--     ORDER BY temp_date.`date`, rooms.id ;