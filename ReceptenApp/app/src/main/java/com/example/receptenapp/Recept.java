package com.example.receptenapp;

import java.util.ArrayList;

public class Recept {

    private String naam;
    private String beschrijving;
    private String ingredienten;
    private int uuid;

    public Recept(String naam, String beschrijving, String ingredienten, int uuid) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.ingredienten = ingredienten;
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

    public int getUuid() {
        return this.uuid;
    }
}
