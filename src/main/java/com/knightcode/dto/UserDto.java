package com.knightcode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String profileImageUrl;

    private String role;

    private boolean isEnabled;

    private Date regDate;

}
