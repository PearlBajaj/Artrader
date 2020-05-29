package au.usyd.artrader.service;

import au.usyd.artrader.domain.*;
import au.usyd.artrader.factory.ArtworkVoFactory;
import au.usyd.artrader.factory.VoFactory;
import au.usyd.artrader.persistence.ArtworkDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service(value="artworkService")
public class ArtworkServiceImpl implements ArtworkService {

    private final static Logger logger = LoggerFactory.getLogger(ArtworkServiceImpl.class);

    @Autowired
    private ArtworkDao artworkDao;

    @Autowired
    private SaleService saleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    private VoFactory<Artwork> voFactory = new ArtworkVoFactory();

    @Override
    public void addArtwork(Artwork artwork) {

        artworkDao.createArtwork(artwork);
        saleService.sellArtwork(artwork, artwork.getArtistId(), artwork.getPrice());
    }

    @Override
    public List<Artwork> getCreations(long artistId, String category, int offset, int size) {
        List<Artwork> artworks = artworkDao.selectArtworksByArtistId(artistId, category, offset, size);
        List<Long> artworkIds = artworks.stream().map(Artwork::getArtworkId).collect(Collectors.toList());
        Map<Long, List<Transaction>> transactionsByArtworkId = transactionService.getTransactions(artworkIds).stream()
                .collect(Collectors.groupingBy(Transaction::getArtworkId));

        return artworks.stream().map(artwork -> {
            List<Transaction> transactions = transactionsByArtworkId.get(artwork.getArtworkId());
            if(CollectionUtils.isEmpty(transactions)) {
                return voFactory.get(artwork, 0.0);
            } else {
                Transaction firstTransaction = transactions.stream().reduce(BinaryOperator.minBy(Comparator.comparing(Transaction::getCreatedTimestamp))).get();
                Double totalRevenue = 0.0;
                if(!ObjectUtils.isEmpty(transactions)) {
                    totalRevenue = transactions.stream().filter(t -> t.getTransactionId() != firstTransaction.getTransactionId())
                            .map(Transaction::getPrice)
                            .reduce(firstTransaction.getPrice(), (sum, price) -> sum + price*(artwork.getProfitRate()/100));
                }
                return voFactory.get(artwork, totalRevenue);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<Artwork> getCollections(long ownerId, String category, int offset, int size) {
        List<Artwork> artworks = artworkDao.selectArtworksByOwnerId(ownerId, category, offset, size);
        List<Long> artworkIds = artworks.stream().map(Artwork::getArtworkId).collect(Collectors.toList());
        List<Sale> sales = saleService.getSales(artworkIds);
        List<Long> onSaleArtworkIds = sales.stream().map(sale -> sale.getArtwork().getArtworkId()).collect(Collectors.toList());
        return artworks.stream()
                .map(artwork -> voFactory.get(artwork, onSaleArtworkIds.contains(artwork.getArtworkId())))
                .collect(Collectors.toList());
    }

    @Override
    public long getCreationsCount(long artistId, String category) {
        return artworkDao.countArtworksByArtistId(artistId, category);
    }

    @Override
    public long getCollectionsCount(long ownerId, String category) {
        return artworkDao.countArtworksByOwnerId(ownerId, category);
    }

    @Override
    public Artwork getArtwork(long artworkId) {
        Artwork artwork = artworkDao.selectArtwork(artworkId);
        User artist = userService.getUser(artwork.getArtistId());
        return voFactory.get(artwork, artist);
    }

    @Override
    public void changeOwner(long artworkId,long ownerId) {
        Artwork artwork = artworkDao.selectArtwork(artworkId);
        artwork.setOwnerId(ownerId);
        artwork.setModifiedTimestamp(new Timestamp(System.currentTimeMillis()));
        artworkDao.updateArtwork(artwork);
    }
}
