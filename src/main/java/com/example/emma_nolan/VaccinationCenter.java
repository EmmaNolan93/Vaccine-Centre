package com.example.emma_nolan;

public class VaccinationCenter {
   private final String centerName;
   private final String address1;
   private final String address2;
   private final String country;
   private final String eircode;
   private final int parkingSpace;

    BoothNode head = null;
    BoothNode last = null;
    public static int BOOTHID = 1;
    public VaccinationCenter(String centerName, String address1, String address2, String country, String eircode, int parkingSpace) {
        this.centerName = centerName;
        this.address1 = address1;
        this.address2 = address2;
        this.country = country;
        this.eircode = eircode;
        this.parkingSpace = parkingSpace;
    }

    public  String printBooths(){
        StringBuilder b = new StringBuilder();
        BoothNode currnode = head;
        while(currnode != null){
            b.append(currnode.getContents().toString()).append("\n");
            currnode = currnode.next;
        }
        return b.toString();
    }

    public void delBooths(String id) { // Dels booths
        BoothNode b = head;
        VAppointmentNode p = null;
        boolean found = false;
        if (b.getContents().getBoothId().equals(id)) {
            p = b.getContents().headi;
            if(b.previous !=null) {
                b.previous.getContents().addVPEnd(p);
            }
            head = b.next;

        }
        else {
            while (!found) {
                if (b.next.getContents().getBoothId().equals(id)) {
                    found = true;
                    p = b.next.getContents().headi;
                    b.getContents().addVPEnd(p);
                    b.next = b.next.next;
                }
                b = b.next;
            }
        }
        try {
            VaccinationCenterController.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBooth(Booth b){ // Adds booths
        BoothNode rt = new BoothNode();
        if(last == null){
            rt.setContents(b);
            rt.next = head;
            head = rt;
            last = rt;
        }
        else {
            rt.setContents(b);
            rt.next = head;
            head = rt;
            rt.previous = last;
            last = rt;
        }
        try {
            VaccinationCenterController.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String printVandB(){
        return "Center name - " + getCenterName() + " Current Booths - " + printBooths();
    }

    public String getCenterName() {
        return centerName;
    }

    @Override
    public String toString() {
        return "VaccinationCenter{" +
                "centerName='" + centerName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", eircode='" + eircode + '\'' +
                ", parkingSpace=" + parkingSpace +
                '}';
    }
}
