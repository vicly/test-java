package vic.test.jpa.sample.blog;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import vic.test.jpa.BaseEntity;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="categories_blogs",
			joinColumns={@JoinColumn(name="cate_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="blog_id", referencedColumnName="id")})	
	private Set<Blog> blogs = new HashSet<Blog>();
	
	public Category() {
		
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
		return "Category id=" + getId() + ", name=" + this.name;
	}
}
