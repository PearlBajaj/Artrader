package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Favourite;

import java.util.List;

public interface FavouriteDao {
    void createFavourite(Favourite favourite);
    void deleteFavourite(Favourite favourite);
    List<Favourite> selectFavourites(Long userId, List<Long> artworkIds);
    Favourite selectFavourite(Long userId, Long artworkId);
    List<Favourite> selectFavouritesByUserId(Long Uid);
}
