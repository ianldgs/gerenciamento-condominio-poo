package com.cotemig.controller;

import com.cotemig.dao.FeeDAOImpl;
import com.cotemig.model.Fee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

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
    public void feeSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute Fee fee) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String fileData = bufferedReader.readLine();

            if(!fileData.isEmpty()) {
                String idCondo = fileData.substring(0, 13);
                String cpfDweller = fileData.substring(14, 25);
                String monthYear = fileData.substring(26, 32);
                double value = Double.parseDouble(fileData.substring(33, 41));

                fee.setValue(value);
                fee.setCode(fileData);
                fee.setPaid(true);
                fee.setCpfDweller(cpfDweller);
                fee.setIdCondo(idCondo);

                int month = Integer.parseInt(monthYear.substring(0, 1));
                int year = Integer.parseInt(monthYear.substring(2, 5));

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);

                fee.setDate(calendar.getTime());

                FeeDAOImpl feeDAO = new FeeDAOImpl();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
