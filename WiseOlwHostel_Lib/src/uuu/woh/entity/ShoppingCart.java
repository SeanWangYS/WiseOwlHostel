/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.entity;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sean
 */
public class ShoppingCart {

    private Customer member;
    private Map<Room, Integer> cart = new HashMap<>();
    private LocalDate startDate;
    private LocalDate endDate;

    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) { //以字串輸入的set
        this.startDate = LocalDate.parse(startDate);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) { //TODO 以字串輸入的set
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) { //TODO 以字串輸入的set
        this.endDate = LocalDate.parse(endDate);
    }

    public int getbookingPeriod() {
        return (int) (this.startDate.until(this.endDate, ChronoUnit.DAYS));
    }

    //mutator(s) 存值方法
    public void addToCart(Room r, int number) {
        if (r == null || number <= 0) {
            throw new IllegalArgumentException("加入購物車產品不得為null，數量必須大於0");
        } 
//        Integer oldNumber = cart.get(r);
//        if (oldNumber == null) {
//            oldNumber = 0;
//        }
//        cart.put(r, oldNumber + number);

           cart.put(r, number); //依我的網站的操作邏輯，我每次使用addToCart，不是直接add房間數量，而是重新定義房間數量-->對Map 做push的概念
        
    }

    public void update(Room r, int number) {
        if (cart.get(r) != null) {
            cart.put(r, number);
        }
    }

    public void remove(Room r) {
        if (cart.get(r) != null) {
            cart.remove(r);
        }
    }

    //accessor 取值方法
    public int size() { //total room types in this cart
        return cart.size();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public Integer getNumber(Room key) { //取得cart中該房型的Booking數量 (原名:get)
        return cart.get(key);
    }

    public Set<Room> getRoomSet() { //取得cart中所有房型的Set (原名:keySet)
        return cart.keySet();
    }

    /**
     * 取得cart中有該明細的購買金額
     *
     * @param key
     * @return
     */
    public double getUnitPriceOfRoom(Room r) {
        return r.getUnitPrice();
    }

    public static final NumberFormat priceNumberFormat = NumberFormat.getCurrencyInstance();

    public String getUnitpriceOfRoomFormedString(Room r) {  //取得某房型單價//未來可以加入VIP計算
        return priceNumberFormat.format(r.getUnitPrice());
    }

    public static final NumberFormat amountNumberFormat = NumberFormat.getIntegerInstance();

    public String getTotalAmountFormedString() { //計算總購買金額(有乘上住宿天數)
        double sum = 0;
        for (Room r : cart.keySet()) {
            double amount = r.getUnitPrice() * this.getNumber(r);
            sum += amount;
        }
        double totalAmount = sum*this.getbookingPeriod();
        return amountNumberFormat.format(totalAmount);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "member=" + member 
                + ",\n cart=" + cart
                + ",\n startDate=" + startDate + ", endDate=" + endDate
                + ",\n total room types in this cart: " + this.size() + ", total amount:" + this.getTotalAmountFormedString() + '}';
    }

}
