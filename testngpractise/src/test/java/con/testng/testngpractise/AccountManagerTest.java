package con.testng.testngpractise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test class for AccountManager, validating database operations such as
 * inserting accounts and exporting to CSV.
 */
public class AccountManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountManagerTest.class);
    private static final String DB_URL = "jdbc:postgresql://10.1.27.41:5432/dummydb";
    private static final String USER = "dummyuser";
    private static final String PASSWORD = "password1";
    private Connection connection;

    /**
     * Default constructor for the AccountManagerTest class.
     * This constructor does not require any parameters.
     */
    public AccountManagerTest() {
        // Default constructor
    }

    /**
     * Sets up the database connection before running tests.
     */
    @BeforeClass
    public void setUp() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            createTestTable();
            logger.info("Database connection established and test table created.");
        } catch (SQLException e) {
            logger.error("Failed to connect to the database: {}", e.getMessage());
        }
    }

    /**
     * Creates a test table for the account manager tests.
     *
     * @throws SQLException if an error occurs during table creation
     */
    private void createTestTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS empaccount (" +
                                 "account_id VARCHAR(50) PRIMARY KEY, " +
                                 "account_login VARCHAR(50), " +
                                 "account_status VARCHAR(50), " +
                                 "meta_type VARCHAR(50));";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    /**
     * Tests the database connection.
     */
    @Test
    public void testDatabaseConnection() {
        assertNotNull(connection, "Connection should not be null after setup.");
    }

    /**
     * Tests table creation.
     */
    @Test
    public void testCreateTestTable() {
        assertTrue(checkTableExists("empaccount"), "Test table should exist.");
    }

    /**
     * Tests the insertion of accounts into the database.
     */
    @Test
    public void testInsertAccounts() {
        AccountManager manager = new AccountManager();
        manager.insertAccounts(); // Inserts accounts into the database
        assertTrue(checkAccountsInserted(), "Accounts should be inserted successfully");
    }

    /**
     * Tests that account insertion does not allow duplicates.
     */
    @Test
    public void testInsertAccountsNoDuplicates() {
        AccountManager manager = new AccountManager();
        manager.insertAccounts(); // Insert accounts first time
        int countAfterFirstInsert = getAccountCount();
        
        manager.insertAccounts(); // Try to insert again
        int countAfterSecondInsert = getAccountCount();
        
        assertTrue(countAfterFirstInsert == countAfterSecondInsert, "No duplicates should be inserted.");
    }

    /**
     * Tests the export of accounts to a CSV file.
     */
    @Test(dependsOnMethods = "testInsertAccounts")
    public void testExportAccountsToCSV() {
        String filePath = "test_acc.csv";
        AccountManager manager = new AccountManager();
        manager.exportAccountsToCSV(filePath);
        assertTrue(new java.io.File(filePath).exists(), "CSV file should be created successfully.");
        
        // Additional verification of CSV content could be done here if needed.
    }

    /**
     * Checks if accounts have been inserted into the database.
     *
     * @return true if accounts exist, false otherwise
     */
    private boolean checkAccountsInserted() {
        return getAccountCount() > 0;
    }

    /**
     * Gets the count of accounts in the database.
     *
     * @return the number of accounts
     */
    private int getAccountCount() {
        String query = "SELECT COUNT(*) FROM empaccount";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error checking account count: {}", e.getMessage());
        }
        return 0;
    }

    /**
     * Checks if a specified table exists in the database.
     *
     * @param tableName the name of the table to check
     * @return true if the table exists, false otherwise
     */
    private boolean checkTableExists(String tableName) {
        String query = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = '" + tableName + "');";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            logger.error("Error checking table existence: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Cleans up the database connection and drops the test table after tests.
     */
    @AfterClass
    public void tearDown() {
        try {
            dropTestTable();
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            logger.info("Database connection closed and test table dropped.");
        } catch (SQLException e) {
            logger.error("Error during teardown: {}", e.getMessage());
        }
    }

    /**
     * Drops the test table after the tests are completed.
     *
     * @throws SQLException if an error occurs during table dropping
     */
    private void dropTestTable() throws SQLException {
        String dropTableSQL = "DROP TABLE IF EXISTS empaccount;";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTableSQL);
        }
    }
}
