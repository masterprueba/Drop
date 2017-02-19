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
 * @author Andres
 */
public class Persona_Model<P> extends General_model {

    public Persona_Model() {
    }

    public TDatosBasicosPersona ConsultarCedula(TDatosBasicosPersona persona) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TDatosBasicosPersona where tdbpCedula = '" + persona.getTdbpCedula() + "'";
        TDatosBasicosPersona result = (TDatosBasicosPersona) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }

    public List<P> ConsultarPersonasConLogin() {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "select persona from TDatosBasicosPersona as persona  inner join persona.TLogins as login on  login.TDatosBasicosPersona.tdbpId = persona";
        List<P> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public TDatosBasicosPersona consultarCliente(String cc) {
        return null;
    }
}
