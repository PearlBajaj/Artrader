package au.usyd.artrader.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class CollectionVoTest extends TestCase {

    private CollectionVo testCollectionVo;

    @Before
    public void setUp() throws Exception {
        testCollectionVo = new CollectionVo();
    }

    public void testSetAndGetArtworkId() {
        Long testArtworkId = new Long(123456789);
        assertNull(testCollectionVo.getArtworkId());
        testCollectionVo.setArtworkId(testArtworkId);
        assertEquals(new Long(123456789),testCollectionVo.getArtworkId());
    }

    public void testSetAndGetName() {
        String testName = "testname";
        assertNull(testCollectionVo.getName());
        testCollectionVo.setName(testName);
        assertEquals(testName,testCollectionVo.getName());
    }

    public void testSetAndGetOwnerId() {
        Long testOwnerId = new Long(10000000);
        assertNull(testCollectionVo.getOwnerId());
        testCollectionVo.setOwnerId(testOwnerId);
        assertEquals(new Long(10000000),testCollectionVo.getOwnerId());
    }


    public void testSetAndGetArtistId() {
        Long testArtistId = new Long(10000001);
        assertNull(testCollectionVo.getArtistId());
        testCollectionVo.setArtistId(testArtistId);
        assertEquals(new Long(10000001),testCollectionVo.getArtistId());
    }


    public void testSetAndGetDescription() {
        String testDescription = "testDescription";
        assertNull(testCollectionVo.getDescription());
        testCollectionVo.setDescription(testDescription);
        assertEquals("testDescription",testCollectionVo.getDescription());
    }

    public void testSetAndGetProfitRate() {
        double testProfitRate = 0.1;
        testCollectionVo.setProfitRate(testProfitRate);
        assertEquals(0.1,testCollectionVo.getProfitRate());
    }

    public void testSetAndGetCategory() {
        String testCategory = "test category";
        assertNull(testCollectionVo.getCategory());
        testCollectionVo.setCategory(testCategory);
        assertEquals("test category",testCategory);
    }

    public void testSetAndGetTimestamp() {
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        assertNull(testCollectionVo.getCreatedTimestamp());
        testCollectionVo.setCreatedTimestamp(testTimeStamp1);
        assertEquals(testTimeStamp1, testCollectionVo.getCreatedTimestamp());

        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        assertNull(testCollectionVo.getModifiedTimestamp());
        testCollectionVo.setModifiedTimestamp(testTimeStamp2);
        assertEquals(testTimeStamp2, testCollectionVo.getModifiedTimestamp());
    }


    public void testSetAndGetDate() {
        String testDate = "18-11-2012";
        assertNull(testCollectionVo.getDate());
        testCollectionVo.setDate(testDate);
        assertEquals("18-11-2012", testCollectionVo.getDate());
    }


    public void testSetAndGetPhoto() {
        String testPhoto = "fajdfa98u23nfa093ofakdlnv2o39uraalfjasdof";
        assertNull(testCollectionVo.getPhoto());
        testCollectionVo.setPhoto(testPhoto);
        assertEquals("fajdfa98u23nfa093ofakdlnv2o39uraalfjasdof",testCollectionVo.getPhoto());
    }


    public void testSetAndGetPhotoData() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file","fileName",
                "text/plain", "test data".getBytes());
        assertNull(testCollectionVo.getPhotoData());
        testCollectionVo.setPhotoData(mockMultipartFile);
        assertEquals(mockMultipartFile, testCollectionVo.getPhotoData());
    }


    public void testSetAndGetPrice() {
        double testPrice = 250.6;
        testCollectionVo.setPrice(testPrice);
        assertEquals(250.6, testCollectionVo.getPrice());
    }

    public void testSetAndGetOnSale() {
        boolean testOnSale = true;
        assertEquals(false, testCollectionVo.isOnSale());
        testCollectionVo.setOnSale(testOnSale);
        assertEquals(true,testCollectionVo.isOnSale());
    }
}