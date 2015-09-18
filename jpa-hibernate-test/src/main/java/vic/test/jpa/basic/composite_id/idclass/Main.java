package vic.test.jpa.basic.composite_id.idclass;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		Car car = new Car();
		car.setChassisSerialNumber("9BW DA05X6 1 T050136");
        car.setEngineSerialNumber("ABC123");
        car.setName("Thunder");
        
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();

		em.clear();
		
		em.getTransaction().begin();
		CarPK pk = new CarPK();
		pk.setChassisSerialNumber("9BW DA05X6 1 T050136");
        pk.setEngineSerialNumber("ABC123");
		Car c = em.find(Car.class, pk);
		em.getTransaction().commit();
		log.info(c.toString());
		
		em.close();
		PersistenceManager.INSTANCE.close();

	}

}
