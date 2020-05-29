package au.usyd.artrader.controller;

import au.usyd.artrader.domain.User;
import au.usyd.artrader.persistence.UserDao;
import au.usyd.artrader.service.FavouriteService;
import au.usyd.artrader.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by mac on 2019/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/persistence-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Autowired
    ApplicationContext ctx;

    private MockMvc mockMvc;

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

        testUser.setPassword("1");

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getLoginPage() throws Exception {


        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/login").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("login"));

    }

    @Test
    public void login_success() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(testUser);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/loginPost").param("email","1@1.com").param("password","1");

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("home"));

    }

    @Test
    public void login_fail() throws Exception {

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/loginPost").param("email","1@1.com").param("password","1");

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("redirect:login"));

    }

    @Test
    public void getSignUpPage() throws Exception {


        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/signup").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("signup"));

    }

    @Test
    public void signUp() throws Exception {


        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/add").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("redirect:login"));


    }

    @Test
    public void logout() throws Exception {


        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/logout").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("redirect:login"));
    }

    @Test
    public void getUserEditPage() throws Exception {


        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/edit").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("user/userEdit"));

    }

}