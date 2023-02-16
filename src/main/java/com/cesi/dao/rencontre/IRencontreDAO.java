package com.cesi.dao.rencontre;

import java.util.List;

import com.cesi.controller.rencontre.model.Rencontre;

public interface IRencontreDAO {
    Rencontre addRencontre(Rencontre rencontre);
    
    Boolean deleteRencontre(Integer id);

    List<Rencontre> getAllRencontres();
    
    List<Rencontre> getRencontresFilter(Integer nuGagnant, Integer nuPerdant);

    Rencontre updateRencontres(Rencontre rencontre, Integer id);
}
