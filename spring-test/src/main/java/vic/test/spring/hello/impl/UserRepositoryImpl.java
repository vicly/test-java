package vic.test.spring.hello.impl;

import java.util.ArrayList;
import java.util.List;

import vic.test.spring.hello.biz.User;
import vic.test.spring.hello.biz.UserRepository;

public class UserRepositoryImpl implements UserRepository {

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		users.add(new User("vic"));
		users.add(new User("lei"));
		return users;
	}

}
