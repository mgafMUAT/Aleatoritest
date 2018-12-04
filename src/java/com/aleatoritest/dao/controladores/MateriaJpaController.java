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
import com.aleatoritest.dao.clases.Asignatura;
import com.aleatoritest.dao.clases.Materia;
import com.aleatoritest.dao.clases.MateriaPK;
import com.aleatoritest.dao.clases.Pregunta;
import java.util.ArrayList;
import java.util.List;
import com.aleatoritest.dao.clases.Prueba;
import com.aleatoritest.dao.controladores.exceptions.IllegalOrphanException;
import com.aleatoritest.dao.controladores.exceptions.NonexistentEntityException;
import com.aleatoritest.dao.controladores.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MauricioGabriel
 */
public class MateriaJpaController implements Serializable {

    public MateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materia materia) throws PreexistingEntityException, Exception {
        if (materia.getMateriaPK() == null) {
            materia.setMateriaPK(new MateriaPK());
        }
        if (materia.getPreguntaList() == null) {
            materia.setPreguntaList(new ArrayList<Pregunta>());
        }
        if (materia.getPruebaList() == null) {
            materia.setPruebaList(new ArrayList<Prueba>());
        }
        materia.getMateriaPK().setAsignaturaId(materia.getAsignatura().getAsignaturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura = materia.getAsignatura();
            if (asignatura != null) {
                asignatura = em.getReference(asignatura.getClass(), asignatura.getAsignaturaId());
                materia.setAsignatura(asignatura);
            }
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : materia.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getPreguntaPK());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            materia.setPreguntaList(attachedPreguntaList);
            List<Prueba> attachedPruebaList = new ArrayList<Prueba>();
            for (Prueba pruebaListPruebaToAttach : materia.getPruebaList()) {
                pruebaListPruebaToAttach = em.getReference(pruebaListPruebaToAttach.getClass(), pruebaListPruebaToAttach.getPruebaPK());
                attachedPruebaList.add(pruebaListPruebaToAttach);
            }
            materia.setPruebaList(attachedPruebaList);
            em.persist(materia);
            if (asignatura != null) {
                asignatura.getMateriaList().add(materia);
                asignatura = em.merge(asignatura);
            }
            for (Pregunta preguntaListPregunta : materia.getPreguntaList()) {
                preguntaListPregunta.getMateriaList().add(materia);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            for (Prueba pruebaListPrueba : materia.getPruebaList()) {
                Materia oldMateriaOfPruebaListPrueba = pruebaListPrueba.getMateria();
                pruebaListPrueba.setMateria(materia);
                pruebaListPrueba = em.merge(pruebaListPrueba);
                if (oldMateriaOfPruebaListPrueba != null) {
                    oldMateriaOfPruebaListPrueba.getPruebaList().remove(pruebaListPrueba);
                    oldMateriaOfPruebaListPrueba = em.merge(oldMateriaOfPruebaListPrueba);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateria(materia.getMateriaPK()) != null) {
                throw new PreexistingEntityException("Materia " + materia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materia materia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        materia.getMateriaPK().setAsignaturaId(materia.getAsignatura().getAsignaturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia persistentMateria = em.find(Materia.class, materia.getMateriaPK());
            Asignatura asignaturaOld = persistentMateria.getAsignatura();
            Asignatura asignaturaNew = materia.getAsignatura();
            List<Pregunta> preguntaListOld = persistentMateria.getPreguntaList();
            List<Pregunta> preguntaListNew = materia.getPreguntaList();
            List<Prueba> pruebaListOld = persistentMateria.getPruebaList();
            List<Prueba> pruebaListNew = materia.getPruebaList();
            List<String> illegalOrphanMessages = null;
            for (Prueba pruebaListOldPrueba : pruebaListOld) {
                if (!pruebaListNew.contains(pruebaListOldPrueba)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prueba " + pruebaListOldPrueba + " since its materia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (asignaturaNew != null) {
                asignaturaNew = em.getReference(asignaturaNew.getClass(), asignaturaNew.getAsignaturaId());
                materia.setAsignatura(asignaturaNew);
            }
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getPreguntaPK());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            materia.setPreguntaList(preguntaListNew);
            List<Prueba> attachedPruebaListNew = new ArrayList<Prueba>();
            for (Prueba pruebaListNewPruebaToAttach : pruebaListNew) {
                pruebaListNewPruebaToAttach = em.getReference(pruebaListNewPruebaToAttach.getClass(), pruebaListNewPruebaToAttach.getPruebaPK());
                attachedPruebaListNew.add(pruebaListNewPruebaToAttach);
            }
            pruebaListNew = attachedPruebaListNew;
            materia.setPruebaList(pruebaListNew);
            materia = em.merge(materia);
            if (asignaturaOld != null && !asignaturaOld.equals(asignaturaNew)) {
                asignaturaOld.getMateriaList().remove(materia);
                asignaturaOld = em.merge(asignaturaOld);
            }
            if (asignaturaNew != null && !asignaturaNew.equals(asignaturaOld)) {
                asignaturaNew.getMateriaList().add(materia);
                asignaturaNew = em.merge(asignaturaNew);
            }
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    preguntaListOldPregunta.getMateriaList().remove(materia);
                    preguntaListOldPregunta = em.merge(preguntaListOldPregunta);
                }
            }
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    preguntaListNewPregunta.getMateriaList().add(materia);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                }
            }
            for (Prueba pruebaListNewPrueba : pruebaListNew) {
                if (!pruebaListOld.contains(pruebaListNewPrueba)) {
                    Materia oldMateriaOfPruebaListNewPrueba = pruebaListNewPrueba.getMateria();
                    pruebaListNewPrueba.setMateria(materia);
                    pruebaListNewPrueba = em.merge(pruebaListNewPrueba);
                    if (oldMateriaOfPruebaListNewPrueba != null && !oldMateriaOfPruebaListNewPrueba.equals(materia)) {
                        oldMateriaOfPruebaListNewPrueba.getPruebaList().remove(pruebaListNewPrueba);
                        oldMateriaOfPruebaListNewPrueba = em.merge(oldMateriaOfPruebaListNewPrueba);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MateriaPK id = materia.getMateriaPK();
                if (findMateria(id) == null) {
                    throw new NonexistentEntityException("The materia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MateriaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia materia;
            try {
                materia = em.getReference(Materia.class, id);
                materia.getMateriaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Prueba> pruebaListOrphanCheck = materia.getPruebaList();
            for (Prueba pruebaListOrphanCheckPrueba : pruebaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materia (" + materia + ") cannot be destroyed since the Prueba " + pruebaListOrphanCheckPrueba + " in its pruebaList field has a non-nullable materia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Asignatura asignatura = materia.getAsignatura();
            if (asignatura != null) {
                asignatura.getMateriaList().remove(materia);
                asignatura = em.merge(asignatura);
            }
            List<Pregunta> preguntaList = materia.getPreguntaList();
            for (Pregunta preguntaListPregunta : preguntaList) {
                preguntaListPregunta.getMateriaList().remove(materia);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
        return findMateriaEntities(false, maxResults, firstResult);
    }

    private List<Materia> findMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materia.class));
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

    public Materia findMateria(MateriaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materia> rt = cq.from(Materia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
