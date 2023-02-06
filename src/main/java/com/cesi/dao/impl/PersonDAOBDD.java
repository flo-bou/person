package com.cesi.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.cesi.dao.PersonDAO;
import com.cesi.dao.model.PersonDTO;

@Repository
public class PersonDAOBDD implements PersonDAO {

    private static final Logger LOG = LoggerFactory.getLogger(PersonDAOBDD.class);
    private static final String ID_FIELD = "id";
    private static final String NOM_FIELD = "nom";
    private static final String PRENOM_FIELD = "prenom";
    private static final String ANNEENAISSANCE_FIELD = "anneeNaissance";
    private static final String NATIONALITE_FIELD = "nationalite";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAOBDD(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<PersonDTO> rowMapper = (rs, rowNum) -> {
        final PersonDTO person = new PersonDTO();
        person.setId(rs.getInt(ID_FIELD));
        person.setNom(rs.getString(NOM_FIELD));
        person.setPrenom(rs.getString(PRENOM_FIELD));
        person.setAnneeNaissance(rs.getInt(ANNEENAISSANCE_FIELD));
        person.setNationalite(rs.getString(NATIONALITE_FIELD));
        return person;
    };

    @Override
    public boolean deletePerson(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public PersonDTO addPerson(PersonDTO person) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
