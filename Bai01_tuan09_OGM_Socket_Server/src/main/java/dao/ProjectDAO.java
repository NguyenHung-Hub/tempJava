package dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entity.Project;
import util.HibernateUtil;

public class ProjectDAO {
	private OgmSessionFactory sessionFactory;

	public ProjectDAO() {
		sessionFactory = HibernateUtil.getInstance().getOgmSessionFactory();
	}

	public boolean add(Project project) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();

		try {
			transaction.begin();
			session.save(project);
			transaction.commit();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return false;
	}

	public Project getProject(String name) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();

		Project project = null;

		try {
			transaction.begin();
			String queryString = "db.projects.find({projectName:\"" + name + "\"})";
			List<Project> rs = session.createNativeQuery(queryString, Project.class).getResultList();

			if (rs.size() > 0) {
				project = rs.get(0);
			}

			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}

		return project;
	}
}
