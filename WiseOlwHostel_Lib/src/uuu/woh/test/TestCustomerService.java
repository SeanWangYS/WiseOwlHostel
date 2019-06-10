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
import uuu.woh.model.CustomerService;

/**
 *
 * @author Sean
 */
public class TestCustomerService {
    public static void main(String[] args) {
//        //========== register test===========================
//        Customer c2 = new Customer("test2@gmail.com", "123456", "Wang","Yu_Sheng" ); 
//        try {
//            c2.setGender('M');
//            c2.setPhone("0916221209");
//            
//        } catch (WOHException ex) {
//            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "客戶資料錯誤", ex);
//        } catch(Exception ex){
//            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "發生非預期錯誤", ex);
//        }
//        System.out.println("c2= " + c2);
//        //=======================================================
//        CustomerService service = new CustomerService();
//        try {
//            service.register(c2);
//        } catch (WOHException ex) {
//            Logger.getLogger(TestCustomerService.class.getName()).log(Level.SEVERE, null, ex);
//        }

//    //========== register test===========================
//    String email =  "test2@gmail.com";
//    String password = "123456";
//    CustomerService service = new CustomerService();
//    
//        try {
//            Customer c =  service.login(email, password);
//            System.out.println("c=" + c);
//        } catch (WOHException ex) {
//            Logger.getLogger(TestCustomerService.class.getName()).log(Level.SEVERE, null, ex);
//        }


//    //========== update test===========================
        Customer c2 = new Customer("test2@gmail.com", "123456", "Wang","Frida" ); 
        try {
            c2.setGender('F');
            c2.setPhone("0916338338");

        } catch (WOHException ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "客戶資料錯誤", ex);
        } catch (Exception ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "發生非預期錯誤", ex);
        }
        System.out.println("c2= " + c2);
        
        CustomerService service = new CustomerService();
        try {
            service.update(c2);
        } catch (WOHException ex) {
            Logger.getLogger(TestCustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
