package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vita.Models.InvoiceDetails;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails,Integer> {
    
}
