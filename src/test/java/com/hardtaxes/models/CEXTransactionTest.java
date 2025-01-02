

public class CEXTransactionTest {
    @Test
    public void testValidCEXTransaction() {
        LocalDateTime now = LocalDateTime.now();
        CEXTransaction tx = new CEXTransaction(
            "1", 
            now,
            new BigDecimal("0.5"),  // amount
            new BigDecimal("30000"), // price
            TransactionType.BUY,
            "BTC/USDT",
            "Binance",
            new BigDecimal("0.001")  // fee
        );
        
        assertEquals("BTC/USDT", tx.getTradingPair());
        assertEquals("Binance", tx.getExchangeName());
        assertEquals(0, new BigDecimal("0.001").compareTo(tx.getExchangeFee()));
        assertEquals(0, new BigDecimal("14999.999").compareTo(tx.getTotalValue())); // Price * Amount - Fee
    }
}