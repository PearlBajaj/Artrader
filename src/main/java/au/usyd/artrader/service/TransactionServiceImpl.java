package au.usyd.artrader.service;

import au.usyd.artrader.domain.*;
import au.usyd.artrader.persistence.ArtworkDao;
import au.usyd.artrader.factory.TransactionVoFactory;
import au.usyd.artrader.factory.VoFactory;
import au.usyd.artrader.persistence.TransactionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private SaleService saleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArtworkService artworkService;

    private VoFactory<Transaction> voFactory = new TransactionVoFactory();

    @Autowired
    private ArtworkDao artworkDao;

    @Override
    public void buyArtwork(long saleId, long buyerId) {
        Sale sale = saleService.getSale(saleId);
        User buyer = userService.getUser(buyerId);

        Transaction transaction = new Transaction();
        transaction.setArtworkId(sale.getArtwork().getArtworkId());
        transaction.setSellerId(sale.getSellerId());
        transaction.setPrice(sale.getPrice());
        transaction.setBuyerId(buyerId);
        transaction.setMobileNumber(buyer.getMobileNumber());
        transaction.setAddress(buyer.getAddress());
        transaction.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));

        transactionDao.createTransaction(transaction);
        saleService.stopSelling(saleId);
        artworkService.changeOwner(sale.getArtwork().getArtworkId(), buyerId);
    }

    @Override
    public List<Transaction> getTransactions(List<Long> artworkIds) {
        return transactionDao.selectTransactions(artworkIds);
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        return transactionDao.selectTransaction(transactionId);
    }

    @Override
    public List<Transaction> getTransactionsByBuyerId(Long buyerId) {
        return transactionDao.selectTransactionsByBuyerId(buyerId);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(Long sellerId) {
        return transactionDao.selectTransactionsBySellerId(sellerId);
    }

    @Override
    public Transaction getShipping(long transactionId) {
        Transaction transaction = transactionDao.selectTransaction(transactionId);
        if(transaction == null) {
            return null;
        }
        Artwork artwork = artworkService.getArtwork(transaction.getArtworkId());
        User artist = userService.getUser(artwork.getArtistId());
        User seller = userService.getUser(transaction.getSellerId());
        User buyer = userService.getUser(transaction.getBuyerId());
        return voFactory.get(transaction, artist, seller, buyer, artwork);
    }

    @Override
    public void completeToShip(long transactionId) {
        Transaction transaction = transactionDao.selectTransaction(transactionId);
        transaction.setShipping(true);
        transactionDao.updateTransaction(transaction);
    }
}
