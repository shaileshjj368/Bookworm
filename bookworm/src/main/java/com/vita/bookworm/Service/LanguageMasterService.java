package com.vita.bookworm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.LanguageMaster;
import com.vita.bookworm.Repository.LanguageMasterRepository;

@Service
public class LanguageMasterService {
    @Autowired
    LanguageMasterRepository languageMasterRepository;

    public LanguageMaster getLanguageById(int id) {
        return languageMasterRepository.findById(id).orElse(null);
    }

    public List<LanguageMaster> getAllLanguages(){
        return languageMasterRepository.findAll();
    }
}
