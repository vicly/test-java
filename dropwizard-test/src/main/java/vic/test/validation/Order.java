package vic.test.validation;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Vic Liu
 */
@GroupSequenceProvider(OrderGroupSequenceProvider.class)
public class Order {

    @NotBlank(message = "type must not be blank")
    private String type;

    // trigger card validation
    @Valid
    // validate card is not null only
    @NotNull(message = "card is invalid", groups = CardPayment.class)
    private Card card;

    @NotBlank(message = "voucherNumber is invalid", groups = VoucherPayment.class)
    private String voucherNumber;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }
}
