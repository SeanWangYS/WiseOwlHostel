/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.entity;

/**
 *
 * @author Sean
 */
public class StockShortageException extends WOHException{
    
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

/**
     * Creates a new instance of <code>StockShortageException</code> without
     * detail message.
     */
    public StockShortageException() {
        super("產品庫存不足");
    }

    public StockShortageException(Room room) {
        super("產品庫存不足");
        this.room = room;
    }
    
    
    /**
     * Constructs an instance of <code>StockShortageException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StockShortageException(String message) {
        super(message);
    }

    public StockShortageException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return this.getClass().getName() +"-"+ this.getMessage() + ":{" + "room=" + room + '}';
    }
    
    
    
    
}
