package com.vita.bookworm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vita.Models.ProductMaster;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Integer> {
    @Query(value = "SELECT * FROM product_master " +
    "WHERE (:typeid IS NULL OR :typeid = type_id) " +
    "AND (:pname IS NULL OR LOWER(product_english_name) LIKE LOWER(CONCAT('%', :pname, '%'))) " +
    "AND (:langid IS NULL OR :langid = language_id) " +
    "AND (:genreid IS NULL OR :genreid = genre_id) " +
    "AND (:aname IS NULL OR author_id IN (SELECT author_id FROM author_master WHERE LOWER(author_name) LIKE LOWER(CONCAT('%', :aname, '%'))))",
    nativeQuery = true)
    List<ProductMaster> getProduct(
            @Param("typeid") Integer typeid,
            @Param("pname") String pname,
            @Param("langid") Integer langid,
            @Param("genreid") Integer genreid,
            @Param("aname") String aname
            );

    @Query(value = "SELECT * FROM product_master", nativeQuery = true)
    List<ProductMaster> getAllProducts();

    @Query(value = "SELECT * FROM product_master WHERE LOWER(product_english_name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<ProductMaster> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM product_master WHERE type_id = :type", nativeQuery = true)
    List<ProductMaster> findByType(@Param("type") int type);

    @Query(value = "SELECT * FROM product_master WHERE genre_id = :genre", nativeQuery = true)
    List<ProductMaster> findByGenre(@Param("genre") int genre);

    @Query(value = "SELECT * FROM product_master WHERE language_id = :language", nativeQuery = true)
    List<ProductMaster> findByLanguage(@Param("language") int language);

    @Query(value = "SELECT * FROM product_master WHERE author_id = :author", nativeQuery = true)
    List<ProductMaster> findByAuthorId(@Param("author") int author);
}
