/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCliente;
import Entity.TPrestamo;
import Model.Prestamo_model;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Prestamo_Controller {

    private final JTextField cc;
    private final JTextField nombre;
    private final JTextField prestamo_actual;
    private final JTextField fecha_ultimo_pago;
    private final JTextField valor_prestamo;
    private final JTextField valor_cuota;
    private final JTextField cantidad_cuotas;
    private final JLocaleChooser metodo;
    private final JLocaleChooser interes;
    private final JDateChooser fecha_ini;
    private final JDateChooser fecha_fin;
    private final Prestamo_model pmodel;

    public Prestamo_Controller(JTextField cc, JTextField nombre, JTextField prestamo_actual, JTextField fecha_ultimo_pago, JTextField valor_prestamo, JTextField valor_cuota, JTextField cantidad_cuotas, JLocaleChooser metodo, JLocaleChooser interes, JDateChooser fecha_ini, JDateChooser fecha_fin) {
        this.cc = cc;
        this.nombre = nombre;
        this.prestamo_actual = prestamo_actual;
        this.fecha_ultimo_pago = fecha_ultimo_pago;
        this.valor_prestamo = valor_prestamo;
        this.valor_cuota = valor_cuota;
        this.cantidad_cuotas = cantidad_cuotas;
        this.metodo = metodo;
        this.interes = interes;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        pmodel = new Prestamo_model();
    }

    //inserta prestamo en la bd
    public void create(TCliente cliente) {
        if (validar()) {
            TPrestamo prestamo = new TPrestamo(cliente, Integer.parseInt(cantidad_cuotas.getText()), new Date(), Long.parseLong(valor_prestamo.getText()), Long.parseLong(valor_cuota.getText()), fecha_ini.getDate(), fecha_fin.getDate(), null);
            if (pmodel.insertar(prestamo)) {
                JOptionPane.showMessageDialog(null, "Prestamo Realizado correctamente!!");
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro del prestamo!!");
            }
        }
    }

    //consulta y trae el objeto cliente
    public TCliente consultarCliente(String cc) {
        TCliente c = (TCliente) pmodel.consultar(TCliente.class, Integer.parseInt(cc));
        return c;
    }

    private boolean validar() {
        String msj = "";
        int valida = 0;
        msj += nombre.getText().equals("") ? "Debe ingresar el cliente \n" : "";        
        try {
            fecha_ini.getCalendar().equals("");
            fecha_fin.getCalendar().equals("");
            msj += fecha_ini.getDate().after(fecha_fin.getDate()) ? "Fecha inicial no puede estar despues de fecha en la que termina \n" : "";
        } catch (NullPointerException ex) {
            msj += "Error en la fecha(inicia-termina)\n";
        }        
        try {
            Long.parseLong(valor_cuota.getText());
            valida = 1;
            Integer.parseInt(cantidad_cuotas.getText());
            valida = 2;
            Long.parseLong(valor_prestamo.getText());
            valida = 3;
        } catch (NumberFormatException ex) {
            switch (valida) {
                case 0:
                    msj += "El valor de la cuota debe ser numerico";
                    break;
                case 1:
                    msj += "La cantidad de cuotas debe ser numerico";
                    break;
                case 2:
                    msj += "El valor del prestamo debe ser numerico";
                    break;
                default:
                    break;
            }
        }
        if (msj.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, msj, "ERROR AL REGISTAR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}
