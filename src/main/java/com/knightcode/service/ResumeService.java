package com.knightcode.service;

import com.knightcode.dto.EducationDto;
import com.knightcode.model.Education;

public interface ResumeService {

    public Education updateEducation(EducationDto educationDto);

}
