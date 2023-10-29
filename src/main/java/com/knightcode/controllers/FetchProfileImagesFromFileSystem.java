package com.knightcode.controllers;
import com.knightcode.config.ApplicationFilesConfig;
import com.knightcode.model.User;
import com.knightcode.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.URLConnection;
import java.security.Principal;

@Controller
@RequestMapping("/FetchUserProfileFromFileSystem")
@RequiredArgsConstructor
public class FetchProfileImagesFromFileSystem {

    private final UserRepository userRepository;

    private final ApplicationFilesConfig filesConfig;


    @RequestMapping("/FetchProfilePic")
    public void FetchLoggedUserProfile(HttpServletRequest request, HttpServletResponse response, Model model, Principal principal) throws IOException {

       String username =  principal.getName();

        User user = this.userRepository.findByEmail(username);

        if(user != null) {
            String replaceVariable = this.filesConfig.getReplaceuseridvariablename().toString();
            String replaceWith = String.valueOf(user.getId()); //"3";//

            //String EXTERNAL_FILE_PATH = artfaabconfig.getUserProfileDirectory()+"profile.jpg";
            //String UserImageName = FetchUserProfileImageName(replaceWith);
            String UserImageName = "";

            String RootPath = filesConfig.getUserProfileDirectory();
            RootPath = RootPath.replace(replaceVariable,replaceWith);

            String[] pathnames;
            File f = new File(RootPath);
            if (f.exists()) {
                // Populates the array with names of files and directories
                pathnames = f.list();
                // For each pathname in the pathnames array
                for (String pathname : pathnames) {
                    // Print the names of files and directories
                    //System.out.println(pathname);
                    UserImageName = pathname;
                }
            }


            String EXTERNAL_FILE_PATH = RootPath+UserImageName;

            File file = new File(EXTERNAL_FILE_PATH);
            if (file.exists()) {

                //get the mimetype
                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) {
                    //unknown mimetype so set the mimetype to application/octet-stream
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                /**
                 * In a regular HTTP response, the Content-Disposition response header is a
                 * header indicating if the content is expected to be displayed inline in the
                 * browser, that is, as a Web page or as part of a Web page, or as an
                 * attachment, that is downloaded and saved locally.
                 *
                 */

                /**
                 * Here we have mentioned it to show inline
                 */
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

                //Here we have mentioned it to show as attachment
                //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

                response.setContentLength((int) file.length());

                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

                FileCopyUtils.copy(inputStream, response.getOutputStream());

            }
        }


    }










    @RequestMapping("/FetchProfilePic/{UserId}")
    public void FetchUserProfile(HttpServletRequest request, HttpServletResponse response,@PathVariable("UserId") String UserId) throws IOException {

        String replaceVariable = filesConfig.getReplaceuseridvariablename().toString();
        String replaceWith = UserId;//"3";//

        //String EXTERNAL_FILE_PATH = artfaabconfig.getUserProfileDirectory()+"profile.jpg";
        //String UserImageName = FetchUserProfileImageName(replaceWith);
        String UserImageName = "";

        String RootPath = filesConfig.getUserProfileDirectory();
        RootPath = RootPath.replace(replaceVariable,replaceWith);

        String[] pathnames;
        File f = new File(RootPath);

        if (f.exists()) {
            // Populates the array with names of files and directories
            pathnames = f.list();
            // For each pathname in the pathnames array
            for (String pathname : pathnames) {
                // Print the names of files and directories
                //System.out.println(pathname);
                UserImageName = pathname;
            }
        }



        //String EXTERNAL_FILE_PATH = artfaabconfig.getUserProfileDirectory()+UserImageName;
        //EXTERNAL_FILE_PATH = EXTERNAL_FILE_PATH.replace(replaceVariable,replaceWith);

        String EXTERNAL_FILE_PATH = RootPath+UserImageName;
        //System.out.println("EXTERNAL_FILE_PATH Free DB: "+EXTERNAL_FILE_PATH);


        File file = new File(EXTERNAL_FILE_PATH);
        if (file.exists()) {

            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            /**
             * In a regular HTTP response, the Content-Disposition response header is a
             * header indicating if the content is expected to be displayed inline in the
             * browser, that is, as a Web page or as part of a Web page, or as an
             * attachment, that is downloaded and saved locally.
             *
             */

            /**
             * Here we have mentioned it to show inline
             */
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            //Here we have mentioned it to show as attachment
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }


    }


}
