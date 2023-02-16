package com.cesi.dao.rencontre.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cesi.controller.rencontre.model.Rencontre;
import com.cesi.dao.rencontre.IRencontreDAO;

@Repository //veut dire qu'on est une dao 
public class RencontreDAORest implements IRencontreDAO {

    private RestTemplate restTemplate;
    String baseUrl = "https://8080-romainvisbec-springcesi-pww6n510xt2.ws-eu87.gitpod.io/";

    @Autowired
    public RencontreDAORest(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Rencontre addRencontre(Rencontre rencontre) {
        ResponseEntity<Rencontre> response;

        String url = baseUrl + "rencontres";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Rencontre> entity = new HttpEntity<Rencontre>(rencontre, headers);
        response = restTemplate.exchange(url, HttpMethod.POST, entity, Rencontre.class);

        Rencontre retour = response.getBody();
        
        return retour;
    }

    @Override
    public Boolean deleteRencontre(Integer id) {
        //declare la réponse
        ResponseEntity<Boolean> response;

        //
        String url = baseUrl + "rencontres/id=" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        response = restTemplate.exchange(url, HttpMethod.DELETE, entity, Boolean.class);

        Boolean retour = response.getBody();
        
        return retour;
    }

    @Override
    public List<Rencontre> getAllRencontres() {
        //declare la réponse
        ResponseEntity<Rencontre[]> response;

        //URL du serv + potentiellement parametres
        String url = baseUrl + "rencontres/";

        //declaration du header
        HttpHeaders headers = new HttpHeaders();
        //dire ce que l'on veut qu'on nous renvoie(type)
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //requete
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        response = restTemplate.exchange(url, HttpMethod.GET, entity, Rencontre[].class);

        //type du retour
        List<Rencontre> retour = Arrays.asList(response.getBody());
        
        //retour final
        return retour;
    }

    @Override
    public List<Rencontre> getRencontresFilter(Integer nuGagnant, Integer nuPerdant) {
        //declare la réponse
        ResponseEntity<Rencontre[]> response;

        String url = baseUrl + "rencontres/?";
        
        //URL du serv + potentiellement parametres
        if(nuPerdant == null){
            url += "nuGagnant="+ nuGagnant;
        }
        else if(nuGagnant == null){
            url += "nuPerdant="+ nuPerdant;
        }
        else{
            url += "nuGagnant="+ nuGagnant + "&nuPerdant=" + nuPerdant;
        }
        
        //declaration du header
        HttpHeaders headers = new HttpHeaders();
        //dire ce que l'on veut qu'on nous renvoie(type)
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //requete
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        response = restTemplate.exchange(url, HttpMethod.GET, entity, Rencontre[].class);

        //type du retour
        List<Rencontre> retour = Arrays.asList(response.getBody());
        
        //retour final
        return retour;
    }


    @Override
    public Rencontre updateRencontres(Rencontre rencontre, Integer id) {
        ResponseEntity<Rencontre> response;

        String url = baseUrl + "rencontres/id=" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Rencontre> entity = new HttpEntity<Rencontre>(rencontre, headers);
        response = restTemplate.exchange(url, HttpMethod.PUT, entity, Rencontre.class);

        Rencontre retour = response.getBody();
        
        return retour;
    }

}
