package com.cotemig.controllers;

import com.cotemig.exceptions.NotFoundException;
import com.cotemig.models.Condo;
import com.cotemig.repositories.CondoRepository;
import com.cotemig.services.CondoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by matheus.elias on 10/31/16.
 */
@Controller
public class CondoController {
    @Autowired
    private CondoRepository condoRepository;

    @GetMapping("/condos")
    public String list(Model model) {
        model.addAttribute("condos", condoRepository.findAll());
        return "condo/list";
    }

    @GetMapping("/condo}")
    public String createForm(Model model) {
        model.addAttribute("condo", new Condo());
        return "condo/form";
    }

    @PostMapping("/condo")
    public String createOrEdit(@Valid Condo condo, BindingResult result) {
        if (result.hasErrors()) {
            return "condo/form";
        }

        condoRepository.saveAndFlush(condo);

        return "redirect:/condos";
    }

    @GetMapping("/condo/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Condo condo = condoRepository.findOne(id);

        if (condo == null) {
            throw new NotFoundException();
        }

        model.addAttribute("condo", condo);

        return "condo/form";
    }

    @DeleteMapping("/condo/{id}")
    public String delete(@PathVariable("id") int id) {
        condoRepository.delete(id);

        return "redirect:/condos";
    }
}
