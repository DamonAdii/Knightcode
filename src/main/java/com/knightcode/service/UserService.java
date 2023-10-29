package com.knightcode.service;

import com.knightcode.dto.*;
import com.knightcode.model.*;

import java.util.List;

public interface UserService {

    public boolean signup(UserDto userDto);

    User updateAccount(UserDto userDto);

    About updateAbout(AboutDto aboutDto,User user);

    public Education updateEducation(EducationDto educationDto, User user);

    public Experience updateExperience(ExperienceDto experienceDto, User user);

    public Project updateProject(ProjectDto projectDto, User user);

    List<User> getAllUsersExceptCurrentUser(long id);

    Boolean verifyToken(String token);

}
