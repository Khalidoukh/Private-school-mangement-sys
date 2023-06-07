package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sms.dbController.CourseController;
import sms.dbController.StaffController;
import sms.dbController.StudentController;
import sms.model.Course;
import sms.model.Staff;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageCourse implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private AnchorPane manageCourse;
    @FXML
    private TextField Coursd;
    @FXML
    private TextField CoursdS;
    @FXML
    private TextField CourseName;
    @FXML
    private TextField CourseNameS;
    @FXML
    private TextField courseDuration;
    @FXML
    private JFXButton Back;

    @FXML
    void Back(ActionEvent event) {
        try {
            AnchorPane courseMgmnt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/CourseManagment.fxml")));
            manageCourse.getChildren().setAll(courseMgmnt);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void update(ActionEvent event) {
        try {

            ValidationController v = new ValidationController();

            if(v.validateEmpty(Coursd)&& v.validateEmpty(CourseName)&& v.validateEmpty(courseDuration)){


                int coursd = Integer.parseInt(Coursd.getText());
                String courseName = CourseName.getText();
                int courseduration = Integer.parseInt(courseDuration.getText());


                Course s = new Course(coursd,courseName,courseduration);
                int i = CourseController.updateCourse(s);

                if (i > 0){

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Staff Management");
                    alert.setHeaderText(null);
                    alert.setContentText("Staff Informations Updated Successfully..!");
                    alert.showAndWait();

                    Coursd.setText(null);
                    CourseName.setText(null);
                    courseDuration.setText(null);


                }
               else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Course Management");
                    alert.setHeaderText(null);
                    alert.setContentText("OOPS there is an error updating Course..!");
                    alert.showAndWait();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void btnDelete(ActionEvent event) {
        try {
            int CId = Integer.parseInt(Coursd.getText());
            Course c = new Course(Integer.parseInt(Coursd.getText()), CourseName.getText(), Integer.parseInt(courseDuration.getText()));

            if(!Coursd.getText().isEmpty()) {

                    int deleteStudent = CourseController.deleteCourse(CId);
                    if (deleteStudent > 0) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Delete Student");
                        alert.setHeaderText(null);
                        alert.setContentText("course " + CourseName.getText() + " has been deleted sucessfully..!");
                        alert.showAndWait();

                        Coursd.setText(null);
                        CourseName.setText(null);
                        courseDuration.setText(null);


                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Delete Student");
                        alert.setHeaderText(null);
                        alert.setContentText("There is an error deleting Student");
                        alert.showAndWait();
                    }

            }
             else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Course");
                    alert.setHeaderText(null);
                    alert.setContentText("There is an error deleting Course");
                    alert.showAndWait();
                }

        } catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchCourseId(ActionEvent event) {
        try {
            int courseId = Integer.parseInt(CoursdS.getText());
            Course s = CourseController.searchCourseId(courseId);
            if (s != null) {
                Coursd.setText(String.valueOf(s.getCourseId()));
                CourseName.setText(s.getCourse_name());
                courseDuration.setText(String.valueOf(s.getCourse_hour()));


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Course Search");
                alert.setHeaderText(null);
                alert.setContentText("Course Not Found");
                alert.showAndWait();

                Coursd.setText(null);
                CourseName.setText(null);
                courseDuration.setText(null);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchCourseName(ActionEvent event) {
        try {
            String courseName = CourseNameS.getText();
            Course s = CourseController.searchCourseName(courseName);
            if (s != null) {

                Coursd.setText(String.valueOf(s.getCourseId()));
                CourseName.setText(s.getCourse_name());
                courseDuration.setText(String.valueOf(s.getCourse_hour()));


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Course Search");
                alert.setHeaderText(null);
                alert.setContentText("Course Not Found");
                alert.showAndWait();

                Coursd.setText(null);
                CourseName.setText(null);
                courseDuration.setText(null);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
