package com.cesi.dao.model;

public class PersonDTO {

    private int id;
    private String nom;
    private String prenom;
    private int anneeNaissance;
    private String nationalite;
    // private final Jd

    public PersonDTO(){}

    public PersonDTO(String nom,String prenom,int anneeNaissance,String nationalite){
        this.nom = nom;
        this.prenom = prenom;
        this.anneeNaissance = anneeNaissance;
        this.nationalite = nationalite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
}