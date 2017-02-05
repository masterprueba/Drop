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
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Domicilio_Controller {

    private Domicilio_UI domUI;
    private final Domicilio_Model domicilioModel;
    private TCasa c;
    private int idTCasa = 0;
    private int ObjectIdentity;

    public Domicilio_Controller(Domicilio_UI domUI) {
        domicilioModel = new Domicilio_Model();
        this.domUI = domUI;
        
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

                //Cerrar ventana
                setData();
                domUI.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
            }
        }
    }

    public boolean validar() {
        if (domUI.jrbPropio.isSelected() == true) {
            idTCasa = 1;
        } else if (domUI.jrbFamiliar.isSelected() == true) {
            idTCasa = 2;
        } else if(domUI.jrbRentado.isSelected() == true){
            idTCasa = 3;
        } else {
            return false;
        }
        return true;
    }

    public void setData() {
        Cliente_UI.idDom = ObjectIdentity;
        Cliente_UI.jtfDomicilioCliente.setText(c.getTcasDir());

        //Cliente_UI.jtfNombreCliente.setText(p.getTperNombre());
    }
}
