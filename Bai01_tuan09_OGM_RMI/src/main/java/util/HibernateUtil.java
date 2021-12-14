package util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.OgmSessionFactory.OgmSessionBuilder;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.cfg.OgmProperties;

public class HibernateUtil {
	private static HibernateUtil instance = null;
	private OgmSessionFactory ogmSessionFactory;

	public HibernateUtil() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySetting(OgmProperties.ENABLED, true).configure().build();

		Metadata metadata = new MetadataSources(registry).addAnnotatedClass(entity.Employee.class)
				.addAnnotatedClass(entity.EmployeeProject.class).addAnnotatedClass(entity.Project.class)
				.getMetadataBuilder().build();

		ogmSessionFactory = metadata.getSessionFactoryBuilder().unwrap(OgmSessionFactoryBuilder.class).build();
	}

	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}

	public OgmSessionFactory getOgmSessionFactory() {
		return ogmSessionFactory;
	}

}
