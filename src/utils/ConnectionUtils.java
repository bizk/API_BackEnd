package utils;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class ConnectionUtils {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	public static void SetupConnection() {
		sessionFactory = utils.HibernateUtils.getSessionFactory();
	}
	
	public static Session getSession() {
		if (sessionFactory == null) SetupConnection();
		return sessionFactory.openSession();
	}
	
	public void close() {
		session.close();
		sessionFactory.close();
	}
}
