package com.cesi.dao.person.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cesi.controller.person.model.Person;
import com.cesi.dao.person.IPersonDAO;
import com.cesi.dao.person.model.PersonDTO;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

// Cette classe effectue un CRUD en base de données sur les Person
@Repository
     public class PersonDAOBDD implements IPersonDAO {

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

        /**
         * Capitalisation d'un RowMapper qui permet de specifier à la main le mapping schema de bdd / dto
         */
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
            public List<Person> getAllPersons() {

                List<Person> listPerson = null;
                List<PersonDTO> dtos = this.jdbcTemplate.query(
                        "select * from person",
                        this.rowMapper);

                if(dtos != null && dtos.size() > 0){
                    listPerson = new ArrayList<Person>();
                    for(PersonDTO dto : dtos) {
                        Person resp = new Person();
                        resp.setId(dto.getId());
                        resp.setNom(dto.getNom());
                        resp.setPrenom(dto.getPrenom());
                        resp.setAnneeNaissance(dto.getAnneeNaissance());
                        resp.setNationalite(dto.getNationalite());
                        listPerson.add(resp);
                    }
                }

                return listPerson;
            }

        /*
         * delete a person and clear cache
         */
        @Override
        @CacheEvict("PersonDTO")
        public boolean deletePerson(Integer id) {

            boolean res = false;
            try {
                final String query = "DELETE FROM person WHERE id = ?";
                this.jdbcTemplate.update(query, id);
                res = true;
            } catch (final Exception e) {
                LOG.error("could not delete: {}", id, e);
            }
            return res;

        }


        /*
         * - Exemple d'utilisation de MapSqlParameterSource
         * - Exemple de SimpleJdbcInsert
         */
        @Override
        public Person addPerson(Person person) {

            // initialisation du POJO de retour
            PersonDTO dto = new PersonDTO();

            // Attention stocker ici une date GMT pour avoir une restitution universelle
            dto.setNom(person.getNom());
            dto.setPrenom(person.getPrenom());
            dto.setAnneeNaissance(person.getAnneeNaissance());
            dto.setNationalite(person.getNationalite());

            final int count = jdbcTemplate.queryForObject("SELECT count(*) from person where nom= ? and prenom= ?",new Object[]{dto.getNom(),dto.getPrenom()}, Integer.class);

            if (count == 0) {

                // Insertion d'une personne
                jdbcTemplate.update(
                        "insert into person (nom, prenom,anneeNaissance,nationalite) values(?,?,?,?)",
                        dto.getNom(), dto.getPrenom(),dto.getAnneeNaissance(),dto.getNationalite());

            }

            // si existe déjà ou si il vient d'être crée, lecture en base
            dto = this.jdbcTemplate.query(
                    "select * from person where nom = ?",
                    this.rowMapper,
                    person.getNom()).get(0);

            Person resp = new Person();
            if(dto != null){
                resp.setId(dto.getId());
                resp.setNom(dto.getNom());
                resp.setPrenom(dto.getPrenom());
                resp.setAnneeNaissance(dto.getAnneeNaissance());
                resp.setNationalite(dto.getNationalite());
            }

            return resp;
        }


        /*
         * Exemple de select paramétré avec le RowMapper
         */
        @Cacheable(value = "PersonDTO")
        @Override
        public List<Person> getPersonsFilter(String id,String name) {

            List<Person> listPerson = null;
            boolean deuxParam = false;

            String query = "select * from person where";
            if(id != null){
                query+= " id = '" + id + "'";
                deuxParam = true;
            }
            if(name != null){
                if(deuxParam){
                    query += " AND";
                }
                query += " nom = '" + name + "'";
                deuxParam = true;
            }
            List<PersonDTO> dtos = this.jdbcTemplate.query(
                    query,
                    this.rowMapper);

            if(dtos != null && dtos.size() > 0){
                listPerson = new ArrayList<Person>();
                for(PersonDTO dto : dtos) {
                    Person resp = new Person();
                    resp.setId(dto.getId());
                    resp.setNom(dto.getNom());
                    resp.setPrenom(dto.getPrenom());
                    resp.setAnneeNaissance(dto.getAnneeNaissance());
                    resp.setNationalite(dto.getNationalite());
                    listPerson.add(resp);
                }
            }

            return  listPerson;

        }

    @Override
    public Person findById(Integer id) {
        // initialisation du POJO de retour
        List<PersonDTO> dto = new ArrayList<PersonDTO>();

        // si existe déjà ou si il vient d'être crée, lecture en base
        dto = this.jdbcTemplate.query(
                "select * from person where id = ?",
                this.rowMapper,
                id);
        Person resp = null;

        if(dto != null && dto.size() > 0){
                resp = new Person();
                resp.setId(dto.get(0).getId());
                resp.setNom(dto.get(0).getNom());
                resp.setPrenom(dto.get(0).getPrenom());
                resp.setAnneeNaissance(dto.get(0).getAnneeNaissance());
                resp.setNationalite(dto.get(0).getNationalite());
        }

        return resp;
    }

    @Override
    public Person update(Person person) {
        try {
            String query = "UPDATE person SET ";
            boolean deuxParam = false;
            if(person.getNom()!= null){;
                if(deuxParam){
                    query+= ",";
                }
                query+="nom = '"+person.getNom() + "'";
                deuxParam = true;
            }
            if(person.getPrenom()!= null){
                if(deuxParam){
                    query+= ",";
                }
                query+="prenom = '"+person.getPrenom() + "'";
                deuxParam = true;
            }
            if(person.getAnneeNaissance() != null){
                if(deuxParam){
                    query+= ",";
                }
                query+="anneeNaissance = '"+person.getAnneeNaissance() + "'";
                deuxParam = true;
            }
            if(person.getNationalite()!= null){
                if(deuxParam){
                    query+= ",";
                }
                query+="nationalite = '"+person.getNom() + "'";
                deuxParam = true;
            }
            query+= " WHERE id = '" + person.getId() + "'";

            int count = this.jdbcTemplate.update(query);
            Person resp = null;

            if(count>0){
                // si existe déjà ou si il vient d'être crée, lecture en base
                List<PersonDTO> dto = this.jdbcTemplate.query("select * from person where id = ?", this.rowMapper, person.getId());

                if(dto != null && dto.size() > 0){
                    resp = new Person();
                    resp.setId(dto.get(0).getId());
                    resp.setNom(dto.get(0).getNom());
                    resp.setPrenom(dto.get(0).getPrenom());
                    resp.setAnneeNaissance(dto.get(0).getAnneeNaissance());
                    resp.setNationalite(dto.get(0).getNationalite());
                }
                return resp;
            }
        } catch (final Exception e) {
            LOG.error("could not update: {}", person.getId(), e);
        }
        return person;
    }
}

