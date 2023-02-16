package com.cesi.services.newPerson;

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
        // on va appeler le service Person
        List<Person> allPersons = personDAO.getAllPersons();
        List<NewPerson> allNewPersons;
        for (Person person : allPersons) {
            // get all rencontres
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
        List<Person> persons = personDAO.getPersonsFilter(id,nom);
        // on va tranformer chaque person en newPerson en allant aussi récupérer les informations de Rencontre
        for (Person person : persons) {
            NewPerson newPerson = new NewPerson(person.getId(), person.getNom(), person.getPrenom());
            // on va récuperer toutes les rencontres puis on va les parcourir pour compter le nombre de victoires et de défaites pour les mettre dans les newPerson
            List<Rencontre> rencontres = rencontreDAO.getRencontresFilter(person.getId(), null);

            newPerson.setNbVictoire();
            newPerson.setNbDefaite();
        }
        // List<NewPerson> newPersons =  personDAO.getPersonsFilter(id,nom);
        List<NewPerson> newPersons;
        return newPersons;
    }

    // public Person addPerson(Person person){
    //     return personDAO.addPerson(person);
    // }

    // public Person update(Person person,Integer id){
    //     if(this.personDAO.findById(id) != null){
    //         person.setId(id);
    //         return this.personDAO.update(person);
    //     }else{
    //         person.setId(id);
    //         return this.personDAO.addPerson(person);
    //     }
    // }
}

