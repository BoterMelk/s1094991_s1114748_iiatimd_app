package com.example.receptenapp;

public class Recept {

    private String naam;
    private String beschrijving;
    private int uuid;

    public Recept(String naam, String beschrijving, int uuid) {
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    public String getNaam() {
        return this.naam;
    }

    public String getBeschrijving() {
        return this.beschrijving;
    }

    public int getUuid() {
        return this.uuid;
    }
}
