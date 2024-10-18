package con.testng.testngpractise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * A test class that provides operations for managing an integer array
 * and tests those operations using TestNG's @DataProvider annotation.
 */
public class ArrayOperationsTest {

    private static final Logger logger = LoggerFactory.getLogger(ArrayOperationsTest.class);
    private int[] array;

    /**
     * Adds a value at a specified index.
     *
     * @param index the index at which the value should be added.
     * @param value the value to be added.
     */
    public void addValue(int index, int value) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = value;
    }

    /**
     * Retrieves a value from a specified index.
     *
     * @param index the index from which to retrieve the value.
     * @return the value at the specified index.
     */
    public int getValue(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }

    /**
     * Returns the current state of the array.
     *
     * @return a copy of the current array.
     */
    public int[] getArray() {
        return Arrays.copyOf(array, array.length);
    }

    /**
     * Data provider for testing array operations.
     *
     * @return Object array containing test data for array operations.
     */
    @DataProvider(name = "arrayData")
    public static Object[][] arrayData() {
        return new Object[][] {
            { 5, new int[]{0, 0, 0, 0, 0}, 0, 10, new int[]{10, 0, 0, 0, 0} },
            { 5, new int[]{1, 2, 3, 4, 5}, 2, 20, new int[]{1, 2, 20, 4, 5} },
            { 3, new int[]{0, 0, 0}, 1, 15, new int[]{0, 15, 0} }
        };
    }

    /**
     * Test method for adding values to the array and retrieving them.
     *
     * @param size the size of the array.
     * @param initialValues the initial values of the array.
     * @param index the index at which to add the value.
     * @param value the value to be added.
     * @param expectedArray the expected state of the array after the operation.
     */
    @Test(dataProvider = "arrayData")
    public void testArrayOperations(int size, int[] initialValues, int index, int value, int[] expectedArray) {
        logger.info("Testing array operations with initial values: {}", Arrays.toString(initialValues));

        // Initialize the array with the correct size
        array = new int[size]; // Initialize with provided size

        // Populate the array with initial values
        for (int i = 0; i < initialValues.length; i++) {
            addValue(i, initialValues[i]);
        }

        logger.info("Array before addition: {}", Arrays.toString(getArray()));
        addValue(index, value);
        logger.info("Array after addition: {}", Arrays.toString(getArray()));

        Assert.assertEquals(getArray(), expectedArray, "Array state is incorrect after addition.");
        Assert.assertEquals(getValue(index), value, "Retrieved value is incorrect.");
    }
}
