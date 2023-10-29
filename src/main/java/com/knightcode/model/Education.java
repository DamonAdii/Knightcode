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
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String collegeName;

    @Column(length = 50)
    private String collegeStartYear;

    @Column(length = 50)
    private String collegeEndYear;

    private String department;

    private String collegeStatus;

    private String collegeDegree;

    @OneToOne
    @JsonIgnore
    private User user;

}
