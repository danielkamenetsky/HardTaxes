package com.hardtaxes.models;

import java.math.BigDecimal;
import java.time.LocalDate;

// This class is used to record transactions from the CEX exchanges
public class CEXTransaction {
    private final LocalDateTime openTime;
    private final LocalDateTime closeTime;
    private final String symbol;
    private final BigDecimal openPrice;
    private final BigDecimal closePrice;
    private final BigDecimal totalSize;
    private final BigDecimal realizedPNL;
    private final BigDecimal exchangeFee;
    private final BigDecimal fundingFee;
    private final BigDecimal roi;
    private BigDecimal cadRate;

    public BigDecimal getCADValue() {
        return (realizedPNL.subtract(exchangeFee).subtract(fundingFee))
               .multiply(cadRate);
    }

    public CEXTransaction( LocalDateTime openTime, LocalDateTime closeTime, String symbol, BigDecimal openPrice, BigDecimal closePrice, BigDecimal totalSize, BigDecimal realizedPNL, BigDecimal exchangeFee, BigDecimal fundingFee, BigDecimal roi) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.symbol = symbol;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.totalSize = totalSize;
        this.realizedPNL = realizedPNL;
        this.exchangeFee = exchangeFee;
        this.fundingFee = fundingFee;
        this.roi = roi;
    }

    public void setCadRate(BigDecimal cadRate) {
        this.cadRate = cadRate;
    }

    public BigDecimal getTotalCADValue() {
        return realizedPNL.subtract(exchangeFee).subtract(fundingFee).multiply(cadRate);
    }
    
    // Getters here

    

    
}