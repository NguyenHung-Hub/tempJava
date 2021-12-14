package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmployeeProject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6892945389030968594L;
	private int hours;

	@ManyToOne
	@JoinColumn(name = "employeeID")
	private Employee employee;

	public EmployeeProject() {
		super();
	}

	public EmployeeProject(int hours, Employee employee) {
		super();
		this.hours = hours;
		this.employee = employee;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeProject [hours=" + hours + ", employee=" + employee + "]";
	}

}
