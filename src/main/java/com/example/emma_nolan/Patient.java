package com.example.emma_nolan;


public class Patient {
    private String name;
    private final String ppsn;
    private final String dateOfBirth;
    private final String address;
    private final String country;
    private final int phoneNum;

    VaccinationRecordNode head = null;

    public Patient(String name, String ppsn, String dateOfBirth, String address, String country, int phoneNum) {
        this.name = name;
        this.ppsn = ppsn;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.country = country;
        this.phoneNum = phoneNum;
    }
    public void addVR(VaccinationRecord p){ // Adds the vr to the chosen patient
        VaccinationRecordNode rt = new VaccinationRecordNode();
        rt.setContents(p);
        rt.next = head;
        head = rt;
        try {
            AddPatientController.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPpsn() {
        return ppsn;
    }


    public  String printVR(){
        StringBuilder b = new StringBuilder();
        VaccinationRecordNode currnode = head;
        while(currnode != null){
            b.append(currnode.getContents().toString()).append("\n");
            currnode = currnode.next;
        }
        return b.toString();
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public String printVandB(){
        return "Center name - " + getName() + " Current Booths - " + printVR();
    }
    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", ppsn='" + ppsn + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", phoneNum=" + phoneNum +
                '}';
    }
}
