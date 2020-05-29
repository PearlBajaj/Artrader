package au.usyd.artrader.service;

import au.usyd.artrader.domain.Favourite;
import au.usyd.artrader.persistence.FavouriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    private static final Logger logger = LoggerFactory.getLogger(FavouriteServiceImpl.class);

    @Autowired
    FavouriteDao favouriteDao;

    @Override
    public void favourite(long userId, long artworkId) {
        Favourite favourite = new Favourite();
        favourite.setUserId(userId);
        favourite.setArtworkId(artworkId);
        favourite.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));

        if(favouriteDao.selectFavourite(userId, artworkId) != null) {
            throw new IllegalArgumentException("Already favourite the artwork.");
        }
        favouriteDao.createFavourite(favourite);
    }

    @Override
    public void unfavourite(long userId, long artworkId) {
        Favourite favourite = new Favourite();
        favourite.setUserId(userId);
        favourite.setArtworkId(artworkId);

        if(favouriteDao.selectFavourite(userId, artworkId) == null) {
            throw new IllegalArgumentException("No favourite artwork.");
        }

        favouriteDao.deleteFavourite(favourite);
    }

    @Override
    public List<Favourite> getFavourites(Long userId, List<Long> artworkIds) {
        if(userId == null) {
            throw new NullPointerException("User Id cannot be null.");
        }

        if(ObjectUtils.isEmpty(artworkIds)) {
            return Collections.emptyList();
        }

        return favouriteDao.selectFavourites(userId, artworkIds);
    }

    @Override
    public List<Favourite> getFavouritesbyuserid(Long userId) {
        return favouriteDao.selectFavouritesByUserId(userId);
    }
}
