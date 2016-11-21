package com.cotemig.controllers;

import com.cotemig.models.Condo;
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

    @GetMapping("/condos")
    public String selectView(Model model) {
        model.addAttribute("condos", condoService.find());

        return "condo/list";
    }

    @GetMapping("/condo")
    public String insertView(Model model) {
        model.addAttribute("condo", new Condo());

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
