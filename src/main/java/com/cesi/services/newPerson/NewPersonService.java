package com.cesi.services.newPerson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cesi.dao.person.IPersonDAO;
import com.cesi.controller.person.model.Person;

@Service
public class NewPersonService {

    private final IPersonDAO newPersonDAO;

    @Autowired
    public NewPersonService(@Qualifier("personDAORest") final IPersonDAO newPersonDAO) {
            this.newPersonDAO = newPersonDAO;
    }

    // public boolean deleteById(final Integer id) {
    //     return newPersonDAO.deletePerson(id);
    // }

    // public List<Person> getAllPersons() {
    //     return newPersonDAO.getAllPersons();
    // }

    public List<Person> getPersonsFilter(String id,String nom) {
        return newPersonDAO.getPersonsFilter(id,nom);
    }

    // public Person addPerson(Person person){
    //     return newPersonDAO.addPerson(person);
    // }

    // public Person update(Person person,Integer id){
    //     if(this.newPersonDAO.findById(id) != null){
    //         person.setId(id);
    //         return this.newPersonDAO.update(person);
    //     }else{
    //         person.setId(id);
    //         return this.newPersonDAO.addPerson(person);
    //     }
    // }
}

