package com.example.PhotoAppUser.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AccountController {

    @GetMapping("/status")
    public String status(){
        return "Photo App Account Manager Working...";
    }
}
