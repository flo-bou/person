package com.cesi.services.newPerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cesi.dao.person.IPersonDAO;
import com.cesi.dao.rencontre.IRencontreDAO;
import com.cesi.dao.rencontre.impl.RencontreDAORest;
import com.cesi.controller.newPerson.model.NewPerson;
import com.cesi.controller.person.model.Person;
import com.cesi.controller.rencontre.model.Rencontre;

@Service
public class NewPersonService {

    private final IPersonDAO personDAO;
    private final IRencontreDAO rencontreDAO;

    @Autowired
    public NewPersonService(IPersonDAO personDAO, IRencontreDAO rencontreDAO) {
        this.personDAO = personDAO;
        this.rencontreDAO = rencontreDAO;
    }

    // public boolean deleteById(final Integer id) {
    //     return personDAO.deletePerson(id);
    // }

    public List<NewPerson> getAllNewPersons() {
        // on va tranformer chaque person en newPerson en allant aussi récupérer les informations de Rencontre
        List<Person> allPersons = personDAO.getAllPersons();
        List<NewPerson> allNewPersons = new ArrayList<NewPerson>();
        // get all rencontres
        for (Person person : allPersons) {
            // pour chaque person, on va créer un newPerson en allant parcourir Rencontre pour remplir les champs nbVictoire et nbDefaite
            Integer nbVictoire = Integer.valueOf(rencontreDAO.getRencontresFilter(person.getId(), null).size());
            Integer nbDefaite = Integer.valueOf(rencontreDAO.getRencontresFilter(null, person.getId()).size());
            NewPerson newPerson = new NewPerson(person.getId(), person.getNom(), person.getPrenom(), nbVictoire, nbDefaite);
            allNewPersons.add(newPerson);
        }
        return allNewPersons;
    }

    /*List<Rencontre> rencontres = rencontreDAO.getAllRencontres();
    for (Person person : persons){
        int nbVictoire = 0 ;
        int nbDefaite = 0;
        for (Rencontre rencontre :  rencontres){
            if(rencontre.getNuGagnant().equals(person.getId())){nbVictoire++;}
            if(rencontre.getNuPerdant().equals(person.getId())){nbDefaite++;}
        }

    }*/

    public List<NewPerson> getNewPersonsFilter(String id, String nom) {
        // on va tranformer chaque person en newPerson en allant aussi récupérer les informations de Rencontre
        List<Person> persons = personDAO.getPersonsFilter(id, nom);
        List<NewPerson> newPersons = new ArrayList<NewPerson>();
        for (Person person : persons) {
            Integer nbVictoire = Integer.valueOf(rencontreDAO.getRencontresFilter(person.getId(), null).size());
            Integer nbDefaite = Integer.valueOf(rencontreDAO.getRencontresFilter(null, person.getId()).size());
            NewPerson newPerson = new NewPerson(person.getId(), person.getNom(), person.getPrenom(), nbVictoire, nbDefaite);
            newPersons.add(newPerson);
        }
        return newPersons;
    }

}
