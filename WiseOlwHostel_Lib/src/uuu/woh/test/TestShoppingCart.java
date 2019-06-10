/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.woh.entity.Customer;
import uuu.woh.entity.Room;
import uuu.woh.entity.ShoppingCart;
import uuu.woh.entity.WOHException;
import uuu.woh.model.CustomerService;
import uuu.woh.model.RoomService;

/**
 *
 * @author Sean
 */
public class TestShoppingCart {
    
    public static void main(String[] args) {
        
        try {
            CustomerService service = new CustomerService();
            Customer c1 = service.login("FridaWang@gmail.com", "123456");
            
            RoomService roomservice = new RoomService();
            Room r1 = roomservice.searchRoomById("1");
            Room r2 = roomservice.searchRoomById("2");
            Room r3 = roomservice.searchRoomById("2");
           
            
            LocalDate startDat = LocalDate.parse("2019-06-15");
            LocalDate endDat = LocalDate.parse("2019-06-17");
            
            ShoppingCart scart =  new ShoppingCart();
            scart.setMember(c1);
            scart.setEndDate(endDat);
            scart.setStartDate(startDat);
            scart.addToCart(r1, 320);
            scart.addToCart(r2, 1);
             scart.addToCart(r2, 1);
             scart.addToCart(r2, 500);
             scart.addToCart(r3, 777); // Room 要加上 hashCode 才能在MAP中被辨認並使用
             
            
         System.out.println(scart.toString());

            
            
        } catch (WOHException ex) {
            Logger.getLogger(TestShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
