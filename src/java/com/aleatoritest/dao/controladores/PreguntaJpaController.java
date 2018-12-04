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
import com.aleatoritest.dao.clases.Usuario;
import java.util.ArrayList;
import java.util.List;
import com.aleatoritest.dao.clases.Materia;
import com.aleatoritest.dao.clases.Pregunta;
import com.aleatoritest.dao.clases.PreguntaPK;
import com.aleatoritest.dao.clases.Prueba;
import com.aleatoritest.dao.controladores.exceptions.NonexistentEntityException;
import com.aleatoritest.dao.controladores.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MauricioGabriel
 */
public class PreguntaJpaController implements Serializable {

    public PreguntaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pregunta pregunta) throws PreexistingEntityException, Exception {
        if (pregunta.getPreguntaPK() == null) {
            pregunta.setPreguntaPK(new PreguntaPK());
        }
        if (pregunta.getUsuarioList() == null) {
            pregunta.setUsuarioList(new ArrayList<Usuario>());
        }
        if (pregunta.getMateriaList() == null) {
            pregunta.setMateriaList(new ArrayList<Materia>());
        }
        if (pregunta.getPruebaList() == null) {
            pregunta.setPruebaList(new ArrayList<Prueba>());
        }
        pregunta.getPreguntaPK().setUsuarioId(pregunta.getUsuario().getUsuarioId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = pregunta.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsuarioId());
                pregunta.setUsuario(usuario);
            }
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : pregunta.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuarioId());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            pregunta.setUsuarioList(attachedUsuarioList);
            List<Materia> attachedMateriaList = new ArrayList<Materia>();
            for (Materia materiaListMateriaToAttach : pregunta.getMateriaList()) {
                materiaListMateriaToAttach = em.getReference(materiaListMateriaToAttach.getClass(), materiaListMateriaToAttach.getMateriaPK());
                attachedMateriaList.add(materiaListMateriaToAttach);
            }
            pregunta.setMateriaList(attachedMateriaList);
            List<Prueba> attachedPruebaList = new ArrayList<Prueba>();
            for (Prueba pruebaListPruebaToAttach : pregunta.getPruebaList()) {
                pruebaListPruebaToAttach = em.getReference(pruebaListPruebaToAttach.getClass(), pruebaListPruebaToAttach.getPruebaPK());
                attachedPruebaList.add(pruebaListPruebaToAttach);
            }
            pregunta.setPruebaList(attachedPruebaList);
            em.persist(pregunta);
            if (usuario != null) {
                usuario.getPreguntaList().add(pregunta);
                usuario = em.merge(usuario);
            }
            for (Usuario usuarioListUsuario : pregunta.getUsuarioList()) {
                usuarioListUsuario.getPreguntaList().add(pregunta);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            for (Materia materiaListMateria : pregunta.getMateriaList()) {
                materiaListMateria.getPreguntaList().add(pregunta);
                materiaListMateria = em.merge(materiaListMateria);
            }
            for (Prueba pruebaListPrueba : pregunta.getPruebaList()) {
                pruebaListPrueba.getPreguntaList().add(pregunta);
                pruebaListPrueba = em.merge(pruebaListPrueba);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPregunta(pregunta.getPreguntaPK()) != null) {
                throw new PreexistingEntityException("Pregunta " + pregunta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pregunta pregunta) throws NonexistentEntityException, Exception {
        pregunta.getPreguntaPK().setUsuarioId(pregunta.getUsuario().getUsuarioId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta persistentPregunta = em.find(Pregunta.class, pregunta.getPreguntaPK());
            Usuario usuarioOld = persistentPregunta.getUsuario();
            Usuario usuarioNew = pregunta.getUsuario();
            List<Usuario> usuarioListOld = persistentPregunta.getUsuarioList();
            List<Usuario> usuarioListNew = pregunta.getUsuarioList();
            List<Materia> materiaListOld = persistentPregunta.getMateriaList();
            List<Materia> materiaListNew = pregunta.getMateriaList();
            List<Prueba> pruebaListOld = persistentPregunta.getPruebaList();
            List<Prueba> pruebaListNew = pregunta.getPruebaList();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsuarioId());
                pregunta.setUsuario(usuarioNew);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuarioId());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            pregunta.setUsuarioList(usuarioListNew);
            List<Materia> attachedMateriaListNew = new ArrayList<Materia>();
            for (Materia materiaListNewMateriaToAttach : materiaListNew) {
                materiaListNewMateriaToAttach = em.getReference(materiaListNewMateriaToAttach.getClass(), materiaListNewMateriaToAttach.getMateriaPK());
                attachedMateriaListNew.add(materiaListNewMateriaToAttach);
            }
            materiaListNew = attachedMateriaListNew;
            pregunta.setMateriaList(materiaListNew);
            List<Prueba> attachedPruebaListNew = new ArrayList<Prueba>();
            for (Prueba pruebaListNewPruebaToAttach : pruebaListNew) {
                pruebaListNewPruebaToAttach = em.getReference(pruebaListNewPruebaToAttach.getClass(), pruebaListNewPruebaToAttach.getPruebaPK());
                attachedPruebaListNew.add(pruebaListNewPruebaToAttach);
            }
            pruebaListNew = attachedPruebaListNew;
            pregunta.setPruebaList(pruebaListNew);
            pregunta = em.merge(pregunta);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPreguntaList().remove(pregunta);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPreguntaList().add(pregunta);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.getPreguntaList().remove(pregunta);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    usuarioListNewUsuario.getPreguntaList().add(pregunta);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                }
            }
            for (Materia materiaListOldMateria : materiaListOld) {
                if (!materiaListNew.contains(materiaListOldMateria)) {
                    materiaListOldMateria.getPreguntaList().remove(pregunta);
                    materiaListOldMateria = em.merge(materiaListOldMateria);
                }
            }
            for (Materia materiaListNewMateria : materiaListNew) {
                if (!materiaListOld.contains(materiaListNewMateria)) {
                    materiaListNewMateria.getPreguntaList().add(pregunta);
                    materiaListNewMateria = em.merge(materiaListNewMateria);
                }
            }
            for (Prueba pruebaListOldPrueba : pruebaListOld) {
                if (!pruebaListNew.contains(pruebaListOldPrueba)) {
                    pruebaListOldPrueba.getPreguntaList().remove(pregunta);
                    pruebaListOldPrueba = em.merge(pruebaListOldPrueba);
                }
            }
            for (Prueba pruebaListNewPrueba : pruebaListNew) {
                if (!pruebaListOld.contains(pruebaListNewPrueba)) {
                    pruebaListNewPrueba.getPreguntaList().add(pregunta);
                    pruebaListNewPrueba = em.merge(pruebaListNewPrueba);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PreguntaPK id = pregunta.getPreguntaPK();
                if (findPregunta(id) == null) {
                    throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PreguntaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta pregunta;
            try {
                pregunta = em.getReference(Pregunta.class, id);
                pregunta.getPreguntaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = pregunta.getUsuario();
            if (usuario != null) {
                usuario.getPreguntaList().remove(pregunta);
                usuario = em.merge(usuario);
            }
            List<Usuario> usuarioList = pregunta.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.getPreguntaList().remove(pregunta);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<Materia> materiaList = pregunta.getMateriaList();
            for (Materia materiaListMateria : materiaList) {
                materiaListMateria.getPreguntaList().remove(pregunta);
                materiaListMateria = em.merge(materiaListMateria);
            }
            List<Prueba> pruebaList = pregunta.getPruebaList();
            for (Prueba pruebaListPrueba : pruebaList) {
                pruebaListPrueba.getPreguntaList().remove(pregunta);
                pruebaListPrueba = em.merge(pruebaListPrueba);
            }
            em.remove(pregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pregunta> findPreguntaEntities() {
        return findPreguntaEntities(true, -1, -1);
    }

    public List<Pregunta> findPreguntaEntities(int maxResults, int firstResult) {
        return findPreguntaEntities(false, maxResults, firstResult);
    }

    private List<Pregunta> findPreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pregunta.class));
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

    public Pregunta findPregunta(PreguntaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pregunta> rt = cq.from(Pregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
