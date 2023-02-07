package com.cesi.controller.person.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private Integer id;
    private String nom;
    private String prenom;
    private Integer anneeNaissance;
    private String nationalite;

    @JsonCreator
    public Person(@JsonProperty("id") Integer id,
                  @JsonProperty("nom") String nom,
                  @JsonProperty("prenom") String prenom,
                  @JsonProperty("anneeNaissance") Integer anneeNaissance,
                  @JsonProperty("nationalite") String nationalite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.anneeNaissance = anneeNaissance;
        this.nationalite = nationalite;
    }

    // UGLY ! public void constructor and setters 4 mapstruct
    public Person() {
    }

    @Override
    public String toString() {
        return this.id +" : "+this.nom + " / " + this.prenom;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(Integer anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
}
