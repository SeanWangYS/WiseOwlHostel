/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.test;

import uuu.woh.entity.Room;

/**
 *
 * @author Sean
 */
public class TestRoom {
    public static void main(String[] args) {
        Room r = new Room(10, "my first room" ,70.23);
        
        System.out.println(r.getId());
        System.out.println(r.getName());
        System.out.println(r.getUnitPrice());
        r.setStock(5);
        System.out.println(r.getStock());
        System.out.println(r.toString());
        
        r.setStock(r.getStock()+5);
        
        System.out.println(r.getStock());
       
    }
}
