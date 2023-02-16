package com.cesi.controller.newPerson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// défini la forme de l'objet NewPerson pour la couche controller
public class NewPerson {

    private Integer id;
    private String nom;
    private String prenom;
    private Integer nbVictoire;
    private Integer nbDefaite;

    @JsonCreator
    public NewPerson(@JsonProperty("id") Integer id,
                    @JsonProperty("nom") String nom,
                    @JsonProperty("prenom") String prenom,
                    @JsonProperty("nbVictoire") Integer nbVictoire,
                    @JsonProperty("nbDefaite") Integer nbDefaite) 
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nbVictoire = nbVictoire;
        this.nbDefaite = nbDefaite;
    }

    // UGLY ! public void constructor and setters 4 mapstruct
    public NewPerson() {
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

    public Integer getNbVictoiree() {
        return nbVictoire;
    }

    public Integer getNbDefaite() {
        return nbDefaite;
    }

}
