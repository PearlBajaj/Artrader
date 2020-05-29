package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Sale;

import java.util.List;

public interface SaleDao {
    void createSale(Sale sale);
    List<Sale> getSalesBySellerId(long sellerId, int offset, int size);
    long countSalesBySellerId(long sellerId);
    Sale getSale(long saleId);
    void updateSale(Sale sale);
    void deleteSale(Sale sale);
    List<Sale> selectSales(String category, List<String> keywords, int offset, int size);
    long countSales(String category, List<String> keywords);
    List<Sale> selectSales(List<Long> artworkIds);
}
