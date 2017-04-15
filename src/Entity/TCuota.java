package Entity;
// Generated 1/04/2017 07:05:07 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * TCuota generated by hbm2java
 */
public class TCuota implements java.io.Serializable {

    private Integer tcuoId;
    private TCobrador TCobrador;
    private TPago TPago;
    private TPrestamo TPrestamo;
    private Date tcuoFecha;
    private long tcuoAbono;
    private long tcuoNuevoSaldo;
    private int tcuoCuotasPagadas;

    public TCuota() {
    }

    public TCuota(TCobrador TCobrador, TPago TPago, TPrestamo TPrestamo, Date tcuoFecha, long tcuoAbono, long tcuoNuevoSaldo, int tcuoCuotasPagadas) {
        this.TCobrador = TCobrador;
        this.TPago = TPago;
        this.TPrestamo = TPrestamo;
        this.tcuoFecha = tcuoFecha;
        this.tcuoAbono = tcuoAbono;
        this.tcuoNuevoSaldo = tcuoNuevoSaldo;
        this.tcuoCuotasPagadas = tcuoCuotasPagadas;
    }

    public Integer getTcuoId() {
        return this.tcuoId;
    }

    public void setTcuoId(Integer tcuoId) {
        this.tcuoId = tcuoId;
    }

    public TCobrador getTCobrador() {
        return this.TCobrador;
    }

    public void setTCobrador(TCobrador TCobrador) {
        this.TCobrador = TCobrador;
    }

    public TPago getTPago() {
        return this.TPago;
    }

    public void setTPago(TPago TPago) {
        this.TPago = TPago;
    }

    public TPrestamo getTPrestamo() {
        return this.TPrestamo;
    }

    public void setTPrestamo(TPrestamo TPrestamo) {
        this.TPrestamo = TPrestamo;
    }

    public Date getTcuoFecha() {
        return this.tcuoFecha;
    }

    public void setTcuoFecha(Date tcuoFecha) {
        this.tcuoFecha = tcuoFecha;
    }

    public long getTcuoAbono() {
        return this.tcuoAbono;
    }

    public void setTcuoAbono(long tcuoAbono) {
        this.tcuoAbono = tcuoAbono;
    }

    public long getTcuoNuevoSaldo() {
        return this.tcuoNuevoSaldo;
    }

    public void setTcuoNuevoSaldo(long tcuoNuevoSaldo) {
        this.tcuoNuevoSaldo = tcuoNuevoSaldo;
    }

    public int getTcuoCuotasPagadas() {
        return this.tcuoCuotasPagadas;
    }

    public void setTcuoCuotasPagadas(int tcuoCuotasPagadas) {
        this.tcuoCuotasPagadas = tcuoCuotasPagadas;
    }

    @Override
    public String toString() {
        String json = "{\"tcuoId\":" + tcuoId + ",";
        if (TCobrador != null) {
            json += "\"TCobrador\":{\"tcobId\":" + TCobrador.getTcobId() + ",\"tcobNombre\":\"" + TCobrador.getTcobNombre() + "\",\"TCuotas\":[]},";
        }
        if (TPago != null) {
            json += "\"TPago\":{\"tpagId\":" + TPago.getTpagId() + ",\"tipo\":\"" + TPago.getTipo() + "\",\"TCuotas\":[]},";
        }
        if (TPrestamo != null) {
            json += "\"TPrestamo\":{\"tpreId\":" + TPrestamo.getTpreId() + ",";
            if (TPrestamo.getTPersona() != null) {
                json += "\"TPersona\":{\"tperId\":" + TPrestamo.getTPersona().getTperId() + ",";
                if (TPrestamo.getTPersona().getTDatosBasicosPersona() != null) {
                    json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpCedula() + "\","
                            + "\"tdbpNombre\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido() + "\","
                            + "\"tdbpTel\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                }
                json += "\"tperCasDir\":\"" + TPrestamo.getTPersona().getTperCasDir() + "\",\"tperCasPro\":\"" + TPrestamo.getTPersona().getTperCasPro() + "\","
                        + "\"tperCasTipo\":\"" + TPrestamo.getTPersona().getTperCasTipo() + "\","
                        + "\"tperEmpNom\":\"" + TPrestamo.getTPersona().getTperEmpNom() + "\",\"tperEmpDir\":\"" + TPrestamo.getTPersona().getTperEmpDir() + "\","
                        + "\"tperEmpTel\":\"" + TPrestamo.getTPersona().getTperEmpTel() + "\",\"tperTipo\":\"" + TPrestamo.getTPersona().getTperTipo() + "\","
                        + "\"tperCodeudor\":\"" + TPrestamo.getTPersona().getTperCodeudor() + "\",\"TPrestamos\":[]},";
            }
            json += " \"tpreValorPrestamo\":" + TPrestamo.getTpreValorPrestamo() + ","
                    + " \"tpreNumCuotas\":" + TPrestamo.getTpreNumCuotas() + ","
                    + " \"tpreIntereses\":" + TPrestamo.getTpreIntereses() + ","
                    + " \"tpreMetodPago\":\"" + TPrestamo.getTpreMetodPago() + "\","
                    + " \"tpreFechaEntrega\":\"" + TPrestamo.getTpreFechaEntrega() + "\","
                    + " \"tpreValorTotal\":" + TPrestamo.getTpreValorTotal() + ","
                    + " \"tpreValorCuota\":" + TPrestamo.getTpreValorCuota() + ","
                    + " \"TCuotas\":[]},";
        }
        json += "\"tcuoFecha\":\"" + tcuoFecha + "\",\"tcuoAbono\":" + tcuoAbono + ",\"tcuoNuevoSaldo\":" + tcuoNuevoSaldo + ","
                + "\"tcuoCuotasPagadas\":" + tcuoCuotasPagadas + "}";

        return json;
    }

    public Object getTcuoMetodoPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getTcuoCobrador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
