package com.knightcode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String role;

    private String profileImageUrl;

    private boolean isEnabled;

    private Date regDate;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private About about;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Education education;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Experience experience;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Project project;

    @ManyToMany
    @JoinTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_softwares", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "software_id"))
    private Set<Software> softwares = new HashSet<>();

}
