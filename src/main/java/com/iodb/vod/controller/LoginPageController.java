package com.iodb.vod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginPageController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        return "login";
    }
}
