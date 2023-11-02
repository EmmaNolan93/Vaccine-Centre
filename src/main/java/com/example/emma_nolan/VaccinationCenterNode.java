package com.example.emma_nolan;

public class VaccinationCenterNode {
    public VaccinationCenter contents;
    public VaccinationCenterNode next = null;

    public VaccinationCenter getContents(){return contents; }
    public void setContents(VaccinationCenter c) {contents = c;}
}
