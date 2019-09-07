package utils;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class ConnectionUtils {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	public static void SetupConnection() {
		sessionFactory = utils.HibernateUtils.getSessionFactory();
		session = sessionFactory.openSession();
	}
	
	public static Session getSession() {
		if (sessionFactory == null || session == null) SetupConnection();
		return session;
	}
	
	public void close() {
		session.close();
		sessionFactory.close();
	}
}
