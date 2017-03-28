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
public class Bitacora_Model<B> extends Models {

    public List<B> consultarFechaBitsesion(String inicio, String fin) {
        List<B> result = null;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TBitacora where tbitFecha  between  '" + inicio + " 00:00:00' and  '" + fin + " 23:59:59'  and  tbitIdentificador = 'INICIO'";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }
}
