package com.allcodingtutorials.blogerrortest;

import java.io.Serializable;

public class model implements Serializable {

    private String title;
    private String description;

    private String datercv;

    public model(String title,  String datercv,String description) {
        this.title = title;
        this.datercv = datercv;
        this.description = description;


    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getDatercv() {
        return datercv;
    }

    public void setDatercv(String datercv) {
        this.datercv = datercv;
    }






}
