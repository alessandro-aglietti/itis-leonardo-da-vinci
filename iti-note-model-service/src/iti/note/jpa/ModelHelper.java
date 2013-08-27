package iti.note.jpa;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class ModelHelper {

	public static <T> T create(T o) {
		EntityManager em = EMF.get();
		em.getTransaction().begin();
		try {
			o = em.merge(o);
		} finally {
			em.getTransaction().commit();
			em.close();
		}

		return o;
	}

	public static <T> T retrieve(long id, Class<T> c) {
		EntityManager em = EMF.get();
		T t = null;
		try {
			t = em.find(c, id);
		} finally {
			em.close();
		}
		return t;
	}

	public static <T> List<T> retrieve(Class<T> c) {
		EntityManager em = EMF.get();
		List<T> tt = null;
		try {
			tt = em.createQuery("select e from " + c.getSimpleName() + " e", c)
					.getResultList();
		} finally {
			em.close();
		}

		return tt;
	}

	public static <T> void delete(T o) {
		EntityManager em = EMF.get();
		em.getTransaction().begin();
		try {
			em.remove(o);
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}
}