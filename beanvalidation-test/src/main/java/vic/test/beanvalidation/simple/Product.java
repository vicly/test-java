package vic.test.beanvalidation.simple;

import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model
 *
 * @author Vic Liu
 */
public class Product {

    @NotNull(message = "product ID may not be null")
    private UUID id;

    @NotBlank
    @Size(min = 3)
    private String name;

    @Min(1)
    private int quantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
