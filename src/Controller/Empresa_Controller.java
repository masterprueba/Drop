/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCasa;
import Entity.TEmpresa;
import Model.Empresa_Model;
import UI.Cliente_UI;
import UI.Empresa_UI;
import UI.ListaDomicilios_UI;
import UI.ListaEmpresas_UI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class Empresa_Controller {

    private Empresa_UI empUI;
    private final Empresa_Model empresaModel;
    private TEmpresa e;
    private List<TEmpresa> listE;
    public DefaultTableModel dfm;

    public Empresa_Controller(Empresa_UI empUI) {
        this.empUI = empUI;
        empresaModel = new Empresa_Model();
    }

    public Empresa_Controller() {
        empresaModel = new Empresa_Model();
    }

    //Medoto guardar
    public void insert() {
        if (validar()) {

            //Objeto
            e = new TEmpresa();
            e.setTempNombre(empUI.jtfNombreEmpresa.getText());
            e.setTempDir(empUI.jtfDireccionEmpresa.getText());
            e.setTempTel(empUI.jtfTelefonoEmpresa.getText());

            int ObjectIdentity = 0;
            ObjectIdentity = Integer.parseInt("" + empresaModel.insertar(e));

            if (ObjectIdentity != 0) {
                e.setTempId(ObjectIdentity);
                //Cerrar ventana
                setData();
                empUI.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
            }
        }
    }

    public List<TEmpresa> selectAll() {
        listE = new ArrayList<TEmpresa>();
        listE = empresaModel.findAll(TEmpresa.class);
        return listE;
    }

    public boolean validar() {

        return true;
    }

    public void setData() {
        Cliente_UI.objEmpr = e;
        Cliente_UI.jtfEmpresaCliente.setText(e.getTempNombre());

        //Cliente_UI.jtfNombreCliente.setText(p.getTperNombre());
    }

    public void initTable(JTable table) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);

        dfm.setColumnIdentifiers(new Object[]{"Empresa", "Teléfono"});
        selectAll();
        for (int i = 0; i < listE.size(); i++) {
            dfm.addRow(new Object[]{listE.get(i).getTempNombre(), listE.get(i).getTempTel()});

        }
    }
    
        public void mouseClickedTable(JTable table, ListaEmpresas_UI leUI) {
        String Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
        for (int i = 0; i < listE.size(); i++) {
            System.out.println(Select);
            if (listE.get(i).getTempNombre() == Select) {
                //System.out.println("Seleccionado" + listC.get(i).getTcasDir());

                Cliente_UI.objEmpr = listE.get(i);
                Cliente_UI.jtfEmpresaCliente.setText(listE.get(i).getTempNombre());
                leUI.dispose();

            }
        }
    }
}
