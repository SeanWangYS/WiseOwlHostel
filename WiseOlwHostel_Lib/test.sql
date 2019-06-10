/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sean
 * Created: 2019/4/28
 */
-- SELECT Store_Name,Sales,Txn_Date FROM store_information;  /*查詢*/

--INSERT INTO store_information(Store_Name,Sales,Txn_Date) /*新增*/
--VALUE('Boston',750,'1999-01-08');

-- UPDATE store_information SET Sales = 750 WHERE Store_Name= 'Boston';  /*修改*/


--DELETE FROM store_information  WHERE Store_Name= 'San Diego';  /*刪除*/


SELECT `date`, rooms.id, rooms.`name`, MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as searched_stock
FROM rooms LEFT JOIN stock_of_rooms ON rooms.id = stock_of_rooms.room_id
AND (`date` BETWEEN '2019-07-24' AND '2019-07-26')
GROUP BY rooms.id
HAVING searched_stock>0;
