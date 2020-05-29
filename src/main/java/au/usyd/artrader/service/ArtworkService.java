package au.usyd.artrader.service;

import au.usyd.artrader.domain.Artwork;

import java.util.List;

public interface ArtworkService {
    void addArtwork(Artwork artwork);
    List<Artwork> getCreations(long artistId, String category, int offset, int size);
    List<Artwork> getCollections(long ownerId, String category, int offset, int size);
    long getCreationsCount(long artistId, String category);
    long getCollectionsCount(long ownerId, String category);
    Artwork getArtwork(long artworkId);
    void changeOwner(long artworkId, long ownerId);
}
