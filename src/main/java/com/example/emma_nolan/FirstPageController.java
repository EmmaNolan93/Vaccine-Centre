package com.example.emma_nolan;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class FirstPageController {
    Stage stage;
    Parent root;
    @FXML
    private Button reg;
    @FXML
    private Button addP;
    @FXML
    private Button addB;
    @FXML
    private Button addV;
    @FXML
    private Button delB;
    @FXML
    private Button statusB;
    @FXML
    private Button View;
    @FXML
    private void regBox (ActionEvent event) throws Exception {
        if(event.getSource()==reg){
            stage = (Stage) reg.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPatient.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void addPBox (ActionEvent event) throws Exception {
        if(event.getSource()==addP){
            stage = (Stage) addP.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateVAppointment.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void vBox (ActionEvent event) throws Exception {
        if(event.getSource()==addV){
            stage = (Stage) addV.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VaccinationCenter.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void bBox(ActionEvent event) throws Exception{
        if(event.getSource()==addB){
            stage = (Stage) addB.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Booth.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void DelBox(ActionEvent event) throws Exception{
        if(event.getSource()==delB){
            stage = (Stage) delB.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteBooths.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void statusBox(ActionEvent event) throws Exception{
        if(event.getSource()==statusB){
            stage = (Stage) statusB.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ApppoinmentStatus.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void View(ActionEvent event) throws Exception{
        if(event.getSource()==View){
            stage = (Stage) View.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Table.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void clears(){
        VaccinationCenterController.clearSystem();
    }
}
