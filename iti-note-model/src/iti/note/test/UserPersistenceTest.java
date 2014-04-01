package iti.note.test;

import java.util.ArrayList;
import java.util.List;

import iti.note.model.Nota;
import iti.note.model.Taccuino;
import iti.note.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserPersistenceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("myFirstPersistenceUnit");
		
		EntityManager em = emf.createEntityManager();
		
		Taccuino taccuino = new Taccuino("Il mio taccuino persistente");
		Nota nota = new Nota("La mia nota persistente");
		taccuino.addNota(nota);
		
		User u = new User();
		u.setNome("Il mio nome");
		
		List<Taccuino> taccuini = new ArrayList<Taccuino>();
		taccuini.add(taccuino);
		
		u.setTaccuini(taccuini);

		em.getTransaction().begin();
		
		em.persist(u);

		em.getTransaction().commit();
	}
}