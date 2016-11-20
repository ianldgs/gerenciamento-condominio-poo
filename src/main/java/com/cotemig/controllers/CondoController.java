package com.cotemig.controllers;

import com.cotemig.models.Condo;
import com.cotemig.services.CondoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by matheus.elias on 10/31/16.
 */
@Controller
public class CondoController {
    @Autowired
    private CondoService condoService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/condo/insert")
    public String insertView(Model model) {
        model.addAttribute("condo", new Condo());
        return "condo/insert";
    }

    @PostMapping("/condo/insert")
    public void insert(@ModelAttribute Condo condo){
        condoService.insert(condo);
    }
}
