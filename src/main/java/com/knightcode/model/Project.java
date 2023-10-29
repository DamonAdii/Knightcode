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
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String projectTitle1;

    private String projectLink1;

    private String projectTitle2;

    private String projectLink2;

    private String projectTitle3;

    private String projectLink3;

    private String projectTitle4;

    private String projectLink4;

    @Column(length = 10000)
    private String projectSummary;

    @OneToOne
    @JsonIgnore
    private User user;


}
