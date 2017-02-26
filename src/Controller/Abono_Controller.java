/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Entity.TCuota;
import Entity.TPrestamo;
import Model.Prestamo_model;
import UI.Abono_ui;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Abono_Controller extends Prestamo_Controller {

    private final Prestamo_model pmodel;
    private TPrestamo prestamo;
    private TCuota abono;

    public Abono_Controller() {
        pmodel = new Prestamo_model();
    }

    @Override
    public void setCliente(TPersona cliente) {
        Abono_ui.a_nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido());
    }

    @Override
    public void setClienteError(TPersona Cliente) {

    }

    public boolean setData(String cc) {
        TPersona cliente = consultarCliente(cc);
        if (cliente != null) {
            Set a = cliente.getTPrestamos();
            if (a.size() > 0) {
                prestamo = (TPrestamo) a.toArray()[a.size() - 1];
                Long valorc = prestamo.getTpreValorCuota();
                Abono_ui.a_totalcuota.setText(valorc + "");
                Abono_ui.a_cuotaneto.setText(valorc - (valorc * ((float) prestamo.getTpreIntereses() / 100)) + "");
                Abono_ui.a_interes.setText(valorc * ((float) prestamo.getTpreIntereses() / 100) + "");
                Set cuotas = prestamo.getTCuotas();
                if (cuotas.size() > 0) {
                    abono = (TCuota) prestamo.getTCuotas().toArray()[cuotas.size() - 1];
                    if (abono.getTcuoNuevoSaldo() < 1) {
                        cliente = null;
                        JOptionPane.showMessageDialog(null, "Este cliente no tiene prestamo activo");
                        return false;
                    }
//                    
                }
            } else {
                prestamo = null;
                abono = null;
                cliente = null;
                JOptionPane.showMessageDialog(null, "Este cliente no tiene prestamo");
                return false;
            }

            return true;
        } else {
            abono = null;
            prestamo = null;
            return false;
        }
    }

    public void insertar() {
        Long saldo;
        int cpagadas;
        if (abono != null) {
            saldo = abono.getTcuoNuevoSaldo() + Long.parseLong(Abono_ui.a_abono.getText());
            cpagadas = (int) ((float) saldo / prestamo.getTpreValorCuota());
        } else {
            saldo = Long.parseLong(Abono_ui.a_abono.getText());
            cpagadas = Integer.parseInt(Abono_ui.a_cantcuotas.getText());
        }
        if (prestamo != null) {
            TCuota cuota = new TCuota(prestamo, Abono_ui.a_fecha.getDate(), Long.parseLong(Abono_ui.a_abono.getText()), saldo, cpagadas, String.valueOf(Abono_ui.a_metodo.getSelectedItem()), Abono_ui.a_cobrador.getText());
            if (pmodel.insertar(cuota, true) != null) {
                Abono_ui.a_debe.setText(prestamo.getTpreValorTotal()-cuota.getTcuoNuevoSaldo() + "");
                Abono_ui.a_fechault.setText(cuota.getTcuoFecha() + "");
                Abono_ui.a_totalPrestamo.setText(prestamo.getTpreValorTotal()+"");
                Abono_ui.a_abonado.setText(cuota.getTcuoNuevoSaldo()+"");
                Abono_ui.a_cuotaspag.setText(cuota.getTcuoCuotasPagadas()+"");
                Abono_ui.a_cuotaspend.setText(prestamo.getTpreNumCuotas() - cuota.getTcuoCuotasPagadas() + "");
                Abono_ui.a_valorprestamo.setText(prestamo.getTpreValorPrestamo()+"");
            }
        }
    }

    public void calcularCantidad() {
        int valorcuota = (int) prestamo.getTpreValorCuota();
        int abonotemp = Integer.parseInt(Abono_ui.a_abono.getText());
        int cantidad = (int) ((float) abonotemp / valorcuota);
        Abono_ui.a_cantcuotas.setText(String.valueOf(cantidad));
    }
}
