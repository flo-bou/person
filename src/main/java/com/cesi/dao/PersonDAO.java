package com.cesi.dao;

import java.util.List;

import com.cesi.dao.model.PersonDTO;

public interface PersonDAO {
    boolean deletePerson(int id);

    PersonDTO addPerson(PersonDTO person);

    List<PersonDTO> getAllPersons();

}
