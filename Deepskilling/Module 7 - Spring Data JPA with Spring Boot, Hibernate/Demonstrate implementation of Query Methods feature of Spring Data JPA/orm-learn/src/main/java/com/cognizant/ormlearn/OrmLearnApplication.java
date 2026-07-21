package com.cognizant.ormlearn;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.StockService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;
    private static StockService stockService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        stockService = context.getBean(StockService.class);

        testContainingText();
        testSorting();
        testStartingWithText();
        testTopResults();
        testBetweenDates();
        testGreaterThan();
    }

    // 1. Search by containing text
    private static void testContainingText() {
        LOGGER.info("=== 1. Query Method: Containing Text ===");
        List<Country> countries = countryService.findCountriesByPartialName("ou");
        LOGGER.debug("Countries containing 'ou': {}", countries);
    }

    // 2. Sorting
    private static void testSorting() {
        LOGGER.info("=== 2. Query Method: Sorting ===");
        List<Country> countries = countryService.getBySearchOrdered("ou");
        LOGGER.debug("Countries containing 'ou' sorted by name ASC: {}", countries);
    }

    // 3. Filter with starting text
    private static void testStartingWithText() {
        LOGGER.info("=== 3. Query Method: Starting With ===");
        List<Country> countries = countryService.findCountriesStartingWith("Z");
        LOGGER.debug("Countries starting with 'Z': {}", countries);
    }

    // 4. Top / Limit result
    private static void testTopResults() {
        LOGGER.info("=== 4. Query Method: Top 3 Results ===");
        List<Country> countries = countryService.getTop3Countries();
        LOGGER.debug("Top 3 Countries by name ASC: {}", countries);
    }

    // 5. Fetch between dates
    private static void testBetweenDates() {
        LOGGER.info("=== 5. Query Method: Between Dates ===");
        try {
            Date startDate = Date.valueOf("2024-01-01");
            Date endDate = Date.valueOf("2024-12-31");
            List<Stock> stocks = stockService.getStocksBetweenDates("FB", startDate, endDate);
            LOGGER.debug("Stocks for FB between {} and {}: {}", startDate, endDate, stocks);
        } catch (Exception e) {
            LOGGER.error("Error fetching stocks between dates: {}", e.getMessage());
        }
    }

    // 6. Greater than or lesser than
    private static void testGreaterThan() {
        LOGGER.info("=== 6. Query Method: Greater Than ===");
        try {
            List<Stock> stocks = stockService.getStocksGreaterThan("FB", new BigDecimal("120.00"));
            LOGGER.debug("Stocks for FB close price > 120.00: {}", stocks);
        } catch (Exception e) {
            LOGGER.error("Error fetching stocks greater than price: {}", e.getMessage());
        }
    }
}
