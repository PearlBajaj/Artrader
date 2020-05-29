package au.usyd.artrader.service;

import au.usyd.artrader.domain.*;
import au.usyd.artrader.factory.SaleVoFactory;
import au.usyd.artrader.factory.VoFactory;
import au.usyd.artrader.persistence.SaleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private FavouriteService favouriteService;

    private VoFactory<Sale> voFactory = new SaleVoFactory();

    @Override
    public void sellArtwork(Artwork artwork, long sellerId, double price) {
        Sale sale = new Sale();
        sale.setSellerId(sellerId);
        sale.setArtwork(artwork);
        sale.setPrice(price);

        saleDao.createSale(sale);
    }

    @Override
    public List<Sale> getForSale(long sellerId, int offset, int size) {
        List<Sale> sales = saleDao.getSalesBySellerId(sellerId, offset, size);
        return getSaleVos(sales, sellerId);
    }

    @Override
    public long getForSaleCount(long sellerId) {
        return saleDao.countSalesBySellerId(sellerId);
    }

    @Override
    public Sale getSale(long saleId) {
        Sale sale = saleDao.getSale(saleId);
        User artist = userService.getUser(sale.getArtwork().getArtistId());
        return voFactory.get(sale, artist);
    }

    @Override
    public void changePrice(long saleId, double price) {
        Sale sale = saleDao.getSale(saleId);
        sale.setPrice(price);
        sale.setModifiedTimestamp(new Timestamp(System.currentTimeMillis()));
        saleDao.updateSale(sale);
    }

    @Override
    public void stopSelling(long saleId) {
        Sale sale = new Sale();
        sale.setSaleId(saleId);
        saleDao.deleteSale(sale);
    }

    @Override
    public List<Sale> getSales(Long loginUserId, String category, List<String> keywords, int offset, int size) {
        List<Sale> sales = saleDao.selectSales(category, keywords, offset, size);
        return getSaleVos(sales, loginUserId);
    }

    @Override
    public long getSalesCount(String category, List<String> keywords) {
        return saleDao.countSales(category, keywords);
    }

    private List<Sale> getSaleVos(List<Sale> sales, Long loginUserId) {
        List<Long> artistIds = sales.stream().map(sale -> sale.getArtwork().getArtistId()).distinct().collect(Collectors.toList());
        List<User> users = userService.getUsers(artistIds);
        Map<Long, User> userByArtistId = users.stream().collect(Collectors.toMap(User::getUserId, user -> user));

        List<Long> artworkIds = sales.stream().map(sale -> sale.getArtwork().getArtworkId()).collect(Collectors.toList());
        List<Favourite> favourites = Collections.EMPTY_LIST;
        if(loginUserId != null) {
            favourites = favouriteService.getFavourites(loginUserId, artworkIds);
        }
        List<Long> favouriteArtworkIds = favourites.stream().map(Favourite::getArtworkId).collect(Collectors.toList());

        return sales.stream()
                .map(sale -> {
                    User artist = userByArtistId.get(sale.getArtwork().getArtistId());
                    boolean isFavourite = favouriteArtworkIds.contains(sale.getArtwork().getArtworkId());
                    return voFactory.get(sale, artist, isFavourite);
                })
                .sorted(Comparator.comparing(Sale::getModifiedTimestamp).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Sale> getSales(List<Long> artworkIds) {
        if(ObjectUtils.isEmpty(artworkIds)) {
            return Collections.emptyList();
        }
        return saleDao.selectSales(artworkIds);
    }
}
