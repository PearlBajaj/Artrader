package au.usyd.artrader.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class UserTest extends TestCase {

    private User testUser;

    @Before
    public void setUp() throws Exception {
        testUser = new User();
    }

    public void testSetAndGetUserId() {
        Long testUserId = new Long(00001);
        assertNull(testUser.getUserId());
        testUser.setUserId(testUserId);
        assertEquals(new Long(1), testUser.getUserId());
    }

    public void testSetAndGetEmail() {
        String testEmail = "testemail@test.com";
        assertNull(testUser.getEmail());
        testUser.setEmail(testEmail);
        assertEquals("testemail@test.com", testUser.getEmail());
    }

    public void testSetAndGetPassWord() {
        String testPassWord = "testPassword";
        assertNull(testUser.getPassword());
        testUser.setPassword(testPassWord);
        assertEquals("testPassword", testUser.getPassword());
    }

    public void testSetAndGetName() {
        String testName= "testName";
        assertNull(testUser.getName());
        testUser.setName(testName);
        assertEquals("testName", testUser.getName());
    }

    public void testSetAndGetMobileNumber() {
        String testMobileNumber= "021548125";
        assertNull(testUser.getMobileNumber());
        testUser.setMobileNumber(testMobileNumber);
        assertEquals("021548125", testUser.getMobileNumber());
    }

    public void testSetAndGetAddress() {
        String testAddress= "testAddress";
        assertNull(testUser.getAddress());
        testUser.setAddress(testAddress);
        assertEquals("testAddress", testUser.getAddress());
    }

    public void testSetAndGetTotalProfit() {
        double testTotalProfit = 1001.2;
        testUser.setTotalProfit(testTotalProfit);
        assertEquals(1001.2, testUser.getTotalProfit());
    }

    public void testSetAndGetTimestamp() {
        Timestamp testTimeStamp1 = new Timestamp(System.currentTimeMillis());
        assertNull(testUser.getCreatedTimestamp());
        testUser.setCreatedTimestamp(testTimeStamp1);
        assertEquals(testTimeStamp1, testUser.getCreatedTimestamp());

        Timestamp testTimeStamp2 = new Timestamp(System.currentTimeMillis());
        assertNull(testUser.getModifiedTimestamp());
        testUser.setModifiedTimestamp(testTimeStamp2);
        assertEquals(testTimeStamp2, testUser.getModifiedTimestamp());
    }

    @Test
    public void testToString() {
        Long testUserId = new Long(00001);
        assertNull(testUser.getUserId());
        testUser.setUserId(testUserId);

        String testEmail = "testemail@test.com";
        assertNull(testUser.getEmail());
        testUser.setEmail(testEmail);

        String testToString = "userId: " + 1
                + ", email: " + "testemail@test.com";
        assertEquals(testToString, testUser.toString());
    }
}