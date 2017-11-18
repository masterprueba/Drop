package Entity;
// Generated 18/11/2017 03:19:48 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TLogin generated by hbm2java
 */
public class TLogin  implements java.io.Serializable {


     private Integer tlogId;
     private TDatosBasicosPersona TDatosBasicosPersona;
     private String tlogUserLogin;
     private String tlogPassword;
     private Set TBitacoras = new HashSet(0);

    public TLogin() {
    }

	
    public TLogin(TDatosBasicosPersona TDatosBasicosPersona) {
        this.TDatosBasicosPersona = TDatosBasicosPersona;
    }
    public TLogin(TDatosBasicosPersona TDatosBasicosPersona, String tlogUserLogin, String tlogPassword, Set TBitacoras) {
       this.TDatosBasicosPersona = TDatosBasicosPersona;
       this.tlogUserLogin = tlogUserLogin;
       this.tlogPassword = tlogPassword;
       this.TBitacoras = TBitacoras;
    }
   
    public Integer getTlogId() {
        return this.tlogId;
    }
    
    public void setTlogId(Integer tlogId) {
        this.tlogId = tlogId;
    }
    public TDatosBasicosPersona getTDatosBasicosPersona() {
        return this.TDatosBasicosPersona;
    }
    
    public void setTDatosBasicosPersona(TDatosBasicosPersona TDatosBasicosPersona) {
        this.TDatosBasicosPersona = TDatosBasicosPersona;
    }
    public String getTlogUserLogin() {
        return this.tlogUserLogin;
    }
    
    public void setTlogUserLogin(String tlogUserLogin) {
        this.tlogUserLogin = tlogUserLogin;
    }
    public String getTlogPassword() {
        return this.tlogPassword;
    }
    
    public void setTlogPassword(String tlogPassword) {
        this.tlogPassword = tlogPassword;
    }
    public Set getTBitacoras() {
        return this.TBitacoras;
    }
    
    public void setTBitacoras(Set TBitacoras) {
        this.TBitacoras = TBitacoras;
    }




}


