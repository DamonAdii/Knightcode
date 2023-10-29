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
public class ProjectDto {

    private String projectTitle1;

    private String projectLink1;

    private String projectTitle2;

    private String projectLink2;

    private String projectTitle3;

    private String projectLink3;

    private String projectTitle4;

    private String projectLink4;

    private String projectSummary;
}
