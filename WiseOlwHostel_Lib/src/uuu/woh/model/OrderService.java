/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.model;

import java.time.LocalDateTime;
import java.util.List;
import uuu.woh.entity.Order;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
public class OrderService {

    private OrdersDAO dao = new OrdersDAO();

    public void insert(Order order) throws WOHException {
        dao.insert(order);
    }

    public List<Order> searchOrderByCustomerEmail(String cutomerEmail) throws WOHException {
        return dao.selectOrderByCustomerEmail(cutomerEmail);
    }

    public Order searchOredrByOrderId(String orderId) throws WOHException {
        return dao.selectOredrByOrderId(orderId);
    }

    public void updateStatusToPAID(int orderId, String customerEmail, String cardF6, String cardL4,
            String auth, String paymentDate, String amount) throws WOHException {

        StringBuilder paymentNote = new StringBuilder("信用卡號:");

        paymentNote.append(cardF6 == null ? "4311-95" : cardF6).append("**-****").append(cardL4 == null ? 2222 : cardL4);

        paymentNote.append(",授權碼:").append(auth == null ? "777777" : auth);

        paymentNote.append(",交易時間:").append(paymentDate == null ? LocalDateTime.now() : paymentDate);

//        paymentNote.append(",刷卡金額:").append(amount);
        System.out.println("orderId = " + orderId);

        System.out.println("customerEmail = " + customerEmail);

        System.out.println("paymentNote = " + paymentNote);

        dao.updateStatusToPAID(orderId, customerEmail, paymentNote.toString());

    }
}
