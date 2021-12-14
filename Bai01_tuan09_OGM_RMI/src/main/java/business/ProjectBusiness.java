package business;

import dao.ProjectDAO;
import entity.Project;

public class ProjectBusiness {
	private ProjectDAO projectDAO;

	public ProjectBusiness() {
		projectDAO = new ProjectDAO();
	}

	public boolean addProject(Project project) {
		return projectDAO.addProject(project);
	}

	public Project getProject(String name) {
		if (name == null || name.trim().equals("")) {
			return null;
		}
		return projectDAO.getProject(name);
	}
}
