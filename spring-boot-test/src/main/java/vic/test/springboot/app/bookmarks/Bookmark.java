package vic.test.springboot.app.bookmarks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Vic Liu
 */
@Entity
public class Bookmark {

    public static Bookmark from(Account account, Bookmark bookmark) {
        return new Bookmark(account, bookmark.getUri(), bookmark.getDescription());
    }

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Account account;

    private String uri;

    private String description;

    private Bookmark() {} // JPA only

    public Bookmark(Account account, String uri, String description) {
        this.account = account;
        this.uri = uri;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }
}
