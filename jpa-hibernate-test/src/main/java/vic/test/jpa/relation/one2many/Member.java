package vic.test.jpa.relation.one2many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vic.test.jpa.BaseEntity;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "members")
public class Member extends BaseEntity {

	@Column
	private String name;

	@ManyToOne
	private Group group;
	
	public Member() {
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("name", getName())
				.toString();
	}
	
}
