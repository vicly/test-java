package vic.test.jpa.relation.many2many_association;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import vic.test.jpa.NamedEntity;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

@Entity
@Table(name = "employee")
public class Employee extends NamedEntity {

	@OneToMany(mappedBy = "employee")
	private List<ProjectAssociation> projects = Lists.newArrayList();

	public List<ProjectAssociation> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectAssociation> projects) {
		this.projects = projects;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("name", getName())
				.toString();
	}
}
