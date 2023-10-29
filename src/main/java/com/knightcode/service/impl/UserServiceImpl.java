package com.knightcode.service.impl;

import com.knightcode.dto.*;
import com.knightcode.model.*;
import com.knightcode.repository.*;
import com.knightcode.service.EmailService;
import com.knightcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final AboutRepository aboutRepository;

    private final EducationRepository educationRepository;

    private final ExperienceRepository experienceRepository;

    private final ProjectRepository projectRepository;

    private final ConfirmationRepository confirmationRepository;

    private final EmailService emailService;

    @Override
    public boolean signup(UserDto userDto) {

        boolean isRegister = false;

        User user = this.repository.findByEmail(userDto.getEmail());

        if(user != null){
            throw new RuntimeException("User Already present");
        }

        User user1 = new User();
        user1.setEmail(userDto.getEmail());
        user1.setFirstname(userDto.getFirstname());
        user1.setEnabled(false);
        user1.setPassword(encoder.encode(userDto.getPassword()));
        user1.setLastname(userDto.getLastname());
        user1.setRole("USER");
        user1.setProfileImageUrl("default.png");
        user1.setRegDate(new Date());

        this.repository.save(user1);


        // save the about
        About about = new About();
        about.setUser(user1);
        about.setCity("");
        about.setDob("");
        about.setCountry("");
        about.setCurrentProject("");
        about.setProfileBio("");
        about.setWorkType("");

        this.aboutRepository.save(about);

        // save the education - resume
        Education education = new Education();
        education.setUser(user1);
        education.setCollegeDegree("");
        education.setDepartment("");
        education.setCollegeName("");
        education.setCollegeEndYear("");
        education.setCollegeStartYear("");
        education.setCollegeStatus("");

        this.educationRepository.save(education);


        // save the experience - resume
        Experience experience = new Experience();
        experience.setUser(user1);
        experience.setExperienceSummary("");
        experience.setCityCompany("");
        experience.setYearOfExperience("");
        experience.setCurrentCompanyName("");
        experience.setCurrentJobDepartment("");
        experience.setJobRole("");
        experience.setJoiningYear("");
        experience.setResignYear("");

        this.experienceRepository.save(experience);

        // save the projects - resume
        Project project = new Project();
        project.setUser(user1);
        project.setProjectLink1("");
        project.setProjectLink2("");
        project.setProjectLink3("");
        project.setProjectLink4("");
        project.setProjectSummary("");
        project.setProjectTitle1("");
        project.setProjectTitle2("");
        project.setProjectTitle3("");
        project.setProjectTitle4("");

        this.projectRepository.save(project);

        isRegister = true;

        // now generate the token by user and save to db
        var confirmation = new Confirmation(user1);

        Confirmation savedConfirmation = this.confirmationRepository.save(confirmation);

        // sent confirmation link to confirm account
        this.emailService.simpleMailMessage(user1.getFirstname(), user1.getEmail(), savedConfirmation.getToken());

        return isRegister;
    }




    @Override
    public Boolean verifyToken(String token) {

        Confirmation findToken = this.confirmationRepository.findByToken(token);

        User user = this.repository.findByEmailIgnoreCase(findToken.getUser().getEmail());

        user.setEnabled(true);

        repository.save(user);

        this.confirmationRepository.delete(findToken);

        return Boolean.TRUE;
    }



    @Override
    public User updateAccount(UserDto userDto) {

        Optional<User> user = this.repository.findById(userDto.getId());

        if(!user.isPresent()){
         throw new RuntimeException("User is not found");
        }

        User user2 = user.get();

        user2.setFirstname(userDto.getFirstname());
        user2.setLastname(userDto.getLastname());

        User user3 = this.repository.save(user2);

        return user3;
    }


    @Override
    public About updateAbout(AboutDto aboutDto,User user) {

        About about = this.aboutRepository.findByUser(user);

        about.setCity(aboutDto.getCity());
        about.setDob(aboutDto.getDob());
        about.setCountry(aboutDto.getCountry());
        about.setCurrentProject(aboutDto.getCurrentProject());
        about.setProfileBio(aboutDto.getProfileBio());
        about.setWorkType(aboutDto.getWorkType());
        about.setCurrentCompany(aboutDto.getCurrentCompany());

        About about1 = this.aboutRepository.save(about);

        return about1;
    }



    @Override
    public Education updateEducation(EducationDto educationDto, User user) {

        Education education = this.educationRepository.findByUser(user);

        education.setCollegeStatus(educationDto.getCollegeStatus());
        education.setCollegeDegree(educationDto.getCollegeDegree());
        education.setDepartment(educationDto.getDepartment());
        education.setCollegeName(educationDto.getCollegeName());
        education.setCollegeStartYear(educationDto.getCollegeStartYear());
        education.setCollegeEndYear(educationDto.getCollegeEndYear());


        Education education1 = this.educationRepository.save(education);

        return education1;
    }






    @Override
    public Experience updateExperience(ExperienceDto experienceDto, User user) {

        Experience experience = this.experienceRepository.findByUser(user);

        experience.setExperienceSummary(experienceDto.getExperienceSummary());
        experience.setYearOfExperience(experienceDto.getYearOfExperience());
        experience.setJobRole(experienceDto.getJobRole());
        experience.setResignYear(experienceDto.getResignYear());
        experience.setCityCompany(experienceDto.getCityCompany());
        experience.setJoiningYear(experienceDto.getJoiningYear());
        experience.setCurrentJobDepartment(experienceDto.getCurrentJobDepartment());
        experience.setCurrentCompanyName(experienceDto.getCurrentCompanyName());


        Experience experience1 = this.experienceRepository.save(experience);

        return experience1;
    }


    @Override
    public Project updateProject(ProjectDto projectDto, User user) {

        Project project = this.projectRepository.findByUser(user);

        project.setProjectTitle1(projectDto.getProjectTitle1());
        project.setProjectTitle2(projectDto.getProjectTitle2());
        project.setProjectTitle3(projectDto.getProjectTitle3());
        project.setProjectTitle4(projectDto.getProjectTitle4());
        project.setProjectLink1(projectDto.getProjectLink1());
        project.setProjectLink2(projectDto.getProjectLink2());
        project.setProjectLink3(projectDto.getProjectLink3());
        project.setProjectLink4(projectDto.getProjectLink4());
        project.setProjectSummary(projectDto.getProjectSummary());

        Project project1 = this.projectRepository.save(project);

        return project1;
    }

    @Override
    public List<User> getAllUsersExceptCurrentUser(long id) {
        return repository.findAllByIdNot(id);
    }


}
