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
import com.aleatoritest.dao.clases.Pregunta;
import java.util.ArrayList;
import java.util.List;
import com.aleatoritest.dao.clases.Usuario;
import com.aleatoritest.dao.clases.Prueba;
import com.aleatoritest.dao.controladores.exceptions.IllegalOrphanException;
import com.aleatoritest.dao.controladores.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MauricioGabriel
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getPreguntaList() == null) {
            usuario.setPreguntaList(new ArrayList<Pregunta>());
        }
        if (usuario.getUsuarioList() == null) {
            usuario.setUsuarioList(new ArrayList<Usuario>());
        }
        if (usuario.getUsuarioList1() == null) {
            usuario.setUsuarioList1(new ArrayList<Usuario>());
        }
        if (usuario.getPruebaList() == null) {
            usuario.setPruebaList(new ArrayList<Prueba>());
        }
        if (usuario.getPreguntaList1() == null) {
            usuario.setPreguntaList1(new ArrayList<Pregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : usuario.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getPreguntaPK());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            usuario.setPreguntaList(attachedPreguntaList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : usuario.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuarioId());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            usuario.setUsuarioList(attachedUsuarioList);
            List<Usuario> attachedUsuarioList1 = new ArrayList<Usuario>();
            for (Usuario usuarioList1UsuarioToAttach : usuario.getUsuarioList1()) {
                usuarioList1UsuarioToAttach = em.getReference(usuarioList1UsuarioToAttach.getClass(), usuarioList1UsuarioToAttach.getUsuarioId());
                attachedUsuarioList1.add(usuarioList1UsuarioToAttach);
            }
            usuario.setUsuarioList1(attachedUsuarioList1);
            List<Prueba> attachedPruebaList = new ArrayList<Prueba>();
            for (Prueba pruebaListPruebaToAttach : usuario.getPruebaList()) {
                pruebaListPruebaToAttach = em.getReference(pruebaListPruebaToAttach.getClass(), pruebaListPruebaToAttach.getPruebaPK());
                attachedPruebaList.add(pruebaListPruebaToAttach);
            }
            usuario.setPruebaList(attachedPruebaList);
            List<Pregunta> attachedPreguntaList1 = new ArrayList<Pregunta>();
            for (Pregunta preguntaList1PreguntaToAttach : usuario.getPreguntaList1()) {
                preguntaList1PreguntaToAttach = em.getReference(preguntaList1PreguntaToAttach.getClass(), preguntaList1PreguntaToAttach.getPreguntaPK());
                attachedPreguntaList1.add(preguntaList1PreguntaToAttach);
            }
            usuario.setPreguntaList1(attachedPreguntaList1);
            em.persist(usuario);
            for (Pregunta preguntaListPregunta : usuario.getPreguntaList()) {
                preguntaListPregunta.getUsuarioList().add(usuario);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            for (Usuario usuarioListUsuario : usuario.getUsuarioList()) {
                usuarioListUsuario.getUsuarioList().add(usuario);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            for (Usuario usuarioList1Usuario : usuario.getUsuarioList1()) {
                usuarioList1Usuario.getUsuarioList().add(usuario);
                usuarioList1Usuario = em.merge(usuarioList1Usuario);
            }
            for (Prueba pruebaListPrueba : usuario.getPruebaList()) {
                Usuario oldUsuarioOfPruebaListPrueba = pruebaListPrueba.getUsuario();
                pruebaListPrueba.setUsuario(usuario);
                pruebaListPrueba = em.merge(pruebaListPrueba);
                if (oldUsuarioOfPruebaListPrueba != null) {
                    oldUsuarioOfPruebaListPrueba.getPruebaList().remove(pruebaListPrueba);
                    oldUsuarioOfPruebaListPrueba = em.merge(oldUsuarioOfPruebaListPrueba);
                }
            }
            for (Pregunta preguntaList1Pregunta : usuario.getPreguntaList1()) {
                Usuario oldUsuarioOfPreguntaList1Pregunta = preguntaList1Pregunta.getUsuario();
                preguntaList1Pregunta.setUsuario(usuario);
                preguntaList1Pregunta = em.merge(preguntaList1Pregunta);
                if (oldUsuarioOfPreguntaList1Pregunta != null) {
                    oldUsuarioOfPreguntaList1Pregunta.getPreguntaList1().remove(preguntaList1Pregunta);
                    oldUsuarioOfPreguntaList1Pregunta = em.merge(oldUsuarioOfPreguntaList1Pregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuarioId());
            List<Pregunta> preguntaListOld = persistentUsuario.getPreguntaList();
            List<Pregunta> preguntaListNew = usuario.getPreguntaList();
            List<Usuario> usuarioListOld = persistentUsuario.getUsuarioList();
            List<Usuario> usuarioListNew = usuario.getUsuarioList();
            List<Usuario> usuarioList1Old = persistentUsuario.getUsuarioList1();
            List<Usuario> usuarioList1New = usuario.getUsuarioList1();
            List<Prueba> pruebaListOld = persistentUsuario.getPruebaList();
            List<Prueba> pruebaListNew = usuario.getPruebaList();
            List<Pregunta> preguntaList1Old = persistentUsuario.getPreguntaList1();
            List<Pregunta> preguntaList1New = usuario.getPreguntaList1();
            List<String> illegalOrphanMessages = null;
            for (Prueba pruebaListOldPrueba : pruebaListOld) {
                if (!pruebaListNew.contains(pruebaListOldPrueba)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prueba " + pruebaListOldPrueba + " since its usuario field is not nullable.");
                }
            }
            for (Pregunta preguntaList1OldPregunta : preguntaList1Old) {
                if (!preguntaList1New.contains(preguntaList1OldPregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pregunta " + preguntaList1OldPregunta + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getPreguntaPK());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            usuario.setPreguntaList(preguntaListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuarioId());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            usuario.setUsuarioList(usuarioListNew);
            List<Usuario> attachedUsuarioList1New = new ArrayList<Usuario>();
            for (Usuario usuarioList1NewUsuarioToAttach : usuarioList1New) {
                usuarioList1NewUsuarioToAttach = em.getReference(usuarioList1NewUsuarioToAttach.getClass(), usuarioList1NewUsuarioToAttach.getUsuarioId());
                attachedUsuarioList1New.add(usuarioList1NewUsuarioToAttach);
            }
            usuarioList1New = attachedUsuarioList1New;
            usuario.setUsuarioList1(usuarioList1New);
            List<Prueba> attachedPruebaListNew = new ArrayList<Prueba>();
            for (Prueba pruebaListNewPruebaToAttach : pruebaListNew) {
                pruebaListNewPruebaToAttach = em.getReference(pruebaListNewPruebaToAttach.getClass(), pruebaListNewPruebaToAttach.getPruebaPK());
                attachedPruebaListNew.add(pruebaListNewPruebaToAttach);
            }
            pruebaListNew = attachedPruebaListNew;
            usuario.setPruebaList(pruebaListNew);
            List<Pregunta> attachedPreguntaList1New = new ArrayList<Pregunta>();
            for (Pregunta preguntaList1NewPreguntaToAttach : preguntaList1New) {
                preguntaList1NewPreguntaToAttach = em.getReference(preguntaList1NewPreguntaToAttach.getClass(), preguntaList1NewPreguntaToAttach.getPreguntaPK());
                attachedPreguntaList1New.add(preguntaList1NewPreguntaToAttach);
            }
            preguntaList1New = attachedPreguntaList1New;
            usuario.setPreguntaList1(preguntaList1New);
            usuario = em.merge(usuario);
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    preguntaListOldPregunta.getUsuarioList().remove(usuario);
                    preguntaListOldPregunta = em.merge(preguntaListOldPregunta);
                }
            }
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    preguntaListNewPregunta.getUsuarioList().add(usuario);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.getUsuarioList().remove(usuario);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    usuarioListNewUsuario.getUsuarioList().add(usuario);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                }
            }
            for (Usuario usuarioList1OldUsuario : usuarioList1Old) {
                if (!usuarioList1New.contains(usuarioList1OldUsuario)) {
                    usuarioList1OldUsuario.getUsuarioList().remove(usuario);
                    usuarioList1OldUsuario = em.merge(usuarioList1OldUsuario);
                }
            }
            for (Usuario usuarioList1NewUsuario : usuarioList1New) {
                if (!usuarioList1Old.contains(usuarioList1NewUsuario)) {
                    usuarioList1NewUsuario.getUsuarioList().add(usuario);
                    usuarioList1NewUsuario = em.merge(usuarioList1NewUsuario);
                }
            }
            for (Prueba pruebaListNewPrueba : pruebaListNew) {
                if (!pruebaListOld.contains(pruebaListNewPrueba)) {
                    Usuario oldUsuarioOfPruebaListNewPrueba = pruebaListNewPrueba.getUsuario();
                    pruebaListNewPrueba.setUsuario(usuario);
                    pruebaListNewPrueba = em.merge(pruebaListNewPrueba);
                    if (oldUsuarioOfPruebaListNewPrueba != null && !oldUsuarioOfPruebaListNewPrueba.equals(usuario)) {
                        oldUsuarioOfPruebaListNewPrueba.getPruebaList().remove(pruebaListNewPrueba);
                        oldUsuarioOfPruebaListNewPrueba = em.merge(oldUsuarioOfPruebaListNewPrueba);
                    }
                }
            }
            for (Pregunta preguntaList1NewPregunta : preguntaList1New) {
                if (!preguntaList1Old.contains(preguntaList1NewPregunta)) {
                    Usuario oldUsuarioOfPreguntaList1NewPregunta = preguntaList1NewPregunta.getUsuario();
                    preguntaList1NewPregunta.setUsuario(usuario);
                    preguntaList1NewPregunta = em.merge(preguntaList1NewPregunta);
                    if (oldUsuarioOfPreguntaList1NewPregunta != null && !oldUsuarioOfPreguntaList1NewPregunta.equals(usuario)) {
                        oldUsuarioOfPreguntaList1NewPregunta.getPreguntaList1().remove(preguntaList1NewPregunta);
                        oldUsuarioOfPreguntaList1NewPregunta = em.merge(oldUsuarioOfPreguntaList1NewPregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getUsuarioId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuarioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Prueba> pruebaListOrphanCheck = usuario.getPruebaList();
            for (Prueba pruebaListOrphanCheckPrueba : pruebaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Prueba " + pruebaListOrphanCheckPrueba + " in its pruebaList field has a non-nullable usuario field.");
            }
            List<Pregunta> preguntaList1OrphanCheck = usuario.getPreguntaList1();
            for (Pregunta preguntaList1OrphanCheckPregunta : preguntaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Pregunta " + preguntaList1OrphanCheckPregunta + " in its preguntaList1 field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pregunta> preguntaList = usuario.getPreguntaList();
            for (Pregunta preguntaListPregunta : preguntaList) {
                preguntaListPregunta.getUsuarioList().remove(usuario);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            List<Usuario> usuarioList = usuario.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.getUsuarioList().remove(usuario);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<Usuario> usuarioList1 = usuario.getUsuarioList1();
            for (Usuario usuarioList1Usuario : usuarioList1) {
                usuarioList1Usuario.getUsuarioList().remove(usuario);
                usuarioList1Usuario = em.merge(usuarioList1Usuario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
