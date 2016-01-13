package vic.test.jpa.basic.date_enum_converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.Date;

import javax.persistence.EntityManager;

import vic.test.jpa.PersistenceManager;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Vic Liu
 */
public class DataEnumConverterTest {

    private static final Logger log = getLogger(DataEnumConverterTest.class);

    private EntityManager em;

    @Before
    public void beforeEachTest() {
        this.em = PersistenceManager.INSTANCE.getEntityManager();

    }

    @After
    public void afterEachTest() {
        PersistenceManager.INSTANCE.close();
    }

    @Test
    public void doIt() {

        Date now = new Date();
        Toy t1 = new Toy();
        t1.setBoughtAt(now);
        t1.setCreatedAt(now);
        t1.setHealthyType(ToyHealthyType.GOOD);
        t1.setMaterialType(ToyMaterialType.WOOD);
        t1.setType(ToyType.SIX_MONTHS_AND_MORE);

        em.getTransaction().begin();
        em.persist(t1);
        em.getTransaction().commit();

        em.clear();

        em.getTransaction().begin();
        Toy t = em.find(Toy.class, t1.getId());
        em.getTransaction().commit();
        log.info(t.toString());

        em.close();
    }

}
