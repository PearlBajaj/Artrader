package au.usyd.artrader.factory;

import au.usyd.artrader.domain.*;

public class ArtworkVoFactory implements VoFactory<Artwork>{
    @Override
    public Artwork get(Artwork artwork, Object... objects) {
        Object object = objects[0];
        if(object instanceof User) {
            return new ArtworkVo(artwork, (User) object);
        } else if(object instanceof Double) {
            return new CreationVo(artwork, (Double) object);
        } else if(object instanceof Boolean) {
            return new CollectionVo(artwork, (Boolean) object);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
