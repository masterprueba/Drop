/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TBanco;
import Entity.TCuentaPagar;
import Persistence.hibernateUtil;
import java.util.List;

/**
 *
 * @author afvc2203
 */
public class Cuentas_Model extends Models {

    public Cuentas_Model() {
    }

    public List<TCuentaPagar> SelectAllWhere() {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TCuentaPagar";
        List<TCuentaPagar> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

}
