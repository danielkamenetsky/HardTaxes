package com.hardtaxes.parsers;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.hardtaxes.models.CEXTransaction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.hardtaxes.models.CEXTransaction;
import org.junit.jupiter.api.Test;
import com.hardtaxes.parsers.PhemexCSVParser;
public class PhemexCSVParserTest {
   @Test
   public void testParseCSV() throws Exception {
       // Create temp CSV file with test data
       String testCsvFile = createTestCSV();
       
       // Initialize parser
       PhemexCSVParser parser = new PhemexCSVParser();
       
       // Parse CSV into list of transactions
       List<CEXTransaction> transactions = parser.parseCSV(testCsvFile);
       
       // Verify list exists and has one entry
       assertNotNull(transactions);
       assertEquals(1, transactions.size());
       
       // Get first transaction
       CEXTransaction tx = transactions.get(0);
       
       // Verify parsed values match test data
       assertEquals("BTC/USD", tx.getSymbol());
       assertEquals(0, new BigDecimal("100.50").compareTo(tx.getRealizedPNL()));
   }

   private String createTestCSV() throws IOException {
       // Create temp file
       File tempFile = File.createTempFile("test-trades", ".csv");
       
       // Write test data in Phemex format
       try (FileWriter writer = new FileWriter(tempFile)) {
           writer.write("Open Time (UTC),Close Time (UTC),Symbol,Open Price,Close Price,Total Size,Closed PNL,Exchange fee,Funding fee,Realised PNL,ROI %\n");
           writer.write("2024-01-01 10:00:00,2024-01-01 11:00:00,BTC/USD,40000,41000,1,100.50,0.50,1.00,99.00,2.5\n");
       }
       
       return tempFile.getAbsolutePath();
   }
}