package com.cesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonDAO personDAO;
    
    @Autowired
    public PersonService(final PersonDAO personDAO){
        this.personDAO = personDAO;
    }
}
