package com.example.receptenapp;

import java.util.ArrayList;

public class Recept {

    private String naam;
    private String beschrijving;
    private String ingredienten;
    private boolean favoriet;
    private int uuid;

    public Recept(String naam, String beschrijving, String ingredienten, boolean favoriet,int uuid) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.ingredienten = ingredienten;
        this.favoriet = favoriet;
    }

    public String getNaam() {
        return this.naam;
    }

    public String getBeschrijving() {
        return this.beschrijving;
    }

    public String getIngredienten() {
        return this.ingredienten;
    }

    public boolean isFavoriet() {
        return favoriet;
    }

    public void setFavoriet(boolean favoriet) {
        this.favoriet = favoriet;
    }

    public int getUuid() {
        return this.uuid;
    }
}
