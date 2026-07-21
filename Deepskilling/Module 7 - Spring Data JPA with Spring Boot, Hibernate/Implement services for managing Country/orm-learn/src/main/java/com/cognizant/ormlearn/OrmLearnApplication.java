package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);

        LOGGER.info("=== Running Service Tests ===");
        testGetAllCountries();
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testFindCountriesByPartialName();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Total countries fetched: {}", countries.size());
        LOGGER.info("End testGetAllCountries");
    }

    private static void testFindCountryByCode() {
        LOGGER.info("Start testFindCountryByCode");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Found Country: {}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country Not Found Exception: {}", e.getMessage());
        }

        try {
            Country invalidCountry = countryService.findCountryByCode("XX");
            LOGGER.debug("Found Country: {}", invalidCountry);
        } catch (CountryNotFoundException e) {
            LOGGER.info("Successfully caught expected exception for code 'XX': {}", e.getMessage());
        }
        LOGGER.info("End testFindCountryByCode");
    }

    private static void testAddCountry() {
        LOGGER.info("Start testAddCountry");
        Country newCountry = new Country("ZZ", "Test Country");
        countryService.addCountry(newCountry);

        try {
            Country fetched = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Successfully added and verified country: {}", fetched);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Failed to find added country: {}", e.getMessage());
        }
        LOGGER.info("End testAddCountry");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start testUpdateCountry");
        try {
            countryService.updateCountry("ZZ", "Updated Test Country");
            Country updated = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Successfully updated country: {}", updated);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Failed to update country: {}", e.getMessage());
        }
        LOGGER.info("End testUpdateCountry");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start testDeleteCountry");
        countryService.deleteCountry("ZZ");

        try {
            countryService.findCountryByCode("ZZ");
            LOGGER.error("Country 'ZZ' still exists after delete!");
        } catch (CountryNotFoundException e) {
            LOGGER.info("Successfully deleted country 'ZZ' (caught expected exception: {})", e.getMessage());
        }
        LOGGER.info("End testDeleteCountry");
    }

    private static void testFindCountriesByPartialName() {
        LOGGER.info("Start testFindCountriesByPartialName");
        List<Country> countriesMatch = countryService.findCountriesByPartialName("ou");
        LOGGER.debug("Countries containing 'ou': {}", countriesMatch);

        List<Country> countriesOrdered = countryService.getBySearch("ou");
        LOGGER.debug("Countries containing 'ou' ordered by name: {}", countriesOrdered);
        LOGGER.info("End testFindCountriesByPartialName");
    }
}
