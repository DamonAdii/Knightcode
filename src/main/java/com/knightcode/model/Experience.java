package com.knightcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String currentCompanyName;

    @Column(length = 50)
    private String joiningYear;

    @Column(length = 50)
    private String resignYear;

    private String yearOfExperience;

    private String cityCompany;

    private String currentJobDepartment;

    private String jobRole;

    @Column(length = 10000)
    private String experienceSummary;

    @OneToOne
    @JsonIgnore
    private User user;

}
