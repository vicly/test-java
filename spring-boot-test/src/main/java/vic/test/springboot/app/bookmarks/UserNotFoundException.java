package vic.test.springboot.app.bookmarks;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("could not find user " + username);
    }
}
