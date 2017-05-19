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
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "SELECT " +
        "DATE_FORMAT(prestamo.tpreFechaEntrega, " +
        "'%y-%m-%d') as fecha , " +
        "prestamo.TPersona.TDatosBasicosPersona.tdbpCedula as cedula, " +
        "prestamo.tpreValorPrestamo as prestado, " +
        "prestamo.tpreValorTotal as invertido, " +
        "(SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId) as abono " +
        "FROM " +
        "TPrestamo as prestamo " +
        "WHERE " +
        "prestamo.tpreFechaEntrega BETWEEN '"+fini+"' AND '"+ffin+"'";
        Query r = s.createQuery(query);                    
            List<T> result = r.list();
        s.getTransaction().commit();

        return result;
    }
}
