package Entity;
// Generated 10/08/2017 08:56:58 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TCobrador generated by hbm2java
 */
public class TCobrador  implements java.io.Serializable {


     private Integer tcobId;
     private String tcobNombre;
     private Set TCuotas = new HashSet(0);

    public TCobrador() {
    }

	
    public TCobrador(String tcobNombre) {
        this.tcobNombre = tcobNombre;
    }
    public TCobrador(String tcobNombre, Set TCuotas) {
       this.tcobNombre = tcobNombre;
       this.TCuotas = TCuotas;
    }
   
    public Integer getTcobId() {
        return this.tcobId;
    }
    
    public void setTcobId(Integer tcobId) {
        this.tcobId = tcobId;
    }
    public String getTcobNombre() {
        return this.tcobNombre;
    }
    
    public void setTcobNombre(String tcobNombre) {
        this.tcobNombre = tcobNombre;
    }
    public Set getTCuotas() {
        return this.TCuotas;
    }
    
    public void setTCuotas(Set TCuotas) {
        this.TCuotas = TCuotas;
    }




}


