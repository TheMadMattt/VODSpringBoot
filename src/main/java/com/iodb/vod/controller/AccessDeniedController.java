package com.iodb.vod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {
    
    @RequestMapping(value = "/403")
    public String errorPage(){
	return "error";
    }
}
