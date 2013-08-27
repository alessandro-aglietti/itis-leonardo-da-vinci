package iti.note.test;

import iti.note.jpa.EMF;
import iti.note.model.Nota;
import iti.note.model.Taccuino;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersistenceMethodTest {
	public static void main(String[] args) {
		Taccuino t = new Taccuino("Taccuino di alessandro 5");

		Nota nota = new Nota("Nota di alessanro");

		t.addNota(nota);

		t = t.create();

		t.setTitolo("Nuovo titolo");

		t = t.update();

		List<Taccuino> tt = Taccuino.retrieveAll();
		System.out.println("Trovati " + tt.size() + " taccuini");

		// test jpa trasparent update

		EntityManager em = EMF.get();

		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {

			t = em.find(Taccuino.class, t.getId());

			t.setTitolo("Trasparent update");

		} finally {
			transaction.commit();
			em.close();
		}

	}
}