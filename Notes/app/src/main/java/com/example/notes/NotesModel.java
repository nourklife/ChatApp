package com.example.notes;

public class NotesModel {
    private String mNotesKey;
    private String Title;
    private String Descriptions;

    public NotesModel() {
    }

    public NotesModel(String mNotesKey, String title, String descriptions) {
        this.mNotesKey = mNotesKey;
        Title = title;
        Descriptions = descriptions;
    }

    public String getmNotesKey() {
        return mNotesKey;
    }

    public String getTital() {
        return Title;
    }

    public String getDescriptions() {
        return Descriptions;
    }
}
