package com.vita.bookworm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vita.Models.AuthorMaster;

public interface AuthorRepository extends JpaRepository<AuthorMaster, Integer> {
    
    @Query(value = "select * from author_master where author_name LIKE concat('%', :name, '%')", nativeQuery = true)
    public List<AuthorMaster> findByName(@Param("name") String name);

    
}
