/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest.dao.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.aleatoritest.dao.clases.Materia;
import com.aleatoritest.dao.clases.Usuario;
import com.aleatoritest.dao.clases.Pregunta;
import com.aleatoritest.dao.clases.Prueba;
import com.aleatoritest.dao.clases.PruebaPK;
import com.aleatoritest.dao.controladores.exceptions.NonexistentEntityException;
import com.aleatoritest.dao.controladores.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MauricioGabriel
 */
public class PruebaJpaController implements Serializable {

    public PruebaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prueba prueba) throws PreexistingEntityException, Exception {
        if (prueba.getPruebaPK() == null) {
            prueba.setPruebaPK(new PruebaPK());
        }
        if (prueba.getPreguntaList() == null) {
            prueba.setPreguntaList(new ArrayList<Pregunta>());
        }
        prueba.getPruebaPK().setMateriaId(prueba.getMateria().getMateriaPK().getMateriaId());
        prueba.getPruebaPK().setUsuarioId(prueba.getUsuario().getUsuarioId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia materia = prueba.getMateria();
            if (materia != null) {
                materia = em.getReference(materia.getClass(), materia.getMateriaPK());
                prueba.setMateria(materia);
            }
            Usuario usuario = prueba.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsuarioId());
                prueba.setUsuario(usuario);
            }
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : prueba.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getPreguntaPK());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            prueba.setPreguntaList(attachedPreguntaList);
            em.persist(prueba);
            if (materia != null) {
                materia.getPruebaList().add(prueba);
                materia = em.merge(materia);
            }
            if (usuario != null) {
                usuario.getPruebaList().add(prueba);
                usuario = em.merge(usuario);
            }
            for (Pregunta preguntaListPregunta : prueba.getPreguntaList()) {
                preguntaListPregunta.getPruebaList().add(prueba);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrueba(prueba.getPruebaPK()) != null) {
                throw new PreexistingEntityException("Prueba " + prueba + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prueba prueba) throws NonexistentEntityException, Exception {
        prueba.getPruebaPK().setMateriaId(prueba.getMateria().getMateriaPK().getMateriaId());
        prueba.getPruebaPK().setUsuarioId(prueba.getUsuario().getUsuarioId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prueba persistentPrueba = em.find(Prueba.class, prueba.getPruebaPK());
            Materia materiaOld = persistentPrueba.getMateria();
            Materia materiaNew = prueba.getMateria();
            Usuario usuarioOld = persistentPrueba.getUsuario();
            Usuario usuarioNew = prueba.getUsuario();
            List<Pregunta> preguntaListOld = persistentPrueba.getPreguntaList();
            List<Pregunta> preguntaListNew = prueba.getPreguntaList();
            if (materiaNew != null) {
                materiaNew = em.getReference(materiaNew.getClass(), materiaNew.getMateriaPK());
                prueba.setMateria(materiaNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsuarioId());
                prueba.setUsuario(usuarioNew);
            }
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getPreguntaPK());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            prueba.setPreguntaList(preguntaListNew);
            prueba = em.merge(prueba);
            if (materiaOld != null && !materiaOld.equals(materiaNew)) {
                materiaOld.getPruebaList().remove(prueba);
                materiaOld = em.merge(materiaOld);
            }
            if (materiaNew != null && !materiaNew.equals(materiaOld)) {
                materiaNew.getPruebaList().add(prueba);
                materiaNew = em.merge(materiaNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPruebaList().remove(prueba);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPruebaList().add(prueba);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    preguntaListOldPregunta.getPruebaList().remove(prueba);
                    preguntaListOldPregunta = em.merge(preguntaListOldPregunta);
                }
            }
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    preguntaListNewPregunta.getPruebaList().add(prueba);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PruebaPK id = prueba.getPruebaPK();
                if (findPrueba(id) == null) {
                    throw new NonexistentEntityException("The prueba with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PruebaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prueba prueba;
            try {
                prueba = em.getReference(Prueba.class, id);
                prueba.getPruebaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prueba with id " + id + " no longer exists.", enfe);
            }
            Materia materia = prueba.getMateria();
            if (materia != null) {
                materia.getPruebaList().remove(prueba);
                materia = em.merge(materia);
            }
            Usuario usuario = prueba.getUsuario();
            if (usuario != null) {
                usuario.getPruebaList().remove(prueba);
                usuario = em.merge(usuario);
            }
            List<Pregunta> preguntaList = prueba.getPreguntaList();
            for (Pregunta preguntaListPregunta : preguntaList) {
                preguntaListPregunta.getPruebaList().remove(prueba);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            em.remove(prueba);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prueba> findPruebaEntities() {
        return findPruebaEntities(true, -1, -1);
    }

    public List<Prueba> findPruebaEntities(int maxResults, int firstResult) {
        return findPruebaEntities(false, maxResults, firstResult);
    }

    private List<Prueba> findPruebaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prueba.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Prueba findPrueba(PruebaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prueba.class, id);
        } finally {
            em.close();
        }
    }

    public int getPruebaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prueba> rt = cq.from(Prueba.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
