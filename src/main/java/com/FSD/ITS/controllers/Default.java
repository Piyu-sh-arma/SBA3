package com.FSD.ITS.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Default {

    @GetMapping({"/"})
    public String fn_Default(){
        return "Congrats. This is default response";
    }

    @GetMapping({"/test"})
    public String fn_Test(){
        return "Test Successfull";
    }
}
