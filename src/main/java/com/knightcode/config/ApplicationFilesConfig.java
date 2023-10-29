package com.knightcode.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("knightcode-directory-config")
public class ApplicationFilesConfig {

    private String UserProfileDirectory , replaceuseridvariablename;

}
