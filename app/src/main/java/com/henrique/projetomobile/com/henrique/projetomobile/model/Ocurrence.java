package com.henrique.projetomobile.com.henrique.projetomobile.model;

import java.io.Serializable;

public class Ocurrence implements Serializable {

    public int Id;
    public String Title;
    public String Description;

    public Ocurrence(){

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
