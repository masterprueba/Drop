/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Login_Controller;
import Persistence.hibernateUtil;
import Entity.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;

/**
 *
 * @author Usuario
 */
public class Models<T> {

    private T Objecto;
    protected Session s;

    public Models() {

    }

    public Serializable insertar(T obj) {
        //boolean test = false;
        String indicador = "INSERT";
        Serializable id = null;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            id = s.save(obj);            
            if (Bitacora(obj, indicador)) {
                s.getTransaction().commit();
                System.err.println("comit");
            } else {
                System.out.println("roolback");
                s.getTransaction().rollback();
                
            }
            //test = true;
        } catch (Exception e) {
            System.out.println("Error al insertar " + e.getLocalizedMessage());
            s.getTransaction().rollback();
        }
        return id;
    }

    public T consultar(Class clase, int id) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        Objecto = (T) s.get(clase, id);
        s.getTransaction().commit();
        return Objecto;
    }

    public boolean editar(T entity) {
        String indicador = "UPDATE";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.update(entity);
            s.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            System.out.println("Error al editar " + e.getLocalizedMessage());
        }
        return test;
    }
//

    public boolean eliminar(T entity) {
        String indicador = "DELETE";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.delete(entity);
            s.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getLocalizedMessage());
        }
        return test;
    }

//
    public List<T> findAll(Class clase) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        List<T> list = s.createCriteria(clase).list();
        s.getTransaction().commit();
        return list;
    }

    private boolean Bitacora(Object obj, String indicador) {

        try {
            TBitacora bitacora = new TBitacora();
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(obj);
            bitacora.setTbitFecha(new Date());
            bitacora.setTLogin(Login_Controller.getUsuarioLogueado());
            bitacora.setTbitIdentificador(indicador);
            bitacora.setTbitRegistro(jsonInString);
            s.save((T) bitacora);
            return true;
        } catch (IOException e) {
            System.out.println("catch "+e.getLocalizedMessage());
            return false;
        }
    }
//
//    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }
//
//    public int count() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
//    }
    
}
