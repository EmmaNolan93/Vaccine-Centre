package com.example.emma_nolan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.emma_nolan.AddPatientController.heads;
import static com.example.emma_nolan.VaccinationCenterController.head;

public class AppiomentStatusController {
        Stage stage;
        Parent root;
        @FXML
        private ChoiceBox<String> center;
        @FXML
        private ChoiceBox<String> booth;
        @FXML
        private ChoiceBox<String> p;
        @FXML
        private Button r;
        @FXML
        private Label sC, sD;

        public void initialize() {
            choiceBoxV();
        }

        public void choiceBoxV() { // adds vaccinations centers to the choice Box
            VaccinationCenterNode add = head;
            while (add != null) {
                center.getItems().add(add.getContents().getCenterName());
                add = add.next;
            }
        }

        public void vr() { // Completes the vc appointments and makes a vaccination record for the patient while also deleting the appointments form the booth
            VaccinationCenterNode vc = head;
            boolean flag = false;
            while (vc != null && !flag) {
                VaccinationCenter add = vc.getContents();
                BoothNode bb = add.head;
                while (bb != null && !bb.getContents().getBoothId().equals(String.valueOf(booth.getValue()))) {
                    bb = bb.next;
                }
                assert bb != null;
                VAppointmentNode vp = bb.getContents().headi;
                while (vp != null && !vp.getContents().getPpsn().equals(String.valueOf(p.getValue()))) {
                    vp = vp.next;
                }
                if (vp != null) {
                    flag = true;
                    VaccinationRecord v = new VaccinationRecord(String.valueOf(p.getValue()), vp.getContents().getDate(), vp.getContents().getVaccine(), vp.getContents().getBatchNumber());
                    addVR(p.getValue(), v);
                    bb.getContents().delVA(String.valueOf(p.getValue()));
                    sC.setTextFill(Color.GREEN);
                    sC.setText("Done");

                }
                vc = vc.next;
            }
        }

        public void choiceBoxB() {
            add(center, booth);
        } // Add boots to the cb based on which vc is chosen form the above choiceBox

        static void add(ChoiceBox<String> center, ChoiceBox<String> booth) {
            int i = center.getSelectionModel().getSelectedIndex();
            int count = 0;
            VaccinationCenterNode add = head;
            count(booth, i, count, add);
        }

        static void count(ChoiceBox<String> booth, int i, int count, VaccinationCenterNode add) {
            while (add != null && count != i) {
                count++;
                add = add.next;
            }
            if (add != null) {
                VaccinationCenter vc = add.getContents();
                BoothNode bb = vc.head;
                while (bb != null) {
                    booth.getItems().add(bb.getContents().getBoothId());
                    bb = bb.next;
                }
            }
        }

        public void choiceBoxP() { // adds patients to the choiceBox that have an appointment based on the booth chosen
            int i = booth.getSelectionModel().getSelectedIndex();
            int count = 0;
            VaccinationCenterNode vc = head;
            while (vc != null) {
                BoothNode add = vc.getContents().head;
                while (add != null && count != i) {
                    count++;
                    add = add.next;
                }
                if (add != null) {
                    Booth vcs = add.getContents();
                    VAppointmentNode bb = vcs.headi;
                    while (bb != null) {
                        p.getItems().add(bb.getContents().getPpsn());
                        bb = bb.next;
                    }
                }
                vc = vc.next;
            }
        }

        public void del() { // Cancels the chosen appointments
            VaccinationCenterNode vc = head;
            boolean flag = false;
            while (vc != null && !flag && vc.getContents().getCenterName().equals(center.getValue())) {
                VaccinationCenter add = vc.getContents();
                BoothNode bb = add.head;
                VAppointmentNode pp = bb.getContents().headi;
                while (bb != null && !bb.getContents().getBoothId().equals(String.valueOf(booth.getValue()))) {
                    bb = bb.next;
                }
                while (pp != null && !pp.getContents().getPpsn().equals(p.getValue())) {
                    pp = pp.next;
                }
                if (pp != null) {
                    assert bb != null;
                    bb.getContents().delVA(String.valueOf(p.getValue()));
                    flag = true;
                    sD.setTextFill(Color.GREEN);
                    sD.setText("Deleted");
                }
                vc = vc.next;
            }
        }

        public static void addVR(String pName, VaccinationRecord p) { // Adds a vaccination record to the patient if it is completed
            PatientNode temp = heads;
            while (temp != null && !temp.getContents().getPpsn().equals(pName)) {
                temp = temp.next;
            }
            if (temp != null) {
                temp.getContents().addVR(p);
                System.out.println(temp.getContents().printVandB());
            }
        }

        public void goBacks(ActionEvent e) throws Exception {
            if (e.getSource() == r) {
                stage = (Stage) r.getScene().getWindow();
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
