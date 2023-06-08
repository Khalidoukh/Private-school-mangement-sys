package sms.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SchedulesMangementController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private AnchorPane ScheduleTime;

        @FXML
        void Back() {
            try {
                AnchorPane courseMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/MainDashboard.fxml")));
                ScheduleTime.getChildren().setAll(courseMgmt);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
