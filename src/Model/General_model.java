/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistence.hibernateUtil;
import Entity.*;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;


/**
 *
 * @author Usuario
 */
public class General_model<T> {
    
    private T Objecto;
    protected Session s;    
    
    public General_model() {
        
    }

    public Serializable insertar(T obj){
        //boolean test = false;
        Serializable id = null;
        try {                        
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            id = s.save(obj);
            s.getTransaction().commit();
            //test = true;
        } catch (Exception e) {
            System.out.println("Error al insertar "+e.getLocalizedMessage());
        }
        return id;
    }
    
   public T consultar(Class clase, int id){
       s = hibernateUtil.getSessionFactory();
       s.beginTransaction();
       Objecto = (T) s.get(clase ,id);
        s.getTransaction().commit();        
        return Objecto;
   } 

    public boolean editar(T entity) {
        boolean test = false;
        try {                        
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.update(entity);
            s.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            System.out.println("Error al editar "+e.getLocalizedMessage());
        }
        return test;
    }
//
    public boolean eliminar(T entity) {
        boolean test = false;
        try {                        
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.delete(entity);
            s.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            System.out.println("Error al editar "+e.getLocalizedMessage());
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
