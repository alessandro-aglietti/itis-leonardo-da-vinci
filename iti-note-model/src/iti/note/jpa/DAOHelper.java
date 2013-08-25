package iti.note.jpa;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class DAOHelper {

	public static <T> void save(T o) {
		EntityManager em = EMF.get();
		try {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public static <T> T findById(long id, Class<T> c) {
		EntityManager em = EMF.get();
		T t = null;
		try {
			t = em.find(c, id);
		} finally {
			em.close();
		}
		return t;
	}

	public static <T> List<T> findAll(Class<T> c) {
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
}