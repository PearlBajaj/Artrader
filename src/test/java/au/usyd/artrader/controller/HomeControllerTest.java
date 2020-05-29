package au.usyd.artrader.controller;

import au.usyd.artrader.domain.Artwork;
import au.usyd.artrader.domain.User;
import au.usyd.artrader.persistence.UserDao;
import au.usyd.artrader.service.FavouriteService;
import au.usyd.artrader.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by mac on 2019/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/persistence-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class HomeControllerTest {

    @Mock
    private UserService userService;

    @Mock
    UserDao userDao;

    @Mock
    private FavouriteService favouriteService;

    @Autowired
    ApplicationContext ctx;

    private MockMvc mockMvc;

    private ArtworkController artworkController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public static final String LOGIN = "login";

    private User testUser;

    private String testEmail = "test@1.com";

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        testUser = new User();

        testUser.setEmail(testEmail);

        testUser.setUserId(6l);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
    @Test
    public void home() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("redirect:sale/search"));

    }

}