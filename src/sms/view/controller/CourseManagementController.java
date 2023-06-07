package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseManagementController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private AnchorPane manageCourse;
    @FXML
    private JFXButton btnCourseAdd;
    @FXML
    private JFXButton btnCourseList;
    @FXML
    private JFXButton btnCourseMng;



    @FXML
    void Back() {
        try {
            AnchorPane courseMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/MainDashboard.fxml")));
            manageCourse.getChildren().setAll(courseMgmt);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    void SetBtnCourseAdd(ActionEvent event) {
        try {
            AnchorPane courseMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/AddCourse.fxml")));
            manageCourse.getChildren().setAll(courseMgmt);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void SetBtnCourseMgment(ActionEvent event) {
        try {
            AnchorPane courseMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/ManageCourse.fxml")));
            manageCourse.getChildren().setAll(courseMgmt);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void SetBtnCourseList(ActionEvent event) {
        try {
            AnchorPane courseMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/PrintCourse.fxml")));
            manageCourse.getChildren().setAll(courseMgmt);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
