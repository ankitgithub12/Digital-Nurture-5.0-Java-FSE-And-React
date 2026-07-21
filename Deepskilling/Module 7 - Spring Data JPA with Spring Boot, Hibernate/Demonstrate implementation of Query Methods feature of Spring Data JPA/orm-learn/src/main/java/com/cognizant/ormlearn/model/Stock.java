package com.cognizant.ormlearn.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "st_id")
    private int id;

    @Column(name = "st_code")
    private String code;

    @Column(name = "st_date")
    private Date date;

    @Column(name = "st_close")
    private BigDecimal close;

    @Column(name = "st_volume")
    private BigDecimal volume;

    public Stock() {
    }

    public Stock(int id, String code, Date date, BigDecimal close, BigDecimal volume) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.close = close;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Stock [id=" + id + ", code=" + code + ", date=" + date + ", close=" + close + ", volume=" + volume + "]";
    }
}
