package vic.test.beanvalidation.simple;

import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Vic Liu
 */
public class Main {

    public static void main(String[] args) {

        // init
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        // run
        Product good = new Product();
        good.setName("TV");
        good.setId(UUID.randomUUID());
        good.setQuantity(10);

        Set<ConstraintViolation<Product>> violations = validator.validate(good);
        System.out.println("No violations: " + (violations.isEmpty()));

        System.out.println("\n\n====> Bad1 \n");
        Product bad1 = new Product();
        bad1.setName("    ");
        violations = validator.validate(bad1);
        for (ConstraintViolation<Product> violation : violations) {
            System.out.println(violation.getMessage() + ": invalidValue=" + violation.getInvalidValue());
        }

        System.out.println("\n\n====> Bad2 \n");
        Product bad2 = new Product();
        bad2.setName("ab");
        bad2.setQuantity(-4);
        violations = validator.validate(bad2);
        for (ConstraintViolation<Product> violation : violations) {
            System.out.println(violation.getMessage() + ": invalidValue=" + violation.getInvalidValue());
        }

    }

}
