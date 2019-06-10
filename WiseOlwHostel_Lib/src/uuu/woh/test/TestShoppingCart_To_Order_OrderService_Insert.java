/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.test;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.woh.entity.Customer;
import uuu.woh.entity.Order;
import uuu.woh.entity.PaymentType;
import uuu.woh.entity.Room;
import uuu.woh.entity.ShoppingCart;
import uuu.woh.entity.WOHException;
import uuu.woh.model.CustomerService;
import uuu.woh.model.OrderService;
import uuu.woh.model.RoomService;

/**
 *
 * @author Sean
 */
public class TestShoppingCart_To_Order_OrderService_Insert {

    public static void main(String[] args) {
        try {
            CustomerService service = new CustomerService();
            Customer c = service.login("SeanWang@gmail.com", "123456");
            
            RoomService rService = new RoomService();
            Room r1 = rService.searchRoomById("1");
            Room r2 = rService.searchRoomById("2");
            Room r3 = rService.searchRoomById("3");
            System.out.println("r3 = " + r3);
            LocalDate startDat = LocalDate.parse("2019-06-15");
            LocalDate endDat = LocalDate.parse("2019-06-17");
            
            ShoppingCart cart = new ShoppingCart();
            cart.setMember(c);
            cart.addToCart(r1, 1);
            cart.addToCart(r2, 1);
            cart.addToCart(r3, 1);
            cart.addToCart(r1, 1);
            cart.setStartDate(startDat);
            cart.setEndDate(endDat);

            //結帳
            Order order = new Order();
            order.setMember(c);
            order.setStartDate(startDat);
            order.setEndDate(endDat);
            order.setPaymentType(PaymentType.CARD);
            order.add(cart);
            order.setCustomerBookingEstimatedArrival("15:00");

            //insert to database
            OrderService oService = new OrderService();
            System.out.println("印訂單內容 = " + order);
            oService.insert(order);
                 
            
            
        } catch (WOHException ex) {
            Logger.getLogger(TestShoppingCart_To_Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
