package com.example.Notes;

import java.util.Date;

public class Note {
    private String title;
    private String description;
    private String date;

    public Note(String title, String description, String date){
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getDate(){
        return date;
    }
}