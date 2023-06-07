package sms.view.controller;

import sms.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomController {
    public static ArrayList<String> getRoom() throws ClassNotFoundException, SQLException {
        Connection conn= DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery("Select roomNum From rooms");

        ArrayList<String>roomList=new ArrayList<>();
        while(rst.next()){
            //Grade grade;
            //grade = new Grade(rst.getString("grade"));
            roomList.add(rst.getString("roomNum"));
        }
        return roomList;
    }
}
