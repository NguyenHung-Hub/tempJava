package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7805388747323991492L;

	@Id
	private Long projectID;
	private String projectName;
	private String type;

	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<String> versions;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();

	public Project() {
		super();
	}

	public Project(Long projectID, String projectName, String type, Set<String> versions) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.type = type;
		this.versions = versions;
	}

	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<String> getVersions() {
		return versions;
	}

	public void setVersions(Set<String> versions) {
		this.versions = versions;
	}

	public List<EmployeeProject> getEmployeeProjects() {
		return employeeProjects;
	}

//	public void setEmployeeProjects(List<EmployeeProject> employeeProjects) {
//		this.employeeProjects = employeeProjects;
//	}

	public void addDetail(Employee employee, int hours) {
		EmployeeProject detail = new EmployeeProject(hours, employee);
		employeeProjects.add(detail);

	}

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", projectName=" + projectName + ", type=" + type + ", versions="
				+ versions + ", employeeProjects=" + employeeProjects + "]";
	}

}
