package com.cotemig.controllers;

import com.cotemig.models.Condo;
import com.cotemig.services.CondoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Created by matheus.elias on 10/31/16.
 */
@Controller
public class CondoController {
    @Autowired
    private CondoService condoService;

    @GetMapping("/condo/find")
    public String selectView(Model model) {
        model.addAttribute("condos", condoService.find());

        return "condo/find";
    }

    @GetMapping("/condo/insert")
    public String insertView(Model model) {
        model.addAttribute("condo", new Condo());
        model.addAttribute("method", "POST");

        return "condo/modify";
    }

    @GetMapping("/condo/update")
    public String updateView(Model model) {
        model.addAttribute("condo", new Condo());
        model.addAttribute("method", "PUT");

        return "condo/modify";
    }

    @PostMapping("/condo/modify")
    public void modify(@ModelAttribute Condo condo){
        condoService.save(condo);
    }

    @GetMapping("/condo/remove")
    public String removeView(Model model) {
        model.addAttribute("condo", new Condo());

        return "condo/remove";
    }

    @PostMapping("/condo/remove")
    public void remove(@ModelAttribute Condo condo){
        condoService.remove(condo.getCnpj());
    }
}
