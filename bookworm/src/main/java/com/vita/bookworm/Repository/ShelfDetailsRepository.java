package com.vita.bookworm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vita.Models.ShelfDetails;

public interface ShelfDetailsRepository extends JpaRepository<ShelfDetails, Integer> {
    @Query(value = "select * from shelf_details where shelf_dtl_id = :shelfId", nativeQuery = true)
    public ShelfDetails findByShelfDtlId(@Param("shelfId") int shelfId);


    @Query(value = "select * from shelf_details where customer_id = :userId", nativeQuery = true)
    public ShelfDetails findByUserId(@Param("userId") int userId);

    @Query(value = "select * from shelf_details where product_id = :productId", nativeQuery = true)
    public ShelfDetails findByProductId(@Param("productId")int productId);

    @Query(value = "select * from shelf_details where shelf_id = :shelfId", nativeQuery = true)
    public List<ShelfDetails> findByShelfId(@Param("shelfId") int shelfId);

    @Query(value = "delete from shelf_details where shelf_dtl_id = :shelfId", nativeQuery = true)
    public void deleteByShelfId(@Param("shelfId") int shelfId);   
}

