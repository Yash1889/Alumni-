package com.alumni.repository;

import com.alumni.model.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    
    // Find alumni by batch year
    List<Alumni> findByBatch(Integer batch);
    
    // Find alumni by company
    List<Alumni> findByCompanyContainingIgnoreCase(String company);
    
    // Find alumni by name (case insensitive)
    List<Alumni> findByNameContainingIgnoreCase(String name);
    
    // Custom query to find alumni by year (same as batch)
    @Query("SELECT a FROM Alumni a WHERE a.batch = :year")
    List<Alumni> findByYear(@Param("year") Integer year);
    
    // Find alumni ordered by batch year
    List<Alumni> findAllByOrderByBatchDesc();
}