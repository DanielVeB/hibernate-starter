package samples.mapping.subselect;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Immutable
@Subselect(value = "SELECT " +
        "i.ID as ITEMID, i.NAME, count(b.ID) as NUMBEROFBIDS " +
        "FROM Item i " +
        "LEFT OUTER JOIN BID b " +
        "ON i.ID = b.ITEM_ID " +
        "GROUP BY i.ID, i.NAME")
@Synchronize({"item", "bid"})
public class ItemBidSummary {

    @Id
    protected int itemId;

    protected String name;

    protected long numberOfBids;

    public ItemBidSummary() {
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public long getNumberOfBids() {
        return numberOfBids;
    }
}
