package vic.test.jerseyswagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Vic Liu
 */
public enum UserType {
    STANDARD, VIP;

    @JsonValue
    public String value() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static UserType fromValue(String value) {
        for (UserType type : UserType.values()) {
            if (type.name().toLowerCase().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown UserType value: " + value);
    }
}
