/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TDatosBasicosPersona;
import Entity.TReferencia;
import Persistence.hibernateUtil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Andres
 */
public class Referencia_Model extends Models {

    public List<TReferencia> SelectAllWhere(TReferencia r) {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TReferencia r WHERE r.TDatosBasicosPersona.tdbpCedula = '" + r.getTDatosBasicosPersona().getTdbpCedula() + "'";
        List<TReferencia> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }
    
}
