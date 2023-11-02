package com.example.emma_nolan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.Objects;

import static com.example.emma_nolan.VaccinationCenterController.head;


public class BoothController {
    Stage stage;
    Parent root;
    Boolean fF = false;
    Boolean aA = false;
    @FXML
    private ChoiceBox<String> center;
    @FXML
    private TextField floor;
    @FXML
    private TextField access;
    @FXML
    private Button r;
    @FXML
    private Label f;
    @FXML
    private Label aC;
    @FXML
    private Label sA;

    public void initialize(){
        choiceBoxC();
    }

    public void a(){ // Makes sure only letters are added to the testified access
        if(AddPatientController.isValidName(access.getText())){
            aC.setTextFill(Color.GREEN);
            aC.setText("Valid");
            aA = true;
        }else {
            aC.setTextFill(Color.RED);
            aC.setText("Invalid");
        }
    }

    public void sumbit(){ // Add the chosen data if everything is filled to the linked list
        String VcName = String.valueOf(center.getValue());
        String fName = String.valueOf(floor.getText());

        int empty = VcName.equals("null")? 0:
                fName.equals("")? 0:
                        String.valueOf(access.getText()).equals("")? 0:1;
        if( empty == 1 && fF && aA) {
            Booth b = new Booth(Integer.parseInt(floor.getText()), access.getText());
            addBooth(VcName, b);
            fF = false;
            aA = false;
            sA.setTextFill(Color.GREEN);
            sA.setText("Added");
        }
        else {
            System.out.println("Everything must be filled in ");
        }
    }
    public void goBacks(ActionEvent e) throws  Exception{
        if(e.getSource()== r){
            stage = (Stage) r.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void addBooth(String vcName, Booth b) { // adds a booth to the chosen vc
        VaccinationCenterNode temp = head;
        while(temp!=null && !temp.getContents().getCenterName().equals(vcName))
            temp=temp.next;
        if(temp!=null) {
            int id = VaccinationCenter.BOOTHID;
            BoothNode n = temp.getContents().head;
            VaccinationCenter.BOOTHID ++;
            String currentID = (temp.getContents().getCenterName().charAt(0) + "" + id);
            b.setBoothId(temp.getContents().getCenterName().charAt(0) + "" + id);
            while(n != null && n.getContents().getBoothId().equals(currentID)){
                int newId = VaccinationCenter.BOOTHID;
                b.setBoothId(temp.getContents().getCenterName().charAt(0) + "" + newId);
                currentID = (temp.getContents().getCenterName().charAt(0) + "" + newId);
                VaccinationCenter.BOOTHID ++;
                n = n.next;
            }
            temp.getContents().addBooth(b);
            System.out.println(temp.getContents().printVandB());
        }
    }
    public void f(){ // Makes sure only nums are entered the textfield
        if(nums(floor.getText())){
            fF = true;
            f.setTextFill(Color.GREEN);
            f.setText("Valid");
        }else {
            f.setTextFill(Color.RED);
            f.setText("Nums only please");
        }
    }

    public  void choiceBoxC(){ // Adds vc to the choiceBox
        VaccinationCenterNode add = head;
        while(add != null){
            center.getItems().add(add.getContents().getCenterName());
            add = add.next;
        }
    }
    public static boolean nums(String num){
        try{
            Integer.parseInt(num);
            return true;
        }
        catch (NumberFormatException e){
            System.out.println("Enter only numbers");
            return false;
        }
        catch (Exception e){
            System.out.println("Error");
        }
        return false;
    }
}
