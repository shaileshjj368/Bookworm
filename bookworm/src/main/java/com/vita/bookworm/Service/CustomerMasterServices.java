package com.vita.bookworm.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.CustomerMaster;
import com.vita.Models.MyShelf;

import com.vita.bookworm.DTO.LoginHelper;
import com.vita.bookworm.Repository.CustomerMasterRepo;
import com.vita.bookworm.Repository.MyShelfRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerMasterServices {
    @Autowired
    CustomerMasterRepo customerMasterRepo;
    
    @Autowired
    MyShelfRepository myShelfRepository;

    public CustomerMaster checkUser(LoginHelper c) {
        CustomerMaster c1 = customerMasterRepo.findByEmailAndPassword(c.getEmail() , c.getPassword());
        return c1;
    }

    @Transactional
    public CustomerMaster registerUser(CustomerMaster c) {
        int age = Period.between(c.getDob(), LocalDate.now()).getYears();
        if (age < 18){
            return null;
        }
        c.setAge(age);
        CustomerMaster c1 = customerMasterRepo.save(c);

        MyShelf m = new MyShelf();
        m.setNoOfBooks(0);
        m = myShelfRepository.save(m);
        c1.setShelfId(m);

        return c1;
    }

    public boolean checkUserExists(CustomerMaster c) {
        CustomerMaster c1 = customerMasterRepo.findByEmail(c.getEmail());
        if (c1 != null) {
            return true;
        }
        return false;
    }

    public CustomerMaster getCustomerMasterById(int id){
        Optional<CustomerMaster> c = customerMasterRepo.findById(id);
        return c.isPresent() ? c.get() : null;
    }

   
}
