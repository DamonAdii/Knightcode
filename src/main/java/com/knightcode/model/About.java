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
@Table(name = "about")
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String dob;

    private String workType;

    private String currentCompany;

    private String currentProject;

    private String city;

    private String country;

    @Column(length = 10000)
    private String profileBio;

    @OneToOne
    @JsonIgnore
    private User user;

}
