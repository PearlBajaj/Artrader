package au.usyd.artrader.domain;

public class ArtworkVo extends Artwork {

    public ArtworkVo() {
    }

    public ArtworkVo(Artwork artwork, User artist) {
        this.setArtworkId(artwork.getArtworkId());
        this.setArtistId(artwork.getArtistId());
        this.setOwnerId(artwork.getOwnerId());
        this.setDate(artwork.getDate());
        this.setName(artwork.getName());
        this.setCategory(artwork.getCategory());
        this.setProfitRate(artwork.getProfitRate());
        this.setDescription(artwork.getDescription());
        this.setPhoto(artwork.getPhoto());
        this.setCreatedTimestamp(artwork.getCreatedTimestamp());
        this.setModifiedTimestamp(artwork.getModifiedTimestamp());
        this.artist = artist;
    }


    private User artist;

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }


}
