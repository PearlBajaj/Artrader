package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Artwork;

import java.util.List;

public interface ArtworkDao {
    void createArtwork(Artwork artwork);
    List<Artwork> selectArtworksByArtistId(long artistId, String category, int offset, int size);
    List<Artwork> selectArtworksByOwnerId(long ownerId, String category, int offset, int size);
    long countArtworksByArtistId(long artistId, String category);
    long countArtworksByOwnerId(long ownerId, String category);
    Artwork selectArtwork(long artworkId);
    void updateArtwork(Artwork artwork);
}
