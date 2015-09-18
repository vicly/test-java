package vic.test.jpa.relation.many2many_association;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import vic.test.jpa.NamedEntity;

@Entity
public class Project extends NamedEntity {
	
	@OneToMany(mappedBy = "project")
	private List<ProjectAssociation> employees = Lists.newArrayList();

	public List<ProjectAssociation> getEmployees() {
		return employees;
	}

	public void setEmployees(List<ProjectAssociation> employees) {
		this.employees = employees;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("name", getName())
				.toString();
	}
	
}
