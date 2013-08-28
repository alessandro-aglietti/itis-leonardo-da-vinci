/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.taccuino.controller;

import it.taccuino.controller.exceptions.NonexistentEntityException;
import it.taccuino.model.Taccuino;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;

/**
 *
 * @author gabrieleposca
 */
public class TaccuinoJpaController implements Serializable {

//    public TaccuinoJpaController(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//    private EntityManagerFactory emf = null;
//
//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }

    @Resource 
    private UserTransaction utx = null;
    
    public void create(Taccuino taccuino) {
        EntityManager em = null;
        try {
            em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            em.persist(taccuino);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Taccuino taccuino) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            taccuino = em.merge(taccuino);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = taccuino.getId();
                if (findTaccuino(id) == null) {
                    throw new NonexistentEntityException("The taccuino with id " + id + " no longer exists.");
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
            Taccuino taccuino;
            try {
                taccuino = em.getReference(Taccuino.class, id);
                taccuino.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The taccuino with id " + id + " no longer exists.", enfe);
            }
            em.remove(taccuino);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Taccuino> findTaccuinoEntities() {
        return findTaccuinoEntities(true, -1, -1);
    }

    public List<Taccuino> findTaccuinoEntities(int maxResults, int firstResult) {
        return findTaccuinoEntities(false, maxResults, firstResult);
    }

    private List<Taccuino> findTaccuinoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Taccuino as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Taccuino findTaccuino(Long id) {
        EntityManager em = EMF.get().createEntityManager();
        try {
            return em.find(Taccuino.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaccuinoCount() {
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Taccuino as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int countNoteByTaccuinoId(String id) {
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("select count(n) from Note as n where n.taccuino.id="+id);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
