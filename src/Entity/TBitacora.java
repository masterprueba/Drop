package Entity;
// Generated 25/02/2017 05:26:01 PM by Hibernate Tools 4.3.1


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

    public TBitacora() {
    }

    public TBitacora(TLogin TLogin, Date tbitFecha, String tbitRegistro, String tbitIdentificador) {
       this.TLogin = TLogin;
       this.tbitFecha = tbitFecha;
       this.tbitRegistro = tbitRegistro;
       this.tbitIdentificador = tbitIdentificador;
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




}


