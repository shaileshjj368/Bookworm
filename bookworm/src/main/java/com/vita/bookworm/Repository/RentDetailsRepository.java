package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vita.Models.RentDetails;

public interface RentDetailsRepository extends JpaRepository<RentDetails , Integer>{
    
}
