package au.usyd.artrader.service;

import au.usyd.artrader.domain.Favourite;

import java.util.List;

public interface FavouriteService {
    void favourite(long userId, long artworkId);
    void unfavourite(long userId, long artworkId);
    List<Favourite> getFavourites(Long userId, List<Long> artworkIds);
    List<Favourite> getFavouritesbyuserid(Long userId);
}
