/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCodeudor;
import Model.Codeudor_Model;
import UI.Cliente__UI;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Codeudor_Controller {

    private Cliente__UI cli_UI;
    private TCodeudor co;
    private Codeudor_Model codeudorModel;
    private int ObjectIdentity = 0;

    public Codeudor_Controller(Cliente__UI cli_UI) {
        this.cli_UI = cli_UI;
        codeudorModel = new Codeudor_Model();
    }

//<editor-fold defaultstate="collapsed" desc="Method to Insert">
    public void insert() {
        if (validar()) {

            //Objeto
            co = new TCodeudor();
            co.setTPersona(cli_UI.objectsPers.get(0));
            co.setTCasa(cli_UI.objectsCasa.get(0));
            co.setTEmpresa(cli_UI.objectsEmpr.get(0));

            ObjectIdentity = Integer.parseInt("" + codeudorModel.insertar(co));

            if (ObjectIdentity != 0) {
                co.setTcodId(ObjectIdentity);
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
