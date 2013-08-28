/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.taccuino.controller;

import it.taccuino.controller.exceptions.NonexistentEntityException;
import it.taccuino.model.Note;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 * 
 * @author gabrieleposca
 */
public class NoteJpaController implements Serializable {

	// public NoteJpaController(EntityManagerFactory emf) {
	// this.emf = emf;
	// }
	// private EntityManagerFactory emf = null;
	//
	// public EntityManager getEntityManager() {
	// return emf.createEntityManager();
	// }

	@Resource
	private UserTransaction utx = null;

	public void create(Note note) {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			em.getTransaction().begin();
			em.persist(note);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Note note) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			em.getTransaction().begin();
			note = em.merge(note);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = note.getId();
				if (findNote(id) == null) {
					throw new NonexistentEntityException("The note with id "
							+ id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = EMF.get().createEntityManager();
			em.getTransaction().begin();
			Note note;
			try {
				note = em.getReference(Note.class, id);
				note.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The note with id " + id
						+ " no longer exists.", enfe);
			}
			em.remove(note);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Note> findNoteEntities() {
		return findNoteEntities(true, -1, -1);
	}

	public List<Note> findNoteEntities(int maxResults, int firstResult) {
		return findNoteEntities(false, maxResults, firstResult);
	}

	private List<Note> findNoteEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = EMF.get().createEntityManager();
		try {
			Query q = em.createQuery("select object(o) from Note as o");
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Note findNote(Long id) {
		EntityManager em = EMF.get().createEntityManager();
		try {
			return em.find(Note.class, id);
		} finally {
			em.close();
		}
	}

	public int getNoteCount() {
		EntityManager em = EMF.get().createEntityManager();
		try {
			Query q = em.createQuery("select count(o) from Note as o");
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public List<Note> findNoteByIdTaccuino(Long id) {
		EntityManager em = EMF.get().createEntityManager();
		try {
			Query q = em
					.createQuery("select n from Note as n where n.taccuino.id="
							+ id);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

}
