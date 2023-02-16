package com.cesi.dao.rencontre.model;

public class RencontreDTO {
    private Integer id;
    private Integer nuGagnant;
    private Integer nuPerdant;
    private String lieuTournoi;
    private Integer annee;

    public RencontreDTO(){};

    public RencontreDTO(int nuGagnant,int nuPerdant,int annee,String lieuTournoi){
        this.nuGagnant = nuGagnant;
        this.nuPerdant = nuPerdant;
        this.annee = annee;
        this.lieuTournoi = lieuTournoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGagnant() {
        return nuGagnant;
    }

    public void setGagnant(int nuGagnant) {
        this.nuGagnant = nuGagnant;
    }

    public Integer getPerdant() {
        return nuPerdant;
    }

    public void setPerdant(int nuPerdant) {
        this.nuPerdant = nuPerdant;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getLieuTournoi() {
        return lieuTournoi;
    }

    public void setLieuTournoi(String lieuTournoi) {
        this.lieuTournoi = lieuTournoi;
    }

}
