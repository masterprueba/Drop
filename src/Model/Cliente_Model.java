/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TCliente;
import Persistence.hibernateUtil;

/**
 *
 * @author Andres
 */
public class Cliente_Model extends General_model{

    public Cliente_Model() {
    }
    
    public TCliente ConsultarCliente(String cc) {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TCliente where TPersona.tperCedula = '" + cc + "'";
        TCliente result = (TCliente) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }
}
