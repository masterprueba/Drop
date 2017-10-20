/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Prestamo_Controller;
import Entity.TCuota;
import Entity.TPrestamo;
import Entity.TReajuste;
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
                + "(SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId AND cuota.TPago.tpagId in (1,2,5,6,7)) as abono, "
                + "(prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId AND cuota.TPago.tpagId in (1,2,5,6,7))) as deuda, "
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
                + "(SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId AND cuota.TPago.tpagId in (1,2,5,6,7)) as abono, "
                + "(prestamo.tpreValorTotal - (SELECT SUM(cuota.tcuoAbono) FROM TCuota as cuota WHERE prestamo.tpreId = cuota.TPrestamo.tpreId AND cuota.TPago.tpagId in (1,2,5,6,7))) as deuda, "
                + "(SELECT SUM(extra.tmulValor) FROM TMulta as extra WHERE prestamo.tpreId = extra.TPrestamo.tpreId AND extra.tmulEstado = 'realizada') as extra "
                + "FROM "
                + "TPrestamo as prestamo "
                + "WHERE concat(prestamo.TPersona.TDatosBasicosPersona.tdbpNombre,' ',prestamo.TPersona.TDatosBasicosPersona.tdbpApellido) like '%" + nombre + "%' AND "
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

    public List<T> prestamoPorFecha(String inicio, String fin) {
        List<T> result;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TPrestamo where tpreFechaEntrega  between  '" + inicio + " 00:00:00' and  '" + fin + " 23:59:59'  ORDER BY tpreId ASC";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }

    public List<T> abonoPorFecha(String inicio, String fin, String sql) {
        List<T> result;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "select cuota from TCuota as cuota join cuota.TCobrador as cobrador join cuota.TPago as pago where cuota.tcuoFecha  between'" + inicio + " 00:00:00' and  '" + fin + " 23:59:59' " + sql + " ORDER BY cuota.tcuoId ASC";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }
    public List<T> multaPorFecha(String inicio, String fin) {
        List<T> result;
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "from TMulta as multa  where multa.tmulFecha  between'" + inicio + " 00:00:00' and  '" + fin + " 23:59:59' ORDER BY multa.tmulId ASC";
        result = s.createQuery(query).list();
        s.getTransaction().commit();
        return result;
    }
    
    public Serializable insertReajuste(TPrestamo prestamo, TCuota cuota, TReajuste tr){
        String indicador = "AGREGO";
        Serializable id = null;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            System.out.println("inicio begin");
            Serializable idtemp = s.save(cuota);
            s.update(prestamo);
            s.save(tr);
        if (bitacora(cuota, indicador, "Prestamo") && bitacora(prestamo,"EDITO","Prestamo")) {
                s.getTransaction().commit();
                id = idtemp;
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
