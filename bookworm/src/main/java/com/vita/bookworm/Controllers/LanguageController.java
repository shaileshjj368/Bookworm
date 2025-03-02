package com.vita.bookworm.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vita.Models.LanguageMaster;
import com.vita.bookworm.Service.LanguageMasterService;



@RestController
@RequestMapping("api/languages")
// @CrossOrigin(origins = "http://localhost:5174/")
public class LanguageController {
    @Autowired
    private LanguageMasterService languageMasterService;
    
    @GetMapping("/")
    public List<LanguageMaster> getLanguages() {
        return languageMasterService.getAllLanguages();
    }
}
