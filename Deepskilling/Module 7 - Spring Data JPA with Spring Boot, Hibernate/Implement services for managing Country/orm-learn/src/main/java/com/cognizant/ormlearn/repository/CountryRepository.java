package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Custom query method to find list of countries matching a partial country name
    List<Country> findByNameContaining(String name);

    // Case-insensitive partial search
    List<Country> findByNameContainingIgnoreCase(String name);

    // Partial search ordered by country name ascending
    List<Country> findByNameContainingOrderByNameAsc(String name);
}
