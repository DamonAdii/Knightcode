package com.knightcode.model;

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
@Table(name = "softwares")
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String softwareImageUrl;

    private String name;

}
