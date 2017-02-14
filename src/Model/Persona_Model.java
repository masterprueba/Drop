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
 * @author Andres
 */
public class Persona_Model<P> extends General_model{

    public Persona_Model() {
    }
    
     public List<P> ConsultarCedula(TPersona persona) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TPersona where tperCedula = '" + persona.getTperCedula() + "'";
        List<P> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }
    
}
