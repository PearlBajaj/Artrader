package au.usyd.artrader.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@IdClass(FavouriteKey.class)
public class Favourite {

    @Id
    @Column
    private long artworkId;

    @Id
    @Column
    private long userId;

    @Column
    private Timestamp createdTimestamp;

    public long getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(long artworkId) {
        this.artworkId = artworkId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}

class FavouriteKey implements Serializable {

    @Column
    private long artworkId;

    @Column
    private long userId;

    public FavouriteKey() {
    }

    public FavouriteKey(long artworkId, long userId) {
        this.artworkId = artworkId;
        this.userId = userId;
    }

    public boolean equals(Object o) {
        if (o instanceof Favourite) {
            Favourite pk = (Favourite) o;
            if (this.artworkId == pk.getArtworkId() && this.userId == (pk.getUserId())) {
                return true;
            }

        }
        return false;

    }

    public int hashCode() {
        String strArtworkId = Long.toString(this.artworkId);
        String strUserId = Long.toString(this.userId);
        String key = strArtworkId+strUserId;
        return key.hashCode();
    }
}