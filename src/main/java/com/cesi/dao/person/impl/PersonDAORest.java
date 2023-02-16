package com.cesi.dao.person.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cesi.controller.person.model.Person;
import com.cesi.dao.person.IPersonDAO;

// classe permet de faire des appels Http à une api REST (url)
@Repository
public class PersonDAORest implements IPersonDAO {

    private RestTemplate restTemplate;
    String baseUrl = "https://8080-romainvisbec-springcesi-pww6n510xt2.ws-eu87.gitpod.io/";

    @Autowired
    public PersonDAORest(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Person addPerson(Person person) {
        ResponseEntity<Person> response;

        String url = baseUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);
        response = restTemplate.exchange(url, HttpMethod.POST, entity, Person.class);

        Person retour = response.getBody();

        return retour;
    }

    @Override
    public boolean deletePerson(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Person> getAllPersons() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Person> getPersonsFilter(String id, String nom) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Person findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Person update(Person person) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
