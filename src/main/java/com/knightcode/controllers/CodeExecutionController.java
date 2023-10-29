package com.knightcode.controllers;


import com.knightcode.dto.CodeSubmissionDto;
import com.knightcode.model.CodeSubmission;
import com.knightcode.model.CompilationResult;
import com.knightcode.service.CompilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.OutputStream;
import java.io.StringWriter;

@Controller
@RequiredArgsConstructor
public class CodeExecutionController {

    private final CompilerService service;

    @PostMapping("/execute")
    public String compile(@RequestParam("javaCode") String javaCode, Model model) {

        System.out.println("Code is :"+javaCode);
        CompilationResult result = this.service.compileJavaCode(javaCode);

        System.out.println(result.getOutput()+" "+result.getOutput());
        // Implement code compilation logic here
        // Store results in the model
        return "home";
    }




}
