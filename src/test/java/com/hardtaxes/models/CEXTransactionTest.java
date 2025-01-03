
package com.hardtaxes.models;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Test;
import com.hardtaxes.models.CEXTransaction;
import com.hardtaxes.models.TransactionType;


public class CEXTransactionTest {
    @Test
    public void testValidCEXTransaction() {
        LocalDateTime now = LocalDateTime.now();
        CEXTransaction tx = new CEXTransaction(
            now,                           // openTime
            now.plusHours(1),             // closeTime
            "BTC/USDT",                   // symbol
            new BigDecimal("30000"),      // openPrice
            new BigDecimal("31000"),      // closePrice
            new BigDecimal("1"),          // totalSize
            new BigDecimal("1000"),       // realizedPNL
            new BigDecimal("10"),         // exchangeFee
            new BigDecimal("5"),          // fundingFee
            new BigDecimal("3.33")        // roi
        );
        
        tx.setCadRate(new BigDecimal("1.35"));
        
        assertEquals("BTC/USDT", tx.getSymbol());
        assertEquals(0, new BigDecimal("1000").compareTo(tx.getRealizedPNL()));
        assertEquals(0, new BigDecimal("10").compareTo(tx.getExchangeFee()));
        assertEquals(0, new BigDecimal("1327.25").compareTo(tx.getTotalCADValue()));
    }
}