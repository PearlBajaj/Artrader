package au.usyd.artrader.service;

import au.usyd.artrader.domain.Favourite;
import au.usyd.artrader.persistence.FavouriteDao;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FavouriteServiceTest extends TestCase {

    @InjectMocks
    FavouriteServiceImpl favouriteService;

    @Mock
    FavouriteDao favouriteDao;

    private long userId = 1;
    private long artworkId = 1;
    private List<Long> artworkIds = new ArrayList<>(Arrays.asList(1l, 2l, 3l, 4l, 5l));
    List<Favourite> favourites;

    @Before
    public void setup() {
        favourites = new ArrayList<>();
        artworkIds.forEach(artworkId -> {
            Favourite favourite = new Favourite();
            favourite.setUserId(userId);
            favourite.setArtworkId(artworkId);
            favourites.add(favourite);
        });
    }

    @Test
    public void favourite_Success() {
        Mockito.when(favouriteDao.selectFavourite(userId, artworkId)).thenReturn(null);
        try {
            favouriteService.favourite(userId, artworkId);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void favourite_AlreadyFavourite_ExceptionThrown() {
        Mockito.when(favouriteDao.selectFavourite(userId, artworkId)).thenReturn(new Favourite());

        try{
            favouriteService.favourite(userId, artworkId);
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            assertEquals(e.getMessage(), "Already favourite the artwork.");
            return;
        }
        fail("Should have thrown an exception");
    }

    @Test
    public void unfavourite_Success() {
        Mockito.when(favouriteDao.selectFavourite(userId, artworkId)).thenReturn(new Favourite());
        try {
            favouriteService.unfavourite(userId, artworkId);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void unfavourite_NoExist_ExceptionThrown() {
        Mockito.when(favouriteDao.selectFavourite(userId, artworkId)).thenReturn(null);

        try{
            favouriteService.unfavourite(userId, artworkId);
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            assertEquals(e.getMessage(), "No favourite artwork.");
            return;
        }
        fail("Should have thrown an exception");
    }

    @Test
    public void getFavourites_Success() {
        Mockito.when(favouriteDao.selectFavourites(userId, artworkIds)).thenReturn(favourites);
        List<Favourite> results = null;
        try {
            results = favouriteService.getFavourites(userId, artworkIds);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }

        assertNotNull(results);
        assertEquals(artworkIds.size(), results.size());
    }

    @Test
    public void getFavourites_NoExists_Success() {
        Mockito.when(favouriteDao.selectFavourites(userId, artworkIds)).thenReturn(new ArrayList<>());
        List<Favourite> results = null;
        try {
            results = favouriteService.getFavourites(userId, artworkIds);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }

        assertNotNull(results);
        assertEquals(0, results.size());
    }

    @Test
    public void getFavourites_NullUserId_ExceptionThrown() {
        try {
            favouriteService.getFavourites(null, artworkIds);
        } catch (Exception e) {
            assertEquals(e.getClass(), NullPointerException.class);
            assertEquals(e.getMessage(), "User Id cannot be null.");
            return;
        }
        fail("Should have thrown an exception");
    }

    @Test
    public void getFavourites_EmptyArtworkIds_Success() {
        List<Favourite> results = favouriteService.getFavourites(userId, new ArrayList<>());

        assertEquals(0, results.size());
    }

    @Test
    public void getFavourites_NullArtworkIds_Success() {
        List<Favourite> results = favouriteService.getFavourites(userId, null);

        assertEquals(0, results.size());
    }
}
