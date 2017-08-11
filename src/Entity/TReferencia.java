package Entity;
// Generated 10/08/2017 08:56:58 PM by Hibernate Tools 4.3.1



/**
 * TReferencia generated by hbm2java
 */
public class TReferencia  implements java.io.Serializable {


     private Integer trefId;
     private TDatosBasicosPersona TDatosBasicosPersona;
     private String trefTipo;
     private String trefNombre;
     private String trefApellido;
     private String trefTelefono;

    public TReferencia() {
    }

	
    public TReferencia(TDatosBasicosPersona TDatosBasicosPersona) {
        this.TDatosBasicosPersona = TDatosBasicosPersona;
    }
    public TReferencia(TDatosBasicosPersona TDatosBasicosPersona, String trefTipo, String trefNombre, String trefApellido, String trefTelefono) {
       this.TDatosBasicosPersona = TDatosBasicosPersona;
       this.trefTipo = trefTipo;
       this.trefNombre = trefNombre;
       this.trefApellido = trefApellido;
       this.trefTelefono = trefTelefono;
    }
   
    public Integer getTrefId() {
        return this.trefId;
    }
    
    public void setTrefId(Integer trefId) {
        this.trefId = trefId;
    }
    public TDatosBasicosPersona getTDatosBasicosPersona() {
        return this.TDatosBasicosPersona;
    }
    
    public void setTDatosBasicosPersona(TDatosBasicosPersona TDatosBasicosPersona) {
        this.TDatosBasicosPersona = TDatosBasicosPersona;
    }
    public String getTrefTipo() {
        return this.trefTipo;
    }
    
    public void setTrefTipo(String trefTipo) {
        this.trefTipo = trefTipo;
    }
    public String getTrefNombre() {
        return this.trefNombre;
    }
    
    public void setTrefNombre(String trefNombre) {
        this.trefNombre = trefNombre;
    }
    public String getTrefApellido() {
        return this.trefApellido;
    }
    
    public void setTrefApellido(String trefApellido) {
        this.trefApellido = trefApellido;
    }
    public String getTrefTelefono() {
        return this.trefTelefono;
    }
    
    public void setTrefTelefono(String trefTelefono) {
        this.trefTelefono = trefTelefono;
    }




}


