package com.example.emma_nolan;

public class VaccinationRecordNode {
    private VaccinationRecord contents;
    public VaccinationRecordNode next = null;

    public VaccinationRecord getContents(){return contents;}
    public void setContents(VaccinationRecord p){contents = p;}
}
