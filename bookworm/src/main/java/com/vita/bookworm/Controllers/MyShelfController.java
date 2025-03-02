package com.vita.bookworm.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vita.Models.ProductMaster;
import com.vita.bookworm.Service.MyShelfServices;

@RestController
@RequestMapping("api/myshelf")
public class MyShelfController {
    @Autowired
    public MyShelfServices myShelfServices;

    @GetMapping("/{id}")
    public List<ProductMaster> getShelfProducts(@PathVariable("id")int myShelfId) {
        return null;
    }
}
