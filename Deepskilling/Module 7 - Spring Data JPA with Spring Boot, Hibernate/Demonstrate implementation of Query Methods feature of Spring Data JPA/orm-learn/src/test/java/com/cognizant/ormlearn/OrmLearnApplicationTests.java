package com.cognizant.ormlearn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.sql.init.mode=always",
    "spring.jpa.defer-datasource-initialization=true",
    "spring.sql.init.data-locations=classpath:country.sql"
})
class OrmLearnApplicationTests {

    @Autowired
    private CountryService countryService;

    @Test
    void testGetAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        assertThat(countries).isNotEmpty();
    }

    @Test
    void testFindCountryByCodeSuccess() throws CountryNotFoundException {
        Country country = countryService.findCountryByCode("IN");
        assertThat(country).isNotNull();
        assertThat(country.getName()).isEqualTo("India");
    }

    @Test
    void testFindCountryByCodeNotFound() {
        assertThrows(CountryNotFoundException.class, () -> {
            countryService.findCountryByCode("XX");
        });
    }

    @Test
    void testAddAndUpdateAndDeleteCountry() throws CountryNotFoundException {
        Country country = new Country("TC", "Test Country");
        countryService.addCountry(country);

        Country fetched = countryService.findCountryByCode("TC");
        assertThat(fetched.getName()).isEqualTo("Test Country");

        countryService.updateCountry("TC", "Updated Test Country");
        Country updated = countryService.findCountryByCode("TC");
        assertThat(updated.getName()).isEqualTo("Updated Test Country");

        countryService.deleteCountry("TC");
        assertThrows(CountryNotFoundException.class, () -> {
            countryService.findCountryByCode("TC");
        });
    }

    @Test
    void testFindCountriesByPartialName() {
        List<Country> countries = countryService.findCountriesByPartialName("ind");
        assertThat(countries).isNotEmpty();
    }
}
