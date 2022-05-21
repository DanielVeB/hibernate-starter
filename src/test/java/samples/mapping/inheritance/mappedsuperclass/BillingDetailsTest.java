package samples.mapping.inheritance.mappedsuperclass;

import config.DatabaseTest;
import org.hibernate.boot.MetadataSources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BillingDetailsTest extends DatabaseTest {

    @Override
    protected void loadData() {
        List<BillingDetails> details = Arrays.asList(
                new CreditCard("owner1", "card1"),
                new BankAccount("owner1"),
                new BankAccount("owner1"));
        details.forEach(session::persist);
    }

    @Override
    protected MetadataSources addAnnotatedClasses(MetadataSources sources) {
        return sources
                .addAnnotatedClass(BillingDetails.class)
                .addAnnotatedClass(CreditCard.class)
                .addAnnotatedClass(BankAccount.class);
    }

    @Test
    public void getBillingDetailsSeparatelyAccountsAndCards() {

        // Impossible Cannot use polymorphic query to get all biling details
        // var details = session.createQuery("SELECT bd FROM BillingDetails bd", BillingDetails.class).getResultList();

        var accounts = session.createQuery("SELECT a FROM BankAccount a",BankAccount.class).getResultList();
        Assertions.assertEquals(2,accounts.size());

        var cards = session.createQuery("SELECT c FROM CreditCard c",CreditCard.class).getResultList();
        Assertions.assertEquals(1,cards.size());

    }
}