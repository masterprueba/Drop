/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCliente;
import UI.Abono_ui;

/**
 *
 * @author Usuario
 */
public class Abono_Controller extends Prestamo_Controller{

    public Abono_Controller() {        
        
    }    
    @Override
    public void setCliente(TCliente cliente){
        Abono_ui.a_nombre.setText(cliente.getTPersona().getTperNombre()+" "+cliente.getTPersona().getTperApellido());
    }
}
