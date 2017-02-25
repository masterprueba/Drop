/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TPersona;
import Persistence.hibernateUtil;
import java.util.List;

/**
 *
 * @author Andres from TPersona as persona where persona.TDatosBasicosPersona.tdbpCedula = 1
 */
public class Persona_Model extends General_model {

    public Persona_Model() {
    }

    

    public TPersona consultarCliente(TPersona persona) {
        System.out.println("Model.Persona_Model.consultarCliente()");
        s = hibernateUtil.getSessionFactory();
        System.out.println("1");
        s.beginTransaction();
        System.out.println("2"+persona.getTDatosBasicosPersona().getTdbpCedula());
        String query = "from TPersona as persona where persona.TDatosBasicosPersona.tdbpCedula = " + persona.getTDatosBasicosPersona().getTdbpCedula()+" and tperTipo = '"+persona.getTperTipo()+"'";
        System.out.println(query);
        TPersona result = (TPersona) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();
        return result;
    }
    
    public TPersona SelectOne(TPersona p) {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TPersona p WHERE p.TDatosBasicosPersona.tdbpCedula = '" + p.getTDatosBasicosPersona().getTdbpCedula() + "' AND p.tperTipo = '" + p.getTperTipo() + "'";
        TPersona result = (TPersona) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }

    public List<TPersona> SelectAllWhere(TPersona p) {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TPersona p WHERE p.tperTipo = '" + p.getTperTipo() + "'";
        List<TPersona> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }    
}
