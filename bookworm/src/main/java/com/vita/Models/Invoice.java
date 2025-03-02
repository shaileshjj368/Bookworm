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

@Data
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private CustomerMaster customerId;
    
    @Column(columnDefinition = "Decimal default 0" )
    private Double amount;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = true,referencedColumnName = "cart_id")
    private CartMaster cartId;
}
