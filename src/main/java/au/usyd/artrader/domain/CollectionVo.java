package au.usyd.artrader.domain;

public class CollectionVo extends Artwork {

    public CollectionVo() {

    }

    public CollectionVo(Artwork artwork, boolean isOnSale) {
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
        this.isOnSale = isOnSale;
    }

    private boolean isOnSale;

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }
}
