/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistence.hibernateUtil;
import org.hibernate.Session;
import Entity.*;
import java.util.List;


/**
 *
 * @author Usuario
 */
public class General_model<T> {
    
    private T Objecto;
    protected Session s;    
    
    public General_model() {
    }

    public boolean insertar(T obj){
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.save(obj);
            s.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            System.out.println("Error mio "+e.getLocalizedMessage());
        }
        return test;
    }
    
   public T consultar(Class clase, int id){
       s = hibernateUtil.getSessionFactory();
       s.beginTransaction();
       Objecto = (T) s.get(clase ,id);
        s.getTransaction().commit();        
        return Objecto;
   } 
   
//   private Class<T> entityClass;    
//
//
//
//    public void create(T entity) {
//        getEntityManager().persist(entity);
//    }
//
//    public void edit(T entity) {
//        getEntityManager().merge(entity);
//    }
//
//    public void remove(T entity) {
//        getEntityManager().remove(getEntityManager().merge(entity));
//    }
//
//    public T find(Object id) {
//        return getEntityManager().find(entityClass, id);
//    }
//
//    public List<T> findAll() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        return getEntityManager().createQuery(cq).getResultList();
//    }
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
