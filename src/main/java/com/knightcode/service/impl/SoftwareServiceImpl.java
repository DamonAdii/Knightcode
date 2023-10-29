package com.knightcode.service.impl;

import com.knightcode.model.Software;
import com.knightcode.repository.SoftwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftwareServiceImpl{

    private final SoftwareRepository softwareRepository;


    public List<Software> searchSoftwares(String softwareName) {
        return softwareRepository.findByNameContaining(softwareName);
    }

}
