package com.example.emma_nolan;


import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class VaccinationCenterController implements Initializable {

    Stage stage;
    Parent root;
    boolean vC = false;
    boolean pp = false;
    @FXML
    private TextField name;
    @FXML
    private TextField address1;
    @FXML
    private TextField address2;
    @FXML
    private TextField country;
    @FXML
    private TextField eircode;
    @FXML
    private TextField parkingSpaces;
    @FXML
    private Button goBack;
    @FXML
    private Label vcName;
    @FXML
    private Label pS;
    @FXML
    private Label lS;

    static VaccinationCenterNode head = null;

    public void submits() { // Summits the data to the linklist if everything is filled in and correct
        int empty = String.valueOf(name.getText()).equals("") ? 0 :
                String.valueOf(address1.getText()).equals("") ? 0 :
                        String.valueOf(address2.getText()).equals("") ? 0 :
                                String.valueOf(country.getText()).equals("") ? 0 :
                                        String.valueOf(eircode.getText()).equals("") ? 0 :
                                                String.valueOf(parkingSpaces.getText()).equals("") ? 0 : 1;
        if (empty == 1 && vC && pp) {
            VaccinationCenter center = new VaccinationCenter(name.getText(), address1.getText(), address2.getText(), country.getText(), eircode.getText(), Integer.parseInt(parkingSpaces.getText()));
            addElement(center);
            System.out.println(prinlist());
            vC = false;
            pp = false;
            lS.setTextFill(Color.GREEN);
            lS.setText("Added");

        } else {
            System.out.println("Everything must be filled in");
        }
    }

    public void vcName(KeyEvent keyEvent) { // Makes sure the VC name has not been entered before but must press enter to check
        VaccinationCenterNode vc = head;
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (AddPatientController.isValidName(name.getText())) {
                vcName.setTextFill(Color.GREEN);
                vcName.setText("Valid");
                vC = true;
            }
            while (vc != null && !vc.getContents().getCenterName().equals(name.getText())) {
                vc = vc.next;
            }
            if (vc != null) {
                vcName.setTextFill(Color.RED);
                vcName.setText("This name is already in use");
                name.setText(null);
            }
        }
    }

    public void goBacks(ActionEvent e) throws Exception {
        if (e.getSource() == goBack) {
            stage = (Stage) goBack.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void park() { // Makes sure only num are entered
        if (BoothController.nums(parkingSpaces.getText())) {
            pS.setTextFill(Color.GREEN);
            pS.setText("Valid");
            pp = true;
        } else {
            pS.setTextFill(Color.RED);
            pS.setText("Numbers Only");
        }
    }

    public static void clearSystem() { // Clears the system
        AddPatientController.heads = null;
        head = null;
        try {
            save();
            AddPatientController.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addElement(VaccinationCenter c) { // adds the VC to the linked list
        VaccinationCenterNode rt = new VaccinationCenterNode();
        rt.setContents(c);
        rt.next = head;
        head = rt;
        try {
            save();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String prinlist() {
        VaccinationCenterNode currNode = head;
        StringBuilder fullList = new StringBuilder("LinkedList: \n");
        while (currNode != null) {
            fullList.append(currNode.getContents().getCenterName()).append("\n");

            currNode = currNode.next;
        }
        return fullList.toString();
    }

    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Vaccination.xml"));
        head = (VaccinationCenterNode) is.readObject();
        is.close();
    }

    public static void save() throws Exception {
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("Vaccination.xml"));
        out.writeObject(head);
        out.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
