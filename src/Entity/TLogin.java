package Entity;
// Generated 17-feb-2017 19:05:00 by Hibernate Tools 4.3.1



/**
 * TLogin generated by hbm2java
 */
public class TLogin  implements java.io.Serializable {


     private Integer tlogId;
     private TPersona TPersona;
     private String tlogUserLogin;
     private String tlogPassword;

    public TLogin() {
    }

	
    public TLogin(TPersona TPersona) {
        this.TPersona = TPersona;
    }
    public TLogin(TPersona TPersona, String tlogUserLogin, String tlogPassword) {
       this.TPersona = TPersona;
       this.tlogUserLogin = tlogUserLogin;
       this.tlogPassword = tlogPassword;
    }
   
    public Integer getTlogId() {
        return this.tlogId;
    }
    
    public void setTlogId(Integer tlogId) {
        this.tlogId = tlogId;
    }
    public TPersona getTPersona() {
        return this.TPersona;
    }
    
    public void setTPersona(TPersona TPersona) {
        this.TPersona = TPersona;
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




}


