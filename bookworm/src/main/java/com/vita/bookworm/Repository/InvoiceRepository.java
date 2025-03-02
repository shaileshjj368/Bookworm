package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vita.Models.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice , Integer> {
    
}
