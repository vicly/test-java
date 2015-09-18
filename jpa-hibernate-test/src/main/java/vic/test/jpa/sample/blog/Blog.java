package vic.test.jpa.sample.blog;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vic.test.jpa.BaseEntity;

@Entity
@Table(name = "blogs")
public class Blog extends BaseEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private BlogUser author;

	@ManyToMany(mappedBy = "blogs")
	private Set<Category> categories = new HashSet<Category>();
	
	public Blog() {
		
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Set<Category> getCategories() {
		return this.categories;
	}
	
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public BlogUser getAuthor() {
		return this.author;
	}
	
	public void setAuthor(BlogUser author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Blog id=" + getId() + ", title=" + this.title;
	}
	
}
