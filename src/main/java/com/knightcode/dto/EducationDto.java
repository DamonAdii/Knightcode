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
public class EducationDto {

    private String collegeName;

    private String collegeStartYear;

    private String collegeEndYear;

    private String department;

    private String collegeStatus;

    private String collegeDegree;

}
