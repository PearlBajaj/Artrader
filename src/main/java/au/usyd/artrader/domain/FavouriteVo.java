package au.usyd.artrader.domain;

public class FavouriteVo extends Favourite {
    public FavouriteVo(Favourite fav, Artwork artwork, User artist){
        this.setArtworkId(fav.getArtworkId());
        this.setUserId(fav.getUserId());
        this.setCreatedTimestamp(fav.getCreatedTimestamp());
        this.artwork = artwork;
        this.artist =  artist;
    }

    private Artwork artwork;
    private User artist;
    public Artwork getArtwork(){
        return artwork;
    }
    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }
    public User getArtist() {
        return artist;
    }
    public void setArtist(User artist) {
        this.artist = artist;
    }

}
