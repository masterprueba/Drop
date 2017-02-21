/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Entity.TCuota;
import Entity.TDatosBasicosPersona;
import Entity.TPrestamo;
import Model.Persona_Model;
import Model.Prestamo_model;
import UI.Prestamo_ui;
import com.toedter.calendar.JDateChooser;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    DecimalFormat formateador;

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
        formateador = new DecimalFormat("###,###.##");
    }

    //inserta prestamo en la bd
    public void create(TPersona cliente) {
        if (validar()) {
            calcularCuota();
            double inter = ((double) Integer.parseInt((String)interes.getSelectedItem())/100)+1;            
            Long vcuota = null;
            try {
                Long valorprestamo = ((Long)formateador.parse(valor_prestamo.getText())) + Integer.parseInt(prestamo_actual.getText());
                Long valortotal = Math.round(valorprestamo * inter); 
                vcuota = (Long) formateador.parse(valor_cuota.getText());
                System.out.println(cliente.getTDatosBasicosPersona().getTdbpNombre());
                TPrestamo prestamo = new TPrestamo(cliente, valorprestamo.intValue(), Integer.parseInt(cantidad_cuotas.getText()),Integer.parseInt((String)interes.getSelectedItem()),(String) metodo.getSelectedItem(), fecha.getDate(),valortotal, vcuota,null);
                if (pmodel.insertar(prestamo) != null) {
                    JOptionPane.showMessageDialog(null, "Prestamo total: $"+valortotal+" Realizado  correctamente!!");
                    Prestamo_ui.jPanel2.setVisible(false);
                    nombre.setText("");
                    Prestamo_ui.P_tel.setText("");
                    Prestamo_ui.P_dir.setText("");
                    prestamo_actual.setText("");
                    valor_prestamo.setText("0");
                    cantidad_cuotas.setText("0");
                    valor_cuota.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro del prestamo!! ");
                    
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "ERROR EN parseo!!");
            }            
        }
    }

    //consulta y trae el objeto cliente 
    public TPersona consultarCliente(String cc) {
        System.out.println("Controller.Prestamo_Controller.consultarCliente()");
        TPersona temp = new TPersona();
        TDatosBasicosPersona datos = new TDatosBasicosPersona();
        datos.setTdbpCedula(cc);
        temp.setTDatosBasicosPersona(datos);
        temp.setTperTipo("cliente");
        TPersona cliente = null;
        Persona_Model cmodel = new Persona_Model();
        try{
            cliente = (TPersona) cmodel.consultarCliente(temp);
            setCliente(cliente);
            Prestamo_ui.jPanel2.setVisible(true);
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(nombre, "Numero de cedula ¡No existe!", "Error C.c", JOptionPane.INFORMATION_MESSAGE);
            cliente = null;
            nombre.setText("");
            Prestamo_ui.P_tel.setText("");
            Prestamo_ui.P_dir.setText("");
            prestamo_actual.setText("");
        }
        
        return cliente;
    }    

    private boolean validar() {
        String msj = "";
        int valida = 0;
        msj += nombre.getText().equals("") ? "Debe ingresar el cliente \n" : "";
        msj += valor_cuota.getText().equals("0") ? "El valor de la cuota debe ser numerico \n" : "";
        msj += cantidad_cuotas.getText().equals("0") ? "La cantidad de cuotas debe ser numerico \n" : "";
        msj += valor_prestamo.getText().equals("0") ? "El valor del prestamo debe ser numerico \n" : "";
        msj += fecha.getDate() == null ? "Debes seleccionar la fecha de inicio \n" : "";        
        try {            
            Long.parseLong(parsearFormato(valor_cuota.getText()));
            valida = 1;
            Integer.parseInt(cantidad_cuotas.getText());
            valida = 2;
            Long.parseLong(parsearFormato(valor_prestamo.getText()));
            valida = 3;            
        } catch (NumberFormatException ex) {
            switch (valida) {
                case 0:
                    msj += "El valor de la cuota debe ser numerico \n";
                    break;
                case 1:
                    msj += "La cantidad de cuotas debe ser numerico \n";
                    break;
                case 2:
                    msj += "El valor del prestamo debe ser numerico \n";
                    break;                             
                default:
                    break;
            }
        }
        if (msj.equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, msj, "ERROR AL REGISTAR ", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    //calcula el valor de cuota y la cantidad de cuotas a pagar    
    public void calcularCuota(){        
        String presta = valor_prestamo.getText().equals("") ? "0" : parsearFormato(valor_prestamo.getText());        
        int prestamo = Integer.parseInt(presta) + Integer.parseInt(prestamo_actual.getText());            
        float inter = (1+((float)Integer.parseInt(String.valueOf(interes.getSelectedItem()))/100));
        int dias = cantidad_cuotas.getText().equals("")||cantidad_cuotas.getText().equals("0") ? 1 : Integer.parseInt(cantidad_cuotas.getText());                
        float valorcuota = (prestamo*inter)/dias;        
        if (Math.round(valorcuota)>0) {            
            valor_cuota.setText(formateador.format(Math.round(valorcuota)));
        }else{
            valor_cuota.setText("0");
        }                
    }
    
    public void setCliente(TPersona cliente){
        nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre()+" "+cliente.getTDatosBasicosPersona().getTdbpApellido());
            Prestamo_ui.P_tel.setText(cliente.getTDatosBasicosPersona().getTdbpTel());
            Prestamo_ui.P_dir.setText(cliente.getTperCasDir()); 
            //Trae el ultimo prestamo del cliente
            Set a = cliente.getTPrestamos();
            if (a.size()>0) {
                TPrestamo tp = (TPrestamo)a.toArray()[a.size()-1];                
                if (tp.getTCuotas().size()>0) {
                    //Trae la ultima cuota del prestamo
                    TCuota tc = (TCuota)tp.getTCuotas().toArray()[tp.getTCuotas().size()-1];
                    //set a prestamo actual
                    prestamo_actual.setText(tc.getTcuoNuevoSaldo()+"");
                }else{
                    prestamo_actual.setText(tp.getTpreValorTotal()+"");
                }
                
            }
            
    }
    protected String parsearFormato(String cadena) {
        String resp = "";           
            for (int i = 0; i < cadena.length(); i++) {
                if (cadena.charAt(i) == '.') {
                } else {
                    resp += cadena.charAt(i);
                }
            }
        try{
            int i = Integer.parseInt(resp);
        }catch(NumberFormatException ex){
            resp="0";
        }
        
        return resp;
    }    
}
