package vic.test.jpa.relation.one2one;

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
		AccountName name = new AccountName()
			.setFirstName("Vic")
			.setLastName("Liu");
		name.setCreatedAt(now);
		
		Account account = new Account().setAccountNumber("1001-1001");
		account.setCreatedAt(now);

		AccountProfile profile = new AccountProfile().setNickname("VL");
		profile.setCreatedAt(now);
		// and relationship
		account.setName(name);
		account.setProfile(profile);

		// when persist
		em.getTransaction().begin();
		em.persist(profile);
		em.persist(account);
		em.getTransaction().commit();

		// then can get it back
		em.clear();
		em.getTransaction().begin();
		account = em.find(Account.class, account.getId());
		em.getTransaction().commit();
		
		log.info(account.toString());
		log.info("" + account.getName());
		log.info("" + account.getProfile());

		em.close();
		PersistenceManager.INSTANCE.close();

	}

}
