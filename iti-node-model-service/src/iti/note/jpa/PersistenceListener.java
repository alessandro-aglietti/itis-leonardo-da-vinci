package iti.note.jpa;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PersistenceListener implements ServletContextListener {

	private EntityManagerFactory entityManagerFactory;

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		// entityManagerFactory = Persistence
		// .createEntityManagerFactory("myFirstPersistenceUnit");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// entityManagerFactory.close();
	}
}