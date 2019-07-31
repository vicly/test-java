package vic.test.springboot.app.jersey;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * @author Vic Liu
 */
@Repository
public class UserRepository {
    private Map<String, User> db = Maps.newHashMap();

    public UserRepository() {
        this.db.put("vic", new User("vic", "Vic Liu"));
    }

    public Collection<User> findAllUsers() {
        return this.db.values();
    }

    public void addUser(User user) {
        this.db.put(user.getId(), user);
    }

    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(this.db.get(id));
    }

}
