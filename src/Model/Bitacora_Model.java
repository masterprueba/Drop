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
            case "GASTOS":
                sql = "and tbitModulo='GASTOS'";
                break;
            case "ABONO":
                sql = "and tbitModulo='PRESTAMO'";
                break;
            case "MULTA":
                sql = "and tbitModulo='PRESTAMO' and tbitClassname = 'Entity.TMulta' ";
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

    public List<B> consultarAllModulo(String modulo) {
        List<B> result = null;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String sql = "";
        switch (modulo) {
            case "PRESTAMO":
                sql = "where tbitModulo = 'PRESTAMO' and tbitClassname = 'Entity.TPrestamo'";
                break;
            case "CLIENTE":
                sql = "where tbitModulo='CLIENTE'";
                break;
            case "GASTOS":
                sql = "where tbitModulo='GASTOS'";
                break;
            case "ABONO":
                sql = "where tbitModulo='PRESTAMO'";
                break;
            case "MULTA":
                sql = "where tbitModulo = 'PRESTAMO' and tbitClassname IN('Entity.TMulta','Entity.TPrestamo')";
                break;
        }
        String query = "from TBitacora " + sql + " ORDER BY tbitFecha DESC";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }
}
