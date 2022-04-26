package com.allcodingtutorials.blogerrortest;

import java.io.Serializable;

public class model implements Serializable {

    private String title;

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

    private String description;
    private String txtv;

    public model(String title, String description, String txtv) {
        this.title = title;
        this.description = description;
        this.txtv = txtv;
    }

    public String getTxtv() {
        return txtv;
    }

    public void setTxtv(String txtv) {
        this.txtv = txtv;
    }
}
