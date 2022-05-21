package samples.mapping.inheritance.onetomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    protected Long id;

    @OneToMany(fetch = FetchType.LAZY)
    protected Set<BillingDetails> defaultBilling;

    public Set<BillingDetails> getDefaultBilling() {
        return defaultBilling;
    }

    public void setDefaultBilling(Set<BillingDetails> defaultBilling) {
        this.defaultBilling = defaultBilling;
    }
}
