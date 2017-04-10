package Entity;
// Generated 1/04/2017 07:05:07 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * TPago generated by hbm2java
 */
public class TPago implements java.io.Serializable {

    private int tpagId;
    private String tipo;
    private Set TCuotas = new HashSet(0);

    public TPago() {
    }

    public TPago(int tpagId) {
        this.tpagId = tpagId;
    }

    public TPago(int tpagId, String tipo, Set TCuotas) {
        this.tpagId = tpagId;
        this.tipo = tipo;
        this.TCuotas = TCuotas;
    }

    public int getTpagId() {
        return this.tpagId;
    }

    public void setTpagId(int tpagId) {
        this.tpagId = tpagId;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set getTCuotas() {
        return this.TCuotas;
    }

    public void setTCuotas(Set TCuotas) {
        this.TCuotas = TCuotas;
    }

    @Override
    public String toString() {
        String json = "{\"tpagId\":" + tpagId + ",\"tipo\":\"" + tipo + "\",\"TCuotas\":[]}";

        return json;
    }
}
