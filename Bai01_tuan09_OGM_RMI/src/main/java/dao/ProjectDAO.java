package dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entity.Project;
import util.HibernateUtil;

public class ProjectDAO {
	private OgmSessionFactory ogmSessionFactory;

	public ProjectDAO() {
		ogmSessionFactory = HibernateUtil.getInstance().getOgmSessionFactory();
	}

	public boolean addProject(Project project) {

		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		boolean rs = false;
		try {
			transaction.begin();

			session.save(project);
			transaction.commit();
			rs = true;
		} catch (Exception e) {
			transaction.rollback();
			
		}

		return rs;
	}

	public Project getProject(String name) {

		Project project = null;

		OgmSession session = ogmSessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();

		try {
			transaction.begin();
			String queryString = "db.projects.find({projectName:\"" + name + "\"})";
			List<Project> list = session.createNativeQuery(queryString, Project.class).getResultList();
			if (list.size() > 0) {
				project = list.get(0);
			}

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return project;
	}
}
