package com.example.chat;

public class NotesModel {
    String tital;
    String Descrption;

    public NotesModel(String tital, String descrption) {
        this.tital = tital;
        Descrption = descrption;
    }

    public String getTital() {
        return tital;
    }

    public String getDescrption() {
        return Descrption;
    }
}
