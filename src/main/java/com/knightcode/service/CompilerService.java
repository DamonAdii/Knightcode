package com.knightcode.service;

import com.knightcode.model.CompilationResult;

public interface CompilerService {

    CompilationResult compileJavaCode(String code);

}
