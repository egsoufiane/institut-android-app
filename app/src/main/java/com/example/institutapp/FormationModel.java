package com.example.institutapp;

import android.widget.ImageView;

public class FormationModel {
    private String formationName;
    private int image;

    public FormationModel(String formationName, int image) {
        this.formationName = formationName;
        this.image = image;
    }

    public String getFormationName() {
        return formationName;
    }

    public int getImage() {
        return image;
    }
}
