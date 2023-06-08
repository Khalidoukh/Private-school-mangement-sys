package sms.dbController;

import javafx.fxml.FXML;
import sms.db.DBConnection;
import sms.model.Course;
import sms.model.Staff;
import sms.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseController {
    public static List<Course> getCourses() throws SQLException, ClassNotFoundException {
        List<Course> courses = new ArrayList<>();

        String SQL = "SELECT * FROM course";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            int courseId = rst.getInt("courseId");
            String courseName = rst.getString("course_name");

            // Create a new Course object with the retrieved data
            Course course = new Course(courseId, courseName);

            // Add the course to the list
            courses.add(course);
        }

        return courses;
    }
    public static int updateCourse(Course course) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE course SET courseId= ? ,course_name= ? ,course_hour= ?  WHERE courseId= '" +course.getCourseId()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, course.getCourseId());
        stm.setObject(2, course.getCourse_name());
        stm.setObject(3, course.getCourse_hour());



        return  stm.executeUpdate();
    }
    public static int AddCourse(Course course)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO course VALUES(?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, course.getCourseId());
        stm.setObject(2, course.getCourse_name());
        stm.setObject(3, course.getCourse_hour());
        stm.setObject(4, course.getGradeId());
        stm.setObject(5, course.getRoomId());
        stm.setObject(6, course.getEmpNo());


        return  stm.executeUpdate();
    }
    public static int deleteCourse(int courseId) throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM course WHERE courseId ='"+courseId+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);


        return  stm.executeUpdate();
    }
    public static Course searchCourseId(Integer courseId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM course WHERE courseId = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, courseId);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Course s=new Course(rst.getInt(1),rst.getString(2),rst.getInt(3));
            return s;
        }
        return null;
    }

    public static Course searchCourseName(String courseName) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM course WHERE course_name = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, courseName);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            Course c=new Course(rst.getInt(1),rst.getString(2),rst.getInt(3));
            return c;
        }
        return null;
    }
    public static ArrayList<String> getCourseName() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery("Select course_name  from course");

        ArrayList<String>stafflist=new ArrayList<>();
        while(rst.next()){
            stafflist.add(rst.getString("course_name"));

        }
        return stafflist;
    }

}
