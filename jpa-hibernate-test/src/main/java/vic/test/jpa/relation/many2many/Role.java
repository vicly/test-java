package vic.test.jpa.relation.many2many;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.google.common.collect.Lists;

import vic.test.jpa.NamedEntity;

@Entity
public class Role extends NamedEntity {

	@ManyToMany(mappedBy = "roles")
	private List<User> users = Lists.newArrayList();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
