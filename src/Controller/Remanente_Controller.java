/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Entity.TRemanente;
import Model.Models;
import UI.Remanente_UI;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Remanente_Controller extends Controllers {

    private final Remanente_UI vista;
    private final Models modelo;
    private TRemanente remanente;

    public Remanente_Controller(Remanente_UI vista) {
        this.vista = vista;
        modelo = new Models();
        cargarCombo();
    }

    private void cargarCombo() {
        modelo.findAll(TCobrador.class).stream().filter((t) -> {
            return !((TCobrador) t).getTcobNombre().equals("REFINANCIACION");
        }).forEach((c) -> {
            vista.jComboBox3.addItem(((TCobrador) c).getTcobNombre());
        });
    }

    public void guardar() {
        if (validar()) {
            llenarObjetoGastos();
            if (Cierre_Controller.consutarCierre(remanente.getTreFecha())) {
                if (modelo.insertar(remanente, "REMANENTE") != null) {
                    vaciarCampos();
                    //traerGastos(obtenerRadiobuttonSeleccionado());
                    JOptionPane.showMessageDialog(null, "Se ha ingresado el remanente correctamente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            }
        }
    }

    private boolean validar() {
        String mensaje = "";
        if (vista.jTextField1.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el valor del remanente \n";
        }
        if (vista.Comp_Fecha.getDate() == null) {
            mensaje += "-No se puede dejar vacia la fecha del remanente \n";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n" + mensaje);
            return false;
        }
        return true;
    }

    private void llenarObjetoGastos() {
        if (remanente == null) {
            remanente = new TRemanente();
        }
        remanente.setTreFecha(vista.Comp_Fecha.getDate());
        remanente.setTreValor(Long.valueOf(vista.jTextField1.getText()));
    }

    private void vaciarCampos() {
        remanente = null;
        vista.jTextField1.setText(null);
        vista.Comp_Fecha.setDate(null);
    }
}
