package au.usyd.artrader.service;

import au.usyd.artrader.domain.Artwork;
import au.usyd.artrader.domain.Sale;

import java.util.List;

public interface SaleService {
    void sellArtwork(Artwork artwork, long sellerId, double price);
    List<Sale> getForSale(long sellerId, int offset, int size);
    long getForSaleCount(long sellerId);
    Sale getSale(long saleId);
    void changePrice(long saleId, double price);
    void stopSelling(long saleId);
    List<Sale> getSales(Long loginUserId, String category, List<String> keywords, int offset, int size);
    long getSalesCount(String category, List<String> keywords);
    List<Sale> getSales(List<Long> artworkIds);
}
