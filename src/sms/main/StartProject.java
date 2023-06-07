package sms.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sms.dbController.CourseController;
import sms.model.Course;

import java.sql.SQLException;
import java.util.List;

public class StartProject extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sms/view/fxml/login.fxml"));
        primaryStage.setTitle("School Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/sms/other/img/HikmaLogo.jpg")));
        primaryStage.show();
    }
    public static void main(String[] args) {

        launch(args);
        //get course
        try {
            // Get the list of courses
            List<Course> courses = CourseController.getCourses();

            // Print the list of courses
            for (Course course : courses) {
                System.out.println(course.getCourseId() + ": " + course.getCourse_name());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}