package Entity;
// Generated 29-may-2017 7:41:45 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TPersona generated by hbm2java
 */
public class TPersona  implements java.io.Serializable {


     private Integer tperId;
     private TDatosBasicosPersona TDatosBasicosPersona;
     private String tperCasDir;
     private String tperCasPro;
     private String tperCasTipo;
     private String tperEmpNom;
     private String tperEmpDir;
     private String tperEmpTel;
     private String tperTipo;
     private String tperCodeudor;
     private Set TPrestamos = new HashSet(0);

    public TPersona() {
    }

	
    public TPersona(TDatosBasicosPersona TDatosBasicosPersona, String tperCasDir, String tperTipo) {
        this.TDatosBasicosPersona = TDatosBasicosPersona;
        this.tperCasDir = tperCasDir;
        this.tperTipo = tperTipo;
    }
    public TPersona(TDatosBasicosPersona TDatosBasicosPersona, String tperCasDir, String tperCasPro, String tperCasTipo, String tperEmpNom, String tperEmpDir, String tperEmpTel, String tperTipo, String tperCodeudor, Set TPrestamos) {
       this.TDatosBasicosPersona = TDatosBasicosPersona;
       this.tperCasDir = tperCasDir;
       this.tperCasPro = tperCasPro;
       this.tperCasTipo = tperCasTipo;
       this.tperEmpNom = tperEmpNom;
       this.tperEmpDir = tperEmpDir;
       this.tperEmpTel = tperEmpTel;
       this.tperTipo = tperTipo;
       this.tperCodeudor = tperCodeudor;
       this.TPrestamos = TPrestamos;
    }
   
    public Integer getTperId() {
        return this.tperId;
    }
    
    public void setTperId(Integer tperId) {
        this.tperId = tperId;
    }
    public TDatosBasicosPersona getTDatosBasicosPersona() {
        return this.TDatosBasicosPersona;
    }
    
    public void setTDatosBasicosPersona(TDatosBasicosPersona TDatosBasicosPersona) {
        this.TDatosBasicosPersona = TDatosBasicosPersona;
    }
    public String getTperCasDir() {
        return this.tperCasDir;
    }
    
    public void setTperCasDir(String tperCasDir) {
        this.tperCasDir = tperCasDir;
    }
    public String getTperCasPro() {
        return this.tperCasPro;
    }
    
    public void setTperCasPro(String tperCasPro) {
        this.tperCasPro = tperCasPro;
    }
    public String getTperCasTipo() {
        return this.tperCasTipo;
    }
    
    public void setTperCasTipo(String tperCasTipo) {
        this.tperCasTipo = tperCasTipo;
    }
    public String getTperEmpNom() {
        return this.tperEmpNom;
    }
    
    public void setTperEmpNom(String tperEmpNom) {
        this.tperEmpNom = tperEmpNom;
    }
    public String getTperEmpDir() {
        return this.tperEmpDir;
    }
    
    public void setTperEmpDir(String tperEmpDir) {
        this.tperEmpDir = tperEmpDir;
    }
    public String getTperEmpTel() {
        return this.tperEmpTel;
    }
    
    public void setTperEmpTel(String tperEmpTel) {
        this.tperEmpTel = tperEmpTel;
    }
    public String getTperTipo() {
        return this.tperTipo;
    }
    
    public void setTperTipo(String tperTipo) {
        this.tperTipo = tperTipo;
    }
    public String getTperCodeudor() {
        return this.tperCodeudor;
    }
    
    public void setTperCodeudor(String tperCodeudor) {
        this.tperCodeudor = tperCodeudor;
    }
    public Set getTPrestamos() {
        return this.TPrestamos;
    }
    
    public void setTPrestamos(Set TPrestamos) {
        this.TPrestamos = TPrestamos;
    }




}


