package vic.test.validation;

import io.dropwizard.jersey.validation.Validators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Vic Liu
 */
public class Main {
    public static void main(String[] args) {
        Validator validator = Validators.newValidator();

        Order order = new Order();

        order.setType("card");

        Card card = new Card();
        card.setNumber("G4001010101010");
        card.setHolder("Vic Liu");
        order.setCard(card);

        order.setType("voucher");
//        order.setVoucherNumber("V001");


        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        if (violations.isEmpty()) {
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAILED:");
            violations.stream().forEach(violation -> {
                System.out.format("%s = %s", violation.getPropertyPath(), violation.getMessage());
                System.out.println("\t" + violation);
            });
        }

    }
}
