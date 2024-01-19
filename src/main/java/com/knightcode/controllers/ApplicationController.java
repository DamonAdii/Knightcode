package com.knightcode.controllers;

import com.knightcode.dto.UserDto;
import com.knightcode.model.User;
import com.knightcode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final UserRepository repository;

    @GetMapping("/message")
    @ResponseBody
    public String getMessage(){

        return "Hi, i user";
    }


    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("title","KnightCode - Be The One");
        return "home";
    }

    @GetMapping("/signin")
    public String getLogin(Model model){
        model.addAttribute("title","SignIn - KnightCode");
        return "login";
    }

    @GetMapping("/signup")
    public String getSignup(Model model){
        model.addAttribute("title","SignUp - KnightCode");
        return "signup";
    }



    @GetMapping("/solve-challenge")
    public String getSolveChallenge(Model model){
        model.addAttribute("title","Challenge - KnightCode");
        return "solve-challenge";
    }



    @GetMapping("/auth/admin/message")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public String getAdminMessage(){

        return "Hi, i'm a Admin";
    }




    @ModelAttribute
    public void isUserLoggedIn(Model model,Principal principal){

        if(principal!=null){
            String username = principal.getName();
            System.out.println("Logged in user is : "+username);

            User user = this.repository.findByEmail(username);

            model.addAttribute("user",user);
        }


    }




}
