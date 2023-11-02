package com.example.emma_nolan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Objects;

import static com.example.emma_nolan.AddPatientController.heads;
import static com.example.emma_nolan.AddPatientController.isValidName;
import static com.example.emma_nolan.VaccinationCenterController.head;

public class CreateVAppoimentsController {
        Stage stage;
        Parent root;

        LocalDate date;
        @FXML
        private Label day, time;
        @FXML
        private DatePicker dates;
        @FXML
        private ChoiceBox<String> timeBox;
        @FXML
        private ChoiceBox<String> v;
        @FXML
        private ChoiceBox<String> l;
        @FXML
        private Button cancelled;
        @FXML
        private ChoiceBox<String> booth;
        @FXML
        private ChoiceBox<String> patient;
        @FXML
        private TextField bN;
        @FXML
        private TextField vN;
        @FXML
        private Label vVN;
        @FXML
        private Label sS;

        public void initialize(){
            timeBox.getItems().addAll("10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00");
            v.getItems().addAll("AstraXeneca", "Pfizer");
            choiceBoxL();
            choiceBoxP();
        }
        public void DatePicker() {
            date = dates.getValue();
            day.setText("Selected date: " + date);
        }

        @FXML
        public void submitBox() { // Adds data to the linked list of all the textfield are filled in which the correct into
            int empty = String.valueOf(timeBox.getValue()).equals("null")? 0:
                    String.valueOf(v.getValue()).equals("null")? 0:
                            String.valueOf(dates.getValue()).equals("null")? 0:
                                    String.valueOf(l.getValue()).equals("null")? 0:
                                            String.valueOf(booth.getValue()).equals("null")? 0:
                                                    String.valueOf(patient.getValue()).equals("null")? 0:
                                                            String.valueOf(bN.getText()).equals("")? 0:
                                                                    String.valueOf(vN.getText()).equals("")? 0:1;
            if(empty == 1) {
                VAppointment vr = new VAppointment(String.valueOf(l.getValue()), String.valueOf(dates.getValue()), String.valueOf(timeBox.getValue()), String.valueOf(v.getValue()), String.valueOf(booth.getValue()), String.valueOf(patient.getValue()), String.valueOf(bN.getText()), vN.getText());
                String pName = String.valueOf(booth.getValue());
                addVP(pName, vr);
                sS.setTextFill(Color.GREEN);
                sS.setText("Added");
            }
            else {
                System.out.println("Everything must be filled in");
            }
        }
        public  void choiceBoxL(){ // adds the vc to the choiceBox
            VaccinationCenterNode add = head;
            while(add != null){
                l.getItems().add(add.getContents().getCenterName());
                add = add.next;
            }
        }
        public  void choiceBoxB(){ // Adds booths based on the chosen vc selected above it
            int i = l.getSelectionModel().getSelectedIndex();
            int count = 0;
            VaccinationCenterNode add = head;
            AppiomentStatusController.count(booth, i, count, add);
        }

        public void  choiceBoxP() { // adds all patients to the choiceBox
            PatientNode add = heads;
            while (add != null) {
                patient.getItems().add(add.getContents().getPpsn());
                add = add.next;
            }
        }

        public void dT(){ // Checks to see if the selected date and time are available
            VaccinationCenterNode c = head;
            BoothNode b = c.getContents().head;
            date = dates.getValue();
            while(b != null && !b.getContents().getBoothId().equals(booth.getValue())) {
                b = b.next;
            }
            if(b != null) {
                VAppointmentNode p = b.getContents().headi;
                while (p != null  && !p.getContents().getDate().equals(String.valueOf(dates.getValue()))) {
                    p = p.next;
                }
                if (p != null && p.getContents().getTime().equals(timeBox.getValue())) {
                    time.setText("This Time and date is already in use");
                    timeBox.setValue(null);
                    dates.setValue(null);
                }
            }
        }
        public static void addVP(String id, VAppointment p) { // Creates the appointments
            VaccinationCenterNode add = head;
            boolean flag = false;
            while (add != null && !flag) {
                VaccinationCenter vc = add.getContents();
                BoothNode bb = vc.head;
                while( bb != null && !bb.getContents().getBoothId().equals(id)){
                    bb = bb.next;
                }
                if(bb != null){
                    bb.getContents().addVP(p);
                    System.out.println(bb.getContents().printVR());
                    flag = true;
                }
                add = add.next;
            }
        }

        public void Vname(){ // Makes sure the vaccinations name is valid
            vN.setOnKeyReleased(event -> {
                if( isValidName(vN.getText() ) ){
                    vVN.setTextFill(Color.GREEN);
                    vVN.setText("Valid Name");
                }else{
                    vVN.setTextFill(Color.RED);
                    vVN.setText("Invalid Name");
                }
            });
        }
        public void goReturn(ActionEvent e) throws  Exception{
            if(e.getSource()== cancelled){
                stage = (Stage) cancelled.getScene().getWindow();
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
