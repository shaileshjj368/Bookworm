package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vita.Models.GenreMaster;

@Repository
public interface GenreMasterRepository extends JpaRepository<GenreMaster, Integer> {
    
}
