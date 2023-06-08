package sms.view.controller;

import sms.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollmentController {
    public static void addEnrollment(int courseId, int studentId) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO enrollment (course_id, student_id) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Get a connection to the database
            connection = DBConnection.getDBConnection().getConnection();
            // Create the prepared statement
            statement = connection.prepareStatement(sql);
            // Set the parameter values
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            // Execute the query
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
