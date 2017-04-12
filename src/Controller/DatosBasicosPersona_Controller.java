/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TDatosBasicosPersona;
import Model.DatosBasicosPersona_Model;
import javax.swing.JOptionPane;

/**
 *
 * @author afvc2203
 */
public class DatosBasicosPersona_Controller {
    private TDatosBasicosPersona dbp;
    private DatosBasicosPersona_Model dbp_Model;
    
    private int ObjectIdAfterInserting;

    public DatosBasicosPersona_Controller() {
        dbp_Model = new DatosBasicosPersona_Model();
    }
    
    
    
    
//<editor-fold defaultstate="collapsed" desc="Method to UPDATE">
    public boolean update(TDatosBasicosPersona objDbp) {
        return dbp_Model.editar(objDbp,"TDatosBasicosPersona");
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Method to INSERT">
    public TDatosBasicosPersona insert(TDatosBasicosPersona objDbp) {
        ObjectIdAfterInserting = Integer.parseInt("" + dbp_Model.insertar(objDbp,"TDatosBasicosPersona"));

        if (ObjectIdAfterInserting != 0) {
            dbp = objDbp;
            dbp.setTdbpId(ObjectIdAfterInserting);
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
        }
        return dbp;
    }
//</editor-fold>     
}
