package com.vita.bookworm.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vita.Models.ProductMaster;
import com.vita.bookworm.DTO.FilterHelper;
import com.vita.bookworm.Service.ProductMasterService;

@RestController
@RequestMapping("/api/products")
// @CrossOrigin(origins = "http://localhost:5174/")
public class ProductSearchController {
    @Autowired
    ProductMasterService productMasterService;

    public ProductSearchController(ProductMasterService productMasterService) {
        this.productMasterService = productMasterService;
    }

    @PostMapping("/search")
    public List<ProductMaster> search(@RequestBody FilterHelper filterHelper) {
        List<ProductMaster> temp = productMasterService.getProduct(filterHelper);
        System.out.println(filterHelper + " " + temp.size() );
        return temp;
    }

    @GetMapping
    public List<ProductMaster> getAllProducts() {
        return productMasterService.getAllProducts();
    }
}
