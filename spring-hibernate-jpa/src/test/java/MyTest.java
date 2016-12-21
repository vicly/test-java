import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import vic.test.jpa.springhibernate.PersistenceConfig;
import vic.test.jpa.springhibernate.User;

/**
 * @author Vic Liu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class, loader = AnnotationConfigContextLoader.class)
//@TransactionConfiguration(defaultRollback = true)
@Transactional
public class MyTest {

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testIt() {

        User user = new User();
        user.setName("Vic");

        em.persist(user);
        em.flush();
        System.out.println(">>> persist: " + user.getId());


        System.out.println(">>> Found: " + em.find(User.class, user.getId()).getName());



    }

}
