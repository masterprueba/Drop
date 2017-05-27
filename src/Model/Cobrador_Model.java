/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TCobrador;
import Persistence.hibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author Usuario
 */
public class Cobrador_Model extends Models {
    
    public TCobrador SelectOne(TCobrador p) {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TCobrador p WHERE p.tcobNombre = '" + p.getTcobNombre()+"'";
        TCobrador result = (TCobrador) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }
    
    public TCobrador first() {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TCobrador p ORDER BY p.id";   
        Query q = s.createQuery(query);
        q.setMaxResults(1);
        TCobrador result = (TCobrador) q.uniqueResult();
        s.getTransaction().commit();

        return result;
    }
}
