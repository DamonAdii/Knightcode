package com.knightcode.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {

    private String currentCompanyName;

    private String joiningYear;

    private String resignYear;

    private String yearOfExperience;

    private String cityCompany;

    private String currentJobDepartment;

    private String jobRole;

    private String experienceSummary;

}
