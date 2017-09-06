/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Entity.TCuota;
import Entity.TPago;
import Entity.TPersona;
import Entity.TPrestamo;
import Model.Cobrador_Model;
import Model.Prestamo_model;
import Model.TPagos_Model;
import UI.Abonos;
import UI.Cuota_UI;
import UI.Multa_Ui;
import UI.Prestamo_ui;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author An
 */
public class Abono extends Prestamo_Controller {

    private Abonos ab_UI;
    private List<TPersona> listp;
    private List<TCobrador> listc;
    private List<TPago> listpa;
    private Cobrador_Model cmodel;
    private TPagos_Model pamodel;
    private final Prestamo_model pmodel;
    public DefaultTableModel dfm;
    private TPrestamo temp;

    public Abono(Abonos ab_UI) {
        this.ab_UI = ab_UI;
        temp = new TPrestamo();
        pmodel = new Prestamo_model();
        if (ab_UI.a_fecha.getDate() == null) {
            //System.out.println(Cuota_UI.a_fecha.getDate().toString());
            ab_UI.a_fecha.setDate(new Date());
        }
        cmodel = new Cobrador_Model();
        pamodel = new TPagos_Model();

    }

    public void insert() {

        if (validar()) {

            temp.setTpreId(Integer.parseInt(ab_UI.idPrestamos.getText()));

            TCobrador cobrador = new TCobrador();
            if (ab_UI.a_cobrador.getText().equals("Por defecto")) {
                cobrador = this.cmodel.first();
            }

            TPago pagos = new TPago();
            if (ab_UI.a_metodo.getText().equals("Por defecto")) {
                pagos = this.pamodel.first();
            }

            int r = JOptionPane.showConfirmDialog(null, "Compruebe los siguientes datos…\n \n Abono : " + Long.parseLong(ab_UI.a_abono.getText()) + "\n Cantidad de Cuotas : " + Integer.parseInt(ab_UI.a_cantcuotas.getText()) + "\n Saldo : " + Long.parseLong(ab_UI.a_saldo.getText()) + "\n \n Presione “Si” en caso que sea correcto.", "Comprobar datos", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {
                TCuota cuota = new TCuota(cobrador, pagos, temp, Abonos.a_fecha.getDate(), Long.parseLong(ab_UI.a_abono.getText()), Long.parseLong(ab_UI.a_saldo.getText()), Integer.parseInt(ab_UI.a_cantcuotas.getText()));
                Serializable idcuota = this.pmodel.insertarSinBitacora(cuota, "PRESTAMO");

                if (idcuota != null) {
                    JOptionPane.showMessageDialog(null, "Se agrego la el abono");
                    ab_UI.a_fecha.setDate(new Date());
                    ab_UI.a_abono.setText("");
                    ab_UI.a_saldo.setText("");
                    ab_UI.a_cantcuotas.setText("");

                    ab_UI.a_cobrador.setText("Por defecto");
                    ab_UI.a_metodo.setText("Por defecto");

                }
            }

        }

    }

    private boolean validar() {
        String msj = "";
        msj += ab_UI.idPrestamos.getText().equals("") ? "Debe ingresar el id del prestamo \n" : "";
        msj += ab_UI.a_cobrador.getText().equals("") ? "Debe ingresar el cobrador \n" : "";
        msj += ab_UI.a_saldo.getText().equals("") ? "Debe ingresar el saldo \n" : "";
        msj += ab_UI.a_metodo.getText().equals("") ? "Debe ingresar el metodo de pago \n" : "";
        msj += ab_UI.a_fecha.getDate() == null ? "Debes seleccionar la fecha de inicio \n" : "";
        msj += ab_UI.a_abono.getText().equals("") ? "Debes ingresar el abono \n" : "";
        msj += ab_UI.a_cantcuotas.getText().equals("") ? "Debes ingresar el abono \n" : "";
        try {
            Integer.parseInt(ab_UI.a_abono.getText());
        } catch (NumberFormatException ex) {
            msj += "El valor de la cuota debe ser numerico \n";
        }
        if (msj.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, msj, "ERROR AL REGISTAR ", JOptionPane.WARNING_MESSAGE);
            return false;
        }

    }
}
