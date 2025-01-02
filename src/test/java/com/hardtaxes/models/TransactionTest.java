package com.hardtaxes.models;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionTest {
    @Test
    public void testValidTransaction() {
        LocalDateTime now = LocalDateTime.now();
        Transaction transaction = new Transaction(
            "1",
            now,
            new BigDecimal("0.5"),
            new BigDecimal("30000"),
            TransactionType.BUY
        );
        
        assertEquals("1", transaction.getId());
        assertEquals(now, transaction.getTimeStamp());
        assertEquals(new BigDecimal("0.5"), transaction.getAmount());
        assertEquals(new BigDecimal("30000"), transaction.getPrice());
        assertEquals(TransactionType.BUY, transaction.getType());
        assertEquals(0, new BigDecimal("15000").compareTo(transaction.getTotalValue()));    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmount() {
        new Transaction("1", LocalDateTime.now(), 
            new BigDecimal("-1"), new BigDecimal("30000"), TransactionType.BUY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroPrice() {
        new Transaction("1", LocalDateTime.now(), 
            new BigDecimal("1"), new BigDecimal("0"), TransactionType.BUY);
    }
}