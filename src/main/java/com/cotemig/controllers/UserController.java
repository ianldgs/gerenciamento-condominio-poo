package com.cotemig.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by matheus.elias on 11/2/16.
 */

@Controller
public class UserController {

    @RequestMapping("/user")
    public String user() {
        return "";
    }
}
