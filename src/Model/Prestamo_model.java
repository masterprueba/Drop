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
 * @author Breiner
 */
public class Prestamo_model<T> extends Models{

    //otra prueba andres
    
    public Prestamo_model() {
    }
    
    public List<T> informePrestamo(String fini, String ffin) {
        System.out.println("ini "+fini+" fin "+ffin);
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "SELECT  DATE_FORMAT(cuota.TPrestamo.tpreFechaEntrega,'%y-%m-%d') as fecha ,"
                + "cuota.TPrestamo.TPersona.TDatosBasicosPersona.tdbpCedula as cedula,"
                + " (SELECT SUM(prestamo.tpreValorPrestamo) FROM TPrestamo as prestamo WHERE prestamo.TPersona.tperId = cuota.TPrestamo.TPersona.tperId) as prestado,"
                + "  (SELECT SUM(prestamo.tpreValorTotal) FROM TPrestamo as prestamo WHERE prestamo.TPersona.tperId = cuota.TPrestamo.TPersona.tperId) as invertido,"
                + " Sum(cuota.tcuoAbono) as pagado "
                + "FROM TCuota as cuota "
                + "WHERE cuota.TPrestamo.tpreFechaEntrega BETWEEN '"+fini+"' AND '"+ffin+"'";
        Query r = s.createQuery(query);                    
            List<T> result = r.list();
        s.getTransaction().commit();

        return result;
    }
}
