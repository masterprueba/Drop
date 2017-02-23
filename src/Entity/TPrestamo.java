package Entity;
// Generated 23-feb-2017 18:30:32 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TPrestamo generated by hbm2java
 */
public class TPrestamo  implements java.io.Serializable {


     private Integer tpreId;
     private TPersona TPersona;
     private int tpreValorPrestamo;
     private int tpreNumCuotas;
     private int tpreIntereses;
     private String tpreMetodPago;
     private Date tpreFechaEntrega;
     private long tpreValorTotal;
     private long tpreValorCuota;
     private Set TCuotas = new HashSet(0);

    public TPrestamo() {
    }

	
    public TPrestamo(TPersona TPersona, int tpreValorPrestamo, int tpreNumCuotas, int tpreIntereses, String tpreMetodPago, Date tpreFechaEntrega, long tpreValorTotal, long tpreValorCuota) {
        this.TPersona = TPersona;
        this.tpreValorPrestamo = tpreValorPrestamo;
        this.tpreNumCuotas = tpreNumCuotas;
        this.tpreIntereses = tpreIntereses;
        this.tpreMetodPago = tpreMetodPago;
        this.tpreFechaEntrega = tpreFechaEntrega;
        this.tpreValorTotal = tpreValorTotal;
        this.tpreValorCuota = tpreValorCuota;
    }
    public TPrestamo(TPersona TPersona, int tpreValorPrestamo, int tpreNumCuotas, int tpreIntereses, String tpreMetodPago, Date tpreFechaEntrega, long tpreValorTotal, long tpreValorCuota, Set TCuotas) {
       this.TPersona = TPersona;
       this.tpreValorPrestamo = tpreValorPrestamo;
       this.tpreNumCuotas = tpreNumCuotas;
       this.tpreIntereses = tpreIntereses;
       this.tpreMetodPago = tpreMetodPago;
       this.tpreFechaEntrega = tpreFechaEntrega;
       this.tpreValorTotal = tpreValorTotal;
       this.tpreValorCuota = tpreValorCuota;
       this.TCuotas = TCuotas;
    }
   
    public Integer getTpreId() {
        return this.tpreId;
    }
    
    public void setTpreId(Integer tpreId) {
        this.tpreId = tpreId;
    }
    public TPersona getTPersona() {
        return this.TPersona;
    }
    
    public void setTPersona(TPersona TPersona) {
        this.TPersona = TPersona;
    }
    public int getTpreValorPrestamo() {
        return this.tpreValorPrestamo;
    }
    
    public void setTpreValorPrestamo(int tpreValorPrestamo) {
        this.tpreValorPrestamo = tpreValorPrestamo;
    }
    public int getTpreNumCuotas() {
        return this.tpreNumCuotas;
    }
    
    public void setTpreNumCuotas(int tpreNumCuotas) {
        this.tpreNumCuotas = tpreNumCuotas;
    }
    public int getTpreIntereses() {
        return this.tpreIntereses;
    }
    
    public void setTpreIntereses(int tpreIntereses) {
        this.tpreIntereses = tpreIntereses;
    }
    public String getTpreMetodPago() {
        return this.tpreMetodPago;
    }
    
    public void setTpreMetodPago(String tpreMetodPago) {
        this.tpreMetodPago = tpreMetodPago;
    }
    public Date getTpreFechaEntrega() {
        return this.tpreFechaEntrega;
    }
    
    public void setTpreFechaEntrega(Date tpreFechaEntrega) {
        this.tpreFechaEntrega = tpreFechaEntrega;
    }
    public long getTpreValorTotal() {
        return this.tpreValorTotal;
    }
    
    public void setTpreValorTotal(long tpreValorTotal) {
        this.tpreValorTotal = tpreValorTotal;
    }
    public long getTpreValorCuota() {
        return this.tpreValorCuota;
    }
    
    public void setTpreValorCuota(long tpreValorCuota) {
        this.tpreValorCuota = tpreValorCuota;
    }
    public Set getTCuotas() {
        return this.TCuotas;
    }
    
    public void setTCuotas(Set TCuotas) {
        this.TCuotas = TCuotas;
    }




}


