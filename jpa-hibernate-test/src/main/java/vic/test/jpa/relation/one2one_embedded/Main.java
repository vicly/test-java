package vic.test.jpa.relation.one2one_embedded;

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
		Contact c = new Contact();
		c.setCreatedAt(now);
		
		Address work = new Address();
		work.setZip("z001");
		work.setAddress("work address");
		c.setWorkAddress(work);

		Address home = new Address();
		home.setZip("z002");
		home.setAddress("home address");
		c.setHomeAddress(home);
	
		// when persist
		em.getTransaction().begin();
		/*
		insert into Contact (id, created_at, home_address, home_zip, work_address, work_zip) 
		values (default, '2015-09-17 09:51:10', 'home address', 'z002', 'work address', 'z001')
		 */
		em.persist(c);
		em.getTransaction().commit();

		// then can get it back
		em.getTransaction().begin();
		c = em.find(Contact.class, c.getId());
		
		em.getTransaction().commit();
		
		log.info("Address: id={}, home={}, work={}", c.getId(), c.getHomeAddress(), c.getWorkAddress());

		em.close();
		PersistenceManager.INSTANCE.close();

	}

}
