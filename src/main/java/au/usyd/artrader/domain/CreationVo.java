package au.usyd.artrader.domain;

public class CreationVo extends Artwork {

    public CreationVo() {

    }

    public CreationVo(Artwork artwork, Double totalRevenue) {
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
        this.totalRevenue = totalRevenue;
    }

    private double totalRevenue;

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
