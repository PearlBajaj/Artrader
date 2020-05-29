package au.usyd.artrader.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.springframework.mock.web.MockMultipartFile;

import java.sql.Timestamp;

public class ArtworkVoTest extends TestCase {

    private ArtworkVo artworkVo;

    @Before
    public void setUp() throws Exception {
        artworkVo = new ArtworkVo();
    }


    public void testSetAndGetArtworkId() {
        Long testArtworkId = new Long(123456789);
        assertNull(artworkVo.getArtworkId());
        artworkVo.setArtworkId(testArtworkId);
        assertEquals(new Long(123456789),artworkVo.getArtworkId());
    }


    public void testSetAndGetName() {
        String testName = "testname";
        assertNull(artworkVo.getName());
        artworkVo.setName(testName);
        assertEquals(testName,artworkVo.getName());
    }


    public void testSetAndGetOwnerId() {
        Long testOwnerId = new Long(10000000);
        assertNull(artworkVo.getOwnerId());
        artworkVo.setOwnerId(testOwnerId);
        assertEquals(new Long(10000000),artworkVo.getOwnerId());
    }


    public void testSetAndGetArtistId() {
        Long testArtistId = new Long(10000001);
        assertNull(artworkVo.getArtistId());
        artworkVo.setArtistId(testArtistId);
        assertEquals(new Long(10000001),artworkVo.getArtistId());
    }


    public void testSetAndGetDescription() {
        String testDescription = "testDescription";
        assertNull(artworkVo.getDescription());
        artworkVo.setDescription(testDescription);
        assertEquals("testDescription",artworkVo.getDescription());
    }


    public void testSetAndGetProfitRate() {
        double testProfitRate = 0.1;
        artworkVo.setProfitRate(testProfitRate);
        assertEquals(0.1,artworkVo.getProfitRate());
    }


    public void testSetAndGetCategory() {
        String testCategory = "test category";
        assertNull(artworkVo.getCategory());
        artworkVo.setCategory(testCategory);
        assertEquals("test category",testCategory);
    }


    public void testSetAndGetTimestamp() {
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        assertNull(artworkVo.getCreatedTimestamp());
        artworkVo.setCreatedTimestamp(testTimeStamp1);
        assertEquals(testTimeStamp1, artworkVo.getCreatedTimestamp());

        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        assertNull(artworkVo.getModifiedTimestamp());
        artworkVo.setModifiedTimestamp(testTimeStamp2);
        assertEquals(testTimeStamp2, artworkVo.getModifiedTimestamp());
    }


    public void testSetAndGetDate() {
        String testDate = "18-11-2012";
        assertNull(artworkVo.getDate());
        artworkVo.setDate(testDate);
        assertEquals("18-11-2012", artworkVo.getDate());
    }


    public void testSetAndGetPhoto() {
        String testPhoto = "fajdfa98u23nfa093ofakdlnv2o39uraalfjasdof";
        assertNull(artworkVo.getPhoto());
        artworkVo.setPhoto(testPhoto);
        assertEquals("fajdfa98u23nfa093ofakdlnv2o39uraalfjasdof",artworkVo.getPhoto());
    }


    public void testSetAndGetPhotoData() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file","fileName",
                "text/plain", "test data".getBytes());
        assertNull(artworkVo.getPhotoData());
        artworkVo.setPhotoData(mockMultipartFile);
        assertEquals(mockMultipartFile, artworkVo.getPhotoData());
    }


    public void testSetAndGetPrice() {
        double testPrice = 250.6;
        artworkVo.setPrice(testPrice);
        assertEquals(250.6, artworkVo.getPrice());
    }

    public void testSetAndGetArtist() {
        User testUser = new User();
        assertNull(artworkVo.getArtist());
        artworkVo.setArtist(testUser);
        assertEquals(testUser, artworkVo.getArtist());
    }

    public void testConstructor() {
        Long testArtworkId = new Long(12);
        Long testArtistId = new Long(13);
        Long testOwnerId = new Long(11);
        String testDate = "12-01-1993";
        String testName = "testName";
        String testCategory = "testCategory";
        double testProfitRate = 0.02;
        String testDescription = "test description.";
        String testPhoto = "fa8fabif89adfa9d09a9f9a8df0a9d8fa09dfa09";
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        User testArtist = new User();

        Artwork testArtwork = new Artwork();
        testArtwork.setArtworkId(testArtworkId);
        testArtwork.setArtistId(testArtistId);
        testArtwork.setOwnerId(testOwnerId);
        testArtwork.setDate(testDate);
        testArtwork.setName(testName);
        testArtwork.setCategory(testCategory);
        testArtwork.setProfitRate(testProfitRate);
        testArtwork.setDescription(testDescription);
        testArtwork.setPhoto(testPhoto);
        testArtwork.setCreatedTimestamp(testTimeStamp1);
        testArtwork.setModifiedTimestamp(testTimeStamp2);

        ArtworkVo testArtworkVo1 = new ArtworkVo(testArtwork,testArtist);
        assertEquals(new Long(12), testArtworkVo1.getArtworkId());
        assertEquals(testArtist, testArtworkVo1.getArtist());
        assertEquals(new Long(11), testArtworkVo1.getOwnerId());
        assertEquals("12-01-1993", testArtworkVo1.getDate());
        assertEquals("testName", testArtworkVo1.getName());
        assertEquals("testCategory", testArtworkVo1.getCategory());
        assertEquals(0.02, testArtworkVo1.getProfitRate());
        assertEquals("test description.", testArtworkVo1.getDescription());
        assertEquals("fa8fabif89adfa9d09a9f9a8df0a9d8fa09dfa09", testArtworkVo1.getPhoto());
        assertEquals(testTimeStamp1, testArtworkVo1.getCreatedTimestamp());
        assertEquals(testTimeStamp2, testArtworkVo1.getModifiedTimestamp());
    }
}