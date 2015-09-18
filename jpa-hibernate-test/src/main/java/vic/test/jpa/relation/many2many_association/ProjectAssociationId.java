package vic.test.jpa.relation.many2many_association;

import java.io.Serializable;

public class ProjectAssociationId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long employeeId;
	private Long projectId;

	public ProjectAssociationId() {
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

	@Override
	public int hashCode() {
		return (int) (employeeId + projectId);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ProjectAssociationId) {
			ProjectAssociationId otherId = (ProjectAssociationId) object;
			return (otherId.employeeId == this.employeeId)
					&& (otherId.projectId == this.projectId);
		}
		return false;
	}
}