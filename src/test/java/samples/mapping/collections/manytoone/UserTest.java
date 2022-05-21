package samples.mapping.collections.manytoone;

import config.DatabaseTest;
import org.hibernate.boot.MetadataSources;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import samples.mapping.inheritance.joinedtable.BillingDetails;
import samples.mapping.inheritance.joinedtable.CreditCard;

class UserTest extends DatabaseTest {

    @Override
    protected void loadData() {
        CreditCard cc = new CreditCard();
        cc.setCardNumber("card");
        cc.setOwner("owner");
        User johndoe = new User();
        johndoe.setDefaultBilling(cc);
        session.persist(cc);
        session.persist(johndoe);

    }

    @Override
    protected MetadataSources addAnnotatedClasses(MetadataSources sources) {
        return sources
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(BillingDetails.class)
                .addAnnotatedClass(CreditCard.class);
    }

    @Test
    public void testGetDefaultBillingAsHibernateProxy() {
        User user = session.find(User.class, 2L);
        BillingDetails defaultBilling = user.getDefaultBilling();

        Assertions.assertTrue(defaultBilling instanceof HibernateProxy);
    }

    @Test
    public void shouldThrowClassCastExceptionWhenTryingToCastLazyLoadedObject() {
        User user = session.find(User.class, 2L);
        BillingDetails bd = user.getDefaultBilling();

        Assertions.assertFalse(bd instanceof CreditCard);
        Assertions.assertThrows(ClassCastException.class, () -> {
            CreditCard creditCard = (CreditCard) bd;
        });
    }

    @Test
    public void shouldBeCreditCardInstanceWhenEagerFetchQuering() {
        User user = (User) session.createQuery(
                        "select u from User u " +
                                "left join fetch u.defaultBilling " +
                                "where u.id = :id")
                .setParameter("id", 2L)
                .getSingleResult();

        BillingDetails bd = user.getDefaultBilling();

        Assertions.assertTrue(bd instanceof CreditCard);
    }
}