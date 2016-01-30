package org.helpful.nag.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {


    @RequestMapping("/")
    public String root(){
        return "Hello World";
    }

}
