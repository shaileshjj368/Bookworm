package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vita.Models.MyShelf;

@Repository
public interface MyShelfRepository extends JpaRepository<MyShelf, Integer> {

    @Query(value = "select * from my_shelf where shelf_id = (select shelf_id from customer_master where customer_id = :custId)" , nativeQuery = true)
    public MyShelf getCustomerById(@Param("custId") int custId);
    
    @Query(value = "select * from my_shelf where shelf_id = (select shelf_id from customer_master where customer_id = :custId)" , nativeQuery = true)
    MyShelf findMyShelf(@Param("custId")int id);
}
