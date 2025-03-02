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
public class ProductBeneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_beneficiary_id")
    private Integer beneficiaryId;
    
    @ManyToOne()
    @JoinColumn(name = "beneficiary_id",  nullable = true)
    private BeneficiaryMaster beneficiaryMaster;

    @ManyToOne()
    @JoinColumn(name = "product_id",  nullable = true)
    private ProductMaster productMaster;

    @Column(name = "percentage")
    private Double percentage;
}
