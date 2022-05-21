package samples.mapping.inheritance.tableperclass;

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
                new CreditCard(),
                new BankAccount(),
                new BankAccount());
        details.forEach(session::persist);
    }

    @Override
    protected MetadataSources addAnnotatedClasses(MetadataSources sources) {
        return sources.addAnnotatedClass(BillingDetails.class)
                .addAnnotatedClass(CreditCard.class)
                .addAnnotatedClass(BankAccount.class);
    }

    @Test
    public void getAllBilingDetailsShouldReturn3Entities() {

        //GET all billing details using UNION
        var details = session.createQuery("SELECT bd FROM BillingDetails bd", BillingDetails.class).getResultList();

        Assertions.assertEquals(3, details.size());

    }
}