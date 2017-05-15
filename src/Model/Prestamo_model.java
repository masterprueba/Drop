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
 * @author Breiner
 */
public class Prestamo_model extends Models{

    //otra prueba andres
    
    public Prestamo_model() {
    }
    
    public List<String> ConsultarUsuarioContraseña() {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TLogin where tlogUserLogin = and tlogPassword = '";
        List<String> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }
}
