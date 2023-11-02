package com.example.emma_nolan;


public class VAppointment {
    private final String location;
    private final String date;
    private final String time;
    private final String vaccine;
    private String booth;
    private final String batchNumber;
    private final String ppsn;
    private final String vaccinatorName;



    public VAppointment(String location, String date, String time, String vaccine, String booth, String ppsn, String batchNumber, String vaccinatorName) {
        this.location = location;
        this.date = date;
        this.time = time;
        this.vaccine = vaccine;
        this.booth = booth;
        this.batchNumber = batchNumber;
        this.ppsn = ppsn;
        this.vaccinatorName = vaccinatorName;
    }
    public String getPpsn() {
        return ppsn;
    }

    public String getBooth() {
        return booth;
    }

    public void setBooth(String booth) {
        this.booth = booth;
    }

    public String getDate() {
        return date;
    }


    public String getTime() {
        return time;
    }


    public String getBatchNumber() {
        return batchNumber;
    }

    public String getVaccine() {
        return vaccine;
    }


    @Override
    public String toString() {
        return "VAppointment{" +
                "location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", vaccine='" + vaccine + '\'' +
                ", booth='" + booth + '\'' +
                ", ppsn='" + ppsn + '\'' +
                '}';
    }
}
