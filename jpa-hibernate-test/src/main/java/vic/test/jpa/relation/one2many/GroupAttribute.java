package vic.test.jpa.relation.one2many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import vic.test.jpa.BaseEntity;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "group_attributes")
public class GroupAttribute extends BaseEntity {

	@Column
	private String name;
	
	@Column
	private String value;
	
	public GroupAttribute() {
		
	}
	
	public String getValue() {
		return this.value;
	}
	
	public GroupAttribute setValue(String value) {
		this.value = value;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public GroupAttribute setName(String name) {
		this.name = name;
		return this;
	}
	
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("name", getName())
				.add("value", getValue())
				.toString();
	}
}
