package com.vita.bookworm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vita.Models.CustomerMaster;
import com.vita.bookworm.DTO.LoginHelper;
import com.vita.bookworm.Service.CustomerMasterServices;

@RestController
// @CrossOrigin(origins = "http://localhost:5174")
public class LoginController {
    @Autowired
    CustomerMasterServices customerMasterServices;

    @PostMapping("/login")
    public CustomerMaster login(@RequestBody LoginHelper c) {
        return customerMasterServices.checkUser(c);
    }

    @PostMapping("/register")
    public CustomerMaster register(@RequestBody CustomerMaster c) {
        if (customerMasterServices.checkUserExists(c)) {
            return null;
        } else {
            CustomerMaster t = customerMasterServices.registerUser(c);
            return t;
        }
    }
}
