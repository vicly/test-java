package vic.test.springboot.app.bookmarks;

public class BookmarkNotFoundException extends RuntimeException {
    public BookmarkNotFoundException(Long userId) {
        super("could not find bookmark " + userId);
    }
}
