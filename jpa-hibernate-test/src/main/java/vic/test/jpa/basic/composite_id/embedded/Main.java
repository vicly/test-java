package vic.test.jpa.basic.composite_id.embedded;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Car2 car = new Car2();
		CarPK2 pk = new CarPK2();
		pk.setChassisSerialNumber("9BW DA05X6 1 T050136");
        pk.setEngineSerialNumber("ABC123");
        car.setPk(pk);
        car.setName("Thunder");
        
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();

		em.clear();
		
		em.getTransaction().begin();
		pk = new CarPK2();
		pk.setChassisSerialNumber("9BW DA05X6 1 T050136");
        pk.setEngineSerialNumber("ABC123");
		Car2 c = em.find(Car2.class, pk);
		em.getTransaction().commit();
		log.info(c.toString());
		
		em.close();
		PersistenceManager.INSTANCE.close();

	}

}
