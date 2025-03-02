package com.vita.bookworm.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.vita.Models.GenreMaster;
import com.vita.bookworm.Service.GenreMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@RestController
@RequestMapping("api/genre")
// @CrossOrigin(origins = "http://localhost:5174/")
public class GenreController {
    @Autowired
    private GenreMasterService genreMasterService;


    @GetMapping("/")
    public List<GenreMaster> getGenres() {
        return genreMasterService.getAll();
    }
    
}
