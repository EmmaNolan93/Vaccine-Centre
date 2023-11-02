package com.example.emma_nolan;
import static com.example.emma_nolan.AddPatientController.heads;

public class Booth {
    private String boothId;
    private final int floorLevel;
    private final String accessibility;
    VAppointmentNode headi = null;

    public Booth( int floorLevel, String accessibility) {
        this.floorLevel = floorLevel;
        this.accessibility = accessibility;
    }

    public String getBoothId() {
        return boothId;
    }

    public void setBoothId(String boothId) {
        this.boothId = boothId;
    }

    public void addVP(VAppointment p){ // Adds vaccinations appointments to the booth
        VAppointmentNode rt = new VAppointmentNode();
        rt.setContents(p);
        rt.next = headi;
        headi = rt;
        try {
            VaccinationCenterController.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addVPEnd(VAppointmentNode a) { // if a booth is deleted and has appointments this will add the appointments onto the next available booth
        VAppointmentNode currnode = headi;
        if (currnode != null) {
            while (currnode.next != null) {
                currnode = currnode.next;
            }
            currnode.next = a;

        }
        else {
            headi = a;
        }
    }
    public  String printVA(){
        StringBuilder p = new StringBuilder();
        VAppointmentNode currnode = headi;
        while(currnode != null){
            p.append(currnode.getContents().toString()).append("\n");
            currnode = currnode.next;
        }
        return p.toString();
    }

    public String printVR(){
        PatientNode add = heads;
        return "Patient ppsn - " + add.getContents().getName() + " Appointment details - " + printVA();
    }

    public void delVA(String patient) { // Dels vaccination appointments
        VAppointmentNode b = headi;
        boolean found = false;
        if ( b.getContents().getPpsn().equals(patient)) {
            headi = b.next;
            try {
                VaccinationCenterController.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            while (!found) {
                if(b.next.getContents().getPpsn().equals(patient)){
                    found = true;
                    b.next = b.next.next;
                    try {
                        VaccinationCenterController.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                b = b.next;
            }
        }
    }
    @Override
    public String toString() {
        return "Booth{" +
                "boothId='" + boothId + '\'' +
                ", floorLevel=" + floorLevel +
                ", accessibility='" + accessibility + '\'' +
                '}';
    }
}