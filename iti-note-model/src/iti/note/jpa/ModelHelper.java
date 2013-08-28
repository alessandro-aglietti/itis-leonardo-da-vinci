package iti.note.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class DAOHelper {

	public static <T> T create(T o) {
		EntityManager em = null;

		try {
			em = EMF.get();
			em.getTransaction().begin();
			o = em.merge(o);
			em.getTransaction().commit();
		} finally {
			closeEm(em);
		}

		return o;
	}

	public static <T> T retrieve(long id, Class<T> c) {
		EntityManager em = null;
		T t = null;

		try {
			em = EMF.get();
			t = em.find(c, id);
		} finally {
			closeEm(em);
		}

		return t;
	}

	public static <T> List<T> retrieve(Class<T> c) {
		EntityManager em = null;
		List<T> tt = new ArrayList<T>();

		try {
			em = EMF.get();
			tt = em.createQuery("select e from " + c.getSimpleName() + " e", c)
					.getResultList();
		} finally {
			closeEm(em);
		}

		return tt;
	}

	public static <T> T update(T o) {
		return create(o);
	}

	public static <T> void delete(T o) {
		EntityManager em = null;

		try {
			em = EMF.get();
			em.getTransaction().begin();
			em.remove(o);
			em.getTransaction().commit();
		} finally {
			closeEm(em);
		}
	}

	public static void closeEm(EntityManager em) {
		if (em != null)
			em.close();
	}
}