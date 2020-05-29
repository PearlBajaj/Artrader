package au.usyd.artrader.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.springframework.mock.web.MockMultipartFile;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class CreationVoTest extends TestCase {

    private CreationVo testCreationVo;

    @Before
    public void setUp() throws Exception {
        testCreationVo = new CreationVo();
    }

    public void testSetAndGetArtworkId() {
        Long testArtworkId = new Long(123456789);
        assertNull(testCreationVo.getArtworkId());
        testCreationVo.setArtworkId(testArtworkId);
        assertEquals(new Long(123456789),testCreationVo.getArtworkId());
    }

    public void testSetAndGetName() {
        String testName = "testname";
        assertNull(testCreationVo.getName());
        testCreationVo.setName(testName);
        assertEquals(testName,testCreationVo.getName());
    }

    public void testSetAndGetOwnerId() {
        Long testOwnerId = new Long(10000000);
        assertNull(testCreationVo.getOwnerId());
        testCreationVo.setOwnerId(testOwnerId);
        assertEquals(new Long(10000000),testCreationVo.getOwnerId());
    }


    public void testSetAndGetArtistId() {
        Long testArtistId = new Long(10000001);
        assertNull(testCreationVo.getArtistId());
        testCreationVo.setArtistId(testArtistId);
        assertEquals(new Long(10000001),testCreationVo.getArtistId());
    }


    public void testSetAndGetDescription() {
        String testDescription = "testDescription";
        assertNull(testCreationVo.getDescription());
        testCreationVo.setDescription(testDescription);
        assertEquals("testDescription",testCreationVo.getDescription());
    }

    public void testSetAndGetProfitRate() {
        double testProfitRate = 0.1;
        testCreationVo.setProfitRate(testProfitRate);
        assertEquals(0.1,testCreationVo.getProfitRate());
    }

    public void testSetAndGetCategory() {
        String testCategory = "test category";
        assertNull(testCreationVo.getCategory());
        testCreationVo.setCategory(testCategory);
        assertEquals("test category",testCategory);
    }

    public void testSetAndGetTimestamp() {
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        assertNull(testCreationVo.getCreatedTimestamp());
        testCreationVo.setCreatedTimestamp(testTimeStamp1);
        assertEquals(testTimeStamp1, testCreationVo.getCreatedTimestamp());

        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        assertNull(testCreationVo.getModifiedTimestamp());
        testCreationVo.setModifiedTimestamp(testTimeStamp2);
        assertEquals(testTimeStamp2, testCreationVo.getModifiedTimestamp());
    }


    public void testSetAndGetDate() {
        String testDate = "18-11-2012";
        assertNull(testCreationVo.getDate());
        testCreationVo.setDate(testDate);
        assertEquals("18-11-2012", testCreationVo.getDate());
    }


    public void testSetAndGetPhoto() {
        String testPhoto = "fajdfa98u23nfa093ofakdlnv2o39uraalfjasdof";
        assertNull(testCreationVo.getPhoto());
        testCreationVo.setPhoto(testPhoto);
        assertEquals("fajdfa98u23nfa093ofakdlnv2o39uraalfjasdof",testCreationVo.getPhoto());
    }


    public void testSetAndGetPhotoData() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file","fileName",
                "text/plain", "test data".getBytes());
        assertNull(testCreationVo.getPhotoData());
        testCreationVo.setPhotoData(mockMultipartFile);
        assertEquals(mockMultipartFile, testCreationVo.getPhotoData());
    }


    public void testSetAndGetPrice() {
        double testPrice = 250.6;
        testCreationVo.setPrice(testPrice);
        assertEquals(250.6, testCreationVo.getPrice());
    }

    public void testSetAndGetTotalRevenue() {
        double testTotalRevenue = 12394.12;
        testCreationVo.setTotalRevenue(testTotalRevenue);
        assertEquals(12394.12, testCreationVo.getTotalRevenue());
    }

}