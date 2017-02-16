/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TLogin;
import Entity.TPersona;
import Persistence.hibernateUtil;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Login_Model<L> extends General_model {

    //consulta el usuario por medio del usuario y contraseña
    public List<L> ConsultarUsuarioContraseña(TLogin usuario) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TLogin where tlogUserLogin = '" + usuario.getTlogUserLogin() + "' and tlogPassword = '" + usuario.getTlogPassword() + "'";
        List<L> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public TLogin ConsultarUsuario(TLogin usuario) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TLogin where tlogUserLogin ='" + usuario.getTlogUserLogin()+ "'";
        TLogin result = (TLogin) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }
}
