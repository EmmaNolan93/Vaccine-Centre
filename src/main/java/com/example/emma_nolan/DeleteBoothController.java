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

import static com.example.emma_nolan.VaccinationCenterController.head;

public class DeleteBoothController {
    Stage stage;
    Parent root;
    @FXML
    private ChoiceBox<String> center;
    @FXML
    private ChoiceBox<String> booth;
    @FXML
    private Button r;
    @FXML
    private Label sD;

    public void initialize() {
        choiceBoxV();

    }

    public void choiceBoxV() { // adds the vc to the cb
        VaccinationCenterNode add = head;
        while (add != null) {
            center.getItems().add(add.getContents().getCenterName());
            add = add.next;
        }
    }

    public void choiceBoxB() {
        AppiomentStatusController.add(center, booth);
    } // add the booths to the cb

    public void del() { // Makes sure everything is selected before going through with the del process
        int empty = String.valueOf(center.getValue()).equals("null")? 0:
                String.valueOf(booth.getValue()).equals("null")? 0:1;
        if(empty == 1) {
            VaccinationCenterNode vc = head;
            boolean flag = false;
            while (vc != null && !flag) {
                VaccinationCenter add = vc.getContents();
                BoothNode bb = add.head;
                while (bb != null && !bb.getContents().getBoothId().equals(String.valueOf(booth.getValue()))) {
                    bb = bb.next;
                }
                if (bb != null) {
                    vc.getContents().delBooths(String.valueOf(booth.getValue()));
                    System.out.println(vc.getContents().printVandB());
                    sD.setTextFill(Color.GREEN);
                    sD.setText("Deleted");
                    flag = true;
                }
                vc = vc.next;
            }
        }
        else {
            System.out.println("Everything must be filled in");
        }
        try {
            VaccinationCenterController.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void r(ActionEvent e) throws Exception{
        if(e.getSource()== r){
            stage = (Stage) r.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
