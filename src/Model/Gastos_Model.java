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
public class Gastos_Model<G> extends Models {

    public List<G> ConsultarGastosMes(int Mes, int Año, String sql) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TGasto where MONTH(tgasFecha) = " + Mes + " and YEAR(tgasFecha)= " + Año + " " + sql;
        List<G> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public List<G> ConsultarPorFechas(String inicio, String fin, String sql) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TGasto where tgasFecha  BETWEEN '" + inicio + "' AND '" + fin + "' " + sql;
        List<G> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public List<G> findAll(Class clase, String sql) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TGasto where " + sql;
        List<G> list = s.createQuery(query).list();
        s.getTransaction().commit();
        return list;
    }
}
