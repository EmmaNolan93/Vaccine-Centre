package com.example.emma_nolan;

public class PatientNode {
    private Patient contents;
    public PatientNode next = null;

    public Patient getContents(){return contents;}
    public void setContents(Patient p){contents = p;}
}
