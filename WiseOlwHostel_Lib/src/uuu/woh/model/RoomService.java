/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.model;

import java.util.List;
import uuu.woh.entity.Room;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
public class RoomService {
    
    private RoomsDAO dao = new RoomsDAO();
    
    public List<Room> getAllRooms()throws WOHException{
        return dao.selectAllRooms();
    }

    public List<Room> searchRoomsByDateAndRoomType(String startDate, String endDate, String roomType) throws WOHException {
        return dao.selectRoomsByDateAndRoomType(startDate, endDate, roomType);
    }

    public List<Room> searchRoomsByDate(String startDate, String endDate) throws WOHException {
        return dao.selectRoomsByDate(startDate, endDate);
    }
    
    public Room searchRoomById(String id) throws WOHException {
        return dao.selectRoomById(id);
    }

    public int searchQuantityOfRoomType() throws WOHException {
        return dao.selectQuantityOfRoomType();
    }

    public void autoInserStockOfRoomsByDate(String startDate, String endDate) throws WOHException {
        dao.autoInserStockOfRoomsByDate(startDate, endDate);
    }
    
    
    
}
