package sms.view.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sms.db.DBConnection;
import sms.dbController.CourseController;
import sms.dbController.GradeController;
import sms.dbController.StudentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sms.model.Course;
import sms.model.Grade;
import sms.model.Student;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;




public class RegisterStudentController implements Initializable {
    @FXML
    private Button selectCourseButton;
    @FXML
    private TextField adNoField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField dobField;

    @FXML
    private TextField doaField;

    @FXML
    private JFXRadioButton genderField;

    @FXML
    private ToggleGroup g;

    @FXML
    private ComboBox<String> loadCombo;

    @FXML
    private TextField parentNameField;

    @FXML
    private TextField nicField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private JFXButton Back;

    @FXML
    private AnchorPane root;



    @Override
    public void initialize(URL location, ResourceBundle resources){

        loadComboBox();
    }

    @FXML
    void Back() {
        try {
            AnchorPane studentMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/StudentManagement.fxml")));
            root.getChildren().setAll(studentMgmt);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void AddStudent(ActionEvent event) {
        try {

            ValidationController v = new ValidationController();

           // if(ValidationController.validateEmpty(adNoField)) //This can be used for Static Methods

            if (v.validateEmpty(adNoField) && v.validateEmpty(nameField) && v.validateEmpty(dobField) && v.validateEmpty(doaField) &&
                    v.validateEmpty(parentNameField) && v.validateEmpty(phoneField) && v.validateNIC(nicField) && v.numbersOnly(adNoField)
                    && v.validatePhone(phoneField) && v.validateDate(doaField) && v.validateDate(dobField)) {


                int adNo = Integer.parseInt(adNoField.getText());
                String fullName = fullNameField.getText();
                String name = nameField.getText();
                String dob = dobField.getText();
                String doa = doaField.getText();
                RadioButton selectedRadioButton = (RadioButton) g.getSelectedToggle(); //Getting Selected Radio Button
                String gender = selectedRadioButton.getText();
                String grade = loadCombo.getValue();
                String parentName = parentNameField.getText();
                String nic = nicField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();

                Student s = new Student(adNo, fullName, name, dob, doa, gender, grade, parentName, nic, phone, address);
                int i = StudentController.AddStudent(s);

                if (i > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Student Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("Student ID " + adNo + " Registered Successfully..!");
                    alert.showAndWait();

                    loadCombo.setValue(null);
                    fullNameField.setText(null);
                    nameField.setText(null);
                    dobField.setText(null);
                    doaField.setText(null);
                    parentNameField.setText(null);
                    nicField.setText(null);
                    phoneField.setText(null);
                    fullNameField.setText(null);
                    addressField.setText(null);
                    // Retrieve course data from the database using SQL query
                    List<Course> courses = CourseController.getCourses();

                    // Create a new dialog
                    Dialog<List<Course>> dialog = new Dialog<>();
                    dialog.setTitle("Select Course");
                    dialog.setHeaderText("Choose the courses Of this Student :");

                    // Set the button types for the dialog
                    ButtonType selectButtonType = new ButtonType("Select", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(selectButtonType, ButtonType.CANCEL);

                    // Create a GridPane to hold the checkbox choices
                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(5);

                    // Add checkbox choices for each course
                    for (int j = 0; j < courses.size(); j++) {
                        Course course = courses.get(j);
                        CheckBox checkBox = new CheckBox(course.getCourse_name());
                        gridPane.add(checkBox, 0, j);
                    }

                    // Set the content of the dialog to the GridPane
                    dialog.getDialogPane().setContent(gridPane);

                    // Convert the result to a list of selected courses when the select button is clicked
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == selectButtonType) {
                            List<Course> selectedCourses = new ArrayList<>();
                            gridPane.getChildren().forEach(node -> {
                                if (node instanceof CheckBox) {
                                    CheckBox checkBox = (CheckBox) node;
                                    if (checkBox.isSelected()) {
                                        Course course = courses.get(GridPane.getRowIndex(checkBox));
                                        selectedCourses.add(course);
                                    }
                                }
                            });
                            return selectedCourses;
                        }
                        return null;
                    });

                    // Show the dialog and wait for the user to close it
                    Optional<List<Course>> result = dialog.showAndWait();

                    // Process the selected courses

                    for (Course course : courses) {
                        int adno;
                        System.out.println(adNoField.getText());
                        adno = Integer.parseInt(adNoField.getText());
                        System.out.println(adno);
                        int courseId = course.getCourseId();

                        // Do something with the selected course
                        System.out.println("Selected Course Of this Student: " + course.getCourseId());

                        try {
                            EnrollmentController.addEnrollment(courseId, adno);

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }


                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Student Registration");
                    alert.setHeaderText(null);
                    alert.setContentText("OOPs there is an error adding Student");
                    alert.showAndWait();
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void reset() {

        adNoField.setText(null);
        fullNameField.setText(null);
        nameField.setText(null);
        dobField.setText(null);
        doaField.setText(null);
        loadCombo.setValue(null);
        adNoField.setText(null);
        parentNameField.setText(null);
        nicField.setText(null);
        phoneField.setText(null);
        fullNameField.setText(null);
        addressField.setText(null);
    }

    @FXML
    private void loadComboBox(){
        ArrayList arrayList = null;
        try {
            arrayList = GradeController.getGrades();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ObservableList observableArray = FXCollections.observableArrayList();
        observableArray.addAll(arrayList);

        if (observableArray != null){
            loadCombo.setItems(observableArray);
        }

    }




}



