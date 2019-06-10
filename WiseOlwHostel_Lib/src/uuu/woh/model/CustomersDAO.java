/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.woh.entity.Customer;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
class CustomersDAO {

    private static final String INSERT_CUSTOMER = "INSERT INTO customers "
            + "(email,password,surname,name,phone,gender) "
            + "VALUES(?,?,?,?,?,?)";

    void insert(Customer c) throws WOHException {
        if (c == null) {
            throw new IllegalArgumentException("新增客戶時Customer物件不得為null");
        }
        try (
                Connection connection = RDBConnection.getConnection();//1.2.取得連線
                PreparedStatement pstmt = connection.prepareStatement(INSERT_CUSTOMER);) { //3.準備指令
            //3.1傳入?值
            pstmt.setString(1, c.getEmail());
            pstmt.setString(2, c.getPassword());
            pstmt.setString(3, c.getSurname());
            pstmt.setString(4, c.getName());
            pstmt.setString(5, c.getPhone());
            pstmt.setString(6, String.valueOf(c.getGender()));

            //4.執行指令
            pstmt.executeUpdate();
            System.out.println("新增客戶成功");
        } catch (SQLException ex) {
            throw new WOHException("新增客戶失敗", ex);
        }
    }

    private static final String SELECT_BY_EMAIL = "SELECT email,password,surname,name,phone,gender "
            + "FROM customers "
            + "WHERE email=?";

    Customer selectByEmail(String email) throws WOHException {
        Customer c = new Customer();
        try (
                //1.2.建立連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_EMAIL);) {
            //3.1傳入?值
            pstmt.setString(1, email);
            //4執行指令
            try (ResultSet rs = pstmt.executeQuery();) {
                //5.處理rs
                while (rs.next()) {
                    c.setEmail(rs.getString("email"));
                    c.setPassword(rs.getString("password"));
                    c.setSurname(rs.getString("surname"));
                    c.setName(rs.getString("name"));
                    c.setPhone(rs.getString("phone"));
                    c.setGender(rs.getString("gender").charAt(0));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "無法查詢客戶-建立連線或執行指令失敗", ex);
            throw new WOHException("查詢客戶失敗", ex);
        }
        return c;
    }

    private static final String UPDATE_CUSTOMER = "UPDATE customers "
            + "SET password=?,surname=?,name=?,phone=?,gender=? "
            + "WHERE email=?";

    void update(Customer c) throws WOHException{
        if (c == null) {
            throw new IllegalArgumentException("修改客戶時Customer物件不得為null");
        }
        try(
                //1.2.建立連線
                Connection connection = RDBConnection.getConnection();
                //3準備指令
                PreparedStatement pstmt =  connection.prepareStatement(UPDATE_CUSTOMER);
                ){
            //3.1 填入?值
            pstmt.setString(1, c.getPassword());
            pstmt.setString(2, c.getSurname());
            pstmt.setString(3, c.getName());
            pstmt.setString(4, c.getPhone());
            pstmt.setString(5, String.valueOf(c.getGender()));
            pstmt.setString(6, c.getEmail());
            //4.執行指令
            int rowCount = pstmt.executeUpdate();
            System.out.println("修改客戶成功rowCount = " + rowCount);
        }catch(SQLException ex){
            throw new WOHException("修改客戶資訊失敗", ex);
        }
    }
}
