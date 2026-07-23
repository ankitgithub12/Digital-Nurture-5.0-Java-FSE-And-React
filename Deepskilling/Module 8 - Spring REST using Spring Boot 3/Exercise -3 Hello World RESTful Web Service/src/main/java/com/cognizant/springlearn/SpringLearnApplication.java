package com.cognizant.springlearn;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Inside main method of SpringLearnApplication");
		SpringApplication.run(SpringLearnApplication.class, args);
		LOGGER.info("SpringLearnApplication started successfully");
		displayCountries();
	}

	public static void displayCountries() {
		LOGGER.info("START: displayCountries");
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
			@SuppressWarnings("unchecked")
			ArrayList<Country> countries = context.getBean("countryList", ArrayList.class);
			LOGGER.debug("Country list: {}", countries);
		}
		LOGGER.info("END: displayCountries");
	}

}
