package samples.mapping.subselect;

import config.DatabaseTest;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemBidSummaryTest extends DatabaseTest {

    @Override
    protected void loadData() {
        Bid bid = new Bid(1, 10);
        Item item = new Item(1, "item1");
        Item item2 = new Item(2, "item2");

        session.persist(bid);
        session.persist(item);
        session.persist(item2);
    }

    @Override
    protected MetadataSources addAnnotatedClasses(MetadataSources sources) {
        return sources
                .addAnnotatedClass(Bid.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(ItemBidSummary.class);
    }


    @Test
    void testSubselectMapping_ShouldReturnItemBidSummaryWithOneBids() {

        ItemBidSummary itemBidSummary = session.find(ItemBidSummary.class, 1);

        Assertions.assertEquals(1, itemBidSummary.getItemId());
        Assertions.assertEquals(1, itemBidSummary.getNumberOfBids());
        Assertions.assertEquals("item1", itemBidSummary.getName());
    }

    @Test
    void testSynchronizeSummaryWhenChangingItemName() {

        var newItemName = "new item2 name";
        Bid bid = new Bid(2, 10);

        session.persist(bid);

        Item item = session.find(Item.class, 2);
        item.setName(newItemName);

        session.persist(item);
        session.flush();

        ItemBidSummary itemBidSummary = session.find(ItemBidSummary.class, 2);

        Assertions.assertEquals(newItemName, itemBidSummary.getName());

    }
}