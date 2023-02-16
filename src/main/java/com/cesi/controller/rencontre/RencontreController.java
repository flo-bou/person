package com.cesi.controller.rencontre;

import com.cesi.controller.rencontre.model.Rencontre;
import com.cesi.services.rencontre.RencontreService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class RencontreController {
    
    public static final String NOT_FOUND_MESSAGE = "Rencontre not found";

    private final RencontreService rencontreService;

    @Autowired
    public RencontreController(RencontreService rencontreService) {
        this.rencontreService = rencontreService;
    }

    @PostMapping("/rencontres")
    public Rencontre tartempion(@RequestBody Rencontre rencontre){
        Rencontre blabla = rencontreService.addRencontre(rencontre);
        return blabla;
    }

    @DeleteMapping("rencontres/{id}")
    public Boolean deleteRencontre(@PathVariable Integer id) {
        Boolean blabla = rencontreService.deleteRencontre(id);
        return blabla;
    }

    @GetMapping("/rencontres")
    public List<Rencontre> listPersons(@RequestParam(value = "nuGagnant", required = false) Integer nuGagnant,
                                        @RequestParam(value = "nuPerdant", required = false) Integer nuPerdant){

        List<Rencontre> res = new ArrayList<Rencontre>();

        if (nuPerdant == null && nuGagnant == null) {
            res = rencontreService.getAllRencontres();
        } else {
            res = rencontreService.getRencontresFilter(nuGagnant, nuPerdant);
        }
        return res;
    }


    @PutMapping("/rencontres/{id}")
    public Rencontre updateRencontres(@RequestBody Rencontre rencontre, @PathVariable Integer id) {
        Rencontre resp = rencontreService.updateRencontres(rencontre, id);
        return resp;
    }
}
