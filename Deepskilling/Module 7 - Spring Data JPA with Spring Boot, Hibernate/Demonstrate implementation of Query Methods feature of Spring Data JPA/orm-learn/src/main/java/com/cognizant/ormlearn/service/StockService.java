package com.cognizant.ormlearn.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional(readOnly = true)
    public List<Stock> getStocksBetweenDates(String code, Date startDate, Date endDate) {
        return stockRepository.findByCodeAndDateBetween(code, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Stock> getStocksGreaterThan(String code, BigDecimal price) {
        return stockRepository.findByCodeAndCloseGreaterThan(code, price);
    }

    @Transactional(readOnly = true)
    public List<Stock> getTop3VolumeStocks() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }
}
