package Entity;
// Generated 18/11/2017 03:19:48 PM by Hibernate Tools 4.3.1



/**
 * TCierre generated by hbm2java
 */
public class TCierre  implements java.io.Serializable {


     private Integer tciId;
     private int tciMes;
     private int tciAno;

    public TCierre() {
    }

    public TCierre(int tciMes, int tciAno) {
       this.tciMes = tciMes;
       this.tciAno = tciAno;
    }
   
    public Integer getTciId() {
        return this.tciId;
    }
    
    public void setTciId(Integer tciId) {
        this.tciId = tciId;
    }
    public int getTciMes() {
        return this.tciMes;
    }
    
    public void setTciMes(int tciMes) {
        this.tciMes = tciMes;
    }
    public int getTciAno() {
        return this.tciAno;
    }
    
    public void setTciAno(int tciAno) {
        this.tciAno = tciAno;
    }




}


