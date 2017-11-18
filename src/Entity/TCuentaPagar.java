package Entity;
// Generated 18-nov-2017 10:27:33 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TCuentaPagar generated by hbm2java
 */
public class TCuentaPagar  implements java.io.Serializable {


     private Integer tcueId;
     private String tcueNombre;
     private long tcueSaldo;
     private Set TMovimientoCuentas = new HashSet(0);

    public TCuentaPagar() {
    }

	
    public TCuentaPagar(String tcueNombre, long tcueSaldo) {
        this.tcueNombre = tcueNombre;
        this.tcueSaldo = tcueSaldo;
    }
    public TCuentaPagar(String tcueNombre, long tcueSaldo, Set TMovimientoCuentas) {
       this.tcueNombre = tcueNombre;
       this.tcueSaldo = tcueSaldo;
       this.TMovimientoCuentas = TMovimientoCuentas;
    }
   
    public Integer getTcueId() {
        return this.tcueId;
    }
    
    public void setTcueId(Integer tcueId) {
        this.tcueId = tcueId;
    }
    public String getTcueNombre() {
        return this.tcueNombre;
    }
    
    public void setTcueNombre(String tcueNombre) {
        this.tcueNombre = tcueNombre;
    }
    public long getTcueSaldo() {
        return this.tcueSaldo;
    }
    
    public void setTcueSaldo(long tcueSaldo) {
        this.tcueSaldo = tcueSaldo;
    }
    public Set getTMovimientoCuentas() {
        return this.TMovimientoCuentas;
    }
    
    public void setTMovimientoCuentas(Set TMovimientoCuentas) {
        this.TMovimientoCuentas = TMovimientoCuentas;
    }




}


