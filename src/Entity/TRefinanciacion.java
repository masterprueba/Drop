package Entity;
// Generated 18/11/2017 03:19:48 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TRefinanciacion generated by hbm2java
 */
public class TRefinanciacion  implements java.io.Serializable {


     private Integer trefiId;
     private Long trefiValor;
     private Integer trefiIdprestamoxr;
     private Integer trefiIdprestamor;
     private Date trefFecha;

    public TRefinanciacion() {
    }

    public TRefinanciacion(Long trefiValor, Integer trefiIdprestamoxr, Integer trefiIdprestamor, Date trefFecha) {
       this.trefiValor = trefiValor;
       this.trefiIdprestamoxr = trefiIdprestamoxr;
       this.trefiIdprestamor = trefiIdprestamor;
       this.trefFecha = trefFecha;
    }
   
    public Integer getTrefiId() {
        return this.trefiId;
    }
    
    public void setTrefiId(Integer trefiId) {
        this.trefiId = trefiId;
    }
    public Long getTrefiValor() {
        return this.trefiValor;
    }
    
    public void setTrefiValor(Long trefiValor) {
        this.trefiValor = trefiValor;
    }
    public Integer getTrefiIdprestamoxr() {
        return this.trefiIdprestamoxr;
    }
    
    public void setTrefiIdprestamoxr(Integer trefiIdprestamoxr) {
        this.trefiIdprestamoxr = trefiIdprestamoxr;
    }
    public Integer getTrefiIdprestamor() {
        return this.trefiIdprestamor;
    }
    
    public void setTrefiIdprestamor(Integer trefiIdprestamor) {
        this.trefiIdprestamor = trefiIdprestamor;
    }
    public Date getTrefFecha() {
        return this.trefFecha;
    }
    
    public void setTrefFecha(Date trefFecha) {
        this.trefFecha = trefFecha;
    }




}


