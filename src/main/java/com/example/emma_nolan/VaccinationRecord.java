package com.example.emma_nolan;


public class VaccinationRecord {
    private final String Name;
    private final String date;
    private final String vaccine;
    private final String batchNumber;


    public VaccinationRecord(String name, String date, String vaccine, String batchNumber) {
        this.Name = name;
        this.date = date;
        this.vaccine = vaccine;
        this.batchNumber = batchNumber;
    }

    public String getName() {
        return Name;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    @Override
    public String toString() {
        return "VaccinationRecord{" +
                "Name='" + Name + '\'' +
                ", date='" + date + '\'' +
                ", vaccine='" + vaccine + '\'' +
                ", batchNumber=" + batchNumber +
                '}';
    }
}
