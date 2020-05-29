package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Transaction;

import java.util.List;

public interface TransactionDao {
    void createTransaction(Transaction transaction);
    List<Transaction> selectTransactions(List<Long> artworkIds);
    List<Transaction> selectTransactionsByBuyerId(Long buyerId);
    List<Transaction> selectTransactionsBySellerId(Long sellerId);
    Transaction selectTransaction(Long transactionId);
    void updateTransaction(Transaction transaction);
}
