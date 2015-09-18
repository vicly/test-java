package vic.test.jpa.sample.blog;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vic.test.jpa.PersistenceManager;

public class Main {

	private final static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		
		// app init
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		
		Date now = new Date();
		// init categories
		em.clear();
		em.getTransaction().begin();
		Category cateJava = new Category();
		cateJava.setName("Java");
		cateJava.setCreatedAt(now);
		Category cateSpring = new Category();
		cateSpring.setName("Spring");
		cateSpring.setCreatedAt(now);
		em.persist(cateJava);
		em.persist(cateSpring);
		em.getTransaction().commit();
		LOG.info("Created categories");
		LOG.info("  " + cateJava);
		LOG.info("  " + cateSpring);
		
		// init user
		em.clear();
		em.getTransaction().begin();
		BlogUser vic = new BlogUser();
		vic.setName("Vic");
		vic.setCreatedAt(now);
		em.persist(vic);
		em.getTransaction().commit();
		LOG.info("Created users");
		LOG.info("  " + vic);
		
		
		// Posting Blog
		Blog blog;
		blog = new Blog();
		blog.setTitle("blog1");
		blog.setAuthor(vic);
		blog.setCreatedAt(now);
		blog = post(em, cateJava.getId(), blog);
		LOG.info("Created blog " + blog);

		blog = new Blog();
		blog.setTitle("blog2");
		blog.setAuthor(vic);
		blog.setCreatedAt(now);
		blog = post(em, cateJava.getId(), blog);
		LOG.info("Created blog " + blog);

		blog = new Blog();
		blog.setTitle("blog3");
		blog.setAuthor(vic);
		blog.setCreatedAt(now);
		blog = post(em, cateJava.getId(), blog);
		LOG.info("Created blog " + blog);
		
		
		// list
		listBlogs(em, vic.getId());
		
		em.close();
		PersistenceManager.INSTANCE.close();
	}
	
	private static void listBlogs(EntityManager em, Long userId) {
		em.clear();
		LOG.info(">> Listing blogs of user " + userId);
		BlogUser user = em.find(BlogUser.class, userId);
		for (Blog blog : user.getBlogs()) {
			LOG.info(blog.toString());
		}
	}
	
	private static Blog post(EntityManager em, Long cateId, Blog newBlog) {
		em.getTransaction().begin();
		Category category = em.find(Category.class, cateId);
		category.getBlogs().add(newBlog);
//		newBlog.getCategories().add(category);
		em.persist(newBlog);
		em.getTransaction().commit();
		return newBlog;
	}
	
	
	
}
