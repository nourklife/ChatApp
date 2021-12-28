package com.example.viewmodel.pojo;

public class Data {
    String name ;
    String data;
    int id;


    public Data(String name, String data, int id) {
        this.name = name;
        this.data = data;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public int getId() {
        return id;
    }
}
