package vic.test.jpa.basic.one_entity_multipletables;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		// given data
		Date now = new Date();
		
		Person tim = new Person();
		tim.setName("Tim");
		tim.setContact("022-1234567");
		tim.setAddress("Tim's home address");
		tim.setCreatedAt(now);

		// when persist
		em.getTransaction().begin();
		em.persist(tim);
		em.getTransaction().commit();

		// then can get it back
		em.getTransaction().begin();
		tim = em.find(Person.class, tim.getId());
		em.getTransaction().commit();
		log.info(tim.toString());

		em.close();
		PersistenceManager.INSTANCE.close();

	}

}
