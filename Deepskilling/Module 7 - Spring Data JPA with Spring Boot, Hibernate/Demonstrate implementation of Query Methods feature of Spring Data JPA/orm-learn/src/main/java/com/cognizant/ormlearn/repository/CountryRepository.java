package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // 1. Search by containing text
    List<Country> findByNameContaining(String name);

    // Case-insensitive partial search
    List<Country> findByNameContainingIgnoreCase(String name);

    // 2. Sorting
    List<Country> findByNameContainingOrderByNameAsc(String name);

    List<Country> findByNameContainingOrderByNameDesc(String name);

    // 3. Filter with starting text
    List<Country> findByNameStartingWith(String prefix);

    List<Country> findByNameStartingWithIgnoreCase(String prefix);

    // 4. Top / Limit query methods
    List<Country> findTop3ByOrderByNameAsc();
}
