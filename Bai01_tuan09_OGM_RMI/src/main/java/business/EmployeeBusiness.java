package business;

import dao.EmployeeDAO;
import dao.ProjectDAO;
import entity.Employee;
import entity.Project;

public class EmployeeBusiness {
	private EmployeeDAO employeeDAO;

	public EmployeeBusiness() {
		employeeDAO = new EmployeeDAO();
	}

	public boolean addEmployee(Employee employee) throws Exception {
		return employeeDAO.addEmployee(employee);
	}

	public Employee getEmployee(Long id) {
		return employeeDAO.getEmployee(id);
	}
}
