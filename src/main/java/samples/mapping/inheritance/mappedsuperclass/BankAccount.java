package samples.mapping.inheritance.mappedsuperclass;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AttributeOverride(
        name = "owner",
        column = @Column(name = "BA_OWNER", nullable = false)
)
public class BankAccount extends BillingDetails {

    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    protected String account;

    public BankAccount(){
        super("owner");
    }

    public BankAccount(String owner) {
        super(owner);
    }
}
