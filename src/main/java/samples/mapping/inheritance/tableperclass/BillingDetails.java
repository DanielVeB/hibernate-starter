package samples.mapping.inheritance.tableperclass;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {
    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    protected String owner;

    public BillingDetails() {
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }
}
