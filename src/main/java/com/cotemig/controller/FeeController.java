package com.cotemig.controller;

import com.cotemig.dao.FeeDAO;
import com.cotemig.dao.FeeDAOImpl;
import com.cotemig.model.Fee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by matheus.elias on 11/2/16.
 */

@Controller
public class FeeController {

    @GetMapping("/fees")
    public String feeForm(Model model) {
        model.addAttribute("fee", new Fee());
        return "fee";
    }

    @PostMapping("/fees/pay")
    public void feeSubmit(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            FeeDAO feeDAO = new FeeDAOImpl();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                feeDAO.addFee(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
