package com.vita.bookworm.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vita.Models.CartDetails;
import com.vita.bookworm.DTO.CartHelper;
import com.vita.bookworm.Service.CartServices;

@RestController
@RequestMapping("/api/mycart")
// @CrossOrigin(origins = "http://localhost:5174/")
public class CartController {
    @Autowired
    CartServices cartServices;

    @GetMapping("/{id}")
    public List<CartDetails> getDetails(@PathVariable("id") int id){
        return cartServices.getDetails(id);
    }

    @DeleteMapping("/{cartId}/Product/{prodId}")
    public void removeCartDetail(@PathVariable("custId") int custId , @PathVariable("prodId") int prodId ){
        Integer i = cartServices.getActiveCart(custId);
        if (i == null) return;
        cartServices.remove(i, prodId);
    }

    @PostMapping("/Cart")
    public void addProducts(@RequestBody CartHelper c) {
        if (c == null){
            return;
        }else{
            System.out.println(c);
            cartServices.addProduct(c);
        }
    }
}
