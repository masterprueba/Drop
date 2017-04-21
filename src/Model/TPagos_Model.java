/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TPago;
import Persistence.hibernateUtil;

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
}
