/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Login_Controller;
import Persistence.hibernateUtil;
import Entity.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class Models<T> {

    private T Objecto;
    protected Session s;

    public Models() {

    }

    public Serializable insertar(T obj, String modulo) {
        //boolean test = false;
        String indicador = "INSERT";
        Serializable id = null;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            id = s.save(obj);
            if (bitacora(obj, indicador, modulo)) {
                s.getTransaction().commit();
                System.err.println("comit");
            } else {
                id = null;
                System.out.println("roolback");
                s.getTransaction().rollback();
            }
            //test = true;
        } catch (Exception e) {
            System.out.println("Error al insertar " + e.getLocalizedMessage());
            s.getTransaction().rollback();
            id = null;
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

    public boolean editar(T entity, String modulo) {
        String indicador = "UPDATE";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.update(entity);
            if (bitacora(entity, indicador, modulo)) {
                s.getTransaction().commit();
                test = true;
                System.err.println("comit");
            } else {
                System.out.println("roolback");
                s.getTransaction().rollback();
            }
        } catch (Exception e) {
            s.getTransaction().rollback();
            System.out.println("Error al editar " + e.getLocalizedMessage());
        }
        return test;
    }
//

    public boolean eliminar(T entity, String modulo) {
        String indicador = "DELETE";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.delete(entity);
            if (bitacora(entity, indicador, modulo)) {
                s.getTransaction().commit();
                test = true;
                System.err.println("comit");
            } else {
                System.out.println("roolback");
                s.getTransaction().rollback();
            }
        } catch (Exception e) {
            s.getTransaction().rollback();
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

    public boolean bitacora(Object obj, String indicador, String modulo) {
        boolean commit = false;
        try {
            if (!s.isConnected()) { // verifica que  haya una sesion abierta si no hay crea una
                s = hibernateUtil.getSessionFactory();
                s.beginTransaction();
                commit = true;
            }
            final TBitacora bitacora = new TBitacora();
            bitacora.setTbitFecha(new Date());
            bitacora.setTLogin(Login_Controller.getUsuarioLogueado());
            bitacora.setTbitIdentificador(indicador);
            bitacora.setTbitRegistro(obj.toString());
            bitacora.setTbitClassname(obj.getClass().getName());
            bitacora.setTbitModulo(modulo);
            s.save(bitacora);  //GUARDA BITACORA
            if (commit) { // si se creo una sesion ejecuta un commit
                s.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            if (commit) {
                s.getTransaction().rollback();
            }
            System.err.println("CATCH " + e.getLocalizedMessage());
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
