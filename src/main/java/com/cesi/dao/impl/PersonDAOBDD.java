package com.cesi.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final RowMapper<PersonDTO> rowMapper = (rs, rowNum) -> {
        final PersonDTO person = new PersonDTO();
        person.setId(rs.getInt(ID_FIELD));
        person.setNom(rs.getString(NOM_FIELD));
        person.setPrenom(rs.getString(PRENOM_FIELD));
        person.setAnneeNaissance(rs.getInt(ANNEENAISSANCE_FIELD));
        person.setNationalite(rs.getString(NATIONALITE_FIELD));
        return person;
    };

    @Autowired
    public PersonDAOBDD(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PersonDTO getPerson(int id) {
        PersonDTO person;
        List<PersonDTO> persons = this.jdbcTemplate.query(
            "SELECT * from person WHERE id = " + id,
            this.rowMapper
        );
        // persons = new ArrayList(persons);
        if (persons.size() == 1)
            person = persons.get(0);
        else  
            person = null;

        return person;
    }

    @Override
    public boolean deletePerson(int id) {
        int nbRow = this.jdbcTemplate.update(
            "DELETE * WHERE id = " + id, 
            this.rowMapper
        );
        return nbRow > 0;
    }

    @Override
    public PersonDTO addPerson(PersonDTO person) {
        // vérifier que la personne existe
        Object personsAlreadyInBase;
        personsAlreadyInBase = this.jdbcTemplate.queryForObject(
            "SELECT * from person WHERE nom = ? AND prenom = ? AND anneenaissance = ? AND nationnalite = ?",
            person.getNom(), person.getPrenom(), person.getAnneeNaissance(), person.getNationalite()
        );
        if(personsAlreadyInBase){

        }
        // insérer en base
        int nbRow = this.jdbcTemplate.update(
            "INSERT INTO Person VALUES(?, ?, ?, ?)", 
            person.getNom(), person.getPrenom(), person.getAnneeNaissance(), person.getNationalite()
        );
        // récupérer les informations introduites en base (avec l'id du coup) et les renvoyer
        Object nbpersons;
        if (nbRow == 1)
            nbpersons = this.jdbcTemplate.queryForObject(
                "SELECT * from person WHERE nom = ? AND prenom = ? AND anneenaissance = ? AND nationnalite = ?",
                person.getNom(), person.getPrenom(), person.getAnneeNaissance(), person.getNationalite()
            );
        return person;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> listPerson = this.jdbcTemplate.query(
            "select * from person", 
            this.rowMapper
        );
        return listPerson;
    }
}
