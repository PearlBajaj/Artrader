package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Transaction;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    private static final Logger logger = LoggerFactory.getLogger(TransactionDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createTransaction(Transaction transaction) {
        Timestamp createdTimestamp = new Timestamp(System.currentTimeMillis());
        transaction.setCreatedTimestamp(createdTimestamp);
        sessionFactory.getCurrentSession().save(transaction);
    }

    @Override
    public List<Transaction> selectTransactions(List<Long> artworkIds) {
        if(ObjectUtils.isEmpty(artworkIds)) {
            return Collections.emptyList();
        }
        return sessionFactory.getCurrentSession().createQuery("FROM Transaction WHERE artworkId IN (:artworkIds) ")
                .setParameterList("artworkIds", artworkIds)
                .list();
    }

    @Override
    public List<Transaction> selectTransactionsByBuyerId(Long buyerId) {
        if(ObjectUtils.isEmpty(buyerId)) {
            return Collections.emptyList();
        }
        return sessionFactory.getCurrentSession().createQuery("FROM Transaction WHERE buyerId = :buyerId" +
                " order by createdTimestamp desc")
                .setParameter("buyerId",buyerId)
                .list();
    }

    @Override
    public List<Transaction> selectTransactionsBySellerId(Long sellerId) {
        if(ObjectUtils.isEmpty(sellerId)) {
            return Collections.emptyList();
        }
        return sessionFactory.getCurrentSession().createQuery("FROM Transaction WHERE sellerId = :sellerId and isShipping = false" +
                " order by createdTimestamp desc")
                .setParameter("sellerId",sellerId)
                .list();
    }

    @Override
    public Transaction selectTransaction(Long transactionId) {
        return (Transaction) sessionFactory.getCurrentSession().get(Transaction.class, transactionId);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().update(transaction);
    }
}
