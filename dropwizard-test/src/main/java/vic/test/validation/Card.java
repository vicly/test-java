package vic.test.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Vic Liu
 */
public class Card {

    @NotNull(groups = CardPayment.class)
    @Size(max = 5, groups = CardPayment.class)
    @Pattern(regexp = "^[0-9]+$", groups = CardPayment.class)
    private String number;

    @NotBlank(message = "card holder is invalid")
    private String holder;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
