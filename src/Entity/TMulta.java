package Entity;
// Generated 26/07/2017 08:45:08 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TMulta generated by hbm2java
 */
public class TMulta  implements java.io.Serializable {


     private Integer tmulId;
     private TPrestamo TPrestamo;
     private int tmulValor;
     private String tmulDescripcion;
     private Date tmulFecha;
     private String tmulEstado;

    public TMulta() {
    }

    public TMulta(TPrestamo TPrestamo, int tmulValor, String tmulDescripcion, Date tmulFecha, String tmulEstado) {
       this.TPrestamo = TPrestamo;
       this.tmulValor = tmulValor;
       this.tmulDescripcion = tmulDescripcion;
       this.tmulFecha = tmulFecha;
       this.tmulEstado = tmulEstado;
    }
   
    public Integer getTmulId() {
        return this.tmulId;
    }
    
    public void setTmulId(Integer tmulId) {
        this.tmulId = tmulId;
    }
    public TPrestamo getTPrestamo() {
        return this.TPrestamo;
    }
    
    public void setTPrestamo(TPrestamo TPrestamo) {
        this.TPrestamo = TPrestamo;
    }
    public int getTmulValor() {
        return this.tmulValor;
    }
    
    public void setTmulValor(int tmulValor) {
        this.tmulValor = tmulValor;
    }
    public String getTmulDescripcion() {
        return this.tmulDescripcion;
    }
    
    public void setTmulDescripcion(String tmulDescripcion) {
        this.tmulDescripcion = tmulDescripcion;
    }
    public Date getTmulFecha() {
        return this.tmulFecha;
    }
    
    public void setTmulFecha(Date tmulFecha) {
        this.tmulFecha = tmulFecha;
    }
    public String getTmulEstado() {
        return this.tmulEstado;
    }
    
    public void setTmulEstado(String tmulEstado) {
        this.tmulEstado = tmulEstado;
    }




}


