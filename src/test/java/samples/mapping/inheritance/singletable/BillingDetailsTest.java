package samples.mapping.inheritance.singletable;

import config.DatabaseTest;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
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
    protected Metadata getMetadata(StandardServiceRegistry standardRegistry) {
        return new MetadataSources(standardRegistry)
                .addAnnotatedClass(BillingDetails.class)
                .addAnnotatedClass(CreditCard.class)
                .addAnnotatedClass(BankAccount.class)
                .getMetadataBuilder()
                .build();
    }


    @Test
    public void getAllBilingDetailsShouldReturn3Entities() {

        var details = session.createQuery("SELECT bd FROM BillingDetails bd", BillingDetails.class).getResultList();

        Assertions.assertEquals(3, details.size());

    }
}