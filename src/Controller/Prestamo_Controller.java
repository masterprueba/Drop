/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCliente;
import Entity.TCuota;
import Entity.TPrestamo;
import Model.Cliente_Model;
import Model.Prestamo_model;
import UI.Prestamo_ui;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Prestamo_Controller {
    private final JDateChooser fecha;
    private final JTextField cc;
    private final JTextField nombre;
    private final JTextField prestamo_actual;    
    private final JTextField valor_prestamo;
    private final JTextField valor_cuota;
    private final JTextField cantidad_cuotas;
    private final JComboBox metodo;
    private final JComboBox interes;   
    private final Prestamo_model pmodel;

    public Prestamo_Controller() {
        this.cc = Prestamo_ui.P_cedula;
        this.nombre = Prestamo_ui.P_nombre;
        this.prestamo_actual = Prestamo_ui.jTextField1;        
        this.valor_prestamo = Prestamo_ui.P_valorprestamo;
        this.valor_cuota = Prestamo_ui.P_valor_cuota;
        this.cantidad_cuotas = Prestamo_ui.P_cantcuotas;
        this.metodo = Prestamo_ui.P_metodo;
        this.interes = Prestamo_ui.P_interes;                
        this.fecha = Prestamo_ui.P_fecha;
        pmodel = new Prestamo_model();
    }

    //inserta prestamo en la bd
    public void create(TCliente cliente) {
        if (validar()) {
            TPrestamo prestamo = new TPrestamo(cliente, Integer.parseInt(cantidad_cuotas.getText()),Integer.parseInt((String)interes.getSelectedItem()),(String) metodo.getSelectedItem(), new Date(), Long.parseLong(valor_prestamo.getText()), Long.parseLong(valor_cuota.getText()), new Date(), new Date(),null);
            if (pmodel.insertar(prestamo) != null) {
                JOptionPane.showMessageDialog(null, "Prestamo Realizado correctamente!!");
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro del prestamo!!");
            }
        }
    }

    //consulta y trae el objeto cliente
    public TCliente consultarCliente(String cc) {
        TCliente cliente = null;
        Cliente_Model cmodel = new Cliente_Model();
        try{
            cliente = (TCliente) cmodel.ConsultarCliente(this.cc.getText());
            nombre.setText(cliente.getTPersona().getTperNombre()+" "+cliente.getTPersona().getTperApellido());
            Prestamo_ui.P_tel.setText(cliente.getTPersona().getTperTel());
            Prestamo_ui.P_dir.setText(cliente.getTCasa().getTcasDir());            
            Set a = cliente.getTPrestamos();
            TPrestamo tp = (TPrestamo)a.toArray()[a.size()-1];
            TCuota tc = (TCuota)tp.getTCuotas().toArray()[tp.getTCuotas().size()-1];
            prestamo_actual.setText(tc.getTcuoNuevoSaldo()+"");
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(nombre, "Numero de cedula ¡No existe!", "Error C.c", JOptionPane.INFORMATION_MESSAGE);            
        }
        
        return cliente;
    }    

    private boolean validar() {
        String msj = "";
        int valida = 0;
        msj += nombre.getText().equals("") ? "Debe ingresar el cliente \n" : "";                
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
    //calcula el valor de cuota y la cantidad de cuotas a pagar
    public void calcularCuota(){
        
        String prestamo = valor_prestamo.getText().equals("") ? "0" :  valor_prestamo.getText();
        String met = String.valueOf(metodo.getSelectedItem());
        float inter = (1+((float)Integer.parseInt(String.valueOf(interes.getSelectedItem()))/100));
        int dias = cantidad_cuotas.getText().equals("") ? 1 : Integer.parseInt(cantidad_cuotas.getText());        
        float valorcuota = (Integer.parseInt(prestamo))*inter/dias;
        valor_cuota.setText(Math.round(valorcuota)+"");
    }
}
