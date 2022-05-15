package samples.mapping.subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bid {
    @Id @GeneratedValue
    private int id;
    @Column(name="item_id")
    private int itemId;
    @Column
    private int amount;

    public Bid(){}
    public Bid(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }
}
