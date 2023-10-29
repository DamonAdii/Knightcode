package com.knightcode.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    @GetMapping("/resume")
    public String getResume(Model model){


        model.addAttribute("template","template_2");
        model.addAttribute("title","Resume - KnightCode");
        return "resume";
    }






}
