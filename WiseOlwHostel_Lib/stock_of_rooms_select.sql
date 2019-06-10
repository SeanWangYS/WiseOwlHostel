-- SELECT `date`,(IFNULL(stock_of_rooms.stock, rooms.stock)) as the_stock, rooms.* 
--     FROM rooms LEFT JOIN stock_of_rooms ON rooms.id = stock_of_rooms.room_id AND (`date` BETWEEN '2019-06-16' AND '2019-06-18') 
-- ORDER BY rooms.id ;
-- 
-- SELECT `date`,MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as the_stock, rooms.* 
--     FROM rooms LEFT JOIN stock_of_rooms ON rooms.id = stock_of_rooms.room_id AND (`date` BETWEEN '2019-06-16' AND '2019-06-18') 
-- GROUP BY rooms.id HAVING the_stock>0;

-- SELECT rooms.id, rooms.stock, stock_of_rooms.room_id, MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as searched_stock, `date`, rooms.* 
-- FROM rooms LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id 
--     AND (`date` BETWEEN '2019-06-15' AND '2019-06-19')
--     GROUP BY rooms.id HAVING searched_stock>0;

-- SELECT rooms.*, MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as searched_stock, `date`
-- FROM rooms LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id 
--     AND (`date` BETWEEN '2019-06-12' AND '2019-06-17')  
--     GROUP BY rooms.id HAVING searched_stock>0;
--==================================================================================================

-- SELECT rooms.*, MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as searched_stock, `date`
-- FROM rooms LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id 
--     AND (`date` BETWEEN '2019-07-01' AND '2019-07-03')  
--     WHERE rooms.`name` LIKE '%%'
--     GROUP BY rooms.id HAVING searched_stock>0;

SELECT `date`, rooms.id, rooms.`name`, MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as searched_stock
FROM rooms LEFT JOIN stock_of_rooms ON rooms.id = stock_of_rooms.room_id
AND (`date` BETWEEN '2019-06-15' AND '2019-06-17')
GROUP BY rooms.id
HAVING searched_stock>0;