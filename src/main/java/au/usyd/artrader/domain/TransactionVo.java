package au.usyd.artrader.domain;

public class TransactionVo extends Transaction {

    public TransactionVo() {

    }

    public TransactionVo(Transaction transaction, User artist, User seller, User buyer, Artwork artwork) {
        this.setTransactionId(transaction.getTransactionId());
        this.setArtworkId(transaction.getArtworkId());
        this.setBuyerId(transaction.getBuyerId());
        this.setAddress(transaction.getAddress());
        this.setPrice(transaction.getPrice());
        this.setMobileNumber(transaction.getMobileNumber());
        this.setSellerId(transaction.getSellerId());
        this.setComment(transaction.getComment());
        this.setShipping(transaction.isShipping());
        this.setCreatedTimestamp(transaction.getCreatedTimestamp());
        this.artist = artist;
        this.seller = seller;
        this.buyer = buyer;
        this.artwork = artwork;
    }

    private User artist;
    private User seller;
    private User buyer;
    private Artwork artwork;

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }
}
