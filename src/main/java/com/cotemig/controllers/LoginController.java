package com.cotemig.controllers;

import com.cotemig.exceptions.InvalidEmailOrPasswordException;
import com.cotemig.models.Condo;
import com.cotemig.models.User;
import com.cotemig.repositories.UserRepository;
import com.cotemig.services.CondoService;
import com.cotemig.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


/**
 * Created by Ian Luca on 20/11/2016.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String form() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session,
                        Model model,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password) {

        try {
            session.setAttribute("user", userService.login(email, password));
            return "redirect:/";
        } catch (InvalidEmailOrPasswordException e) {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/")
    public String greet() {
        return "greeting";
    }
}
