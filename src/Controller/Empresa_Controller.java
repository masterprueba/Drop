/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TEmpresa;
import Model.Empresa_Model;
import UI.Cliente_UI;
import UI.Empresa_UI;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Empresa_Controller {

    private Empresa_UI empUI;
    private final Empresa_Model empresaModel;
    private TEmpresa e;

    public Empresa_Controller(Empresa_UI empUI) {
        this.empUI = empUI;
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

                //Cerrar ventana
                setData();
                empUI.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
            }
        }
    }

    public boolean validar() {

        return true;
    }

    public void setData() {
        Cliente_UI.idEmp = e.getTempId();
        Cliente_UI.jtfEmpresaCliente.setText(e.getTempNombre());

        //Cliente_UI.jtfNombreCliente.setText(p.getTperNombre());
    }
}
