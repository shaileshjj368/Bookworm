package com.vita.bookworm.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.CartDetails;
import com.vita.Models.CartMaster;
import com.vita.Models.CustomerMaster;
import com.vita.Models.ProductMaster;
import com.vita.bookworm.DTO.CartHelper;
import com.vita.bookworm.Repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServices {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerMasterServices customerMasterServices;

    @Autowired
    private ProductMasterService productMasterService;

    @Autowired
    private CartDetailsService cartDetailsService;

    public CartMaster addCart(CartMaster cm) {
        if (cm == null || cm.getCustomerId() == null) {
            throw new IllegalArgumentException("CartMaster or CustomerId cannot be null");
        }
        return cartRepository.save(cm);
    }

    public List<CartDetails> getDetails(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid cart ID");
        }
        Optional<CartMaster> cm = cartRepository.findById(id);
        if (cm.isEmpty())
            return null;
        CartMaster c = cm.get();
        return cartDetailsService.findByCartId(c.getCartId());
    }

    public CartMaster getCart(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid cart ID");
        }
        return cartRepository.findById(id).orElse(null);
    }

    public Integer getActiveCart(int custId) {
        if (custId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
        CartMaster cd = cartRepository.getActiveCart(custId);
        return cd == null ? null : cd.getCartId();
    }

    @Transactional
    public void addProduct(CartHelper ch) {
        if (ch == null || ch.getCustId() == 0 || ch.getProdId() == 0) {
            throw new IllegalArgumentException("CartHelper, CustomerId, or ProductId cannot be null");
        }
        // find customer
        CustomerMaster c = customerMasterServices.getCustomerMasterById(ch.getCustId());
        if (c == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        // find cart
        Integer i = getActiveCart(c.getCustomerId());
        CartMaster cm = null;
        if (i == null) {
            // create cart
            cm = new CartMaster();
            cm.setCustomerId(c);
            cm.setIsActive(true);
            cm.setCost(0.0);
            cm = cartRepository.save(cm);
        } else {
            cm = cartRepository.findById(i).orElse(null);
            if (cm == null) {
                throw new IllegalArgumentException("Cart not found");
            }
        }
        ProductMaster p = productMasterService.getProductById(ch.getProdId());
        if (p == null) {
            throw new IllegalArgumentException("Product not found");
        }
        // Check if the product is already in the cart
        List<CartDetails> cartDetails = cartDetailsService.findByCartId(cm.getCartId());
        for (CartDetails detail : cartDetails) {
            if (detail.getProductId().equals(p)) {
                throw new IllegalArgumentException("Product already in the cart");
            }
        }
        if (ch.isRented()) {
            cm.setCost(cm.getCost() + ch.getNoOfDays() * p.getRentPerDay());
        } else {
            Double price = p.getProductBasePrice();
            LocalDate it = p.getProductOffPriceExpiryDate();
            if (it != null && it.isBefore(LocalDate.now())) {
                price = p.getProductOfferPrice();
            }
            cm.setCost(price);
        }
        CartDetails tcd = new CartDetails();
        tcd.setCartId(cm);
        tcd.setIsRented(ch.isRented());
        tcd.setProductId(p);
        if (ch.isRented())
            tcd.setRentNoOfDays(ch.getNoOfDays());
        cartDetailsService.addCartDetail(tcd);
    }

    public void afterCheckout(int custId) {
        if (custId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }

    @Transactional
    public void remove(int cartId, int prodId) {
        cartRepository.removeProduct(cartId, prodId);
    }
}
