package com.example.myapplication;

public class rate {
    private String name;
    private int rate;
    private byte[] image;

    public rate(String name, int rate, byte[] image) {
        this.name = name;
        this.rate = rate;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public byte[] getImage() {
        return image;
    }
}
