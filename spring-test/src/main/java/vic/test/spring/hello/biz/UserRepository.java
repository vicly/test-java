package vic.test.spring.hello.biz;

import java.util.List;

public interface UserRepository {
	List<User> findAll();
}
