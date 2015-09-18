package vic.test.jpa.basic.inheritance;

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
		
		Square square =  new Square();
		square.setCreatedAt(now);
		square.setSideLength(10);
		square.setName("Square X");

		Circle circle = new Circle();
		circle.setCreatedAt(now);
		circle.setRadius(5);
		circle.setName("Circle X");

		em.getTransaction().begin();
		em.persist(circle);
		em.persist(square);
//		Hibernate: insert into shape (id, created_at, name) values (default, ?, ?)
//		Hibernate: insert into Circle (radius, id) values (?, ?)
//		Hibernate: insert into shape (id, created_at, name) values (default, ?, ?)
//		Hibernate: insert into Square (sideLength, id) values (?, ?)
		
		em.getTransaction().commit();

		em.getTransaction().begin();
		log.info(">> Find all shapes");
		/*
		Hibernate: 
			select shape0_.id as id1_27_, 
				   shape0_.created_at as created_2_27_, 
				   shape0_.name as name3_27_, 
				   shape0_1_.radius as radius1_4_, 
				   shape0_2_.sideLength as sideLeng1_6_, 
				   case 
						when shape0_1_.id is not null then 1 
						when shape0_2_.id is not null then 2 
						when shape0_.id is not null then 0 
				   end as clazz_ 
			 from shape shape0_ left 
	   outer join Circle shape0_1_ on shape0_.id=shape0_1_.id 
  left outer join Square shape0_2_ on shape0_.id=shape0_2_.id

		 */

		for (Shape s : em.createQuery("SELECT s FROM Shape s", Shape.class).getResultList()) {
			log.info(s.toString());
		}
		
		log.info(">> Find square by id");
		Shape s = em.find(Shape.class, square.getId());
		log.info(s.toString());
		
		log.info(">> Find circle by id");
		s = em.find(Shape.class, circle.getId());
		log.info(s.toString());
		em.getTransaction().commit();

		em.close();
		PersistenceManager.INSTANCE.close();

	}

}
