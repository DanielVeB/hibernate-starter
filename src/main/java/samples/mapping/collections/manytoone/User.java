package samples.mapping.collections.manytoone;

import samples.mapping.inheritance.joinedtable.BillingDetails;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    protected BillingDetails defaultBilling;

    public User() {
    }

    public BillingDetails getDefaultBilling() {
        return defaultBilling;
    }

    public void setDefaultBilling(BillingDetails defaultBilling) {
        this.defaultBilling = defaultBilling;
    }

    public Long getId() {
        return id;
    }
}
