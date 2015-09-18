package vic.test.jpa.basic.date_enum_converter;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

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
		PersistenceManager.INSTANCE.close();

	}

}
