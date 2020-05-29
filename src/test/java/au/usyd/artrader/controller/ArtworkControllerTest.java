package au.usyd.artrader.controller;

import au.usyd.artrader.domain.Artwork;
import au.usyd.artrader.domain.User;
import au.usyd.artrader.persistence.UserDao;
import au.usyd.artrader.service.ArtworkService;
import au.usyd.artrader.service.FavouriteService;
import au.usyd.artrader.service.UserService;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by mac on 2019/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/persistence-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ArtworkControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ArtworkController.class);
    private static final int PAGE_SIZE = 8;

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
    public void addArtworkView() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);



        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/artwork/add").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("artwork/artworkAdd"));

    }

    @Test
    public void addArtwork1() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        Artwork artwork = new Artwork();

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/artwork/add").sessionAttr(LOGIN,user).requestAttr("artwork",artwork);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());

    }

    @Test
    public void getCreations() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/artwork/creations").
                param("page","1").param("category","All").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("artwork/creationList"));

    }

    @Test
    public void getCollections() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/artwork/collections").
                param("page","1").param("category","All").sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("artwork/collectionList"));

    }

    @Test
    public void getArtwork() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        long artworkId = 20;

        MockMvcRequestBuilders.post("/artwork/").param("artworkId",String.valueOf(artworkId));

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/artwork/").sessionAttr(LOGIN,user).param("artworkId","20");

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(status().is4xxClientError());

    }

    @Test
    public void getFavourites() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/artwork/favourites").
             sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(view().name("artwork/favouriteList"));

    }

    @Test
    public void favourite() throws Exception {
        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        long artworkID = 20l;

        Mockito.when((favouriteService)).thenReturn(null);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/artwork/{artworkId}/favourite",artworkID).sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());

    }

    @Test
    public void unfavourite() throws Exception {

        Mockito.when(userService.getUser(testEmail)).thenReturn(testUser);

        User user = userService.getUser(testEmail);

        long artworkID = 40l;

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/artwork/{artworkId}/favourite",artworkID).sessionAttr(LOGIN,user);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        resultActions.andExpect(status().is2xxSuccessful());

    }


}