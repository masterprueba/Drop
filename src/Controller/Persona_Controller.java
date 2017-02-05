/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Model.Persona_Model;
import UI.Cliente_UI;
import UI.Domicilio_UI;
import UI.ListaPersonas_UI;
import UI.Persona_UI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class Persona_Controller {

    private Persona_UI perUI;
    private final Persona_Model personaModel;
    private TPersona p;
    private List<TPersona> listP;
    private int id;
    private int ObjectIdentity = 0;
    public DefaultTableModel dfm;

    public Persona_Controller(Persona_UI perUI) {
        this.perUI = perUI;
        personaModel = new Persona_Model();
    }

    public Persona_Controller() {
        personaModel = new Persona_Model();
    }

    //Medoto guardar
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

                //Cerrar ventana
                setData();
                perUI.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
            }
        }
    }

    public List<TPersona> selectAll() {
        listP = new ArrayList<TPersona>();
        listP = personaModel.findAll(TPersona.class);
        return listP;
    }

    public boolean validar() {

        return true;
    }

    public void setData() {
        switch (perUI.elemento) {
            case "Cliente":
                Cliente_UI.idPer = ObjectIdentity;
                Cliente_UI.jtfCedulaCliente.setText(p.getTperCedula());
                Cliente_UI.jtfNombreCliente.setText(p.getTperNombre());
                Cliente_UI.jtfApellidoCliente.setText(p.getTperApellido());
                Cliente_UI.jtfTelefonoCliente.setText(p.getTperTel());
                //Cliente_UI.tester();
                break;
            case "Domicilio":
                Domicilio_UI.idProd = ObjectIdentity;
                Domicilio_UI.jtfPropietario.setText(p.getTperNombre());
                break;
            default:
                break;
        }

    }

    public void initTable(JTable table) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);

        dfm.setColumnIdentifiers(new Object[]{"Nombre y Apellido", "Cedula"});
        selectAll();
        for (int i = 0; i < listP.size(); i++) {
            dfm.addRow(new Object[]{listP.get(i).getTperNombre() + " " + listP.get(i).getTperApellido(), listP.get(i).getTperCedula()});

        }
    }

    public void mouseClickedTable(JTable table, ListaPersonas_UI lpUI) {
        String Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
        for (int i = 0; i < listP.size(); i++) {
            if (listP.get(i).getTperCedula() == Select) {
                //System.out.println("Seleccionado" + listaP.get(i).getTperCedula());
                switch (lpUI.elemento) {
                    case "Domicilio":
                        Domicilio_UI.persona = listP.get(i);
                        Domicilio_UI.jtfPropietario.setText(listP.get(i).getTperNombre() + " " + listP.get(i).getTperApellido());
                        lpUI.dispose();
                        break;
                    case "Cliente":
                        Cliente_UI.idPer = listP.get(i).getTperId();
                        Cliente_UI.jtfCedulaCliente.setText(listP.get(i).getTperCedula());
                        Cliente_UI.jtfNombreCliente.setText(listP.get(i).getTperNombre());
                        Cliente_UI.jtfApellidoCliente.setText(listP.get(i).getTperApellido());
                        Cliente_UI.jtfTelefonoCliente.setText(listP.get(i).getTperTel());
                        lpUI.dispose();
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    
}
