package Entity;
// Generated 11/02/2017 12:56:19 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TPersona generated by hbm2java
 */
public class TPersona  implements java.io.Serializable {


     private Integer tperId;
     private String tperCedula;
     private String tperNombre;
     private String tperApellido;
     private String tperTel;
     private Set TReferencias = new HashSet(0);
     private Set TCodeudors = new HashSet(0);
     private Set TClientes = new HashSet(0);
     private Set TLogins = new HashSet(0);
     private Set TCasas = new HashSet(0);

    public TPersona() {
    }

    public TPersona(String tperCedula, String tperNombre, String tperApellido, String tperTel, Set TReferencias, Set TCodeudors, Set TClientes, Set TLogins, Set TCasas) {
       this.tperCedula = tperCedula;
       this.tperNombre = tperNombre;
       this.tperApellido = tperApellido;
       this.tperTel = tperTel;
       this.TReferencias = TReferencias;
       this.TCodeudors = TCodeudors;
       this.TClientes = TClientes;
       this.TLogins = TLogins;
       this.TCasas = TCasas;
    }
   
    public Integer getTperId() {
        return this.tperId;
    }
    
    public void setTperId(Integer tperId) {
        this.tperId = tperId;
    }
    public String getTperCedula() {
        return this.tperCedula;
    }
    
    public void setTperCedula(String tperCedula) {
        this.tperCedula = tperCedula;
    }
    public String getTperNombre() {
        return this.tperNombre;
    }
    
    public void setTperNombre(String tperNombre) {
        this.tperNombre = tperNombre;
    }
    public String getTperApellido() {
        return this.tperApellido;
    }
    
    public void setTperApellido(String tperApellido) {
        this.tperApellido = tperApellido;
    }
    public String getTperTel() {
        return this.tperTel;
    }
    
    public void setTperTel(String tperTel) {
        this.tperTel = tperTel;
    }
    public Set getTReferencias() {
        return this.TReferencias;
    }
    
    public void setTReferencias(Set TReferencias) {
        this.TReferencias = TReferencias;
    }
    public Set getTCodeudors() {
        return this.TCodeudors;
    }
    
    public void setTCodeudors(Set TCodeudors) {
        this.TCodeudors = TCodeudors;
    }
    public Set getTClientes() {
        return this.TClientes;
    }
    
    public void setTClientes(Set TClientes) {
        this.TClientes = TClientes;
    }
    public Set getTLogins() {
        return this.TLogins;
    }
    
    public void setTLogins(Set TLogins) {
        this.TLogins = TLogins;
    }
    public Set getTCasas() {
        return this.TCasas;
    }
    
    public void setTCasas(Set TCasas) {
        this.TCasas = TCasas;
    }




}

