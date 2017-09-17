/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistence.hibernateUtil;
import java.util.List;

/**
 *
 * @author ITERIA
 */
public class Cierre_Model extends Models {

    public List consultarAllCierre() {
        List result;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TCierre";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }
}
