package vic.test.jpa.relation.many2many_association;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "project_association")
@IdClass(ProjectAssociationId.class)
public class ProjectAssociation {
	@Id
	@Column(name = "project_id")
	private Long projectId;
	@Id
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "title")
	private String title;

	@ManyToOne
//	@PrimaryKeyJoinColumn(name = "employee_id", referencedColumnName = "id")
	@JoinColumn(name = "employee_id", updatable = false, insertable = false, referencedColumnName = "id")
	/*
	 * if this JPA model doesn't create a table for the "project_association" entity,
	 * please comment out the @PrimaryKeyJoinColumn, and use the ff:
	 * 
	 * @JoinColumn(name = "employeeId", updatable = false, insertable = false)
	 * or @JoinColumn(name = "employeeId", updatable = false, insertable =
	 * false, referencedColumnName = "id")
	 */
	private Employee employee;

	@ManyToOne
	//@PrimaryKeyJoinColumn(name = "project_id", referencedColumnName = "id")
	@JoinColumn(name = "project_id", updatable = false, insertable = false, referencedColumnName = "id")
	/*
	 * the same goes here: if this JPA model doesn't create a table for the
	 * "project_association" entity, please comment out the @PrimaryKeyJoinColumn, and use
	 * the ff:
	 * 
	 * @JoinColumn(name = "projectId", updatable = false, insertable = false) or
	 * @JoinColumn(name = "projectId", updatable = false, insertable = false,
	 * referencedColumnName = "id")
	 */
	private Project project;
	
	public ProjectAssociation() {
		
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("employeeId", this.employeeId)
				.add("projectId", projectId)
				.toString();
	}
	
}
