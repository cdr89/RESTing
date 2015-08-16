package it.caldesi.resting;

import it.caldesi.resting.exceptions.AssertionFailedException;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public abstract class TestCase {

    /**
     * set to true if you want to call setUp() and cleanUp() for
     * each test void into your TestCase subclass.
     */
    protected boolean refreshForEachvoid = false;

    /**
     * Initialize all you need into the testing functions.
     * setUp() will be called once per TestCase subclass by default,
     * if you need to refresh the environment once per test function
     * set refreshForEachFuction = true;
     */
    public abstract void setUp();

    /**
     * Clean the test environment if you used some file or put
     * entries into DB.
     * cleanUp() will be called once per TestCase subclass by default,
     * if you need to refresh the environment once per test function
     * set refreshForEachFuction = true;
     */
    public abstract void cleanUp();


    /********************************
     ********** ASSERTIONS **********
     ********************************/

    /**
     * Assert value == true
     *
     * @param value
     * @throws AssertionFailedException
     */
    protected final static void assertTrue(boolean value) throws AssertionFailedException {
        if (value != true)
            throw new AssertionFailedException("Expected \"true\"");
    }

    /**
     * Assert value == false
     *
     * @param value
     * @throws AssertionFailedException
     */
    public final static void assertFalse(boolean value) throws AssertionFailedException {
        if (value != false)
            throw new AssertionFailedException("Expected \"false\"");
    }

    /**
     * Assert value1.equals(value2)
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertEqual(Object value1, Object value2) throws AssertionFailedException {
        if ((value1 == null && value2 != null) || !value1.equals(value2))
            throw new AssertionFailedException("Expected \"" + value1 + "\" to be equals to \"" + value2 + "\"");
    }

    /**
     * Assert !value1.equals(value2)
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertNotEqual(Object value1, Object value2) throws AssertionFailedException {
        if ((value1 == null && value2 == null) || value1.equals(value2))
            throw new AssertionFailedException("Expected \"" + value1 + "\" not to be equals to \"" + value2 + "\"");
    }

    /**
     * Assert value1 == value2
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertIdentical(Object value1, Object value2) throws AssertionFailedException {
        if (value1 != value2)
            throw new AssertionFailedException("Expected \"" + value1 + "\" to be identical to \"" + value2 + "\"");
    }

    /**
     * Assert value1 != value2
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertNotIdentical(Object value1, Object value2) throws AssertionFailedException {
        if (value1 == value2)
            throw new AssertionFailedException("Expected \"" + value1 + "\" not to be identical to \"" + value2 + "\"");
    }

    /**
     * Assert value1 < value2
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertLessThan(Comparable value1, Comparable value2) throws AssertionFailedException {
        if (value1.compareTo(value2) >= 0)
            throw new AssertionFailedException("Expected \"" + value1 + "\" to be less than \"" + value2 + "\"");
    }

    /**
     * Assert value1 <= value2
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertLessThanOrEqual(Comparable value1, Comparable value2) throws AssertionFailedException {
        if (value1.compareTo(value2) > 0)
            throw new AssertionFailedException("Expected \"" + value1 + "\" to be less than or equals to \"" + value2 + "\"");
    }

    /**
     * Assert value1 > value2
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertGreaterThan(Comparable value1, Comparable value2) throws AssertionFailedException {
        if (value1.compareTo(value2) <= 0)
            throw new AssertionFailedException("Expected \"" + value1 + "\" to be greater than \"" + value2 + "\"");
    }

    /**
     * Assert value1 >= value2
     *
     * @param value1
     * @param value2
     * @throws AssertionFailedException
     */
    public final static void assertGreaterThanOrEqual(Comparable value1, Comparable value2) throws AssertionFailedException {
        if (value1.compareTo(value2) <= 0)
            throw new AssertionFailedException("Expected \"" + value1 + "\" to be greater than or equals to \"" + value2 + "\"");
    }

}