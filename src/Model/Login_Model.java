/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TLogin;
import Persistence.hibernateUtil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 * @param <L>
 */
public class Login_Model<L> extends Models {

    //consulta el usuario por medio del usuario y contraseña
    public List<L> ConsultarUsuarioContraseña(TLogin usuario) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TLogin where tlogUserLogin = '" + usuario.getTlogUserLogin() + "' and tlogPassword = '" + usuario.getTlogPassword() + "'";
        List<L> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public TLogin consultarUsuario(TLogin usuario, int Via) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TLogin where tlogUserLogin ='" + usuario.getTlogUserLogin() + "'";
        if (Via == 2) {
            query = "from TLogin where tlogUserLogin ='" + usuario.getTlogUserLogin() + "' and TDatosBasicosPersona.tdbpCedula <> " + usuario.getTDatosBasicosPersona().getTdbpCedula();
        } else if (Via == 3) {
            query = "from TLogin where TDatosBasicosPersona.tdbpId ='" + usuario.getTDatosBasicosPersona().getTdbpId() + "'";
        }
        TLogin result = (TLogin) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();

        return result;
    }

    public Date Trial() {
        s.beginTransaction();
        String query = "select max(tbitFecha) from TBitacora";
        Date fecha = (Date) s.createQuery(query).uniqueResult();
        s.getTransaction().commit();
        return fecha;
    }
}
