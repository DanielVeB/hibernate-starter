package samples.mapping.inheritance.mappedsuperclass;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BillingDetails {

    @NotNull
    protected String owner;

    public String getOwner() {
        return owner;
    }


    public BillingDetails(String owner) {
        this.owner = owner;
    }
}
