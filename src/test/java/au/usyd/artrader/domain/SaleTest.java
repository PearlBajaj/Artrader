package au.usyd.artrader.domain;

import junit.framework.TestCase;
import org.junit.Before;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class SaleTest extends TestCase {
    
    private Sale testSale;
    @Before
    public void setUp() throws Exception {
        testSale = new Sale();
    }
    public void testSetAndGetSaleId() {
        Long testSaleId = new Long(123456789);
        assertNull(testSale.getSaleId());
        testSale.setSaleId(testSaleId);
        assertEquals(new Long(123456789),testSale.getSaleId());
    }

    public void testSetAndGetSellerId() {
        Long testSellerId = new Long(1);
        assertNull(testSale.getSellerId());
        testSale.setSellerId(testSellerId);
        assertEquals(new Long(1),testSale.getSellerId());
    }

    public void testSetAndGetPrice () {
        double testPrice = 21312.2;
        testSale.setPrice(testPrice);
        assertEquals(21312.2, testSale.getPrice());
    }

    public void testSetAndGetTimestamp() {
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        assertNull(testSale.getCreatedTimestamp());
        testSale.setCreatedTimestamp(testTimeStamp1);
        assertEquals(testTimeStamp1, testSale.getCreatedTimestamp());

        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        assertNull(testSale.getModifiedTimestamp());
        testSale.setModifiedTimestamp(testTimeStamp2);
        assertEquals(testTimeStamp2, testSale.getModifiedTimestamp());

        Timestamp testTimeStamp3 = new Timestamp(System.currentTimeMillis());
        assertNull(testSale.getDeletedTimestamp());
        testSale.setDeletedTimestamp(testTimeStamp3);
        assertEquals(testTimeStamp3, testSale.getDeletedTimestamp());
    }

    public void testSetAndGetArtwork () {
        Artwork testArtwork = new Artwork();
        assertNull(testSale.getArtwork());
        testSale.setArtwork(testArtwork);
        assertEquals(testArtwork, testSale.getArtwork());
    }

}