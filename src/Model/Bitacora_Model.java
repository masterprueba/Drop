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

    public List<B> consultarBitacora(String inicio, String fin, String modulo) {
        List<B> result = null;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String sql = "";
        switch (modulo) {
            //PARA INICIO DE ESION
            case "INICIO":
                sql = "and  tbitIdentificador = 'INICIO' ";
                break;
            //PARA MODULO PRESTAMO
            case "PRESTAMO":
                sql = "and tbitModulo = 'PRESTAMO' and tbitClassname = 'Entity.TPrestamo' ";
                break;
            case "CLIENTE":
                sql = "and tbitModulo='CLIENTE'";
                break;
        }
        String query = "from TBitacora where tbitFecha  between  '" + inicio + " 00:00:00' and  '" + fin + " 23:59:59' " + sql + " ORDER BY tbitFecha DESC";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }

    public List<B> consultarInicioUsuario(String id) {
        List<B> result = null;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "select bitacora from TBitacora as bitacora  inner join bitacora.TLogin as login on  login.TDatosBasicosPersona.tdbpId = " + id + "  where  bitacora.tbitIdentificador = 'INICIO' ORDER BY  bitacora.tbitFecha DESC";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }
}
