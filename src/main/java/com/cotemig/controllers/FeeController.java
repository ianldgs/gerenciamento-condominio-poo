package com.cotemig.controllers;

import com.cotemig.repositories.FeeRepository;
import com.cotemig.models.Fee;
import com.cotemig.services.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by matheus.elias on 11/2/16.
 */

@Controller
public class FeeController {
    @Autowired
    FeeService feeService;

    @GetMapping("/fees")
    public String feeForm(Model model) {
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
