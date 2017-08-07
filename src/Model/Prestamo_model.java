/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Prestamo_Controller;
import Persistence.hibernateUtil;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Breiner
 */
public class Prestamo_model<T> extends Models {

    //otra prueba andres
    public Prestamo_model() {
    }

    public List<T> informePrestamo(String fini, String ffin) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "SELECT "
                + "DATE_FORMAT(prestamo.tpreFechaEntrega, "
                + "'%y-%m-%d') as fecha , "
                + "prestamo.TPersona.TDatosBasicosPersona.tdbpCedula as cedula, "
                + "concat(prestamo.TPersona.TDatosBasicosPersona.tdbpNombre,' ',prestamo.TPersona.TDatosBasicosPersona.tdbpApellido) as Cliente, "
                + "prestamo.tpreValorPrestamo as prestado, "
                + "(prestamo.tpreValorPrestamo-prestamo.tpreRefinanciado) as invertido, "
                + "prestamo.tpreValorTotal as valortotal, "
                + "(SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId) as abono, "
                + "(prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId)) as deuda, "
                + "(SELECT SUM(extra.tmulValor) FROM TMulta as extra WHERE prestamo.tpreId = extra.TPrestamo.tpreId AND extra.tmulEstado = 'realizada') as extra "
                + "FROM "
                + "TPrestamo as prestamo "
                + "WHERE "
                + "prestamo.tpreFechaEntrega BETWEEN '" + fini + "' AND '" + ffin + " 23:59:59'";
        Query r = s.createQuery(query);
        List<T> result = r.list();
        s.getTransaction().commit();

        return result;
    }
    
    public List<T> informePrestamoXn(String fini, String ffin, String nombre) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "SELECT "
                + "DATE_FORMAT(prestamo.tpreFechaEntrega, "
                + "'%y-%m-%d') as fecha , "
                + "prestamo.TPersona.TDatosBasicosPersona.tdbpCedula as cedula, "
                + "concat(prestamo.TPersona.TDatosBasicosPersona.tdbpNombre,' ',prestamo.TPersona.TDatosBasicosPersona.tdbpApellido) as Cliente, "
                + "prestamo.tpreValorPrestamo as prestado, "
                + "(prestamo.tpreValorPrestamo-prestamo.tpreRefinanciado) as invertido, "
                + "prestamo.tpreValorTotal as valortotal, "
                + "(SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId) as abono, "
                + "(prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId)) as deuda, "
                + "(SELECT SUM(extra.tmulValor) FROM TMulta as extra WHERE prestamo.tpreId = extra.TPrestamo.tpreId AND extra.tmulEstado = 'realizada') as extra "
                + "FROM "
                + "TPrestamo as prestamo "
                + "WHERE concat(prestamo.TPersona.TDatosBasicosPersona.tdbpNombre,' ',prestamo.TPersona.TDatosBasicosPersona.tdbpApellido) like '%"+nombre+"%' AND "
                + "prestamo.tpreFechaEntrega BETWEEN '" + fini + "' AND '" + ffin + " 23:59:59'";
        Query r = s.createQuery(query);
        List<T> result = r.list();
        s.getTransaction().commit();

        return result;
    }

    public List<T> refinanciaPrestamo() {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "SELECT "
                + "prestamo.tpreId, "
                + "concat(prestamo.TPersona.TDatosBasicosPersona.tdbpNombre,' ',prestamo.TPersona.TDatosBasicosPersona.tdbpApellido) as nombre, "
                + "prestamo.TPersona.TDatosBasicosPersona.tdbpCedula as cedula, "
                + "prestamo.tpreValorTotal as valortotal, "
                + "(prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId)) as deuda, "
                + "prestamo.tpreNumCuotas as cuotas "
                + "FROM "
                + "TPrestamo as prestamo "
                + "WHERE  prestamo.tpreId = (SELECT MAX(tpreId) FROM TPrestamo WHERE TPersona.TDatosBasicosPersona.tdbpCedula = prestamo.TPersona.TDatosBasicosPersona.tdbpCedula) "
                + "GROUP BY prestamo.TPersona.TDatosBasicosPersona.tdbpCedula ";

        Query r = s.createQuery(query);
        List<T> result = r.list();
        s.getTransaction().commit();
        Iterator itr = result.iterator();
        Object[] f = new Object[10];
        boolean existe = false;
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            System.out.println("1-" + obj[0] + " 2-" + obj[1] + " 3-" + obj[2] + " 4-" + obj[4] + " 5-" + obj[3]);
        }
        return result;
    }

    public Serializable insertarPrestamo(T obj, String modulo) {
        //boolean test = false;
        String indicador = "AGREGO";
        Serializable id = null;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            System.out.println("inicio begin");
            id = s.save(obj);
            System.out.println("guarde obj ");
            if (Prestamo_Controller.listc != null) {
                if (Prestamo_Controller.listc.size() > 0) {
                    System.out.println("entre a nuevo insert tamaño " + Prestamo_Controller.listc.size());
                    for (int i = 0; i < Prestamo_Controller.listc.size(); i++) {
                        System.out.println("entre al for");
                        s.save(Prestamo_Controller.listc.get(i));
                        System.out.println("guarde el " + i);
                        bitacora(Prestamo_Controller.listc.get(i), indicador, modulo);
                        System.out.println("bitacora " + 1);
                    }
                }
            }
            if (bitacora(obj, indicador, modulo)) {
                s.getTransaction().commit();
                System.err.println("comit");
            } else {
                id = null;
                System.out.println("roolback");
                s.getTransaction().rollback();
            }
            //test = true;
        } catch (Exception e) {
            System.out.println("Error al insertar " + e.getLocalizedMessage());
            s.getTransaction().rollback();
            id = null;
        }
        return id;
    }
}
