package vic.test.springboot.app.bookmarks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author Vic Liu
 */
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "account")
    private Set<Bookmark> bookmarks = Sets.newHashSet();

    private Account() {
        // JPA only
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }
}
