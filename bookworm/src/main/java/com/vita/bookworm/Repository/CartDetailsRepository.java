package com.vita.bookworm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vita.Models.CartDetails;

public interface CartDetailsRepository extends JpaRepository<CartDetails , Integer>{
    @Query(value = "select * from cart_details where cart_id = :cid" , nativeQuery = true)
    public List<CartDetails> findByCartId(@Param("cid") int cid);
}
