package Entity;
// Generated 29-may-2017 7:41:45 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TCobrador generated by hbm2java
 */
public class TCobrador  implements java.io.Serializable {


     private int tcobId;
     private String tcobNombre;
     private Set TCuotas = new HashSet(0);

    public TCobrador() {
    }

	
    public TCobrador(int tcobId, String tcobNombre) {
        this.tcobId = tcobId;
        this.tcobNombre = tcobNombre;
    }
    public TCobrador(int tcobId, String tcobNombre, Set TCuotas) {
       this.tcobId = tcobId;
       this.tcobNombre = tcobNombre;
       this.TCuotas = TCuotas;
    }
   
    public int getTcobId() {
        return this.tcobId;
    }
    
    public void setTcobId(int tcobId) {
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


