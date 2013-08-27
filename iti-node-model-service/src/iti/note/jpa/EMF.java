package iti.note.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("myFirstPersistenceUnit");

	private EMF() {

	}

	public static final EntityManager get() {
		return emf.createEntityManager();
	}
}
