package samples.mapping.inheritance.mappedsuperclass;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AttributeOverride(
        name = "owner",
        column = @Column(name = "CC_OWNER", nullable = false)
)
public class CreditCard extends BillingDetails{

    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    protected String cardNumber;

    public CreditCard(){
        super("owner");
    }
    public CreditCard(String owner) {
        super(owner);
    }

    public CreditCard(String owner, String cardNumber) {
        super(owner);
        this.cardNumber = cardNumber;
    }
}
