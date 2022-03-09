package com.example.islamicapp.data.pojo.azkar;

public class ZekrTypes {
    private String zekrName;

    private int zekrImageId;

    public ZekrTypes(String zekrName) {
        this.zekrName = zekrName;
        this.zekrImageId=-1;
    }

    public ZekrTypes(String zekrName, int zekrImageId) {
        this.zekrName = zekrName;
        this.zekrImageId = zekrImageId;
    }

    public String getZekrName() {
        return zekrName;
    }

    public int getZekrImageId() {
        return zekrImageId;
    }
}
