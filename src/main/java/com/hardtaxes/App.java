package com.hardtaxes;

import java.math.BigDecimal;

import com.hardtaxes.models.CEXTransaction;
import com.hardtaxes.parsers.PhemexCSVParser;
import java.util.List;
import java.util.ArrayList;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            PhemexCSVParser parser = new PhemexCSVParser();

            // Read all transactions from CSV
            List<CEXTransaction> transactions = parser.parseCSV("data/phemex_trades.csv");

            System.out.println("Transaction Summary");
            System.out.println("-------------------");


            for (CEXTransaction tx : transactions) {
                BigDecimal totalFees = tx.getExchangeFee().add(tx.getFundingFee());
                System.out.println("Trading Pair: " + tx.getSymbol());
                System.out.println("Realized PNL: $" + tx.getRealizedPNL());
                System.out.println("Total Fees: $" + totalFees);
                System.out.println("-------------------");

            }


        } catch (Exception e) {
            System.out.println("Error processing file:" + e.getMessage());
            e.printStackTrace();
            
        }
    }
}
