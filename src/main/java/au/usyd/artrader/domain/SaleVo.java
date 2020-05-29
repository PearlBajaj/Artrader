package au.usyd.artrader.domain;

public class SaleVo extends Sale {

    public SaleVo() {

    }

    public SaleVo(Sale sale, User artist) {
        setSale(sale);
        this.artist = artist;
    }

    public SaleVo(Sale sale, User artist, boolean isFavourite) {
        setSale(sale);
        this.artist = artist;
        this.isFavourite = isFavourite;
    }

    private User artist;

    private boolean isFavourite;

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    private void setSale(Sale sale) {
        this.setSaleId(sale.getSaleId());
        this.setSellerId(sale.getSellerId());
        this.setPrice(sale.getPrice());
        this.setCreatedTimestamp(sale.getCreatedTimestamp());
        this.setModifiedTimestamp(sale.getModifiedTimestamp());
        this.setArtwork(sale.getArtwork());
    }
}
