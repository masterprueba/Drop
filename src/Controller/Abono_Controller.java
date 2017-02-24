/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Entity.TCuota;
import Entity.TPrestamo;
import UI.Abono_UI;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Abono_Controller extends Prestamo_Controller {

    public Abono_Controller() {

    }

    @Override
    public void setCliente(TPersona cliente) {
        Abono_UI.a_nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido());
    }

    @Override
    public void setClienteError(TPersona Cliente) {

    }

    public boolean setData(String cc) {
        TPersona cliente = consultarCliente(cc);
        if (cliente != null) {
            Set a = cliente.getTPrestamos();
            if (a.size() > 0) {
                TPrestamo tp = (TPrestamo) a.toArray()[a.size() - 1];                
                Long valorc= tp.getTpreValorCuota();
                Abono_UI.a_totalcuota.setText(valorc + "");
                Abono_UI.a_cuotaneto.setText(valorc - (valorc*((float)tp.getTpreIntereses()/100))+"");
                Abono_UI.a_interes.setText(valorc*((float)tp.getTpreIntereses()/100)+"");
                Set cuotas = tp.getTCuotas();
                if (cuotas.size() > 0) {
                    TCuota tc = (TCuota) tp.getTCuotas().toArray()[cuotas.size() - 1];
                    if (tc.getTcuoNuevoSaldo()<1) {
                        cliente = null;
                        JOptionPane.showMessageDialog(null, "Este cliente no tiene prestamo activo");
                        return false;
                    }
//                    Abono_ui.a_saldo.setText(tc.getTcuoNuevoSaldo() + "");
//                    Abono_ui.a_fechault.setText(tc.getTcuoFecha() + "");
//                    Abono_ui.a_totalPrestamo.setText((int) (tp.getTpreValorTotal() * ((float) ((float) tp.getTpreIntereses() / 100) + 1)) + "");
//                    Abono_ui.a_abonado.setText((int) (tp.getTpreValorTotal() * ((float) ((float) tp.getTpreIntereses() / 100) + 1)) - tc.getTcuoNuevoSaldo() + "");
//                    //Abono_ui.a_cuotaspag.setText(tc.get);
//                    //Abono_ui.a_cuotaspend.setText(tp.getTpreNumCuotas()-tc.getTcuoAbono()+"");
                }
            }else{
                cliente = null;                
                JOptionPane.showMessageDialog(null, "Este cliente no tiene prestamo");
                return false;
            }

            return true;
        } else {            
            return false;
        }
    }
}
