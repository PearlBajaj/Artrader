package au.usyd.artrader.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class SaleVoTest extends TestCase {

    SaleVo testSaleVo;

    @Before
    public void setUp() throws Exception {
        testSaleVo = new SaleVo();
    }

    public void testSetAndGetSaleId() {
        Long testSaleId = new Long(123456789);
        assertNull(testSaleVo.getSaleId());
        testSaleVo.setSaleId(testSaleId);
        assertEquals(new Long(123456789),testSaleVo.getSaleId());
    }

    public void testSetAndGetSellerId() {
        Long testSellerId = new Long(1);
        assertNull(testSaleVo.getSellerId());
        testSaleVo.setSellerId(testSellerId);
        assertEquals(new Long(1),testSaleVo.getSellerId());
    }

    public void testSetAndGetPrice () {
        double testPrice = 21312.2;
        testSaleVo.setPrice(testPrice);
        assertEquals(21312.2, testSaleVo.getPrice());
    }

    public void testSetAndGetTimestamp() {
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        assertNull(testSaleVo.getCreatedTimestamp());
        testSaleVo.setCreatedTimestamp(testTimeStamp1);
        assertEquals(testTimeStamp1, testSaleVo.getCreatedTimestamp());

        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        assertNull(testSaleVo.getModifiedTimestamp());
        testSaleVo.setModifiedTimestamp(testTimeStamp2);
        assertEquals(testTimeStamp2, testSaleVo.getModifiedTimestamp());

        Timestamp testTimeStamp3 = new Timestamp(System.currentTimeMillis());
        assertNull(testSaleVo.getDeletedTimestamp());
        testSaleVo.setDeletedTimestamp(testTimeStamp3);
        assertEquals(testTimeStamp3, testSaleVo.getDeletedTimestamp());
    }

    public void testSetAndGetArtwork () {
        Artwork testArtwork = new Artwork();
        assertNull(testSaleVo.getArtwork());
        testSaleVo.setArtwork(testArtwork);
        assertEquals(testArtwork, testSaleVo.getArtwork());
    }


    public void testSetAndGetArtist () {
        User testArtist = new User();
        assertNull(testSaleVo.getArtist());
        testSaleVo.setArtist(testArtist);
        assertEquals(testArtist, testSaleVo.getArtist());
    }

    public void testSetAndGetFavourite () {
        boolean testFavourite = true;
        assertEquals(false, testSaleVo.isFavourite());
        testSaleVo.setFavourite(testFavourite);
        assertEquals(true, testSaleVo.isFavourite());
    }

    public void testConstructors(){
        Long testSaleId = new Long(123456789);
        Long testSellerId = new Long(1);
        double testPrice = 1312.12;
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        Artwork testArtwork = new Artwork();
        User testArtist = new User();
        boolean testFavourite = true;


        Sale testSale = new Sale();
        testSale.setSaleId(testSaleId);
        testSale.setSellerId(testSellerId);
        testSale.setPrice(testPrice);
        testSale.setCreatedTimestamp(testTimeStamp1);
        testSale.setModifiedTimestamp(testTimeStamp2);
        testSale.setArtwork(testArtwork);

        SaleVo testSaleVo1 = new SaleVo(testSale,testArtist);
        assertEquals(new Long(123456789), testSaleVo1.getSaleId());
        assertEquals(new Long(1), testSaleVo1.getSellerId());
        assertEquals(1312.12, testSaleVo1.getPrice());
        assertEquals(testTimeStamp1, testSaleVo1.getCreatedTimestamp());
        assertEquals(testTimeStamp2, testSaleVo1.getModifiedTimestamp());
        assertEquals(testArtwork, testSaleVo1.getArtwork());
        assertEquals(testArtist, testSaleVo1.getArtist());

        SaleVo testSaleVo2 = new SaleVo(testSale,testArtist,testFavourite);
        assertEquals(new Long(123456789), testSaleVo1.getSaleId());
        assertEquals(new Long(1), testSaleVo1.getSellerId());
        assertEquals(1312.12, testSaleVo1.getPrice());
        assertEquals(testTimeStamp1, testSaleVo1.getCreatedTimestamp());
        assertEquals(testTimeStamp2, testSaleVo1.getModifiedTimestamp());
        assertEquals(testArtwork, testSaleVo1.getArtwork());
        assertEquals(testArtist, testSaleVo1.getArtist());
        assertEquals(testFavourite, testSaleVo2.isFavourite());

    }

}