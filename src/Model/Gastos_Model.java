/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistence.hibernateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ITERIA
 */
public class Gastos_Model<G> extends General_model {

    public List<G> ConsultarGastosMes(int Mes, int Año) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TGasto where MONTH(tgasFecha) = " + Mes + " and YEAR(tgasFecha)= " + Año;
        List<G> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public List<G> ConsultarPorFechas(String inicio, String fin) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        System.err.println(inicio);
        String query = "from TGasto where tgasFecha  BETWEEN '" + inicio + "' AND '" + fin + "'";
        List<G> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }
}
