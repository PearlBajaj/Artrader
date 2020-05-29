package au.usyd.artrader.factory;

import au.usyd.artrader.domain.Sale;
import au.usyd.artrader.domain.SaleVo;
import au.usyd.artrader.domain.User;

public class SaleVoFactory implements VoFactory<Sale> {
    @Override
    public Sale get(Sale sale, Object... objects) {
        if(objects[0] instanceof User && objects.length == 1) {
            return new SaleVo(sale, (User)objects[0]);
        }
        if(objects[0] instanceof User && objects[1] instanceof Boolean) {
            return new SaleVo(sale, (User)objects[0], (Boolean)objects[1]);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
