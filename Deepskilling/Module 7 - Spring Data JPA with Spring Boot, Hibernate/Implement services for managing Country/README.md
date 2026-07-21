# Implement Services for Managing Country

## Overview
This module demonstrates the implementation of country management services using **Spring Data JPA** and **Hibernate**.

---

## Features Implemented
1. **Find a country based on country code**: `findCountryByCode(String countryCode)`
2. **Add new country**: `addCountry(Country country)`
3. **Update country**: `updateCountry(String code, String name)`
4. **Delete country**: `deleteCountry(String countryCode)`
5. **Find list of countries matching a partial country name**: `findCountriesByPartialName(String name)` / `getBySearch(String name)`

---

## Configuration Explanation: Hibernate `ddl-auto`

The `spring.jpa.hibernate.ddl-auto` configuration property controls how Hibernate manages database schema creation and validation:

| Setting | Behavior |
| :--- | :--- |
| **`create`** | Drops existing tables (data and structure) and creates new tables every time the application starts. |
| **`validate`** | Validates existing database schema against Java entity classes. Throws an exception if a matching table or column is missing. |
| **`update`** | Automatically updates the schema: creates non-existent tables/columns without dropping existing data. |
| **`create-drop`** | Creates tables on application startup and drops them when the `SessionFactory` is closed (ideal for testing). |

```properties
# Hibernate ddl auto configured as requested
spring.jpa.hibernate.ddl-auto=validate
```

---

## Database Population Script (`country.sql`)
The database is pre-populated with country data using `src/main/resources/country.sql`.

Example Insert Script:
```sql
DELETE FROM country;
INSERT INTO country (co_code, co_name) VALUES ('AF', 'Afghanistan');
INSERT INTO country (co_code, co_name) VALUES ('IN', 'India');
INSERT INTO country (co_code, co_name) VALUES ('US', 'United States');
-- (Includes 240+ countries)
```

---

## Code Architecture

### Entity: `Country.java`
```java
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "co_code")
    private String code;

    @Column(name = "co_name")
    private String name;
    // Getters, Setters, Constructors
}
```

### Repository: `CountryRepository.java`
```java
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByNameContainingIgnoreCase(String name);
    List<Country> findByNameContainingOrderByNameAsc(String name);
}
```

### Service: `CountryService.java`
```java
@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        return countryRepository.findById(countryCode)
            .orElseThrow(() -> new CountryNotFoundException("Country not found with code: " + countryCode));
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

    @Transactional(readOnly = true)
    public List<Country> findCountriesByPartialName(String name) {
        return countryRepository.findByNameContainingIgnoreCase(name);
    }
}
```

---

## Running the Application & Tests

To execute the tests with Maven:
```bash
cd orm-learn
mvn clean test
```
