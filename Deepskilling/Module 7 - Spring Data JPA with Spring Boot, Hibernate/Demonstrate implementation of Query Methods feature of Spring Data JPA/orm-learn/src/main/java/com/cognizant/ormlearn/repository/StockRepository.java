package com.cognizant.ormlearn.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // 1. Fetch between dates
    List<Stock> findByCodeAndDateBetween(String code, Date startDate, Date endDate);

    // 2. Greater than or lesser than
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal closePrice);

    List<Stock> findByCloseLessThan(BigDecimal closePrice);

    // 3. Top / First result limits with sorting
    List<Stock> findTop3ByOrderByVolumeDesc();

    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
