/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TDatosBasicosPersona;
import Persistence.hibernateUtil;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DatosBasicosPersona_Model extends Models {
    
    public TDatosBasicosPersona consultarCedula(TDatosBasicosPersona persona) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TDatosBasicosPersona where tdbpCedula = '" + persona.getTdbpCedula() + "'";
        TDatosBasicosPersona result = (TDatosBasicosPersona) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }

    public List<TDatosBasicosPersona> ConsultarPersonasConLogin() {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "select persona from TDatosBasicosPersona as persona  inner join persona.TLogins as login on  login.TDatosBasicosPersona.tdbpId = persona";
        List<TDatosBasicosPersona> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }
}
