package au.usyd.artrader.service;

import au.usyd.artrader.domain.Favourite;
import au.usyd.artrader.domain.User;
import au.usyd.artrader.persistence.UserDao;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends TestCase {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDao userDao;

    User testUser;
    private List<Long> userIds = new ArrayList<>(Arrays.asList(1l, 2l, 3l, 4l, 5l));
    private String testEmail = "test@test.com";
    List<User> testUsers;

    @Before
    public void setUp() throws Exception {
        testUsers = new ArrayList<>();
        userIds.forEach(userId -> {
            User user = new User();
            user.setEmail(testEmail);
            user.setUserId(userId);
            testUsers.add(user);
        });
    }

    @Test
    public void getUser_noUser() {
        Mockito.when(userDao.selectUsersByEmail("test@test.com")).thenReturn(null);
        try {
            userService.getUser(testEmail);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void getUser_hasUser() {
        Mockito.when(userDao.selectUsersByEmail("test@test.com")).thenReturn(testUsers);
        try {
            User user = userService.getUser(testEmail);
            assertEquals(new Long(1),user.getUserId());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void getUsers_noUser() {
        Mockito.when(userDao.selectUsersByUserIds(userIds)).thenReturn(Collections.emptyList());
        try {
            List<User> users = userService.getUsers(userIds);
            assertEquals(Collections.emptyList(),users);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void getUsers_hasUser() {
        Mockito.when(userDao.selectUsersByUserIds(userIds)).thenReturn(testUsers);
        try {
            List<User> users = userService.getUsers(userIds);
            assertEquals(testUsers,users);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }
    
}