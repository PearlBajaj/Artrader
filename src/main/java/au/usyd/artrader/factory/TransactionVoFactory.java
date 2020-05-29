package au.usyd.artrader.factory;

import au.usyd.artrader.domain.Artwork;
import au.usyd.artrader.domain.Transaction;
import au.usyd.artrader.domain.TransactionVo;
import au.usyd.artrader.domain.User;

public class TransactionVoFactory implements VoFactory<Transaction> {
    @Override
    public Transaction get(Transaction transaction, Object... objects) {
        if(objects[0] instanceof User
                && objects[1] instanceof User
                && objects[2] instanceof User
                && objects[3] instanceof Artwork) {
            return new TransactionVo(transaction, (User)objects[0], (User)objects[1], (User)objects[2], (Artwork)objects[3]);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
