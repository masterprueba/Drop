package Entity;
// Generated 29-may-2017 7:41:45 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TCuota generated by hbm2java
 */
public class TCuota  implements java.io.Serializable {


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




}


