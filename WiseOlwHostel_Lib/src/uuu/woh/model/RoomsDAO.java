/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.woh.entity.Room;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
class RoomsDAO {

    private static final String SELECT_ALL_ROOMS = "SELECT * FROM rooms";

    List<Room> selectAllRooms() throws WOHException {
        List<Room> list = new ArrayList<>();

        try (
                //1.2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_ROOMS);
                //4.執行指令
                ResultSet rs = pstmt.executeQuery();) {
            //5.處理指令
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setNameTitle(rs.getString("name_title"));
                r.setUnitPrice(rs.getDouble("unit_price"));
                r.setRoomType(rs.getString("room_type"));
                r.setMax(rs.getInt("max"));
                r.setStock(rs.getInt("stock"));
                r.setBalcony(rs.getString("balcony"));
                r.setPhotoUrl(rs.getString("photo_url"));
                r.setDescription(rs.getString("description"));
                list.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new WOHException("查詢房間失敗", ex);
        }
        return list;
    }

    private static final String SELECT_ROOMS_BY_DATE_AND_ROOM_TYPE
            = "SELECT rooms.*, MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as searched_stock, `date` "
            + "FROM rooms LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id "
            + "AND (`date` BETWEEN ? AND ?) "
            + "WHERE rooms.`name` LIKE ?"
            + "GROUP BY rooms.id HAVING searched_stock>0";

    List<Room> selectRoomsByDateAndRoomType(String startDate, String endDate, String roomType) throws WOHException {
        List<Room> list = new ArrayList<>();
        try (
                //1.2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ROOMS_BY_DATE_AND_ROOM_TYPE);) {

            //3.1輸入?值
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            pstmt.setString(3, '%'+roomType+'%');
            try (
                    //4.執行指令
                    ResultSet rs = pstmt.executeQuery();) {
                //5.處理rs
                while (rs.next()) {
                    Room r = new Room();
                    r.setId(rs.getInt("id"));
                    r.setName(rs.getString("name"));
                    r.setNameTitle(rs.getString("name_title"));
                    r.setUnitPrice(rs.getDouble("unit_price"));
                    r.setMax(rs.getInt("max"));
                    r.setRoomType(rs.getString("room_type"));
                    r.setStock(rs.getInt("searched_stock"));
                    r.setBalcony(rs.getString("balcony"));
                    r.setPhotoUrl(rs.getString("photo_url"));
                    r.setDescription(rs.getString("description"));
                    list.add(r);
                }
            }
        } catch (SQLException ex) {
            throw new WOHException("從日期與房型查詢房間失敗", ex);
        }
        return list;
    }
    
    private static final String SELECT_ROOMS_BY_DATE
            = "SELECT rooms.*, MIN(IFNULL(stock_of_rooms.stock, rooms.stock)) as searched_stock, `date` "
            + "FROM rooms LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id "
            + "AND (`date` BETWEEN ? AND ?) "
            + "GROUP BY rooms.id HAVING searched_stock>0";
    
    List<Room> selectRoomsByDate(String startDate, String endDate) throws WOHException {
        List<Room> list = new ArrayList<>();
        try (
                //1.2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ROOMS_BY_DATE_AND_ROOM_TYPE);) {

            //3.1輸入?值
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            try (
                    //4.執行指令
                    ResultSet rs = pstmt.executeQuery();) {
                //5.處理rs
                while (rs.next()) {
                    Room r = new Room();
                    r.setId(rs.getInt("id"));
                    r.setName(rs.getString("name"));
                    r.setNameTitle(rs.getString("name_title"));
                    r.setUnitPrice(rs.getDouble("unit_price"));
                    r.setMax(rs.getInt("max"));
                    r.setRoomType(rs.getString("room_type"));
                    r.setStock(rs.getInt("searched_stock"));
                    r.setBalcony(rs.getString("balcony"));
                    r.setPhotoUrl(rs.getString("photo_url"));
                    r.setDescription(rs.getString("description"));
                    list.add(r);
                }
            }
        } catch (SQLException ex) {
            throw new WOHException("從日期查詢房間失敗", ex);
        }
        return list;
    }

    private static final String SELECT_ROOM_BY_ID = "SELECT * FROM rooms WHERE id=?";

    Room selectRoomById(String id) throws WOHException {
        Room r = null;
        try (
                //1.2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ROOM_BY_ID);) {
            //3.1輸入?值
            pstmt.setString(1, id);
            try (
                    //4.執行指令
                    ResultSet rs = pstmt.executeQuery();) {
                //5.處理rs
                while (rs.next()) {
                    r = new Room();
                    r.setId(rs.getInt("id"));
                    r.setName(rs.getString("name"));
                    r.setNameTitle(rs.getString("name_title"));
                    r.setUnitPrice(rs.getDouble("unit_price"));
                    r.setMax(rs.getInt("max"));
                    r.setRoomType(rs.getString("room_type"));
                    r.setStock(rs.getInt("stock"));
                    r.setBalcony(rs.getString("balcony"));
                    r.setPhotoUrl(rs.getString("photo_url"));
                    r.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException ex) {
            throw new WOHException("查詢房間失敗", ex);
        }
        return r;
    }

    //寫一個查詢房型個數的method 
    private static final String SELECT_QUANTITY_OF_ROOM_TYPE = "SELECT count(*) FROM rooms";

    int selectQuantityOfRoomType() throws WOHException {
        int quantity = 0;
        try (
                //1.2.取得連線 
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_QUANTITY_OF_ROOM_TYPE);
                //4.執行指令
                ResultSet rs = pstmt.executeQuery();) {
            //5.處理rs
            while (rs.next()) {
                quantity = Integer.parseInt(rs.getString("count(*)"));
            }
        } catch (SQLException ex) {
            throw new WOHException("查詢房型數量失敗", ex);
        }
        return quantity;
    }

    
    private static final String CREATE_TEMPRARY_TABLE
            = "CREATE TEMPORARY TABLE temp_date ( "
            + "`date` DATE NOT NULL "
            + ")ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4";

    private String INSERT_TEMPRARY_TABLE = "INSERT INTO temp_date VALUES ('";

    private static final String INSERT_STOCK_OF_ROOM
            = "INSERT INTO stock_of_rooms (`date`, room_id, stock) "
            + "SELECT temp_date.`date`, rooms.id, rooms.stock "
            + " FROM  temp_date JOIN rooms "
            + "    LEFT JOIN stock_of_rooms ON rooms.id=stock_of_rooms.room_id AND temp_date.`date`= stock_of_rooms.`date`  "
            + "    WHERE stock_of_rooms.room_id IS null "
            + "    ORDER BY temp_date.`date`, rooms.id";

    //依照查詢期間，自動更新stock_of_rooms
    void autoInserStockOfRoomsByDate(String startDate, String endDate) throws WOHException {
        LocalDate parseStartDate = LocalDate.parse(startDate);
        LocalDate parseEtartDate = LocalDate.parse(endDate);
        int period = (int)parseStartDate.until(parseEtartDate, ChronoUnit.DAYS);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        for (int i = 0; i <= period; i++) {
            String date = parseStartDate.plusDays(i).format(fmt);
            if(i<period){
            INSERT_TEMPRARY_TABLE = INSERT_TEMPRARY_TABLE + date + "'),('";
            }else{
            INSERT_TEMPRARY_TABLE = INSERT_TEMPRARY_TABLE + date + "')";
            }
        }
        System.out.println("INSERT_TEMPRARY_TABLE = " + INSERT_TEMPRARY_TABLE);

        try (
                //1.2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt1 = connection.prepareStatement(CREATE_TEMPRARY_TABLE);
                PreparedStatement pstmt2 = connection.prepareStatement(INSERT_TEMPRARY_TABLE);
                PreparedStatement pstmt3 = connection.prepareStatement(INSERT_STOCK_OF_ROOM);
                ) {
            connection.setAutoCommit(false);
            try{
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            }catch (Exception ex){
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new WOHException("自動新增庫存失敗", ex);
        }
    }

}
