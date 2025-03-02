package com.vita.bookworm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.CartDetails;
import com.vita.bookworm.Repository.CartDetailsRepository;

@Service
public class CartDetailsService {
    @Autowired
    CartDetailsRepository cartDetailsRepository;

    public List<CartDetails> findByCartId(int id) {
        return cartDetailsRepository.findByCartId(id);
    }

    public void addCartDetail(CartDetails cd ) {
        cartDetailsRepository.save(cd);
    }
}
