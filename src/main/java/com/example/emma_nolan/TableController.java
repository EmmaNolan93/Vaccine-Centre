package com.example.emma_nolan;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.emma_nolan.AddPatientController.heads;
import static com.example.emma_nolan.VaccinationCenterController.head;

public class TableController {
    Stage stage;
    Parent root;
    @FXML
    private ListView<String> table;
    @FXML
    private Button r;
    @FXML
    private ChoiceBox<String> patient;
    @FXML
    private ChoiceBox<String> center;
    @FXML
    private ChoiceBox<String> vaccine;
    @FXML
    private TextField batch;
    @FXML
    private Label bn;
    @FXML
    private Label bn1;

    public void initialize(){
        choiceBoxP();
        choiceBoxC();
        vaccine.getItems().addAll("AstraXeneca", "Pfizer");
    }

    public void table() { // gets all pending appointments
        VaccinationCenterNode c = head;
        while (c != null) {
            BoothNode b = c.getContents().head;
            while (b != null) {
                VAppointmentNode p = b.getContents().headi;
                if (p != null) {
                    table.getItems().add(b.getContents().printVR());
                }
                b = b.next;
            }
            c = c.next;
        }
        System.out.println("Done");
    }
    public  void search(){ // Searches through the appointments and records thar matches the patients name
        VaccinationCenterNode c = head;
        while (c !=null){
            BoothNode b = c.getContents().head;
            while (b != null) {
                VAppointmentNode a = b.getContents().headi;
                while ( a != null) {
                    if (a.getContents().getPpsn().equals(patient.getValue())) {
                        table.getItems().add(a.getContents().toString());
                    }
                     a = a.next;
                }
                b = b.next;
            }
            c = c.next;
        }
    }

    public  void searchBN(){ // Find appointments/Record that batch the Batch num of the vaccine used
        VaccinationCenterNode c = head;
        PatientNode pp = heads;
        int i = 0;
        int e = 0;
        while (c !=null){
            BoothNode b = c.getContents().head;
            while (b != null) {
                VAppointmentNode a = b.getContents().headi;
                while ( a != null){
                    if(a.getContents().getBatchNumber().equals(batch.getText()) || a.getContents().getVaccine().equals(vaccine.getValue())){
                    table.getItems().add(a.getContents().toString());
                    i ++;
                    bn.setText("Pending " + i);
                }
                a = a.next;
                }
                b = b.next;
            }
            c = c.next;
        }
        while (pp != null){
            VaccinationRecordNode r = pp.getContents().head;
            while (r != null) {
                if (r.getContents().getBatchNumber().equals(batch.getText())) {
                    table.getItems().add(r.getContents().toString());
                    e++;
                    bn1.setText("Complete " + e);
                }
                r = r.next;
            }
            pp = pp.next;
        }
    }

    public void Return(ActionEvent e) throws  Exception {
        if (e.getSource() == r) {
            stage = (Stage) r.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void  choiceBoxP() { // adds patients to the choiceBox
        PatientNode add = heads;
        while (add != null) {
            patient.getItems().add(add.getContents().getPpsn());
            add = add.next;
        }
    }
    public  void choiceBoxC(){ // Adds vc to the choiceBox
        VaccinationCenterNode add = head;
        while(add != null){
            center.getItems().add(add.getContents().getCenterName());
            add = add.next;
        }
    }
    public  void searchC(){ // Searches through the appointments and records thar matches the Centers name
        VaccinationCenterNode c = head;
        while (c !=null){
            BoothNode b = c.getContents().head;
            while (b != null) {
                if(c.getContents().getCenterName().equals(center.getValue())) {
                    VAppointmentNode a = b.getContents().headi;
                    while (a != null) {
                        table.getItems().add(a.getContents().toString());
                        a = a.next;
                    }
                    b = b.next;
                }
            }
            c = c.next;
        }
    }
}
