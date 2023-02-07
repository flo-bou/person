package com.cesi.controller.rencontre.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rencontre {

    private Integer id;
    private Integer nuGagnant;
    private Integer nuPerdant;
    private String lieuTournoi;
    private Integer annee;

    @JsonCreator
    public Rencontre(@JsonProperty("id") Integer id,
                    @JsonProperty("nuGagnant") Integer nuGagnant,
                    @JsonProperty("nuPerdant") Integer nuPerdant,
                    @JsonProperty("lieuTournoi") String lieuTournoi,
                    @JsonProperty("annee") Integer annee) {
        this.id = id;
        this.nuGagnant = nuGagnant;
        this.nuPerdant = nuPerdant;
        this.lieuTournoi = lieuTournoi;
        this.annee = annee;
    }

    public Rencontre() {
    }
    @Override
    public String toString() {
        return this.nuGagnant +" : "+this.nuPerdant + " / " + this.lieuTournoi;
    }

    public Integer getId() {
        return id;
    }
    public Integer getNuGagnant() {
        return nuGagnant;
    }

    public Integer getNuPerdant() {
        return nuPerdant;
    }

    public String getLieuTournoi() {
        return lieuTournoi;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNuGagnant(Integer nuGagnant) {
        this.nuGagnant = nuGagnant;
    }
    public void setNuPerdant(Integer nuPerdant) {
        this.nuPerdant = nuPerdant;
    }
    public void setLieuTournoi(String lieuTournoi) {
        this.lieuTournoi = lieuTournoi;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

}
