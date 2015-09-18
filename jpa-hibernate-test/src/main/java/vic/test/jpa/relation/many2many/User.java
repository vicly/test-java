package vic.test.jpa.relation.many2many;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.common.collect.Lists;

import vic.test.jpa.NamedEntity;

@Entity
public class User extends NamedEntity {
	
	@ManyToMany
    @JoinTable(
    		name="user_user_type", 
    		joinColumns={@JoinColumn(name="user_id")}, 
    		inverseJoinColumns={@JoinColumn(name="user_type_id")}
    )
	private List<UserType> types = Lists.newArrayList();
	
	@ManyToMany
	@JoinTable(
		name = "user_role",
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")}
	)
	private List<Role> roles = Lists.newArrayList();

	public List<UserType> getTypes() {
		return types;
	}

	public void setTypes(List<UserType> types) {
		this.types = types;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
