package com.hardtaxes.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

enum TransactionType {
    BUY, SELL
}

public class Transaction {
   private String id;
   private LocalDateTime timestamp;
   private BigDecimal amount;
   private BigDecimal price;
   private TransactionType type;

    // Transaction constructor for creating new transactions
    public Transaction(String id, LocalDateTime timestamp, BigDecimal amount, BigDecimal price, TransactionType type) {
        validateTransaction(amount, price); 
        this.id = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.price = price;
        this.type = type;
    }

   private void validateTransaction(BigDecimal amount, BigDecimal price) {
       if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("Amount must be positive");
       }
       if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("Price must be positive");
       }
   }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }  

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public BigDecimal getTotalValue() {
        return amount.multiply(price);
    } 
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
}


// Transaction.java - Base Class

// Core attributes: id, timestamp, amount, price, type (buy/sell)
// Tracks single crypto transaction details
// Contains getters/setters and basic transaction validation
// Uses BigDecimal for accurate currency calculations