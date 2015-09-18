package vic.test.jpa.relation.one2many;

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
		Member vic = new Member();
		vic.setName("Vic");
		vic.setCreatedAt(now);
		Member tom = new Member();
		tom.setName("Tom");
		tom.setCreatedAt(now);
		Member jordan = new Member();
		jordan.setName("Jordan");
		jordan.setCreatedAt(now);
		
		GroupAttribute maxMember = new GroupAttribute();
		maxMember.setValue("20");
		maxMember.setName("maxMember");
		maxMember.setCreatedAt(now);
		GroupAttribute minMember = new GroupAttribute();
		minMember.setValue("5");
		minMember.setName("maxMember");
		minMember.setCreatedAt(now);
		Group java = new Group();
		java.setName("Java");
		java.setCreatedAt(now);
		Group unix = new Group();
		unix.setName("Unix");
		unix.setCreatedAt(now);
		
		java.getMembers().add(vic);
		java.getMembers().add(tom);
		java.getAttributes().add(minMember);
		java.getAttributes().add(maxMember);
		unix.getMembers().add(jordan);
		
		log.info("Persisting groups");
		em.getTransaction().begin();
		em.persist(java);
		em.persist(unix);
		em.getTransaction().commit();

		log.info("Retrieving groups");
		em.getTransaction().begin();
		TypedQuery<Group> findAllGroups = em.createQuery("SELECT g FROM Group g", Group.class);
		for (Group g : findAllGroups.getResultList()) {
			log.info(g.toString());
			for (GroupAttribute attr : g.getAttributes()) {
				log.info("\t{}", attr);
			}
			for (Member m : g.getMembers()) {
				log.info("\t{}", m);
			}
		}
		em.getTransaction().commit();
		
		em.clear(); // cause SELECT
		log.info("Retrieving members");
		for (Member m : em.createQuery("SELECT m FROM Member m", Member.class).getResultList()) {
			log.info(m.toString());
			log.info("\t{}", m.getGroup());
		}

		em.close();
		PersistenceManager.INSTANCE.close();
		
	}

}
