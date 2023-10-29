package com.knightcode.service.impl;

import com.knightcode.model.CompilationResult;
import com.knightcode.service.CompilerService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class JavaCompilerService implements CompilerService {

    @Override
    public CompilationResult compileJavaCode(String code) {
        CompilationResult result = new CompilationResult();
        try {
            // Save the code to a .java file
            String fileName = "Main.java";
            Path sourceFile = Paths.get("/temp"+ File.separator +fileName);
            Files.write(sourceFile, code.getBytes());

            // Compile the code
            Process process = new ProcessBuilder("javac", fileName).start();
            int exitCode = process.waitFor();

            // Capture the compilation output
            InputStream output = process.getInputStream();
            InputStream error = process.getErrorStream();

            String outputText = new String(output.readAllBytes(), StandardCharsets.UTF_8);
            String errorText = new String(error.readAllBytes(), StandardCharsets.UTF_8);

            result.setOutput(outputText);
            result.setError(errorText);
            result.setSuccess(exitCode == 0);
            System.out.println("Process Text is :"+outputText);
            System.out.println("Error Text is :"+error);
            System.out.println("File path is :"+sourceFile);
            // Clean up the source file
            // Files.delete(sourceFile);
        } catch (IOException | InterruptedException e) {
            result.setSuccess(false);
            result.setError(e.getMessage());
        }
        return result;
    }



}
