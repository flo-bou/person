package com.cesi.controller.newPerson;

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
public class NewPersonController {

    public static final String NOT_FOUND_MESSAGE = "Person not found";

    private static final Logger LOG = LoggerFactory.getLogger(NewPersonController.class);

//    private static final PersonMapper MAPPER = PersonMapper.INSTANCE;

    private final PersonService personService;

    @Autowired
    public NewPersonController(PersonService personService) {
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

}

