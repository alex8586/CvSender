package com.alex.repositories;

import com.alex.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select b from Company b where b.name = :name")
    Company findByName(@Param("name")String name);
}
