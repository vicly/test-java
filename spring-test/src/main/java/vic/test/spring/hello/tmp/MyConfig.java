package vic.test.spring.hello.tmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vic Liu
 */
@Configuration
public class MyConfig {
    @Bean
    public BeanA beanA() {
        return new BeanA(beanB());
    }

/*
    // Event no Autowired, Spring will resolve b by type
    // @Autowired
    @Bean
    public BeanA beanA(BeanB b) {
        return new BeanA(b);
    }
*/

    @Bean
    public BeanB beanB() {
        return new BeanB();
    }
}
