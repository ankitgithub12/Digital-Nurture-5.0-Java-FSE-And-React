package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found");
        }
        return result.get();
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Country country = findCountryByCode(code);
        country.setName(name);
        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // 1. Search by containing text
    @Transactional(readOnly = true)
    public List<Country> findCountriesByPartialName(String name) {
        return countryRepository.findByNameContainingIgnoreCase(name);
    }

    // 2. Sorting
    @Transactional(readOnly = true)
    public List<Country> getBySearchOrdered(String name) {
        return countryRepository.findByNameContainingOrderByNameAsc(name);
    }

    // 3. Filter with starting text
    @Transactional(readOnly = true)
    public List<Country> findCountriesStartingWith(String prefix) {
        return countryRepository.findByNameStartingWithIgnoreCase(prefix);
    }

    // 4. Top / Limit query results
    @Transactional(readOnly = true)
    public List<Country> getTop3Countries() {
        return countryRepository.findTop3ByOrderByNameAsc();
    }
}
