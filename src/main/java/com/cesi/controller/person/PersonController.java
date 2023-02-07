package com.cesi.controller.person;

import com.cesi.controller.person.model.Person;
import com.cesi.services.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class PersonController {

    public static final String NOT_FOUND_MESSAGE = "Person not found";

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

//    private static final PersonMapper MAPPER = PersonMapper.INSTANCE;

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<Person> listPersons(@RequestParam(value = "id", required = false) String id,
                                    @RequestParam(value = "nom", required = false) String nom) {

        List<Person> res = new ArrayList<Person>();

        try {
            if (id == null &&  nom == null) {
                res = this.personService.getAllPersons();
            } else {
                res = this.personService.getPersonsFilter(id,nom);
            }

        } catch (final Exception e) {
            LOG.error("Pb du service /person: ", e);
        }

        return res;

    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {

        Person resp = null;


        try {
            resp = this.personService.addPerson(person);

        } catch (final Exception e) {
            LOG.error("Pb du service /person: ", e);
        }

        return resp;

    }

    @DeleteMapping("persons/{id}")
    public Boolean deletePersonRest(@PathVariable Integer id) {

        Boolean resp = null;

        try {
            resp = this.personService.deleteById(id);

        } catch (final Exception e) {
            LOG.error("Pb du service /person: ", e);
        }

        return resp;

    }

    @PutMapping("persons/{id}")
    public Person updatePersonRest(@RequestBody Person person, @PathVariable Integer id) {

        Person resp = null;

        try {
            resp = this.personService.update(person,id);
        } catch (final Exception e) {
            LOG.error("Pb du service /person: ", e);
        }

        return resp;

    }

}

