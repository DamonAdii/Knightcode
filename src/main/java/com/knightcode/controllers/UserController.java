package com.knightcode.controllers;

import com.knightcode.dto.UserDto;
import com.knightcode.service.UserService;
import com.knightcode.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public String signup(@ModelAttribute("user") UserDto userDto,Model model){

        boolean isRegister = this.service.signup(userDto);

        if(isRegister){
            Message message = new Message("Register Successfully, we have sent a mail to your registered email! please verify you account","alert-success");
            model.addAttribute("message",message);
            return "signup";
        }

        Message message = new Message("Registration Failed! Please try another one","alert-danger");
        model.addAttribute("message",message);
        return "signup";

    }


    @GetMapping("/confirm")
    public String confirmUserAccount(@RequestParam("token") String token, Model model)
    {
        Boolean isSuccess = this.service.verifyToken(token);

        Message message = new Message("Account verified succefully","alert-success");

        model.addAttribute("message",message);

        return "login";
    }


}
