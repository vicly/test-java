package vic.test.jpa.relation.many2many_association;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

		// Request 1: create employee and project
		Date now = new Date();
		Employee rick = new Employee();
		rick.setCreatedAt(now);
		rick.setName("rick");
		Employee john = new Employee();
		john.setCreatedAt(now);
		john.setName("john");
		Project px = new Project();
		px.setCreatedAt(now);
		px.setName("px");

		em.clear();
		em.getTransaction().begin();
		em.persist(px);
		em.persist(john);
		em.persist(rick);
		em.getTransaction().commit();

		// Request 2: create association
		em.clear(); // new context
		Long rickId = rick.getId();
		Long johnId = john.getId();
		Long pxId = px.getId();
		rick = em.find(Employee.class, rickId);
		john = em.find(Employee.class, johnId);
		px =  em.find(Project.class, pxId);

		ProjectAssociation rickPa = new ProjectAssociation();
		rickPa.setEmployee(rick);
		rickPa.setProject(px);
		rickPa.setTitle("Lead");
		rickPa.setEmployeeId(rick.getId());
		rickPa.setProjectId(px.getId());
		rick.getProjects().add(rickPa);
		px.getEmployees().add(rickPa);
		
		ProjectAssociation johnPa = new ProjectAssociation();
		johnPa.setEmployee(john);
		johnPa.setProject(px);
		johnPa.setTitle("Member");
		johnPa.setEmployeeId(john.getId());
		johnPa.setProjectId(px.getId());
		john.getProjects().add(johnPa);
		px.getEmployees().add(johnPa);
		
		em.getTransaction().begin();
		em.persist(johnPa);
		em.persist(rickPa);
		em.getTransaction().commit();

		
		// Request 3: print saved data
		em.clear();
		em.getTransaction().begin();
		rick = em.find(Employee.class, rick.getId());
		log.info(rick.toString());
		for (ProjectAssociation pa : rick.getProjects()) {
			log.info("\t{}, {}", pa.getProject(), pa.getEmployee());
		}

		john = em.find(Employee.class, john.getId());
		log.info(john.toString());
		for (ProjectAssociation pa : john.getProjects()) {
			log.info("\t{}, {}", pa.getProject(), pa.getEmployee());
		}
		em.getTransaction().commit();
		

		em.close();
		PersistenceManager.INSTANCE.close();
	}

}
