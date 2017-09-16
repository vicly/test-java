package vic.test.dropwizard.inputcheck;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

/**
 * Api model.
 * @author Vic Liu
 */
public class User {

    private String id;

    private String name;

    @NotNull
    private Integer age;

    @NotNull(message = "INVALID_TS")
    private Instant ts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Instant getTs() {
        return ts;
    }

    public void setTs(Instant ts) {
        this.ts = ts;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
    }
}
