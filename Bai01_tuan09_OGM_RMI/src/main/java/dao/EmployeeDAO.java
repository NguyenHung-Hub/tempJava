package dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entity.Employee;
import entity.Project;
import util.HibernateUtil;

public class EmployeeDAO {
	private OgmSessionFactory ogmSessionFactory;

	public EmployeeDAO() {
		ogmSessionFactory = HibernateUtil.getInstance().getOgmSessionFactory();
	}

	public boolean addEmployee(Employee employee) throws Exception {

		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		boolean rs = false;
		try {
			transaction.begin();

			session.save(employee);
			transaction.commit();
			rs = true;
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}

		return rs;
	}

	public Employee getEmployee(Long id) {

		Employee employee = null;

		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();

		try {
			transaction.begin();
			employee = session.find(Employee.class, id);
			

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return employee;
	}
}
