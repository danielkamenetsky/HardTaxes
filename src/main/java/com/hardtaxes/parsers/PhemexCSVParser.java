package com.hardtaxes.parsers;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.hardtaxes.models.CEXTransaction;
import com.opencsv.CSVReader;

public class PhemexCSVParser {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
    private CEXTransaction createTransaction(String[] nextLine) {
        // Parse the CSV line and create a new CEXTransaction object
        return new CEXTransaction(
            LocalDateTime.parse(nextLine[0], DATE_FORMAT),
            LocalDateTime.parse(nextLine[1], DATE_FORMAT),
            nextLine[2],
            new BigDecimal(nextLine[3]),
            new BigDecimal(nextLine[4]),
            new BigDecimal(nextLine[5]),
            new BigDecimal(nextLine[6]),
            new BigDecimal(nextLine[7]),
            new BigDecimal(nextLine[8]),
            new BigDecimal(nextLine[9])
        );
    }


}
