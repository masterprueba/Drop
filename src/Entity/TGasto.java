package Entity;
// Generated 16/09/2017 09:05:34 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TGasto generated by hbm2java
 */
public class TGasto  implements java.io.Serializable {


     private Integer tgasId;
     private String tgasDesc;
     private Date tgasFecha;
     private Long tgasCosto;

    public TGasto() {
    }

    public TGasto(String tgasDesc, Date tgasFecha, Long tgasCosto) {
       this.tgasDesc = tgasDesc;
       this.tgasFecha = tgasFecha;
       this.tgasCosto = tgasCosto;
    }
   
    public Integer getTgasId() {
        return this.tgasId;
    }
    
    public void setTgasId(Integer tgasId) {
        this.tgasId = tgasId;
    }
    public String getTgasDesc() {
        return this.tgasDesc;
    }
    
    public void setTgasDesc(String tgasDesc) {
        this.tgasDesc = tgasDesc;
    }
    public Date getTgasFecha() {
        return this.tgasFecha;
    }
    
    public void setTgasFecha(Date tgasFecha) {
        this.tgasFecha = tgasFecha;
    }
    public Long getTgasCosto() {
        return this.tgasCosto;
    }
    
    public void setTgasCosto(Long tgasCosto) {
        this.tgasCosto = tgasCosto;
    }




}


