package com.vita.bookworm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vita.bookworm.DTO.ProductDiscout;
import com.vita.bookworm.Service.ProductMasterService;


@RestController
public class ProductMasterController {
    @Autowired
    ProductMasterService productMasterService;
    
    @PostMapping("admin/api/productDiscount")
    public void addDiscount(@RequestBody ProductDiscout p){
        productMasterService.addDiscount(p);
    }
}
