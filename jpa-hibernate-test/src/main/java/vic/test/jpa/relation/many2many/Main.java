package vic.test.jpa.relation.many2many;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Date now = new Date();
		
		UserType lead = new UserType();
		lead.setCreatedAt(now);
		lead.setName("Team Lead");
		lead.setCreatedAt(now);
		
		UserType member = new UserType();
		member.setCreatedAt(now);
		member.setName("Team Member");
		member.setCreatedAt(now);
		
		UserType permanent = new UserType();
		permanent.setCreatedAt(now);
		permanent.setName("Permanent");
		permanent.setCreatedAt(now);
		
		UserType contractor = new UserType();
		contractor.setCreatedAt(now);
		contractor.setName("Contractor");
		contractor.setCreatedAt(now);
		
		Role dev = new Role();
		dev.setName("Developer");
		dev.setCreatedAt(now);
		
		Role dba = new Role();
		dba.setName("DBA");
		dba.setCreatedAt(now);
		
		Role pm = new Role();
		pm.setName("PM");
		pm.setCreatedAt(now);
		
		
		User vic = new User();
		vic.setCreatedAt(now);
		vic.setName("Vic");

		User david = new User();
		david.setCreatedAt(now);
		david.setName("David");
		
		vic.getTypes().add(permanent);
		vic.getTypes().add(lead);
		vic.getRoles().add(pm);
		vic.getRoles().add(dev);
		pm.getUsers().add(vic);
		dev.getUsers().add(vic);
		
		david.getTypes().add(contractor);
		david.getTypes().add(member);
		david.getRoles().add(dev);
		david.getRoles().add(dba);
		dev.getUsers().add(david);
		dba.getUsers().add(david);
		
		
		
		log.info("Persisting");
		em.getTransaction().begin();
		
		em.persist(permanent);
		em.persist(contractor);
		em.persist(lead);
		em.persist(member);
		
		em.persist(pm);
		em.persist(dev);
		em.persist(dba);
		
		em.persist(vic);
		em.persist(david);
		em.getTransaction().commit();

		log.info("Retrieving users");
		//em.clear();
		em.getTransaction().begin();
		TypedQuery<User> findAllGroups = em.createQuery("SELECT g FROM User g", User.class);
		for (User u : findAllGroups.getResultList()) {
			log.info(u.toString());
			for (Role r : u.getRoles()) {
				log.info("\t{}", r);
			}
			for (UserType t : u.getTypes()) {
				log.info("\t{}", t);
			}
		}
		em.getTransaction().commit();
		

		log.info("Retrieving roles");
		//em.clear();
		em.getTransaction().begin();
		for (Role r : em.createQuery("SELECT r FROM Role r", Role.class).getResultList()) {
			log.info(r.toString());
			for (User u : r.getUsers()) {
				log.info("\t{}", u);
			}
		}
		em.getTransaction().commit();
		
		
		em.close();
		PersistenceManager.INSTANCE.close();
	}

}
