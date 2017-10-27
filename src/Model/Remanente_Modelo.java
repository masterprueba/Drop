/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TRemanente;
import Persistence.hibernateUtil;
import java.util.List;

/**
 *
 * @author PC
 */
public class Remanente_Modelo extends Models {

    public TRemanente comprobarExistencia(int dia, int mes, int año) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TRemanente as remanente   where remanente.treFecha =  '" + año + "-" + mes + "-" + dia + "'";
        TRemanente result = (TRemanente) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();
        return result;
    }

    public List<TRemanente> traerRemanentes(String sql) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TRemanente as remanente   where  " + sql;
        List<TRemanente> result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }
}
