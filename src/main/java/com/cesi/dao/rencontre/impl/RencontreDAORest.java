package com.cesi.dao.rencontre.impl;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cesi.controller.rencontre.model.Rencontre;
import com.cesi.dao.rencontre.IRencontreDAO;

@Repository
public class RencontreDAORest implements IRencontreDAO {

    private RestTemplate restTemplate;

    @Autowired
    public RencontreDAORest(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Rencontre addRencontre(Rencontre rencontre) {
        ResponseEntity<Rencontre> response;

        String url = "https://8080-flobou-person-iwg87uasowt.ws-eu85.gitpod.io/rencontres";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Rencontre> entity = new HttpEntity<Rencontre>(rencontre, headers);
        response = restTemplate.exchange(url, HttpMethod.POST, entity, Rencontre.class);

        Rencontre retour = response.getBody();

        return retour;
    }
    
}
