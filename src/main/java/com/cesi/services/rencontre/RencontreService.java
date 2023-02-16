package com.cesi.services.rencontre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesi.controller.rencontre.model.Rencontre;
import com.cesi.dao.rencontre.IRencontreDAO;

@Service //dire qu'on est un service
public class RencontreService {
    private final IRencontreDAO rencontreDAO;

    @Autowired
    public RencontreService (IRencontreDAO rencontreDAO) {
        this.rencontreDAO = rencontreDAO;
    }

    public Rencontre addRencontre(Rencontre rencontre) {
        return rencontreDAO.addRencontre(rencontre);
    }

    public Boolean deleteRencontre(Integer id) {
        return rencontreDAO.deleteRencontre(id);
    }

    public List<Rencontre> getAllRencontres() {
        return rencontreDAO.getAllRencontres();
    }

    public List<Rencontre> getRencontresFilter(Integer nuGagnant, Integer nuPerdant) {
        return rencontreDAO.getRencontresFilter(nuGagnant, nuPerdant);
    }

    public Rencontre updateRencontres(Rencontre rencontre, Integer id) {
        return rencontreDAO.updateRencontres(rencontre, id);
    }
}
