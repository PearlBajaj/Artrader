package au.usyd.artrader.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class TransactionTest extends TestCase {

    private Transaction testTransaction;

    @Before
    public void setUp() throws Exception {
        testTransaction = new Transaction();
    }

    public void testSetAndGetTransactionId() {
        Long testTransactionId = new Long(123456789);
        assertNull(testTransaction.getTransactionId());
        testTransaction.setTransactionId(testTransactionId);
        assertEquals(new Long(123456789),testTransaction.getTransactionId());
    }

    public void testSetAndGetSellerId() {
        Long testSellerId = new Long(1);
        assertNull(testTransaction.getSellerId());
        testTransaction.setSellerId(testSellerId);
        assertEquals(new Long(1),testTransaction.getSellerId());
    }

    public void testSetAndGetBuyerId() {
        Long testBuyerId = new Long(2);
        assertNull(testTransaction.getBuyerId());
        testTransaction.setBuyerId(testBuyerId);
        assertEquals(new Long(2),testTransaction.getBuyerId());
    }

    public void testSetAndGetArtworkId() {
        Long testArtworkId = new Long(3);
        assertNull(testTransaction.getArtworkId());
        testTransaction.setArtworkId(testArtworkId);
        assertEquals(new Long(3),testTransaction.getArtworkId());
    }

    public void testSetAndGetPrice () {
        double testPrice = 21312.2;
        testTransaction.setPrice(testPrice);
        assertEquals(21312.2, testTransaction.getPrice());
    }

    public void testSetAndGetMobileNumber() {
        String testMobileNumber = "0123912312";
        assertNull(testTransaction.getMobileNumber());
        testTransaction.setMobileNumber(testMobileNumber);
        assertEquals("0123912312", testTransaction.getMobileNumber());
    }

    public void testSetAndGetAddress() {
        String testAddress = "test address";
        assertNull(testTransaction.getAddress());
        testTransaction.setAddress(testAddress);
        assertEquals("test address", testTransaction.getAddress());
    }

    public void testSetAndGetComment() {
        String testAddress = "test comment, extra. testing.";
        assertNull(testTransaction.getComment());
        testTransaction.setComment(testAddress);
        assertEquals("test comment, extra. testing.", testTransaction.getComment());
    }

    public void testShipping() {
        boolean testShipping = true;
        assertFalse(testTransaction.isShipping());
        testTransaction.setShipping(testShipping);
        assertTrue(testTransaction.isShipping());
    }


    public void testSetAndGetTimestamp() {
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        assertNull(testTransaction.getCreatedTimestamp());
        testTransaction.setCreatedTimestamp(testTimeStamp1);
        assertEquals(testTimeStamp1, testTransaction.getCreatedTimestamp());
    }
}