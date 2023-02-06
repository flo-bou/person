package com.cesi.person;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecuteDAO {
    @Autowired
    private DataSource dataSource;

    // @Autowired
    // PersonService personService;

    @PostConstruct
    public void init(){

    }
}