/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistence.hibernateUtil;
import java.util.Iterator;
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
        "(prestamo.tpreValorPrestamo-prestamo.tpreRefinanciado) as invertido, "+        
        "prestamo.tpreValorTotal as valortotal, " +
        "prestamo.tpreNumCuotas as numcuotas, " +
        "(SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId) as abono, " +
        "(prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId)) as deuda, "+
        "(SELECT SUM(extra.tmulValor) FROM TMulta as extra WHERE prestamo.tpreId = extra.TPrestamo.tpreId AND extra.tmulEstado = 'realizada') as extra " +
        "FROM " +
        "TPrestamo as prestamo " +        
        "WHERE " +
        "prestamo.tpreFechaEntrega BETWEEN '"+fini+"' AND '"+ffin+" 23:59:59'";
        Query r = s.createQuery(query);                    
            List<T> result = r.list();
        s.getTransaction().commit();

        return result;
    }
    
    public List<T> refinanciaPrestamo() {        
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "SELECT " + 
        "max(prestamo.tpreId),"+
        "concat(prestamo.TPersona.TDatosBasicosPersona.tdbpNombre,' ',prestamo.TPersona.TDatosBasicosPersona.tdbpApellido) as nombre, " +
        "prestamo.TPersona.TDatosBasicosPersona.tdbpCedula as cedula, " +
        "prestamo.tpreValorTotal as valortotal, " +                
        "CASE WHEN (prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId)) <= 0 THEN '' ELSE (prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId)) END as deuda, "+
                "prestamo.tpreNumCuotas as cuotas " +
        "FROM " +
        "TPrestamo as prestamo "+
        "GROUP BY prestamo.TPersona.TDatosBasicosPersona.tdbpCedula";
        Query r = s.createQuery(query);                    
            List<T> result = r.list();
        s.getTransaction().commit();        
        Iterator itr = result.iterator();                
        Object[] f = new Object[10];
        boolean existe = false;
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next(); 
            System.out.println("1-"+obj[0]+" 2-"+obj[1]+" 3-"+obj[2]+" 4-"+obj[4]);
        }
        return result;
    }
}
