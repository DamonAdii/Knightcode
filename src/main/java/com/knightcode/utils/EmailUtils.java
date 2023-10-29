package com.knightcode.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token) {
        return "Hello " + name + ",\n\nYour new account has been created. Please click the link below to verify your account. \n\n" +
                getVerificationUrl(host, token) + "\n\nThe support Team";
    }

    public static String getVerificationUrl(String host, String token) {

        // Construct the confirmation link with the token.
        //String confirmationLink = "http://" + request.getServerName() + ":" + request.getServerPort() +
        //       request.getContextPath() + "/confirm?token=" + token;

        return host + "/confirm?token=" + token;

    }

}
