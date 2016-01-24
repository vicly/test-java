package vic.test.jersey;

/**
 * Business exception
 *
 * @author Vic Liu
 */
public class TechException extends RuntimeException {

    public TechException(String message) {
        super(message);
    }

}
