package com.example.emma_nolan;

public class BoothNode {
    private Booth contents;
    public BoothNode next = null;
    public BoothNode previous = null;

    public Booth getContents(){return contents;}
    public void setContents(Booth b){contents = b;}
}

