package vic.test.jpa.sample.blog;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import vic.test.jpa.BaseEntity;

@Entity
public class BlogUser extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "author")
	private Set<Blog> blogs = new HashSet<Blog>();
	
	public BlogUser() {
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<Blog> getBlogs() {
		return this.blogs;
	}
	
	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "User id=" + getId() + ", name=" + this.name;
	}
	
	
}
