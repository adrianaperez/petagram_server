package com.aperez.aplicacionpetagram.restApi.model;

import com.aperez.aplicacionpetagram.model.Petegram;

import java.util.ArrayList;

public class PetegramResponse {
    ArrayList<Petegram> petegrams;

    public ArrayList<Petegram> getPetegrams() {
        return petegrams;
    }

    public void setPetegrams(ArrayList<Petegram> petegrams) {
        this.petegrams = petegrams;
    }
}
