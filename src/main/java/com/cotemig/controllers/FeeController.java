package com.cotemig.controllers;

import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.repositories.CondoRepository;
import com.cotemig.services.CondoService;
import com.cotemig.services.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by matheus.elias on 11/2/16.
 */

@Controller
public class FeeController {
    @Autowired
    FeeService feeService;

    @Autowired
    CondoService condoService;

    @Autowired
    CondoRepository condoRepository;

    @GetMapping("/fee")
    public String feeForm(Model model) {
        model.addAttribute("condos", condoService.find());

        return "fee/form";
    }

    @PostMapping("/fee")
    public String create(Model model,
                         @RequestParam("condoId") int condoId,
                         @RequestParam("totalAmount") double totalAmount,
                         @RequestParam("month") int month,
                         @RequestParam("year") int year,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "fee/form";
        }

        Condo condo = condoRepository.findOne(condoId);

        feeService.divideTotalByResident(condo, totalAmount, month, year);

        return "redirect:fees";
    }

    @GetMapping("/fees")
    public String feeList(Model model) {
        model.addAttribute("fee", new Fee());
        return "fee";
    }

    @GetMapping("/fees/confirm")
    public String getConfirm() {
        return "fee/confirm";
    }

    @PostMapping("/fees/confirm")
    public String postConfirm(@RequestParam("file") MultipartFile file, Model model) {
        try {
            feeService.parseFile(file);
            model.addAttribute("message", "Pagamentos confirmados com sucesso!");
            return "fee/confirm";
        }
        catch (IOException e) {
            model.addAttribute("message", "Ocorreu um erro ao ler o arquivo.");
            return "fee/confirm";
        }
    }
}
