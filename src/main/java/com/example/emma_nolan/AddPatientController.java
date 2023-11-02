package com.example.emma_nolan;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;


public class AddPatientController {
    Stage stage;
    Parent root;
    boolean pp = false;
    boolean pn = false;
    boolean n = false;
    @FXML
    private TextField name;
    @FXML
    private  TextField ppsn;
    @FXML
    private DatePicker dB;
    @FXML
    private TextField address1;
    @FXML
    private TextField country;
    @FXML
    private TextField phone;
    @FXML
    private Button r;
    @FXML
    private Label nameV;
    @FXML
    private Label pN;
    @FXML
    private Label lS;

    static PatientNode heads = null;

    @FXML
    public void name(){  // Method to make sure the name is valid e.g. it doesn't have any numbers in it
        name.setOnKeyReleased(event -> {
            if( isValidName(name.getText() ) ){
                nameV.setTextFill(Color.GREEN);
                nameV.setText("Valid Name");
                n = true;

            }else{
                nameV.setTextFill(Color.RED);
                nameV.setText("Invalid User Name");
                System.out.println("Invalid User Name");
            }
        });
    }
    public void submits(){ // Makes sure everything is filled out before the data is added to the linked list.
        int empty = String.valueOf(name.getText()).equals("")? 0:
                String.valueOf(ppsn.getText()).equals("")? 0:
                        String.valueOf(dB.getValue()).equals("null")? 0:
                                String.valueOf(address1.getText()).equals("")? 0:
                                        String.valueOf(country.getText()).equals("")? 0:
                                                String.valueOf(phone.getText()).equals("")? 0:1;
        if(empty == 1 && pp && pn && n ) {
            Patient patient = new Patient(name.getText(), ppsn.getText().toUpperCase(), String.valueOf(dB.getValue()), address1.getText(), country.getText(), Integer.parseInt(phone.getText()));
            addElement(patient);
            System.out.println(prinlist());
            lS.setTextFill(Color.GREEN);
            lS.setText("Added");
            pp = false;
            pn = false;
            n = false;
        }
        else {
            System.out.println("Everything must be filled in");
        }
    }
    public static void addElement(Patient p) { // adds to the linked list
        PatientNode rt = new PatientNode();
        rt.setContents(p);
        rt.next = heads;
        heads = rt;
        try {
            save();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void phonenum(){ // Makes sure only nums are entered for phone num
        if (phone.getText().matches("\\d{8}|\\d{10}")){
            System.out.println("Its Valid Number");
            //return true;
            pn = true;
        }
        else {
            System.out.println("Invalid Input..!");
            //return false;
        }
    }

    public  void ppsn(){ // Makes sure the ppsn is accurate
        PatientNode p = heads;
        if (isValidPpsn(ppsn.getText().toUpperCase())) {
            pN.setTextFill(Color.GREEN);
            pN.setText("Valid");
            pp = true;
        }
        while( p != null && !p.getContents().getPpsn().equals(ppsn.getText().toUpperCase())){
            p = p.next;
        }
        if(p != null && p.getContents().getPpsn().equals(ppsn.getText().toUpperCase())){
            pN.setTextFill(Color.RED);
            nameV.setText("Invalid");
            ppsn.setText(null);

        }

    }


    public static boolean isValidName(String s){
        String regex="[A-Za-z\\s]+";
        return s.matches(regex);//returns true if input and regex matches otherwise false;
    }

    public static  boolean isValidPpsn(String p){
        String regex="[A-Za-z\\s]+";
        if( p.length() < 8 || p.length() > 9 ){
            return false;
        }
        else if(!p.substring(7, 8).matches(regex)){
            return false;
        }
        try{
            Integer.parseInt(p.substring(0, 7));
        }
        catch (Exception e){
            System.out.println("Invalid PPSN");
            return false;
        }

        return true;
    }

    /*  public static  boolean isValidEircode(String e){
          String regex =" [ A-Za-z\\s]+";
          if(e.length() < 7 || e.length() > 9){
              return false
          }
          try{
              Integer.parseInt(e.substring(1,3));

              }

          }

          return true;
      }*/
    public static String prinlist() { // prints the array list to show that it has been added
        PatientNode currNode = heads;
        StringBuilder fullList = new StringBuilder("LinkedList: \n");
        while (currNode != null) {
            fullList.append(currNode.getContents().getName()).append("\n");

            currNode = currNode.next;
        }
        return fullList.toString();
    }
    public void goReturn(ActionEvent e) throws  Exception{ // Returns you to the first page
        if(e.getSource()== r){
            stage = (Stage) r.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Patient.xml"));
        heads = (PatientNode) is.readObject();
        is.close();
    }

    public static void save() throws Exception
    {
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("Patient.xml"));
        out.writeObject(heads);
        out.close();
    }

}
