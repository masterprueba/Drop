/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TPago;
import Persistence.hibernateUtil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Usuario
 */
public class TPagos_Model extends Models{
    public TPago SelectOne(TPago p) {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TPago p WHERE p.tipo = '" + p.getTipo()+"'";
        TPago result = (TPago) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }
    
    public TPago first() {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TPago p ORDER BY p.id"; 
        Query q = s.createQuery(query);
        q.setMaxResults(1);
        TPago result = (TPago) q.uniqueResult();
        s.getTransaction().commit();

        return result;
    }
    
    public List<TPago> listPagos(Class clase) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TPago p WHERE p.tipo != 'Refinanciado-.'";
        Query r = s.createQuery(query);
        List<TPago> list = r.list();        
        s.getTransaction().commit();
        return list;
    }
}
