package Entity;
// Generated 10/08/2017 08:56:58 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TBitacora generated by hbm2java
 */
public class TBitacora  implements java.io.Serializable {


     private Integer tbitId;
     private TLogin TLogin;
     private Date tbitFecha;
     private String tbitRegistro;
     private String tbitIdentificador;
     private String tbitClassname;
     private String tbitModulo;

    public TBitacora() {
    }

	
    public TBitacora(TLogin TLogin, Date tbitFecha, String tbitRegistro, String tbitIdentificador) {
        this.TLogin = TLogin;
        this.tbitFecha = tbitFecha;
        this.tbitRegistro = tbitRegistro;
        this.tbitIdentificador = tbitIdentificador;
    }
    public TBitacora(TLogin TLogin, Date tbitFecha, String tbitRegistro, String tbitIdentificador, String tbitClassname, String tbitModulo) {
       this.TLogin = TLogin;
       this.tbitFecha = tbitFecha;
       this.tbitRegistro = tbitRegistro;
       this.tbitIdentificador = tbitIdentificador;
       this.tbitClassname = tbitClassname;
       this.tbitModulo = tbitModulo;
    }
   
    public Integer getTbitId() {
        return this.tbitId;
    }
    
    public void setTbitId(Integer tbitId) {
        this.tbitId = tbitId;
    }
    public TLogin getTLogin() {
        return this.TLogin;
    }
    
    public void setTLogin(TLogin TLogin) {
        this.TLogin = TLogin;
    }
    public Date getTbitFecha() {
        return this.tbitFecha;
    }
    
    public void setTbitFecha(Date tbitFecha) {
        this.tbitFecha = tbitFecha;
    }
    public String getTbitRegistro() {
        return this.tbitRegistro;
    }
    
    public void setTbitRegistro(String tbitRegistro) {
        this.tbitRegistro = tbitRegistro;
    }
    public String getTbitIdentificador() {
        return this.tbitIdentificador;
    }
    
    public void setTbitIdentificador(String tbitIdentificador) {
        this.tbitIdentificador = tbitIdentificador;
    }
    public String getTbitClassname() {
        return this.tbitClassname;
    }
    
    public void setTbitClassname(String tbitClassname) {
        this.tbitClassname = tbitClassname;
    }
    public String getTbitModulo() {
        return this.tbitModulo;
    }
    
    public void setTbitModulo(String tbitModulo) {
        this.tbitModulo = tbitModulo;
    }




}


