/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.woh.entity.Customer;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
public class TestCustomer {
    
    public static void main(String[] args) {
       Customer c = new Customer("test1@gmail.com", "123456", "Wang","Yu_Sheng" ); 
        try {
            c.setGender('M');
            c.setPhone("0916221209");
            
        } catch (WOHException ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "客戶資料錯誤", ex);
        } catch(Exception ex){
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "發生非預期錯誤", ex);
        }
       
        System.out.println("c= " + c);
        
        
       
    }
    
    
    
    
}
