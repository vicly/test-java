package vic.test.jpa.relation.one2many;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import vic.test.jpa.BaseEntity;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

@Entity
@Table(name = "groups")
public class Group extends BaseEntity {
	
	public final static String FIND_ALL = "Group.findAll";
	public final static String FIND_BY_NAME = "Group.findByName";
	
	@Column
	private String name;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "group_id")
	private List<Member> members = Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id")
	private List<GroupAttribute> attributes = Lists.newArrayList();
	
	public Group() {}
	
	
	public String getName() {
		return this.name;
	}
	
	public Group setName(String name) {
		this.name = name;
		return this;
	}
	
	public List<Member> getMembers() {
		return this.members;
	}
	
	public Group setMembers(List<Member> members) {
		this.members = members;
		return this;
	}
	
	public List<GroupAttribute> getAttributes() {
		return this.attributes;
	}
	
	public Group setAttributes(List<GroupAttribute> attributes) {
		this.attributes = attributes;
		return this;
	}
	
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("name", getName())
				.toString();
	}
}
