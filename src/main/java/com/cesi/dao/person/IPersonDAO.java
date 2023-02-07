package com.cesi.dao.person;

import java.util.List;

import com.cesi.controller.person.model.Person;

public interface IPersonDAO {

    boolean deletePerson(Integer id);

    Person addPerson(Person person);

    List<Person> getAllPersons();

    List<Person> getPersonsFilter(String id,String nom);

    Person findById(Integer id);

    Person update(Person person);
}