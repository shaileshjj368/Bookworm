package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vita.Models.CustomerMaster;

@Repository
public interface CustomerMasterRepo extends JpaRepository<CustomerMaster, Integer> {
    @Query(value = "SELECT * FROM customer_master c WHERE c.email = :email and c.password = :password" , nativeQuery = true)
    CustomerMaster findByEmailAndPassword(@Param("email") String email , @Param("password") String password);

    @Query(value = "SELECT * FROM customer_master c WHERE c.email = :email" , nativeQuery = true)
    CustomerMaster findByEmail(@Param("email") String email);
}
