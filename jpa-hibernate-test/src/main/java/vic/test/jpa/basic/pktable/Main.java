package vic.test.jpa.basic.pktable;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Harddisk hd = new Harddisk();
		hd.setName("Sumsung HDD 5400");
		
		VedioCard vc = new VedioCard();
		vc.setName("WooDoo");
		
		em.getTransaction().begin();
		em.persist(hd);
		em.persist(vc);
		em.getTransaction().commit();

		em.clear();
		
		em.getTransaction().begin();
		hd = em.find(Harddisk.class, hd.getId());
		vc = em.find(VedioCard.class, vc.getId());
		em.getTransaction().commit();
		log.info(hd.toString());
		log.info(vc.toString());
		
		em.close();
		PersistenceManager.INSTANCE.close();

	}

}
