/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Model.Persona_Model;
import UI.Abono_ui;
import UI.Cliente__UI;
import UI.Domicilio_UI;
import UI.ListaPersonas_UI;
import UI.Persona_UI;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class Persona_Controller {

//<editor-fold defaultstate="collapsed" desc="Variables">
    private Persona_UI perUI;
    private String modulo;
    private final Persona_Model personaModel;
    private TPersona p;
    private List<TPersona> listP;
    private int ObjectIdentity = 0;
    public DefaultTableModel dfm;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Persona_Controller(Persona_UI perUI, String modulo) {
        this.modulo = modulo;
        this.perUI = perUI;
        personaModel = new Persona_Model();
    }
    
    public Persona_Controller(String modulo) {
        this.modulo = modulo;
        personaModel = new Persona_Model();
        selectAll();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to Insert">
    public void insert() {
        if (validar()) {

            //Objeto
            p = new TPersona();
            p.setTperCedula(perUI.jtfCedulaPersona.getText());
            p.setTperNombre(perUI.jtfNombrePersona.getText());
            p.setTperApellido(perUI.jtfApellidoPersona.getText());
            p.setTperTel(perUI.jtfTelefonoPersona.getText());

            ObjectIdentity = Integer.parseInt("" + personaModel.insertar(p));

            if (ObjectIdentity != 0) {
                p.setTperId(ObjectIdentity);
                //Cerrar ventana
                setData();
                perUI.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method Select">
    public void selectAll() {
        listP = personaModel.findAll(TPersona.class);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to SET Data in Objects and JTextFiels For The GUI">
    public void setData() {
        switch (modulo) {
            case "Cliente":
                Cliente__UI.objectsPers.set(0, p);
                Cliente__UI.jtfCedulaCliente.setText(p.getTperCedula());
                Cliente__UI.jtfNombreCliente.setText(p.getTperNombre());
                Cliente__UI.jtfApellidoCliente.setText(p.getTperApellido());
                Cliente__UI.jtfTelefonoCliente.setText(p.getTperTel());
                break;
            case "Codeudor":
                Cliente__UI.objectsPers.set(1, p);
                Cliente__UI.jtfCedulaCodeudor.setText(p.getTperCedula());
                Cliente__UI.jtfNombreCodeudor.setText(p.getTperNombre());
                Cliente__UI.jtfApellidoCodeudor.setText(p.getTperApellido());
                Cliente__UI.jtfTelefonoCodeudor.setText(p.getTperTel());
                break;
            case "Domicilio":
                Domicilio_UI.objectPer = p;
                Domicilio_UI.jtfPropietario.setText(p.getTperNombre());
                break;
            case "abono":                
                Abono_ui.a_nombre.setText(p.getTperNombre()+" "+p.getTperApellido());   
                Abono_ui.a_cedula.setText(p.getTperCedula());
                break;
            default:
                break;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="INIT JTable">
    public void initTable(JTable table) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);

        dfm.setColumnIdentifiers(new Object[]{"Nombre y Apellido", "Cedula"});
        for (int i = 0; i < listP.size(); i++) {
            dfm.addRow(new Object[]{listP.get(i).getTperNombre() + " " + listP.get(i).getTperApellido(), listP.get(i).getTperCedula()});

        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to Event MouseCliked in Rows of The JTable">
    public void mouseClickedTable(JTable table, ListaPersonas_UI lpUI) {
        String Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
        for (int i = 0; i < listP.size(); i++) {
            if (listP.get(i).getTperCedula() == Select) {
                p = listP.get(i);

                setData();
                lpUI.dispose();
            }
        }
    }
//</editor-fold>

    public boolean validar() {
        return true;
    }
}
