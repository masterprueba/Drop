/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TBanco;
import Persistence.hibernateUtil;
import java.util.List;

/**
 *
 * @author afvc2203
 */
public class Banco_Model extends Models {

    public Banco_Model() {
    }

    public List<TBanco> SelectAllWhere() {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TBanco";
        List<TBanco> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

}
