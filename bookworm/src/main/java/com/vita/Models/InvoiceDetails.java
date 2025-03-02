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
public class InvoiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inv_dtl_id")
    private Integer invDtlId;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = true)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private ProductMaster product;

    private Double basePrice;

    private Double salePrice;

    private String tranType;

    private Integer rentNoOfDays;
}