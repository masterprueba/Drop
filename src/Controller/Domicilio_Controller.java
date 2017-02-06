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

    private Domicilio_UI domUI;
    private final Domicilio_Model domicilioModel;
    private TCasa c;
    private List<TCasa> listC;
    private int idTCasa = 0;
    private int ObjectIdentity;
    public DefaultTableModel dfm;

    public Domicilio_Controller(Domicilio_UI domUI) {
        domicilioModel = new Domicilio_Model();
        this.domUI = domUI;

    }

    public Domicilio_Controller() {
        domicilioModel = new Domicilio_Model();
    }

    public void insert() {
        if (validar()) {
            TTipoCasa tipoc = new TTipoCasa();
            tipoc.setTtipId(idTCasa);

            //Objeto
            c = new TCasa();
            c.setTcasDir(domUI.jtfDireccionDomicilio.getText());
            c.setTTipoCasa(tipoc);
            c.setTPersona(domUI.persona);

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

    public List<TCasa> selectAll() {
        listC = new ArrayList<TCasa>();
        listC = domicilioModel.findAll(TCasa.class);
        return listC;
    }

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

    public void initTable(JTable table) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);

        dfm.setColumnIdentifiers(new Object[]{"Dirección", "Propietario", "Tipo"});
        selectAll();
        for (int i = 0; i < listC.size(); i++) {
            dfm.addRow(new Object[]{listC.get(i).getTcasDir(), listC.get(i).getTPersona().getTperNombre() + " " + listC.get(i).getTPersona().getTperApellido(), listC.get(i).getTTipoCasa().getTtipDesc()});

        }
    }

    public void setData() {
        Cliente_UI.objCasa = c;
        Cliente_UI.jtfDomicilioCliente.setText(c.getTcasDir());

        //Cliente_UI.jtfNombreCliente.setText(p.getTperNombre());
    }

    public void mouseClickedTable(JTable table, ListaDomicilios_UI ldUI) {
        String Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
        for (int i = 0; i < listC.size(); i++) {
            System.out.println(Select);
            if (listC.get(i).getTcasDir() == Select) {
                //System.out.println("Seleccionado" + listC.get(i).getTcasDir());

                Cliente_UI.objCasa = listC.get(i);
                Cliente_UI.jtfDomicilioCliente.setText(listC.get(i).getTcasDir());
                ldUI.dispose();

            }
        }
    }
}
