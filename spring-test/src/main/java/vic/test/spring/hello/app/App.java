package vic.test.spring.hello.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import vic.test.spring.hello.biz.User;
import vic.test.spring.hello.biz.UserService;

public class App {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		for (User user : userService.findAll()) {
			System.out.println(user.getName());
		}
	}
	
}
