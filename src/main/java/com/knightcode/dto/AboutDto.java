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
public class AboutDto {

    private long id;

    private long userId;

    private String dob;

    private String workType;

    private String currentCompany;

    private String currentProject;

    private String city;

    private String country;

    private String profileBio;


}
