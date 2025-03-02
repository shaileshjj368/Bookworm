package com.vita.bookworm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vita.Models.LanguageMaster;

@Repository
public interface LanguageMasterRepository extends JpaRepository<LanguageMaster, Integer> {

}
