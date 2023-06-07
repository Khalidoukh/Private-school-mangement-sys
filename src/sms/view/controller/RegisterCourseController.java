package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sms.dbController.CourseController;
import sms.dbController.GradeController;
import sms.dbController.StudentController;
import sms.model.Course;
import sms.model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterCourseController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBox();
        loadComboBox1();
        loadComboBox2();
    }
    @FXML
    private AnchorPane addCourse;

    @FXML
    private TextField courseID;

    @FXML
    private TextField coursena;

    @FXML
    private TextField courseDur;


    @FXML
    private ComboBox loadCombo;

    @FXML
    private ComboBox loadCombo1;

    @FXML
    private ComboBox loadCombo2;


    @FXML
    private JFXButton Back;

    @FXML
    void Back(ActionEvent event) {
        try {
            AnchorPane courseMgmnt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/CourseManagment.fxml")));
            addCourse.getChildren().setAll(courseMgmnt);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void AddCourse(ActionEvent event) {
        try {

            ValidationController v = new ValidationController();

            // if(ValidationController.validateEmpty(adNoField)) //This can be used for Static Methods

            if (v.validateEmpty(courseID) && v.validateEmpty(coursena) && v.validateEmpty(courseDur)) {


                int CouId = Integer.parseInt(courseID.getText());
                String courseName = coursena.getText();
                int duration;
                duration = Integer.parseInt(courseDur.getText());
                int grade;
                grade = Integer.parseInt(loadCombo.getValue().toString());
                int room = Integer.parseInt(loadCombo1.getValue().toString());
                String prof = String.valueOf(loadCombo2.getValue());
                System.out.println(prof);

                Course course = new Course(CouId,courseName,duration,grade,room,prof);
                int i = CourseController.AddCourse(course);

                if (i > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Course Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("Course Name " + coursena.getText() + " Registered Successfully..!");
                    alert.showAndWait();

                    courseID.setText(null);
                    coursena.setText(null);
                    courseDur.setText(null);
                    loadCombo.setValue(null);
                    loadCombo1.setValue(null);
                    loadCombo2.setValue(null);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Course Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("OOPs there is an error adding Course");
                    alert.showAndWait();
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // reset course
    @FXML
    private void reset() {
        coursena.setText(null);
        courseDur.setText(null);
        courseID.setText(null);
        loadCombo.setValue(null);
        loadCombo1.setValue(null);
        loadCombo2.setValue(null);

    }

    @FXML
    private void loadComboBox(){
        ArrayList arrayList = null;
        try {
            arrayList = GradeController.getGradesId();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ObservableList observableArray = FXCollections.observableArrayList();
        observableArray.addAll(arrayList);

        if (observableArray != null){
            loadCombo.setItems(observableArray);
        }

    }
    @FXML
    private void loadComboBox1(){
        ArrayList arrayList = null;
        try {
            arrayList = RoomController.getRoom();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ObservableList observableArray = FXCollections.observableArrayList();
        observableArray.addAll(arrayList);

        if (observableArray != null){
            loadCombo1.setItems(observableArray);
        }

    }
    @FXML
    private void loadComboBox2(){
        ArrayList arrayList = null;
        try {
            arrayList = PrintStaffsController.getProf();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ObservableList observableArray = FXCollections.observableArrayList();
        observableArray.addAll(arrayList);

        if (observableArray != null){
            loadCombo2.setItems(observableArray);
        }

    }
}
