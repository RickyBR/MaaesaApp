package com.example.maesaapp;

public class AddCardResponse {

    private String name;
    private String short_description;
    private String data;

    @Override
    public String toString() {
        return "AddCardResponse{" +
                "name='" + name + '\'' +
                ", short_description='" + short_description + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }


}
