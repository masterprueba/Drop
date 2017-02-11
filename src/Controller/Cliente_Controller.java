/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCliente;
import Model.Cliente_Model;
import UI.Cliente__UI;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Cliente_Controller {

    private Cliente__UI cli_UI;
    private TCliente c;
    private Cliente_Model clienteModel;
    private int ObjectIdentity = 0;

    public Cliente_Controller(Cliente__UI cli_UI) {
        this.cli_UI = cli_UI;
        clienteModel = new Cliente_Model();
    }

//<editor-fold defaultstate="collapsed" desc="Method to Insert">
    public void insert() {
        if (validar()) {
            
            //Objeto
            c = new TCliente();
            c.setTPersona(cli_UI.objectsPers.get(0));
            c.setTCodeudor(cli_UI.objectsCode.get(0));
            c.setTCasa(cli_UI.objectsCasa.get(0));
            c.setTEmpresa(cli_UI.objectsEmpr.get(0));
            
            ObjectIdentity = Integer.parseInt("" + clienteModel.insertar(c));

            if (ObjectIdentity != 0) {
                c.setTcliId(ObjectIdentity);
                //Cerrar ventana
                //setData();
                //perUI.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
            }
        }
    }
//</editor-fold>

    public boolean validar() {
        return true;
    }
}
