package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sms.db.DBConnection;
import sms.model.Course;
import sms.tableModel.StaffTableModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PrintCourse implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private AnchorPane printCourse;

    @FXML
    private JFXButton Back;
    @FXML
    private JFXButton generate;
    @FXML
    private TableView<Course> CourseTable;
    ObservableList<Course> courseList = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Course, String> IDColumn;

    @FXML
    private TableColumn<Course, String> CourseNameColumn;

    @FXML
    private TableColumn<Course, String> DurationColumn;


    @FXML
    private TableColumn<Course, String> GradeColumn;

    @FXML
    private TableColumn<Course, String> RoomColumn;

    @FXML
    private TableColumn<Course, String> ProfColumn;



    @FXML
    void Back(ActionEvent event) {
        try {
            AnchorPane courseMgmnt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/CourseManagment.fxml")));
            printCourse.getChildren().setAll(courseMgmnt);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    void generate() {

        try{
            CourseTable.getItems().clear();

            Connection conn = DBConnection.getDBConnection().getConnection();



                String sql = "select * from course";
                ResultSet rs = conn.createStatement().executeQuery(sql);

                while (rs.next()) {

                    Course course = new Course(rs.getInt("courseId"),rs.getString("course_name"),rs.getInt("course_hour"),rs.getInt("gradeId"),rs.getInt("roomId"), rs.getString("empNo"));
                    courseList.add(course);
                }


            //Ctrl+D for copy above line
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
            CourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("course_name"));
            DurationColumn.setCellValueFactory(new PropertyValueFactory<>("course_hour"));
            GradeColumn.setCellValueFactory(new PropertyValueFactory<>("gradeId"));
            RoomColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
            ProfColumn.setCellValueFactory(new PropertyValueFactory<>("empNo"));

            CourseTable.setItems(courseList);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
