/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistence.hibernateUtil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Usuario
 */
public class Multa_Model<G> extends Models{
    
    public List<G> ConsultarMes(int Mes, int Año, String sql) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TMulta where MONTH(tmulFecha) = " + Mes + " and YEAR(tmulFecha)= " + Año + " " + sql;
        List<G> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public List<G> ConsultarPorFechas(String inicio, String fin, String sql) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TMulta where tmulFecha  BETWEEN '" + inicio + "' AND '" + fin + "' " + sql;
        List<G> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public List<G> findAll(Class clase, String sql) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TMulta where " + sql;
        List<G> list = s.createQuery(query).list();
        s.getTransaction().commit();
        return list;
    }
    
    public Object getPrestamo(String cc) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TPrestamo as prestamo where prestamo.TPersona.TDatosBasicosPersona.tdbpCedula = "+cc+" ORDER BY prestamo.tpreFechaEntrega DESC";
        Query q = s.createQuery(query).setMaxResults(1);
        Object obj = q.uniqueResult();
        s.getTransaction().commit();
        return obj;
    }
}
