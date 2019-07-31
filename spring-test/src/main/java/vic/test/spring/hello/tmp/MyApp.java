package vic.test.spring.hello.tmp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Vic Liu
 */
public class MyApp {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        BeanA a = context.getBean(BeanA.class);
        BeanB b = context.getBean(BeanB.class);

        System.out.println(a);
        // same b instance, and "new B()" only once
        System.out.println(a.getB());
        System.out.println(b);

    }
}
