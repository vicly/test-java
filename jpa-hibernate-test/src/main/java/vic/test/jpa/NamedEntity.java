package vic.test.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.google.common.base.MoreObjects;

@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {
	@Column(name = "name", length = 100, nullable = false, unique = true)
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("name", getName())
				.toString();
	}
}