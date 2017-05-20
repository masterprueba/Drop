package Entity;
// Generated 19-may-2017 19:08:27 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TDatosBasicosPersona generated by hbm2java
 */
public class TDatosBasicosPersona  implements java.io.Serializable {


     private Integer tdbpId;
     private String tdbpCedula;
     private String tdbpNombre;
     private String tdbpApellido;
     private String tdbpTel;
     private Set TReferencias = new HashSet(0);
     private Set TLogins = new HashSet(0);
     private Set TPersonas = new HashSet(0);

    public TDatosBasicosPersona() {
    }

	
    public TDatosBasicosPersona(String tdbpCedula, String tdbpNombre, String tdbpTel) {
        this.tdbpCedula = tdbpCedula;
        this.tdbpNombre = tdbpNombre;
        this.tdbpTel = tdbpTel;
    }
    public TDatosBasicosPersona(String tdbpCedula, String tdbpNombre, String tdbpApellido, String tdbpTel, Set TReferencias, Set TLogins, Set TPersonas) {
       this.tdbpCedula = tdbpCedula;
       this.tdbpNombre = tdbpNombre;
       this.tdbpApellido = tdbpApellido;
       this.tdbpTel = tdbpTel;
       this.TReferencias = TReferencias;
       this.TLogins = TLogins;
       this.TPersonas = TPersonas;
    }
   
    public Integer getTdbpId() {
        return this.tdbpId;
    }
    
    public void setTdbpId(Integer tdbpId) {
        this.tdbpId = tdbpId;
    }
    public String getTdbpCedula() {
        return this.tdbpCedula;
    }
    
    public void setTdbpCedula(String tdbpCedula) {
        this.tdbpCedula = tdbpCedula;
    }
    public String getTdbpNombre() {
        return this.tdbpNombre;
    }
    
    public void setTdbpNombre(String tdbpNombre) {
        this.tdbpNombre = tdbpNombre;
    }
    public String getTdbpApellido() {
        return this.tdbpApellido;
    }
    
    public void setTdbpApellido(String tdbpApellido) {
        this.tdbpApellido = tdbpApellido;
    }
    public String getTdbpTel() {
        return this.tdbpTel;
    }
    
    public void setTdbpTel(String tdbpTel) {
        this.tdbpTel = tdbpTel;
    }
    public Set getTReferencias() {
        return this.TReferencias;
    }
    
    public void setTReferencias(Set TReferencias) {
        this.TReferencias = TReferencias;
    }
    public Set getTLogins() {
        return this.TLogins;
    }
    
    public void setTLogins(Set TLogins) {
        this.TLogins = TLogins;
    }
    public Set getTPersonas() {
        return this.TPersonas;
    }
    
    public void setTPersonas(Set TPersonas) {
        this.TPersonas = TPersonas;
    }




}


