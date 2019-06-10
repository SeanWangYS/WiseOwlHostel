/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.entity;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class StockOfRoom {
    
    private LocalDate date; //Pkey //required
    private Room room; //Pkey //required
    private int stock; //required
    //是不是應該要用MAP存房型跟stock，老師說不用，多此一舉

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}
