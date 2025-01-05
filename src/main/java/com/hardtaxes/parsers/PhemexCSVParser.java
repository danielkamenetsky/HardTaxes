package com.hardtaxes.parsers;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.hardtaxes.models.CEXTransaction;
import com.opencsv.CSVReader;

public class PhemexCSVParser {

    // Main method that reads CSV and returns list of trades
    public List<CEXTransaction> parseCSV(String fileName) throws Exception {
        // List to store transactions
        List<CEXTransaction> transactions = new ArrayList<>();
        // try to create new CSVReader object with FileReader object with fileName as argument
        FileReader fileReader = new FileReader(fileName);
        try (CSVReader reader = new CSVReader(fileReader)) {
            reader.readNext();

            String[] line;
            while ((line = reader.readNext()) != null)  {
                transactions.add(createTransaction (line));
            }
        }
        return transactions;
    
    }
    private static final DateTimeFormatter DATE_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private CEXTransaction createTransaction(String[] line) {
        // Only take the date part before the space
        LocalDateTime openDate = LocalDate.parse(line[0].split(" ")[0], DATE_FORMAT).atStartOfDay();
        LocalDateTime closeDate = LocalDate.parse(line[1].split(" ")[0], DATE_FORMAT).atStartOfDay();
        
        return new CEXTransaction(
            openDate,  // will be at 00:00
            closeDate, // will be at 00:00
            line[2].split(" ")[0],
            new BigDecimal(line[3]),
            new BigDecimal(line[4]),
            new BigDecimal(line[5].replace("E+03", "000")),
            new BigDecimal(line[6].split(" ")[0]),
            new BigDecimal(line[7].split(" ")[0]),
            new BigDecimal(line[8].split(" ")[0]),
            new BigDecimal(line[10].replace("%", ""))
        );
    }
}