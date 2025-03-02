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
public class ShelfDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_dtl_id")
    private Integer shelfDetailId;

    @ManyToOne
    @JoinColumn(name = "shelf_id", nullable = true)
    private MyShelf shelf;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private ProductMaster product;

    private Double basePrice;
        
    private String tranType;

    @ManyToOne
    @JoinColumn(name = "rent_id", nullable = true)
    private RentDetails rentDetails;
}