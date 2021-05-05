package com.example.maesaapp;

public class AddCardRequest {
  private int id;
  private String name;
  private String short_description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AddCardRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", short_description='" + short_description + '\'' +
                '}';
    }
}
