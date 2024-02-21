package com.kazimoto.digitalbackend.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "Logged in successfully";
    }
}
