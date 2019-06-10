/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.woh.entity.Room;
import uuu.woh.entity.WOHException;
import uuu.woh.model.RoomService;

/**
 *
 * @author Sean
 */
public class TestRoomService {
    public static void main(String[] args) {
        RoomService service = new RoomService();
//        try {
//            List<Room> list = service.getAllRooms();
//            System.out.println("llist= " + list);
//        } catch (WOHException ex) {
//            Logger.getLogger(TestRoomService.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("ex= " + ex);
//        }
            //=================================================
        String dateFrom = "2019-07-03";
        String dateTo = "2019-07-07";
//        try {
//            List<Room> list = service.searchRoomsByDate(dateFrom, dateTo);
//            System.out.println("list=" + list);
//            System.out.println(list.get(0).getNameTitle());
//        } catch (WOHException ex) {
//            Logger.getLogger(TestRoomService.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //=========================================================
        try {
            service.autoInserStockOfRoomsByDate(dateFrom,dateTo);       
        } catch (WOHException ex) {
            Logger.getLogger(TestRoomService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
