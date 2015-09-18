package vic.test.spring.hello.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import vic.test.spring.hello.biz.User;
import vic.test.spring.hello.biz.UserRepository;

@Configuration
@ComponentScan(basePackages = {
		"vic.test.spring.hello"
})
public class AppConfig {

	@Bean
	UserRepository mockUserRepository() {
		return new UserRepository() {
			@Override
			public List<User> findAll() {
				List<User> users = new ArrayList<User>();
				users.add(new User("mockU1"));
				users.add(new User("mockU2"));
				return users;
			}
		};
	}
}
