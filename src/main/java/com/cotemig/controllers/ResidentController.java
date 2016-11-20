package com.cotemig.controllers;

import com.cotemig.exceptions.NotFoundException;
import com.cotemig.models.Resident;
import com.cotemig.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by matheus.elias on 11/2/16.
 */
@Controller
public class ResidentController {
    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping("residents")
    public String list() {
        return "resident/list";
    }

    @GetMapping("resident")
    public String createForm(Model model) {
        model.addAttribute("resident", new Resident());
        return "resident/form";
    }

    @PostMapping("resident")
    public String create(@Valid Resident resident, BindingResult result) {
        if (result.hasErrors()) {
            return "resident/form";
        }

        residentRepository.saveAndFlush(resident);

        return "redirect:residents";
    }

    @GetMapping("resident/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        Resident resident = residentRepository.findOne(id);

        if (null == resident) {
            throw new NotFoundException();
        }

        model.addAttribute("resident", resident);

        return "resident/form";
    }

    @PutMapping("resident/{id}")
    public String edit(@Valid Resident resident) {
        residentRepository.saveAndFlush(resident);

        return "resident/form";
    }

    @DeleteMapping("resident/{id}")
    public String delete(int id) {
        residentRepository.delete(id);
        residentRepository.flush();

        return "redirect:resident/list";
    }
}
