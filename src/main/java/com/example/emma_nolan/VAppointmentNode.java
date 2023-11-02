package com.example.emma_nolan;

public class VAppointmentNode {
    private VAppointment contents;
    public VAppointmentNode next = null;

    public VAppointment getContents(){return contents;}
    public void setContents(VAppointment p){contents = p;}
}
