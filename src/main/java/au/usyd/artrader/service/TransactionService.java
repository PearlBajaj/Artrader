package au.usyd.artrader.service;

import au.usyd.artrader.domain.Transaction;

import java.util.List;

public interface TransactionService {
    void buyArtwork(long saleId, long buyerId);
    Transaction getTransaction(Long transactionId);
    List<Transaction> getTransactions(List<Long> artworkIds);
    List<Transaction> getTransactionsByBuyerId(Long buyerId);
    List<Transaction> getTransactionsBySellerId(Long sellerId);
    Transaction getShipping(long transactionId);
    void completeToShip(long transactionId);
}
