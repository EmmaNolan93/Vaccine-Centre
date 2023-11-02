package com.example.emma_nolan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("FirstPage.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 320, 360);
        stage.setScene(scene1);
        try {
            VaccinationCenterController.load();
            AddPatientController.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}