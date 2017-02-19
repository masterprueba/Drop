/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Entity.TCuota;
import Entity.TPrestamo;
import UI.Abono_ui;
import java.util.Set;

/**
 *
 * @author Usuario
 */
public class Abono_Controller extends Prestamo_Controller{

    public Abono_Controller() {        
        
    }    
    @Override
    public void setCliente(TPersona cliente){
        Abono_ui.a_nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre()+" "+cliente.getTDatosBasicosPersona().getTdbpApellido());
    }
    
    public void setData(String cc){
        TPersona cliente = consultarCliente(cc);
        Set a = cliente.getTPrestamos();
        TPrestamo tp = (TPrestamo)a.toArray()[a.size()-1];
        Abono_ui.a_valorprestamo.setText(tp.getTpreValorTotal()+"");        
        //Abono_ui.a_cantcuotas.setText(tp.getTpreNumCuotas()+"");
        Abono_ui.a_totalcuota.setText(tp.getTpreValorCuota()+"");
        TCuota tc = (TCuota)tp.getTCuotas().toArray()[tp.getTCuotas().size()-1];
        Abono_ui.a_saldo.setText(tc.getTcuoNuevoSaldo()+"");
        Abono_ui.a_fechault.setText(tc.getTcuoFecha()+"");
        Abono_ui.a_totalPrestamo.setText((int)(tp.getTpreValorTotal()*((float)((float)tp.getTpreIntereses()/100)+1))+"");
        Abono_ui.a_abonado.setText((int)(tp.getTpreValorTotal()*((float)((float)tp.getTpreIntereses()/100)+1))-tc.getTcuoNuevoSaldo()+"");
        //Abono_ui.a_cuotaspag.setText(tc.get);
        //Abono_ui.a_cuotaspend.setText(tp.getTpreNumCuotas()-tc.getTcuoAbono()+"");
    }
}
