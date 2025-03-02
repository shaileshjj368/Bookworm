package com.vita.Models;

import java.time.LocalDate;
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
public class RentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Integer rentId;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = true)
    private ProductMaster productId; 

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = true)
    private CustomerMaster customerId; 

    @Column(nullable = true)
    private LocalDate rentStartDate;

    @Column(nullable = true)
    private LocalDate rentEndDate;

    @Column(nullable = true)
    private String rentStatus; 
}