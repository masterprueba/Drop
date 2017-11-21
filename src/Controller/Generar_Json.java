/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author ITERIA
 */
public class Generar_Json {

    public static String obtenerJson(Object object) {
        String json = null;
        System.err.println(object.getClass().getName());
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
                                    + "\"tdbpTel\":\"" + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"tdbpCel\":\"" + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpCel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
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
                        + "\"tdbpApellido\":\"" + dBPersona.getTdbpApellido() + "\",\"tdbpTel\":\"" + dBPersona.getTdbpTel() + "\"," + "\"tdbpCel\":\"" + dBPersona.getTdbpCel() + "\","
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
                            + "\"tdbpApellido\":\"" + login.getTDatosBasicosPersona().getTdbpApellido() + "\",\"tdbpTel\":\"" + login.getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"tdbpCel\":\"" + login.getTDatosBasicosPersona().getTdbpCel() + "\","
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
                            + "\"tdbpTel\":\"" + persona.getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"tdbpCel\":\"" + persona.getTDatosBasicosPersona().getTdbpCel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
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
                                + "\"tdbpTel\":\"" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"tdbpCel\":\"" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpCel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
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
                            + "\"tdbpTel\":\"" + referencia.getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"tdbpCel\":\"" + referencia.getTDatosBasicosPersona().getTdbpCel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                }
                json += "\"trefTipo\":\"" + referencia.getTrefTipo() + "\",\"trefNombre\":\"" + referencia.getTrefNombre() + "\",\"trefApellido\":\"" + referencia.getTrefApellido() + "\","
                        + "\"trefTelefono\":\"" + referencia.getTrefTelefono() + "\"}";
                break;
            case "Entity.TMulta":
                TMulta multa = (TMulta) object;
                json = "{\"tmulId\":" + multa.getTmulId() + ",";
                if (multa.getTPrestamo() != null) {
                    json += "\"TPrestamo\":{ \"tpreId\":" + multa.getTPrestamo().getTpreId() + ",";
                    if (multa.getTPrestamo().getTPersona() != null) {
                        json += "\"TPersona\":{\"tperId\":" + multa.getTPrestamo().getTPersona().getTperId() + ",";
                        if (multa.getTPrestamo().getTPersona().getTDatosBasicosPersona() != null) {
                            json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpCedula() + "\","
                                    + "\"tdbpNombre\":\"" + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido() + "\","
                                    + "\"tdbpTel\":\"" + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"tdbpCel\":\"" + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpCel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                        }
                        json += "\"tperCasDir\":\"" + multa.getTPrestamo().getTPersona().getTperCasDir() + "\",\"tperCasPro\":\"" + multa.getTPrestamo().getTPersona().getTperCasPro() + "\","
                                + "\"tperCasTipo\":\"" + multa.getTPrestamo().getTPersona().getTperCasTipo() + "\","
                                + "\"tperEmpNom\":\"" + multa.getTPrestamo().getTPersona().getTperEmpNom() + "\",\"tperEmpDir\":\"" + multa.getTPrestamo().getTPersona().getTperEmpDir() + "\","
                                + "\"tperEmpTel\":\"" + multa.getTPrestamo().getTPersona().getTperEmpTel() + "\",\"tperTipo\":\"" + multa.getTPrestamo().getTPersona().getTperTipo() + "\","
                                + "\"tperCodeudor\":\"" + multa.getTPrestamo().getTPersona().getTperCodeudor() + "\",\"TPrestamos\":[]},";
                    }
                    json += " \"tpreValorPrestamo\":" + multa.getTPrestamo().getTpreValorPrestamo() + ","
                            + " \"tpreNumCuotas\":" + multa.getTPrestamo().getTpreNumCuotas() + ","
                            + " \"tpreIntereses\":" + multa.getTPrestamo().getTpreIntereses() + ","
                            + " \"tpreMetodPago\":\"" + multa.getTPrestamo().getTpreMetodPago() + "\","
                            + " \"tpreFechaEntrega\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(multa.getTPrestamo().getTpreFechaEntrega()) + "\","
                            + " \"tpreValorTotal\":" + multa.getTPrestamo().getTpreValorTotal() + ","
                            + " \"tpreValorCuota\":" + multa.getTPrestamo().getTpreValorCuota() + ","
                            + " \"TCuotas\":[]},";

                }
                json += "\"tmulValor\":" + multa.getTmulValor() + ",\"tmulDescripcion\":\"" + multa.getTmulDescripcion() + "\",\"tmulFecha\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(multa.getTmulFecha()) + "\","
                        + "\"tmulEstado\":\"" + multa.getTmulEstado() + "\"}";
                break;
            case "Entity.TBanco":
                TBanco banco = (TBanco) object;
                json = "{\"tbanCuenta\":\"" + banco.getTbanCuenta() + "\",\"tbanNombre\":\"" + banco.getTbanNombre() + "\",\"tbanTipo\":\"" + banco.getTbanTipo() + "\",\"tbanSaldo\":" + banco.getTbanSaldo() + "\"TMovimientoBancos\":[]}";
                break;
            case "Entity.TMovimientoBanco":
                TMovimientoBanco movimiento = (TMovimientoBanco) object;
                json = "{\"tmovId\":" + movimiento.getTmovId() + ",";
                if (movimiento.getTBanco() != null) {
                    json += "\"tbanCuenta\":\"" + movimiento.getTBanco().getTbanCuenta() + "\",\"tbanNombre\":\"" + movimiento.getTBanco().getTbanNombre() + "\",\"tbanTipo\":\"" + movimiento.getTBanco().getTbanTipo() + "\",\"tbanSaldo\":" + movimiento.getTBanco().getTbanSaldo() + "\"TMovimientoBancos\":[],";
                }
                json += "\"tmovTipo\":\"" + movimiento.getTmovTipo() + "\",\"tmovSaldo\":" + movimiento.getTmovSaldo() + "\",\"tmovPorcentaje\":" + movimiento.getTmovPorcentaje() + ",\"tmovFecha\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(movimiento.getTmovFecha()) + "\",\"tmovConcepto\":\"" + movimiento.getTmovConcepto() + "\"}";

                break;
            default:
                JOptionPane.showMessageDialog(null, "Se agrego una nueva entidad, agregar al json");
                break;
            case "Entity.TCuentaPagar":
                TCuentaPagar cuentaPagar = (TCuentaPagar) object;
                json = "{\"tcueId\":" + cuentaPagar.getTcueId() + ",\"tcueNombre\":\"" + cuentaPagar.getTcueNombre() + "\",\"tcueSaldo\":" + cuentaPagar.getTcueSaldo() + ",\"TMovimientoCuentas\":[]}";
                break;
            case "Entity.TMovimientoCuenta":
                TMovimientoCuenta movimientocuenta = (TMovimientoCuenta) object;
                json = "{\"tmocId\":" + movimientocuenta.getTmocId() + ",";
                if (movimientocuenta.getTCuentaPagar() != null) {
                    json = "{\"tcueId\":" + movimientocuenta.getTCuentaPagar().getTcueId() + ",\"tcueNombre\":\"" + movimientocuenta.getTCuentaPagar().getTcueNombre() + "\",\"tcueSaldo\":" + movimientocuenta.getTCuentaPagar().getTcueSaldo() + ",\"TMovimientoCuentas\":[]}";
                }
                json += "\"tmocTipo\":\"" + movimientocuenta.getTmocTipo() + "\",\"tmocSaldo\":" + movimientocuenta.getTmocSaldo() + ",\"tmocFecha\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(movimientocuenta.getTmocFecha()) + "\"}";
                break;
            case "Entity.TCierre":
                TCierre cierre = (TCierre) object;
                json = "{\"tciAno\":" + cierre.getTciAno() + ",\"tciMes\":" + cierre.getTciMes() + "}";
                break;
            case "Entity.TRemanente":
                TRemanente remanente = (TRemanente) object;
                json = "{\"treId\":" + remanente.getTreId() + ",\"treFecha\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(remanente.getTreFecha()) + "\",\"treValor\":" + remanente.getTreValor() + "}";
                break;

            case "Entity.TRefinanciacion":
                TRefinanciacion refinanciacion = (TRefinanciacion) object;
                json = "{\"trefiId\":" + refinanciacion.getTrefiId() + ",\"trefiValor\":" + refinanciacion.getTrefiValor() + ",\"trefiIdprestamoxr\":" + refinanciacion.getTrefiIdprestamoxr() + ",\"trefiIdprestamor\":" + refinanciacion.getTrefiIdprestamor() + ",\"trefFecha\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(refinanciacion.getTrefFecha()) + "\"}";
                break;
        }
        return json;
    }
}
