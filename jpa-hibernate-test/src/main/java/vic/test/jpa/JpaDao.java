package vic.test.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class JpaDao {

	private EntityManagerFactory emf;
	
	public JpaDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public <T> T persist(T entity) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			if (et.isActive()) {
				et.rollback();
			}
			throw pe;
		}
		return entity;
	}
	
}
