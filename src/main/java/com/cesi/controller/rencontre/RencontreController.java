package com.cesi.controller.rencontre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cesi.controller.rencontre.model.Rencontre;
import com.cesi.services.rencontre.RencontreService;

@RestController
@Validated
public class RencontreController {
    public static final String NOTE_FOUND_MESSAGE = "Rencontre not found";
    
    private final RencontreService rencontreService;

    @Autowired
    public RencontreController(RencontreService rencontreService){
        this.rencontreService = rencontreService;
    }

    @PostMapping("/rencontres")
    public Rencontre addRencontre(@RequestBody Rencontre rencontre){
        Rencontre retour = rencontreService.addRencontre(rencontre);
        return retour;
    }
}
