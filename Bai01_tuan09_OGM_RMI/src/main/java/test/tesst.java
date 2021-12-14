package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import business.EmployeeBusiness;
import business.ProjectBusiness;
import entity.Employee;
import entity.Project;

public class tesst {
	public static void main(String[] args) {
		ProjectBusiness projectBusiness = new ProjectBusiness();

		Set<String> versions = new HashSet<String>();
		versions.add("1.0.0");
		versions.add("1.0.1");
		versions.add("1.0.2");

		Project p1 = new Project(106l, "p6", "software", versions);
		Project p2 = new Project(200l, "p2", "software", new HashSet<String>(Arrays.asList("1.0.0", "1.0.2")));

		Employee employee = new Employee();
		employee.setEmployeeID(200l);
		p1.addDetail(employee, 8);

//		 theem
		try {
			boolean rs = projectBusiness.addProject(p1);
			if (rs) {
				System.out.println("them thanh cong");
			}else {
				System.out.println("them that bai");
				
			}
		} catch (Exception e) {
			e.getMessage();
		}

		// tim

		Project temp = projectBusiness.getProject("p1");

		if (temp == null) {
			System.out.println("khoong tim thay");
		} else {
			System.out.println(temp);
		}
		
		EmployeeBusiness employeeBusiness = new EmployeeBusiness();
		Employee e = employeeBusiness.getEmployee(100l);
		System.out.println(e);

	}
}
