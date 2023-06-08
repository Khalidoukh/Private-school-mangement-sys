package sms.dbController;

import sms.db.DBConnection;
import sms.model.Staff;

import java.sql.*;
import java.util.ArrayList;


public class StaffController {

    public static int AddStaff(Staff staff)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO staffs VALUES(?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, staff.getEmpNo());
        stm.setObject(2, staff.getTeacherName());
        stm.setObject(3, staff.getNic());
        stm.setObject(4, staff.getGender());
        stm.setObject(5, staff.getEmail());
        stm.setObject(6, staff.getPhone());
        stm.setObject(7, staff.getAddress());


        return  stm.executeUpdate();
    }

    public static Staff searchStaff(Integer empNo) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM staffs WHERE empNo = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, empNo);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Staff staff = new Staff(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7));
            return staff;
        }
        return null;
    }

    public static Staff searchStaffByName(String teacherName) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM staffs WHERE teacherName LIKE '%"+teacherName+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
       // stm.setObject(1, name);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Staff staff = new Staff(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7));
            return staff;
        }
        return null;
    }

    public static Staff searchOldStaff(Integer empNo) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM oldstaffs WHERE empNo = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, empNo);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Staff staff = new Staff(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7));
            return staff;
        }
        return null;
    }

    public static int deleteStaff(Integer empNo) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM staffs WHERE empNo ='"+empNo+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        return  stm.executeUpdate();
    }


    public static int updateStaff(Staff staff) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE staffs SET empNo= ? ,teacherName= ? ,nic= ?,gender= ? ,email=?,phone= ? ,address= ?  WHERE empNo= '" +staff.getEmpNo()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, staff.getEmpNo());
        stm.setObject(2, staff.getTeacherName());
        stm.setObject(3, staff.getNic());
        stm.setObject(4, staff.getGender());
        stm.setObject(5, staff.getEmail());
        stm.setObject(6, staff.getPhone());
        stm.setObject(7, staff.getAddress());


        return  stm.executeUpdate();
    }

    public static int updateOldStaff(Staff staff) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE staffs SET empNo= ? ,teacherName= ? ,nic= ?,gender= ? ,email=?  ,phone= ? ,address= ?  WHERE empNo= '" +staff.getEmpNo()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, staff.getEmpNo());
        stm.setObject(2, staff.getTeacherName());
        stm.setObject(3, staff.getNic());

        stm.setObject(4, staff.getGender());
        stm.setObject(5, staff.getEmail());
        stm.setObject(6, staff.getPhone());
        stm.setObject(7, staff.getAddress());


        return  stm.executeUpdate();
    }
    public static ArrayList<String> getstaff() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery("Select distinct teacherName from staffs");

        ArrayList<String>stafflist=new ArrayList<>();
        while(rst.next()){
            stafflist.add(rst.getString("teacherName"));

        }
        return stafflist;
    }
}
