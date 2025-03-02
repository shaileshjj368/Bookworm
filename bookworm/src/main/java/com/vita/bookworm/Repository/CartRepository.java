package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vita.Models.CartMaster;


public interface CartRepository extends JpaRepository<CartMaster, Integer> {
    @Query(value = "select distinct * from cart_master where customer_id = :id and is_active = 1", nativeQuery = true)
    public CartMaster getActiveCart(@Param("id") int id);

    @Modifying
    @Query(value = "insert into cart_details (is_rented, rent_no_of_days, product_id, cart_id) values (:isrent, :days, :prod, :cartid)", nativeQuery = true)
    public void addCartDetail(@Param("isrent") boolean isrent, @Param("days") int days, @Param("prod") int prod, @Param("cartid") int cartid);

    @Query(value = "select count(1) from my_shelf where shelf_id = (select shelf_id from customer_master where customer_id = :custId) and product_id = :prodId", nativeQuery = true)
    public Integer userHasProduct(@Param("custId") int custId, @Param("prodId") int prodId);

    @Modifying
    @Query(value = "delete from cart_details where cart_id = :cartId and product_id = :prodId", nativeQuery = true)
    public void removeProduct(@Param("cartId") int cartId, @Param("prodId") int prodId);
}
