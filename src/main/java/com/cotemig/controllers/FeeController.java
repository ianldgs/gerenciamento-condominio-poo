package com.cotemig.controllers;

import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.repositories.FeeRepository;
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
    FeeRepository feeRepository;

    @GetMapping("/fees")
    public String list(Model model) {
        model.addAttribute("fees", feeRepository.findAll());
        return "fee/list";
    }

    @GetMapping("/fees/filter")
    public String filter(Model model,
                         @RequestParam("year") int year,
                         @RequestParam("month") int month) {

        model.addAttribute("fee",
            feeRepository.findByDateWithFormat(
                String.format("%04d/%02d", year, month),
                "YYYY/MM"
            )
        );

        return "fee/list";
    }

    @GetMapping("/fee")
    public String feeForm(Model model) {
        model.addAttribute("fee", new Fee());
        model.addAttribute("condos", condoService.find());

        return "fee/form";
    }

    @PostMapping("/fee")
    public String create(Condo condo, double totalAmount, int month, int year, BindingResult result) {
        if (result.hasErrors()) {
            return "fee/form";
        }

        feeService.divideTotalByResident(condo, totalAmount, month, year);

        return "redirect:fees";
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
