package com.knightcode.controllers;

import com.knightcode.config.ApplicationFilesConfig;
import com.knightcode.dto.*;
import com.knightcode.model.*;
import com.knightcode.repository.*;
import com.knightcode.service.UserService;
import com.knightcode.service.impl.FollowService;
import com.knightcode.utils.Message;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository repository;

    private final UserService service;

    private final AboutRepository aboutRepository;

    private final EducationRepository educationRepository;

    private final ExperienceRepository experienceRepository;

    private final ProjectRepository projectRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ApplicationFilesConfig filesConfig;

    private final FollowService followService;



    @GetMapping("/profile")
    public String getUserProfile(Model model, Principal principal){

        String username = principal.getName();
        System.out.println("Logged in user is : "+username);

        // now get the user fram database

        User user = this.repository.findByEmail(username);

        About about = this.aboutRepository.findByUser(user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        Project project = this.projectRepository.findByUser(user);

        // Load the list of other users

        List<User> userList = service.getAllUsersExceptCurrentUser(user.getId());
        model.addAttribute("userList", userList);

        // Retrieve the user's updated skills

        model.addAttribute("education",education);

        model.addAttribute("currentUser",user);

        model.addAttribute("experience",experience);

        model.addAttribute("project",project);

        model.addAttribute("about",about);

        model.addAttribute("user",user);

        return "profile";
    }



    @GetMapping("/profile/{userId}")
    public String viewUserProfile(@PathVariable Long userId, Model model,Principal principal) {

        String username =  principal.getName();
        User currentUser = this.repository.findByEmail(username); // Replace with your authentication logic

        User user = repository.findById(userId).orElse(null);

        About about = this.aboutRepository.findByUser(user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        Project project = this.projectRepository.findByUser(user);

        // Load the list of other users

        boolean isFollowing = this.followService.isFollowing(currentUser,user);

        List<User> userList = service.getAllUsersExceptCurrentUser(user.getId());

        if (user != null) {
            model.addAttribute("userList", userList);

            model.addAttribute("currentUser",currentUser);

            model.addAttribute("isFollowing",isFollowing);

            model.addAttribute("userId",userId);

            model.addAttribute("education",education);

            model.addAttribute("experience",experience);

            model.addAttribute("project",project);

            model.addAttribute("about",about);

            model.addAttribute("user", user);

            return "profile"; // Return the name of the Thymeleaf template for displaying the user's profile

        } else {
            // Handle the case where the user with the given ID is not found, e.g., show an error page or redirect to a user list
            return "redirect:/users"; // Redirect to the user list or an error page
        }
    }



    @GetMapping("/update-profile")
    public String updateProfile(Model model, Principal principal){

        String username = principal.getName();
        System.out.println("Logged in user is : "+username);

        // now get the user fram database

        User user = this.repository.findByEmail(username);

        About about = this.aboutRepository.findByUser(user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        Project project = this.projectRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();
        model.addAttribute("updatedSkills", updatedSkills);


        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);

        model.addAttribute("education",education);

        model.addAttribute("experience",experience);

        model.addAttribute("project",project);

        model.addAttribute("about",about);

        model.addAttribute("user",user);

        model.addAttribute("title","Update Profile - KnightCode");

        return "update-profile";
    }


    @PostMapping("/update-account")
    public String updateAccount(@ModelAttribute UserDto userDto,Model model){

        System.out.println(userDto.getFirstname());
        System.out.println(userDto.getLastname());
        System.out.println(userDto.getId());

        User user = this.service.updateAccount(userDto);

        About about = this.aboutRepository.findByUser(user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        Project project = this.projectRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();
        model.addAttribute("updatedSkills", updatedSkills);

        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);


        if(user!=null){

            Message message = new Message("Account Updated Successfully","alert-success");
            model.addAttribute("updatedSkills", updatedSkills);
            model.addAttribute("message",message);
            model.addAttribute("about",about);
            model.addAttribute("education",education);
            model.addAttribute("experience",experience);
            model.addAttribute("project",project);
            model.addAttribute("user",user);
            return "update-profile";

        }

        Message message = new Message("Account Not Updated!!!Please Try with other","alert-danger");
        model.addAttribute("message",message);
        model.addAttribute("updatedSkills", updatedSkills);
        return "update-profile";
    }







    @PostMapping("/update-about")
    public String updateAccount(@ModelAttribute AboutDto aboutDto, Model model,Principal principal){

        System.out.println(aboutDto.getUserId());

        String username = principal.getName();

        User user =  this.repository.findByEmail(username);

        About about = this.service.updateAbout(aboutDto,user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        Project project = this.projectRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();

        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);

        if(about!=null){

            Message message = new Message("About Updated Successfully","alert-success");
            model.addAttribute("user",user);
            model.addAttribute("updatedSkills", updatedSkills);
            model.addAttribute("about",about);
            model.addAttribute("education",education);
            model.addAttribute("experience",experience);
            model.addAttribute("project",project);
            model.addAttribute("aboutm",message);

            return "update-profile";

        }

        Message message = new Message("About Not Updated!!!Please Try with other","alert-danger");
        model.addAttribute("aboutm",message);
        model.addAttribute("updatedSkills", updatedSkills);
        return "update-profile";
    }












    // update education

    @PostMapping("/update-resume-education")
    public String updateEducation(@ModelAttribute EducationDto educationDto, Model model, Principal principal){

        System.out.println(educationDto.getCollegeName());

        String username = principal.getName();

        User user =  this.repository.findByEmail(username);

        Education education = this.service.updateEducation(educationDto,user);

        Experience experience = this.experienceRepository.findByUser(user);

        Project project = this.projectRepository.findByUser(user);

        About about = this.aboutRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();

        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);

        if(about!=null){

            Message message = new Message("Education Updated Successfully","alert-success");
            model.addAttribute("user",user);
            model.addAttribute("updatedSkills", updatedSkills);
            model.addAttribute("about",about);
            model.addAttribute("education",education);
            model.addAttribute("experience",experience);
            model.addAttribute("project",project);
            model.addAttribute("educationMessage",message);

            return "update-profile";

        }

        Message message = new Message("Education Not Updated!!!Please Try with other","alert-danger");
        model.addAttribute("educationMessage",message);
        model.addAttribute("updatedSkills", updatedSkills);
        return "update-profile";
    }








    // update experience

    @PostMapping("/update-resume-experience")
    public String updateExperience(@ModelAttribute ExperienceDto experienceDto, Model model, Principal principal){

        System.out.println(experienceDto.getCurrentCompanyName());

        String username = principal.getName();

        User user =  this.repository.findByEmail(username);

        Experience experience = this.service.updateExperience(experienceDto,user);

        Education education = this.educationRepository.findByUser(user);

        Project project = this.projectRepository.findByUser(user);

        About about = this.aboutRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();

        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);

        if(about!=null){

            Message message = new Message("Experience Updated Successfully","alert-success");
            model.addAttribute("user",user);
            model.addAttribute("updatedSkills", updatedSkills);
            model.addAttribute("about",about);
            model.addAttribute("education",education);
            model.addAttribute("experience",experience);
            model.addAttribute("project",project);
            model.addAttribute("experienceMessage",message);

            return "update-profile";

        }

        Message message = new Message("Experience Not Updated!!!Please Try with other","alert-danger");
        model.addAttribute("experienceMessage",message);
        model.addAttribute("updatedSkills", updatedSkills);
        return "update-profile";
    }








    // update project

    @PostMapping("/update-resume-project")
    public String updateProject(@ModelAttribute ProjectDto projectDto, Model model, Principal principal){

        System.out.println(projectDto.getProjectTitle1());

        String username = principal.getName();

        User user =  this.repository.findByEmail(username);

        Project project = this.service.updateProject(projectDto,user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        About about = this.aboutRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();

        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);

        if(about!=null){

            Message message = new Message("Projects Updated Successfully","alert-success");
            model.addAttribute("user",user);
            model.addAttribute("updatedSkills", updatedSkills);
            model.addAttribute("about",about);
            model.addAttribute("education",education);
            model.addAttribute("experience",experience);
            model.addAttribute("project",project);
            model.addAttribute("projectMessage",message);

            return "update-profile";

        }

        Message message = new Message("Projects Not Updated!!!Please Try with other","alert-danger");
        model.addAttribute("projectMessage",message);
        model.addAttribute("updatedSkills", updatedSkills);
        return "update-profile";
    }






    // change password

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute("oldPassword") String oldPassword,@ModelAttribute("newPassword") String newPassword,Model model,Principal principal){

        System.out.println("old password is : "+oldPassword);
        System.out.println("new password is : "+newPassword);

        String username = principal.getName();

         User user = this.repository.findByEmail(username);

         Project project = this.projectRepository.findByUser(user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        About about = this.aboutRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();

        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);

         if(!this.bCryptPasswordEncoder.matches(oldPassword,user.getPassword())){

             Message message = new Message("Old Password is not matching!!!Please Try with other","alert-danger");
             model.addAttribute("passwordMessage",message);
             model.addAttribute("updatedSkills", updatedSkills);
             model.addAttribute("user",user);
             model.addAttribute("about",about);
             model.addAttribute("education",education);
             model.addAttribute("experience",experience);
             model.addAttribute("project",project);
             return "update-profile";
         }

         user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));

         this.repository.save(user);

        model.addAttribute("user",user);
        model.addAttribute("about",about);
        model.addAttribute("updatedSkills", updatedSkills);
        model.addAttribute("education",education);
        model.addAttribute("experience",experience);
        model.addAttribute("project",project);
        Message message = new Message("New Password is Updated","alert-success");
        model.addAttribute("passwordMessage",message);

        return "update-profile";
    }




    // update-profile-picture

    @PostMapping("/update-profile-picture")
    public String updateProfilePic(@RequestParam("profileImageUrl") MultipartFile file,Model model,Principal principal) throws IOException {




        /*
         * 1.delete image in directory.
         * 2.delete the image in database.
         * 3.create user directory.
         * 4.rename the file save the file in the specified directory.
         * 5.update the database usertable.
         */


        System.out.println("file image name is :"+file.getOriginalFilename());

        String username = principal.getName();

        User user = this.repository.findByEmail(username);

        long User_Id = user.getId();

        String replaceVariable = this.filesConfig.getReplaceuseridvariablename().toString();
        String replaceWith = String.valueOf(User_Id);
        String EXTERNAL_FILE_PATH = this.filesConfig.getUserProfileDirectory() ;
        String RootPath =  EXTERNAL_FILE_PATH.replace(replaceVariable, replaceWith);
        String ProfileImagePath = RootPath + file.getOriginalFilename();

        System.out.println("EXTERNAL_FILE_PATH : " + EXTERNAL_FILE_PATH);

        int T = 0;
        int UserProfileDeleted = 0;
        T++;
        File file1 = new File(EXTERNAL_FILE_PATH);
        if (file1.delete()) {
            UserProfileDeleted++;
            System.out.println(EXTERNAL_FILE_PATH+" File deleted");
        } else {
            UserProfileDeleted++;
            System.out.println("Failed to delete the user profile ");
        }


        //create user directory if does not exist
        int DirectoryDone = 0;
        T++;
        File dir = new File(RootPath);
        FileUtils.deleteDirectory(dir);
        if (!dir.exists()){
            dir.mkdirs();
            DirectoryDone++;
        } else {
            DirectoryDone++;
        }


        String fileName = file.getOriginalFilename();

        String NewProfilePhotoFileName = "p_"+User_Id+"."+fileName.split("\\.")[1];
        //System.out.println("NewProfilePhotoFileName : "+NewProfilePhotoFileName);
        int SaveImageInDirectory = 0;
        T++;
        file.transferTo(new File(RootPath+NewProfilePhotoFileName));
        File dir1 = new File(RootPath+NewProfilePhotoFileName);
        if (dir1.exists()) {
            SaveImageInDirectory++;
        }

        //update the database usertable
        Project project = this.projectRepository.findByUser(user);

        Education education = this.educationRepository.findByUser(user);

        Experience experience = this.experienceRepository.findByUser(user);

        About about = this.aboutRepository.findByUser(user);

        // Retrieve the user's updated skills
        Set<Skill> updatedSkills = user.getSkills();

        // Retrieve the user's updated skills
        Set<Software> updatedSoftwares = user.getSoftwares();
        model.addAttribute("updatedSoftwares", updatedSoftwares);

        if(!file.isEmpty()){

            //

            try {

                user.setProfileImageUrl(NewProfilePhotoFileName);

                this.repository.save(user);

                Message message = new Message("File is Uploaded Successfully","alert-success");
                model.addAttribute("profileImageMessage",message);
                model.addAttribute("updatedSkills", updatedSkills);
                model.addAttribute("user",user);
                model.addAttribute("about",about);
                model.addAttribute("education",education);
                model.addAttribute("experience",experience);
                model.addAttribute("project",project);
                return "update-profile";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        Message message = new Message("File is Empty","alert-danger");
        model.addAttribute("profileImageMessage",message);
        model.addAttribute("updatedSkills", updatedSkills);
        model.addAttribute("user",user);
        model.addAttribute("about",about);
        model.addAttribute("education",education);
        model.addAttribute("experience",experience);
        model.addAttribute("project",project);
        return "update-profile";
    }








}
