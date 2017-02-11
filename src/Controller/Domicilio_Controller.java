/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCasa;
import Entity.TPersona;
import Entity.TTipoCasa;
import Model.Domicilio_Model;
import UI.Cliente_UI;
import UI.Cliente__UI;
import UI.Codeudor_UI;
import UI.Domicilio_UI;
import UI.ListaDomicilios_UI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class Domicilio_Controller {

//<editor-fold defaultstate="collapsed" desc="Variables">
    private Domicilio_UI domUI;
    private final Domicilio_Model domicilioModel;
    private String modulo;
    private TCasa c;
    private List<TCasa> listC;
    private int idTCasa = 0;
    private int ObjectIdentity;
    public DefaultTableModel dfm;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Domicilio_Controller(Domicilio_UI domUI, String modulo) {
        this.modulo = modulo;
        domicilioModel = new Domicilio_Model();
        this.domUI = domUI;
    }
    
    public Domicilio_Controller(String modulo) {
        this.modulo = modulo;
        domicilioModel = new Domicilio_Model();
        selectAll();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to Insert">
    public void insert() {
        if (validar()) {
            TTipoCasa tipoc = new TTipoCasa();
            tipoc.setTtipId(idTCasa);
            
            //Objeto
            c = new TCasa();
            c.setTcasDir(domUI.jtfDireccionDomicilio.getText());
            c.setTTipoCasa(tipoc);
            c.setTPersona(domUI.objectPer);
            
            ObjectIdentity = 0;
            ObjectIdentity = Integer.parseInt("" + domicilioModel.insertar(c));
            
            if (ObjectIdentity != 0) {
                c.setTcasId(ObjectIdentity);
                //Cerrar ventana
                setData();
                domUI.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method Select">
    public void selectAll() {listC = domicilioModel.findAll(TCasa.class);}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Validate">
    public boolean validar() {
        if (domUI.jrbPropio.isSelected() == true) {
            idTCasa = 1;
        } else if (domUI.jrbFamiliar.isSelected() == true) {
            idTCasa = 2;
        } else if (domUI.jrbRentado.isSelected() == true) {
            idTCasa = 3;
        } else {
            return false;
        }
        return true;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="INIT JTable">
    public void initTable(JTable table) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);
        
        dfm.setColumnIdentifiers(new Object[]{"Dirección", "Propietario", "Tipo"});
        
        for (int i = 0; i < listC.size(); i++) {
            dfm.addRow(new Object[]{listC.get(i).getTcasDir(), listC.get(i).getTPersona().getTperNombre() + " " + listC.get(i).getTPersona().getTperApellido(), listC.get(i).getTTipoCasa().getTtipDesc()});
            
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to SET Data in Objects and JTextFiels For The GUI">
    public void setData() {
        switch (modulo) {
            case "Cliente":
                Cliente__UI.objectsCasa.set(0, c);
                Cliente__UI.jtfDomicilioCliente.setText(c.getTcasDir());
                break;
            case "Codeudor":
                Cliente__UI.objectsCasa.set(1, c);
                Cliente__UI.jtfDomicilioCodeudor.setText(c.getTcasDir());
                break;
            default:
                break;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to Event MouseCliked in Rows of The JTable">
    public void mouseClickedTable(JTable table, ListaDomicilios_UI ldUI) {
        String Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
        for (int i = 0; i < listC.size(); i++) {
            System.out.println(Select);
            if (listC.get(i).getTcasDir() == Select) {
                //System.out.println("Seleccionado" + listC.get(i).getTcasDir());
                c = listC.get(i);
                
                setData();
                ldUI.dispose();
                
            }
        }
    }
//</editor-fold>
}
