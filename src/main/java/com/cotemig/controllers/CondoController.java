package com.cotemig.controllers;

import com.cotemig.exceptions.NotFoundException;
import com.cotemig.models.Condo;
import com.cotemig.repositories.CondoRepository;
import com.cotemig.services.CondoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by matheus.elias on 10/31/16.
 */
@Controller
public class CondoController {
    @Autowired
    private CondoService condoService;

    @Autowired
    private CondoRepository condoRepository;

    @GetMapping("/condos")
    public String selectView(Model model) {
        model.addAttribute("condos", condoService.find());

        return "condo/list";
    }

    @GetMapping("/condo/{id}")
    public String insertView(@PathVariable int id, Model model) {
        Condo condo = condoRepository.findOne(id);

        if (condo == null) {
            throw new NotFoundException();
        }

        model.addAttribute("condo", condo);

        return "condo/form";
    }

    @PostMapping("/condo/modify")
    public String modify(@ModelAttribute Condo condo){
        condoService.save(condo);

        return "redirect:/condos";
    }

    @DeleteMapping("/condo/{id}")
    public String delete(@PathVariable("id") int id) {
        condoService.remove(id);

        return "redirect:/condos";
    }
}
