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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import uuu.woh.entity.Customer;
import uuu.woh.entity.Order;
import uuu.woh.entity.OrderItem;
import uuu.woh.entity.PaymentType;
import uuu.woh.entity.Room;
import uuu.woh.entity.StockShortageException;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
public class OrdersDAO {

    private static final String INSERT_ORDER = "INSERT INTO orders "
            + "(id,customer_email,order_date,order_time,start_date,end_date, "
            + "payment_type,status, "
            + "customer_country,customer_city,customer_booking_estimated_arrival) "
            + "VALUES "
            + "(?,?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_items "
            + "(order_id,room_id,`number`,price) "
            + "VALUES(?,?,?,?);";

    private static final String UPDATE_ROOM_STOCK
            = "UPDATE woh.stock_of_rooms "
            + "SET "
            + "stock = stock-? "
            + "WHERE date = ? AND room_id = ?";

    void insert(Order order) throws WOHException {

        if (order == null) {
            throw new IllegalArgumentException("建立訂單時資料不得為mull");
        }
        try (
                //1.2.建立連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt0 = connection.prepareStatement(UPDATE_ROOM_STOCK);
                PreparedStatement pstmt1 = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement pstmt2 = connection.prepareStatement(INSERT_ORDER_ITEM);) {
            connection.setAutoCommit(false);
            try {
                //3.0.處理庫存
                LocalDate stratDate = order.getStartDate();
                for (int i = 0; i <= order.getbookingPeriod()-1; i++) {
                    String Date = stratDate.plusDays(i).toString();
                    pstmt0.setString(2, Date);
                    for (OrderItem item : order.getOrderItemSet()) {
                        pstmt0.setInt(3, item.getRoom().getId());
                        pstmt0.setInt(1, item.getNumber());
                        int rows = pstmt0.executeUpdate();
                        System.out.println("rows="+ rows);
                        if (rows == 0) {
                            throw new StockShortageException(item.getRoom());
                        }
                    }
                }
                //3.1傳入?的值pstmt1
                pstmt1.setInt(1, order.getId());  // 第一個?  會丟進去該屬性的default值0 (因為先前沒設定過)
                pstmt1.setString(2, order.getMember().getEmail());
                pstmt1.setString(3, order.getOrderDate().toString());
                pstmt1.setString(4, order.getOrderTime().toString());
                pstmt1.setString(5, order.getStartDate().toString());
                pstmt1.setString(6, order.getEndDate().toString());
                pstmt1.setString(7, order.getPaymentType().name());
                pstmt1.setInt(8, order.getStatus());
                pstmt1.setString(9, order.getCustomerCountry());
                pstmt1.setString(10, order.getCustomerCity());
                pstmt1.setString(11, order.getCustomerBookingEstimatedArrival());

                //4.執行指令pstmt1
                pstmt1.executeUpdate(); //這裡執行完，order table中的 id column(PKey) 已經自動給號
                //5.處理rs
                try (ResultSet rs = pstmt1.getGeneratedKeys()) { //執行完上面的指令，利用.getGeneratedKeys())取得有自動給號的資料
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        order.setId(id); //這裡存入Order 的id 屬性，是為了給下面使用
                    }
                }

                //新增OrderItem
                System.out.println("pstmt2 = " + pstmt2);
                for (OrderItem item : order.getOrderItemSet()) {
                    //3.1傳入pstmt2中?的值
                    pstmt2.setInt(1, order.getId());  //就是給這裡用
                    pstmt2.setInt(2, item.getRoom().getId());
                    pstmt2.setInt(3, item.getNumber());
                    pstmt2.setDouble(4, item.getRoom().getUnitPrice());

                    //4.執行指令pstmt2
                    pstmt2.executeUpdate();
                }
                connection.commit();
                System.out.println("建立訂單成功");

            } catch (Exception ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new WOHException("建立訂單失敗", ex);
        }
    }

    private static final String SELECT_ORDER_BY_CUSTOMER_EMAIL
            = "SELECT orders.id, customer_email, order_date, order_time, start_date, end_date, payment_type, status, customer_booking_estimated_arrival, "
            + "SUM(price* `number`) as total_amount "
            + "FROM orders LEFT JOIN order_items ON orders.id= order_items.order_id "
            + "WHERE customer_email=? "
            + "GROUP BY orders.id "
            + "ORDER BY order_date desc, order_time desc";

    List<Order> selectOrderByCustomerEmail(String cutomerEmail) throws WOHException {
        List<Order> list = new ArrayList<>();

        try (
                //1.2.取得連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDER_BY_CUSTOMER_EMAIL);) {
            //3.1傳入?值
            pstmt.setString(1, cutomerEmail);

            //4執行指令
            ResultSet rs = pstmt.executeQuery();

            //5.處理rs
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));

                Customer member = new Customer();
                member.setEmail(rs.getString("customer_email"));
                order.setMember(member);

                order.setOrderDate(rs.getString("order_date"));
                order.setOrderTime(rs.getString("order_time"));

                order.setStartDate(rs.getString("start_date"));
                order.setEndDate(rs.getString("end_date"));
                
                //從database撈出來的total_amount還未乘上住宿天數，這邊做處理
                double new_total_amount = rs.getDouble("total_amount")*order.getbookingPeriod();
                order.setTotalAmount(new_total_amount);
                
                order.setPaymentType(PaymentType.valueOf(rs.getString("payment_type")));
                order.setStatus(rs.getInt("status"));

                order.setCustomerBookingEstimatedArrival(rs.getString("customer_booking_estimated_arrival"));

                list.add(order);
            }
        } catch (SQLException ex) {
            throw new WOHException("以客戶信箱查詢該客戶歷史訂單失敗");
        }
        return list;
    }

    private static final String SELECT_ORDER_BY_ORDER_ID
            = "SELECT orders.id as oid, customers.email as cemail, customers.name as cname, customers.surname as csurname,  "
            + "order_date, order_time, start_date, end_date, status, customer_country, customer_city, customer_booking_estimated_arrival, "
            + "payment_type, payment_note, "
            + "room_id, rooms.name as rname, photo_url, price, `number`  "
            + "FROM orders  "
            + "LEFT JOIN order_items ON orders.id = order_items.order_id "
            + "LEFT JOIN customers ON orders.customer_email= customers.email "
            + "LEFT JOIN rooms ON order_items.room_id = rooms.id "
            + "WHERE orders.id = ?";

    Order selectOredrByOrderId(String orderId) throws WOHException {
        Order order = new Order();
        try (
                //1.2. 取得連線    
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDER_BY_ORDER_ID);) {
            //3.1傳入?值
            pstmt.setString(1, orderId);
            //4.執行指令
            ResultSet rs = pstmt.executeQuery();
            //5.處理rs
            while (rs.next()) {
                order.setId(rs.getInt("oid"));
                order.setOrderDate(rs.getString("order_date"));
                order.setOrderTime(rs.getString("order_time"));
                order.setStartDate(rs.getString("start_date"));
                order.setEndDate(rs.getString("end_date"));
                order.setPaymentType(PaymentType.valueOf(rs.getString("payment_type")));
                order.setStatus(rs.getInt("status"));
                order.setCustomerBookingEstimatedArrival(rs.getString("customer_booking_estimated_arrival"));
                order.setCustomerCountry(rs.getString("customer_country"));
                order.setCustomerCity(rs.getString("customer_city"));

                OrderItem item = new OrderItem();
                item.setOrderId(rs.getInt("oid"));

                RoomService rService = new RoomService();
                Room r = rService.searchRoomById(String.valueOf(rs.getInt("room_id")));
                item.setRoom(r);

                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getDouble("price"));

                order.add(item);
            }
        } catch (SQLException ex) {
            throw new WOHException("以訂單ID查詢訂單失敗");
        }
        return order;
    }
    
     private static final String UPDATE_STATUS_TO_ENTERED = "UPDATE orders SET status=2" //狀態設定為已付款

            + ", payment_note=? WHERE id=? AND customer_email=?"

            + " AND status=0" + " AND payment_type='" + PaymentType.CARD.name() + "'";

 

    public void updateStatusToPAID(int orderId, String customerEmail, String paymentNote) throws WOHException {

        try (Connection connection = RDBConnection.getConnection(); //2. 建立連線

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_STATUS_TO_ENTERED) //3. 準備指令

                ) {

            //3.1 傳入?的值

            pstmt.setString(1, paymentNote);

            pstmt.setInt(2, orderId);

            pstmt.setString(3, customerEmail);

 

            //4. 執行指令

            pstmt.executeUpdate();

        } catch (SQLException ex) {

            System.out.println("修改信用卡付款入帳狀態失敗-" + ex);

            throw new WOHException("修改信用卡付款入帳狀態失敗!", ex);

        }

    }
}
