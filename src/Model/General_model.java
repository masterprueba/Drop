/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Login_Controller;
import Persistence.hibernateUtil;
import Entity.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class General_model<T> {

    private T Objecto;
    protected Session s;

    public General_model() {

    }

    public Serializable insertar(T obj, boolean bitacora) {
        //boolean test = false;
        String indicador = "INSERT";
        Serializable id = null;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            id = s.save(obj);
            if (bitacora) {
                if (Bitacora(obj, indicador)) {
                    s.getTransaction().commit();
                } else {
                    s.getTransaction().rollback();
                }
            } else {
                s.getTransaction().commit();
            }
            //test = true;
        } catch (Exception e) {
            System.out.println("Error al insertar " + e.getLocalizedMessage());
            s.getTransaction().rollback();
        }
        return id;
    }

    public T consultar(Class clase, int id) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        Objecto = (T) s.get(clase, id);
        s.getTransaction().commit();
        return Objecto;
    }

    public boolean editar(T entity) {
        String indicador = "UPDATE";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.update(entity);
            s.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            System.out.println("Error al editar " + e.getLocalizedMessage());
        }
        return test;
    }
//

    public boolean eliminar(T entity) {
        String indicador = "DELETE";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.delete(entity);
            s.getTransaction().commit();
            test = true;
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getLocalizedMessage());
        }
        return test;
    }

//
    public List<T> findAll(Class clase) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        List<T> list = s.createCriteria(clase).list();
        s.getTransaction().commit();
        return list;
    }

    private boolean Bitacora(Object obj, String indicador) {
        TBitacora bitacora = new TBitacora();
        String clase = obj.getClass().getName();                
//        switch (clase) {
//            case "Entity.TCodeudor":
//                TCodeudor codeudor = (TCodeudor) obj;
//                cadena = codeudor.getTcodId().toString() + ";" + codeudor.getTcodClienteCedula() + ";" + codeudor.getTcodCodeudorCedula();
//                break;
//            case "Entity.TCuota":
//                TCuota cuota = (TCuota) obj;
//                cadena = cuota.getTPrestamo().getTpreId() + ";" + cuota.getTcuoFecha() + ";" + cuota.getTcuoAbono() + ";" + cuota.getTcuoNuevoSaldo() + ";" + cuota.getTcuoCuotasPagadas() + ";" + cuota.getTcuoMetodoPago() + ";" + cuota.getTcuoCobrador();
//                break;
//            case "Entity.TDatosBasicosPersona":
//                TDatosBasicosPersona datosper = (TDatosBasicosPersona) obj;
//                cadena = datosper.getTdbpId() + ";" + datosper.getTdbpCedula() + ";" + datosper.getTdbpNombre() + ";" + datosper.getTdbpApellido() + ";" + datosper.getTdbpTel();
//                break;
//            case "Entity.TGasto":
//                TGasto gasto = (TGasto) obj;
//                cadena = gasto.getTgasId() + ";" + gasto.getTgasDesc() + ";" + gasto.getTgasFecha() + ";" + gasto.getTgasCosto();
//                break;
//            case "Entity.TLogin":
//                TLogin login = (TLogin) obj;
//                cadena = login.getTlogId() + ";" + login.getTDatosBasicosPersona().getTdbpId() + ";" + login.getTlogUserLogin() + ";" + login.getTlogPassword();
//                break;
//            case "Entity.TPersona":
//                TPersona persona = (TPersona) obj;
//                cadena = persona.getTperId() + ";" + persona.getTDatosBasicosPersona().getTdbpId() + ";" + persona.getTperCasDir() + ";" + persona.getTperCasPro() + ";" + persona.getTperEmpNom() + ";" + persona.getTperEmpDir() + ";" + persona.getTperEmpTel() + ";" + persona.getTperCasTipo() + ";" + persona.getTperTipo() + ";" + persona.getTperIdenty();
//                break;
//            case "Entity.TPrestamo":
//                TPrestamo prestamo = (TPrestamo) obj;
//                cadena = prestamo.getTpreId() + ";" + prestamo.getTPersona().getTperId() + ";" + prestamo.getTpreValorPrestamo() + ";" + prestamo.getTpreNumCuotas() + ";" + prestamo.getTpreIntereses() + ";" + prestamo.getTpreMetodPago() + ";" + prestamo.getTpreFechaEntrega() + ";" + prestamo.getTpreValorTotal() + ";" + prestamo.getTpreValorCuota();
//                break;
//            case "Entity.TReferencia":
//                TReferencia referencia = (TReferencia) obj;
//                cadena = referencia.getTrefId() + ";" + referencia.getTDatosBasicosPersona().getTdbpId() + ";" + referencia.getTrefTipo() + ";" + referencia.getTrefNombre() + ";" + referencia.getTrefApellido() + ";" + referencia.getTrefTelefono();
//                break;
//        }
        System.err.println(obj.toString());
        try {
            bitacora.setTbitFecha(new Date());
            bitacora.setTLogin(Login_Controller.getUsuarioLogueado());
            bitacora.setTbitIdentificador(indicador);
            bitacora.setTbitRegistro(obj.toString());
            s.save((T) bitacora);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//
//    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }
//
//    public int count() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
//    }

}
