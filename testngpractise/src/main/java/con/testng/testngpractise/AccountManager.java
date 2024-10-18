package con.testng.testngpractise;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 
 * The AccountManager class provides functionality to manage user accounts
 * in a PostgreSQL database, including inserting new accounts and exporting
 * account data to a CSV file.
 * @author Kunal Kale 
 * @version 1.0
 */

//Class contents...

/**
 * Inserts predefined accounts into the database.
 */

//** Default Constructor


public class AccountManager {
//*Default Constructor 
	
    private static final Logger logger = LoggerFactory.getLogger(AccountManager.class);

    private static final String DB_URL = "jdbc:postgresql://10.1.27.41:5432/dummydb"; // Update your database URL
    private static final String USER = "dummyuser"; // Update your database username
    private static final String PASSWORD = "password1"; // Update your database password
    
    /**
     * Main method to run the AccountManager.
     * It inserts predefined accounts into the database and exports them to a CSV file.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        AccountManager manager = new AccountManager(); // Default Constructor which create the object
        manager.insertAccounts(); // Inserts accounts into the database
        manager.exportAccountsToCSV("acc.csv"); // Exports accounts to a CSV file
    }
    
    /**
     * Default constructor for the AccountManager class.
     * This constructor is used to create an instance of the AccountManager
     * and does not require any parameters.
     */
    public AccountManager() {
        // Default constructor - no initialization required
    }

    
    /**
     * Inserts a list of predefined accounts into the database.
     * It checks for existing accounts before insertion to avoid duplicates.
     */

    public void insertAccounts() 
    {
    	
        String insertAccountSQL = "INSERT INTO empaccount (account_id, account_login, account_status, meta_type) VALUES (?, ?, ?, ?)";
        String checkAccountSQL = "SELECT COUNT(*) FROM empaccount WHERE account_id = ?";

        String[][] accounts = {
            {"ram", "ram.patel", "active", "User Account"},
            {"amar", "amar.sharma", "active", "User Account"},
            {"vishnu", "vishnu.singh", "active", "User Account"},
            {"shree", "shree.verma", "active", "User Account"},
            {"ajinkya", "ajinkya.kumar", "active", "User Account"},
            {"naman", "naman.bansal", "active", "User Account"},
            {"tushar", "tushar.yadav", "active", "User Account"},
            {"pravin", "pravin.dixit", "active", "User Account"},
            {"rohan", "rohan.joshi", "active", "User Account"},
            {"ishwar", "ishwar.mishra", "active", "User Account"},
            {"sudhir", "sudhir.pandey", "active", "User Account"},
            {"mahesh", "mahesh.gupta", "active", "User Account"},
            {"raj", "raj.thakur", "active", "User Account"},
            {"neeraja", "neeraja.rawat", "active", "User Account"},
            {"kagal", "kagal.choudhary", "active", "User Account"},
            {"amit", "amit.singh", "active", "User Account"},
            {"nitin", "nitin.sharma", "active", "User Account"},
            {"karthikey", "karthikey.suresh", "active", "User Account"},
            {"jayram", "jayram.patel", "active", "User Account"},
            {"dipali", "dipali.yadav", "active", "User Account"},
            {"monali", "monali.kumar", "active", "User Account"}
        };

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement accountStmt = connection.prepareStatement(insertAccountSQL);
             PreparedStatement checkStmt = connection.prepareStatement(checkAccountSQL)) {
        	
        	 // Predefined accounts to be inserted

            for (String[] account : accounts) {
                // Check if the account_id already exists
                checkStmt.setString(1, account[0]);
                ResultSet rs = checkStmt.executeQuery();
               
                
                // Retrieve account details from the result set
    if (rs.next() && rs.getInt(1) == 0) { // If count is 0, insert the account
                    accountStmt.setString(1, account[0]);
                    accountStmt.setString(2, account[1]);
                    accountStmt.setString(3, account[2]);
                    accountStmt.setString(4, account[3]);
                    accountStmt.executeUpdate();
                    logger.info("Inserted account: {}", account[0]);
                } else {
                    logger.info("Account already exists: {}", account[0]); 
                }
            }

            logger.info("Accounts insertion completed.");

        } catch (SQLException e) {
            logger.error("Error inserting accounts: {}", e.getMessage());
        }
    }
    /**
     * Exports account details from the database to a CSV file.
     *
     * @param filePath the path of the file to which the accounts will be exported
     */    
    public void exportAccountsToCSV(String filePath) {
        String query = "SELECT " +
                       "account_id AS \"AccountId\", " +
                       "account_login AS \"AccountLogin\", " +
                       "account_status AS \"Status\", " +
                       "meta_type AS \"$metaType\" " +
                       "FROM empaccount;";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery();
             FileWriter out = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Account Id", "Account Login", "Status", "$metaType"))) {

            while (rs.next()) {
                String accountId = rs.getString("AccountId");
                String accountLogin = rs.getString("AccountLogin");
                String accountStatus = rs.getString("Status");
                String metaType = rs.getString("$metaType");

                csvPrinter.printRecord(accountId, accountLogin, accountStatus, metaType);
                logger.info("Exported account: {}", accountLogin);
            }

            csvPrinter.flush();
            logger.info("Accounts exported successfully to {}", filePath);

        } catch (SQLException e) {
            logger.error("Database error: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("File error: {}", e.getMessage()); // Log any IO exceptions
        }
    }
}

