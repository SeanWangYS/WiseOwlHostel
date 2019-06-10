/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
public class RDBConnection {
    
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/woh?zeroDateTimeBehavior=convertToNull&characterEncondig=utf8";
    private static final String userid = "root";
    private static final String pwd = "1234";
    
    public static Connection getConnection() throws WOHException{
        
        try {
            //1.載入Driver
            Class.forName(driver);
            
            try {
                //2.建立連線
                Connection connection = DriverManager.getConnection(url, userid, pwd);
                return connection;
            } catch (SQLException ex) {
                Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw new WOHException("建立連線失敗",ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new WOHException("載入Driver失敗",ex);
        }
        
    }
    
}
