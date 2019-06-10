/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Sean
 */
public class Order {

    private int id; //PKey, auto-increment

    private Customer member; //required

    private LocalDate orderDate = LocalDate.now(); //required

    private LocalTime orderTime = LocalTime.now();//required

    private LocalDate startDate; //required

    private LocalDate endDate; //required

    private final Set<OrderItem> orderItemSet = new HashSet<>(); //required //加final是因為不讓new起來的HS<>() 被改掉

    private double totalAmount; //required //房間加總價格(以乘上住宿天數) //這個屬性是被算出來的

    private PaymentType paymentType; //required

    private String paymentNote; //optional

    private int status = 0; //0-新訂單,1-已付款,2-已入帳,3-已出貨,4-已送達,5-已簽收,6-已完成	

    private String customerCountry;

    private String customerCity;

    private String customerBookingEstimatedArrival;

    public int getId() {
        return id;
    }

    public String getFormatedId() {
        return String.format("WOH"+ LocalDate.now().getDayOfYear() +"%04d", this.getId());
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderDate(String orderDate) {
        if (orderDate != null) {
            this.setOrderDate(LocalDate.parse(orderDate));
        }
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderTime(String orderTime) {
        if (orderTime != null) {
            this.setOrderTime(LocalTime.parse(orderTime));
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        if (orderDate != null) {
            this.setStartDate(LocalDate.parse(startDate));
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) {
        if (orderDate != null) {
            this.setEndDate(LocalDate.parse(endDate));
        }
    }

    public int getbookingPeriod() {
        return (int) (this.startDate.until(this.endDate, ChronoUnit.DAYS));
    }

    public double getTotalAmount() {
        if (this.orderItemSet != null && this.orderItemSet.size() > 0) {
            double sum = 0;
            for (OrderItem orderItem : this.orderItemSet) {
                sum += orderItem.getRoom().getUnitPrice() * orderItem.getNumber();
            }
            return Math.round(sum * this.getbookingPeriod());

        } else {
            return Math.round(totalAmount);
        }
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerBookingEstimatedArrival() {
        return customerBookingEstimatedArrival;
    }

    public void setCustomerBookingEstimatedArrival(String customerBookingEstimatedArrival) {
        this.customerBookingEstimatedArrival = customerBookingEstimatedArrival;
    }

    //TODO: orderItemSet mutators(setter): 2 add methods
    public void add(ShoppingCart cart) { //從購物車內取出資料，做成OrderItem，在加入Set<OrderItem>
        for (Room r : cart.getRoomSet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setRoom(r);
            orderItem.setNumber(cart.getNumber(r));
            orderItem.setPrice(r.getUnitPrice());
            this.orderItemSet.add(orderItem);
        }
    }

    public void add(OrderItem item) { //把一個OrderItem 加入Set<OrderItem>
        this.orderItemSet.add(item);  //給dao從database將訂單明細料讀出並存入OrderItem物件的屬性，然後加入Order物件()中
    }

    //TODO: orderItemSet accessor(getter)
    public Set<OrderItem> getOrderItemSet() {
        //return orderItemSet;//回傳集合的參考，不宜
        return new HashSet<>(orderItemSet); //回傳集合的複本
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "id=" + id + ", member=" + member + ", orderDate=" + orderDate + ", orderTime=" + orderTime
                + ",\n startDate=" + startDate + ", endDate=" + endDate
                + ",\n orderItemSet=" + orderItemSet
                + ",\n totalAmount=" + totalAmount
                + ",\n paymentType=" + paymentType + ", paymentNote=" + paymentNote + ", status=" + status
                + ",\n customerCountry=" + customerCountry + ", customerCity=" + customerCity + ", customerBookingEstimatedArrival=" + customerBookingEstimatedArrival + '}';
    }

}
