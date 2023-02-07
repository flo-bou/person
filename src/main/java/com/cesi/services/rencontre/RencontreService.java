package com.cesi.services.rencontre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesi.controller.rencontre.model.Rencontre;
import com.cesi.dao.rencontre.IRencontreDAO;

@Service
public class RencontreService {
    private final IRencontreDAO rencontreDAO;

    @Autowired
    public RencontreService(IRencontreDAO rencontreDAO) {
        this.rencontreDAO = rencontreDAO;
    }

    public Rencontre addRencontre(Rencontre rencontre){
        Rencontre resp = rencontreDAO.addRencontre(rencontre);
        return resp;
    }
    
}
