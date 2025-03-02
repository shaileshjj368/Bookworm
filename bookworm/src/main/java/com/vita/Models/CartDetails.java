package com.vita.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_details_id")
    private Integer cartDetailsId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = true)
    private CartMaster cartId;

    @ManyToOne    
    @JoinColumn(name = "product_id", nullable = true)
    private ProductMaster productId;

    @Column(nullable = false)
    private Boolean isRented;

    private Integer rentNoOfDays;

}
