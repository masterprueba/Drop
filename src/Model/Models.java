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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class Models<T> {

    private T Objecto;
    protected Session s;

    public Models() {

    }

    public Serializable insertar(T obj, String modulo) {
        //boolean test = false;
        String indicador = "AGREGO";
        Serializable id = null;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            id = s.save(obj);
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

    public T consultar(Class clase, int id) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        Objecto = (T) s.get(clase, id);
        s.getTransaction().commit();
        return Objecto;
    }

    public boolean editar(T entity, String modulo) {
        String indicador = "EDITO";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.update(entity);
            if (bitacora(entity, indicador, modulo)) {
                s.getTransaction().commit();
                test = true;
                System.err.println("comit");
            } else {
                System.out.println("roolback");
                s.getTransaction().rollback();
            }
        } catch (Exception e) {
            s.getTransaction().rollback();
            System.out.println("Error al editar " + e.getLocalizedMessage());
        }
        return test;
    }
//

    public boolean eliminar(T entity, String modulo) {
        String indicador = "ELIMINO";
        boolean test = false;
        try {
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.delete(entity);
            if (bitacora(entity, indicador, modulo)) {
                s.getTransaction().commit();
                test = true;
                System.err.println("comit");
            } else {
                System.out.println("roolback");
                s.getTransaction().rollback();
            }
        } catch (Exception e) {
            s.getTransaction().rollback();
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

    public boolean bitacora(Object obj, String indicador, String modulo) {
        boolean commit = false;
        try {
            if (!s.isConnected()) { // verifica que  haya una sesion abierta si no hay crea una
                s = hibernateUtil.getSessionFactory();
                s.beginTransaction();
                commit = true;
            }
            final TBitacora bitacora = new TBitacora();
            bitacora.setTbitFecha(new Date());
            bitacora.setTLogin(Login_Controller.getUsuarioLogueado());
            bitacora.setTbitIdentificador(indicador);
            bitacora.setTbitRegistro(obtenerJson(obj));
            bitacora.setTbitClassname(obj.getClass().getName());
            bitacora.setTbitModulo(modulo);
            s.save(bitacora);  //GUARDA BITACORA
            if (commit) { // si se creo una sesion ejecuta un commit
                s.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            if (commit) {
                s.getTransaction().rollback();
            }
            System.err.println("CATCH " + e.getLocalizedMessage());
            return false;
        }
    }

    private String obtenerJson(Object object) {
        String json = null;
        System.err.println(object.getClass().toString());
        switch (object.getClass().getName()) {
            case "Entity.TCobrador":
                TCobrador cobrador = (TCobrador) object;
                json = "{\"tcobId\":" + cobrador.getTcobId() + ",\"tcobNombre\":\"" + cobrador.getTcobNombre() + "\",\"TCuotas\":[]}";
                break;
            case "Entity.TCuota":
                TCuota cuota = (TCuota) object;
                json = "{\"tcuoId\":" + cuota.getTcuoId() + ",";
                if (cuota.getTCobrador() != null) {
                    json += "\"TCobrador\":{\"tcobId\":" + cuota.getTCobrador().getTcobId() + ",\"tcobNombre\":\"" + cuota.getTCobrador().getTcobNombre() + "\",\"TCuotas\":[]},";
                }
                if (cuota.getTPago() != null) {
                    json += "\"TPago\":{\"tpagId\":" + cuota.getTPago().getTpagId() + ",\"tipo\":\"" + cuota.getTPago().getTipo() + "\",\"TCuotas\":[]},";
                }
                if (cuota.getTPrestamo() != null) {
                    json += "\"TPrestamo\":{ \"tpreId\":" + cuota.getTPrestamo().getTpreId() + ",";
                    if (cuota.getTPrestamo().getTPersona() != null) {
                        json += "\"TPersona\":{\"tperId\":" + cuota.getTPrestamo().getTPersona().getTperId() + ",";
                        if (cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona() != null) {
                            json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpCedula() + "\","
                                    + "\"tdbpNombre\":\"" + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido() + "\","
                                    + "\"tdbpTel\":\"" + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                        }
                        json += "\"tperCasDir\":\"" + cuota.getTPrestamo().getTPersona().getTperCasDir() + "\",\"tperCasPro\":\"" + cuota.getTPrestamo().getTPersona().getTperCasPro() + "\","
                                + "\"tperCasTipo\":\"" + cuota.getTPrestamo().getTPersona().getTperCasTipo() + "\","
                                + "\"tperEmpNom\":\"" + cuota.getTPrestamo().getTPersona().getTperEmpNom() + "\",\"tperEmpDir\":\"" + cuota.getTPrestamo().getTPersona().getTperEmpDir() + "\","
                                + "\"tperEmpTel\":\"" + cuota.getTPrestamo().getTPersona().getTperEmpTel() + "\",\"tperTipo\":\"" + cuota.getTPrestamo().getTPersona().getTperTipo() + "\","
                                + "\"tperCodeudor\":\"" + cuota.getTPrestamo().getTPersona().getTperCodeudor() + "\",\"TPrestamos\":[]},";
                    }
                    json += " \"tpreValorPrestamo\":" + cuota.getTPrestamo().getTpreValorPrestamo() + ","
                            + " \"tpreNumCuotas\":" + cuota.getTPrestamo().getTpreNumCuotas() + ","
                            + " \"tpreIntereses\":" + cuota.getTPrestamo().getTpreIntereses() + ","
                            + " \"tpreMetodPago\":\"" + cuota.getTPrestamo().getTpreMetodPago() + "\","
                            + " \"tpreFechaEntrega\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(cuota.getTPrestamo().getTpreFechaEntrega()) + "\","
                            + " \"tpreValorTotal\":" + cuota.getTPrestamo().getTpreValorTotal() + ","
                            + " \"tpreValorCuota\":" + cuota.getTPrestamo().getTpreValorCuota() + ","
                            + " \"TCuotas\":[]},";
                }
                json += "\"tcuoFecha\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(cuota.getTcuoFecha()) + "\",\"tcuoAbono\":" + cuota.getTcuoAbono() + ",\"tcuoNuevoSaldo\":" + cuota.getTcuoNuevoSaldo() + ","
                        + "\"tcuoCuotasPagadas\":" + cuota.getTcuoCuotasPagadas() + "}";
                break;
            case "Entity.TDatosBasicosPersona":
                TDatosBasicosPersona dBPersona = (TDatosBasicosPersona) object;
                json = "{\"tdbpId\":" + dBPersona.getTdbpId() + ",\"tdbpCedula\":\"" + dBPersona.getTdbpCedula() + "\",\"tdbpNombre\":\"" + dBPersona.getTdbpNombre() + "\","
                        + "\"tdbpApellido\":\"" + dBPersona.getTdbpApellido() + "\",\"tdbpTel\":\"" + dBPersona.getTdbpTel() + "\","
                        + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]}";
                break;
            case "Entity.TGasto":
                TGasto gasto = (TGasto) object;
                json = "{\"tgasId\":" + gasto.getTgasId() + ",\"tgasDesc\":\"" + gasto.getTgasDesc() + "\",\"tgasFecha\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(gasto.getTgasFecha()) + "\",\"tgasCosto\":\"" + gasto.getTgasCosto() + "\"}";
                break;
            case "Entity.TLogin":
                TLogin login = (TLogin) object;
                json = "{\"tlogId\":" + login.getTlogId() + ",";
                if (login.getTDatosBasicosPersona() != null) {
                    json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + login.getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + login.getTDatosBasicosPersona().getTdbpCedula() + "\",\"tdbpNombre\":\"" + login.getTDatosBasicosPersona().getTdbpNombre() + "\","
                            + "\"tdbpApellido\":\"" + login.getTDatosBasicosPersona().getTdbpApellido() + "\",\"tdbpTel\":\"" + login.getTDatosBasicosPersona().getTdbpTel() + "\","
                            + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                }
                json += "\"tlogUserLogin\":\"" + login.getTlogUserLogin() + "\",\"tlogPassword\":\"" + login.getTlogPassword() + "\",\"TBitacoras\":[]}";
                break;
            case "Entity.TPago":
                TPago pago = (TPago) object;
                json = "{\"tpagId\":" + pago.getTpagId() + ",\"tipo\":\"" + pago.getTipo() + "\",\"TCuotas\":[]}";
                break;
            case "Entity.TPersona":
                TPersona persona = (TPersona) object;
                json = "{\"tperId\":" + persona.getTperId() + ",";
                if (persona.getTDatosBasicosPersona() != null) {
                    json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + persona.getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + persona.getTDatosBasicosPersona().getTdbpCedula() + "\","
                            + "\"tdbpNombre\":\"" + persona.getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + persona.getTDatosBasicosPersona().getTdbpApellido() + "\","
                            + "\"tdbpTel\":\"" + persona.getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                }
                json += "\"tperCasDir\":\"" + persona.getTperCasDir() + "\",\"tperCasPro\":\"" + persona.getTperCasPro() + "\","
                        + "\"tperCasTipo\":\"" + persona.getTperCasTipo() + "\","
                        + "\"tperEmpNom\":\"" + persona.getTperEmpNom() + "\",\"tperEmpDir\":\"" + persona.getTperEmpDir() + "\","
                        + "\"tperEmpTel\":\"" + persona.getTperEmpTel() + "\",\"tperTipo\":\"" + persona.getTperTipo() + "\","
                        + "\"tperCodeudor\":\"" + persona.getTperCodeudor() + "\",\"TPrestamos\":[]}";
                break;
            case "Entity.TPrestamo":
                TPrestamo prestamo = (TPrestamo) object;
                json = "{\"tpreId\":" + prestamo.getTpreId() + ",";
                if (prestamo.getTPersona() != null) {
                    json += "\"TPersona\":{\"tperId\":" + prestamo.getTPersona().getTperId() + ",";
                    if (prestamo.getTPersona().getTDatosBasicosPersona() != null) {
                        json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpCedula() + "\","
                                + "\"tdbpNombre\":\"" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido() + "\","
                                + "\"tdbpTel\":\"" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                    }
                    json += "\"tperCasDir\":\"" + prestamo.getTPersona().getTperCasDir() + "\",\"tperCasPro\":\"" + prestamo.getTPersona().getTperCasPro() + "\","
                            + "\"tperCasTipo\":\"" + prestamo.getTPersona().getTperCasTipo() + "\","
                            + "\"tperEmpNom\":\"" + prestamo.getTPersona().getTperEmpNom() + "\",\"tperEmpDir\":\"" + prestamo.getTPersona().getTperEmpDir() + "\","
                            + "\"tperEmpTel\":\"" + prestamo.getTPersona().getTperEmpTel() + "\",\"tperTipo\":\"" + prestamo.getTPersona().getTperTipo() + "\","
                            + "\"tperCodeudor\":\"" + prestamo.getTPersona().getTperCodeudor() + "\",\"TPrestamos\":[]},";
                }
                json += " \"tpreValorPrestamo\":" + prestamo.getTpreValorPrestamo() + ","
                        + " \"tpreNumCuotas\":" + prestamo.getTpreNumCuotas() + ","
                        + " \"tpreIntereses\":" + prestamo.getTpreIntereses() + ","
                        + " \"tpreMetodPago\":\"" + prestamo.getTpreMetodPago() + "\","
                        + " \"tpreFechaEntrega\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(prestamo.getTpreFechaEntrega()) + "\","
                        + " \"tpreValorTotal\":" + prestamo.getTpreValorTotal() + ","
                        + " \"tpreValorCuota\":" + prestamo.getTpreValorCuota() + ","
                        + " \"TCuotas\":[]}";
                break;
            case "Entity.TReferencia":
                TReferencia referencia = (TReferencia) object;
                json = "{\"trefId\":" + referencia.getTrefId() + ",";
                if (referencia.getTDatosBasicosPersona() != null) {
                    json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + referencia.getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + referencia.getTDatosBasicosPersona().getTdbpCedula() + "\","
                            + "\"tdbpNombre\":\"" + referencia.getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + referencia.getTDatosBasicosPersona().getTdbpApellido() + "\","
                            + "\"tdbpTel\":\"" + referencia.getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                }
                json += "\"trefTipo\":\"" + referencia.getTrefTipo() + "\",\"trefNombre\":\"" + referencia.getTrefNombre() + "\",\"trefApellido\":\"" + referencia.getTrefApellido() + "\","
                        + "\"trefTelefono\":\"" + referencia.getTrefTelefono() + "\"}";
                break;
        }
        return json;
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
