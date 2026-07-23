package com.cognizant.springlearn.service;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.cognizant.springlearn.Country;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @SuppressWarnings("unchecked")
    public Country getCountry(String code) {
        LOGGER.info("START: getCountry for code {}", code);
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            ArrayList<Country> countries = context.getBean("countryList", ArrayList.class);
            
            Country match = countries.stream()
                    .filter(country -> country.getCode().equalsIgnoreCase(code))
                    .findFirst()
                    .orElse(null);
            
            LOGGER.info("END: getCountry for code {}, found country: {}", code, match);
            return match;
        }
    }
}
