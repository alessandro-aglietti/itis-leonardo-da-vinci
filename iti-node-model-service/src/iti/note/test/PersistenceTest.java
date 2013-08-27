package iti.note.test;

import iti.note.model.Nota;
import iti.note.model.Taccuino;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("myFirstPersistenceUnit");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Taccuino taccuino = new Taccuino("Il mio taccuino persistente");
		em.persist(taccuino);

		Nota nota = new Nota("La mia nota persistente");
		em.persist(nota);

		taccuino.addNota(nota);
		em.persist(taccuino);

		em.getTransaction().commit();
	}
}