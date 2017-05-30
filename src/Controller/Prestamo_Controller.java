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
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Prestamo_Controller extends Controllers{

    private final JDateChooser fecha;
    private final JTextField cc;
    private final JTextField nombre;
    private final JTextField prestamo_actual;
    private final JTextField valor_prestamo;
    private final JTextField valor_cuota;
    private final JTextField cantidad_cuotas;
    private final JComboBox metodo;
    private final JTextField interes;
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
        formateador = new DecimalFormat("###,###.##");
    }
    

    //inserta prestamo en la bd
    public void create(TPersona cliente) {
        if (validar()) {
            calcularCuota();
            double inter = ((double) Integer.parseInt((String) interes.getText()) / 100) + 1;
            Long vcuota = null;
            try {
                Long valorprestamo = ((Long) formateador.parse(valor_prestamo.getText()));
                Long valortotal = Math.round((valorprestamo) * inter);
                vcuota = (Long) formateador.parse(valor_cuota.getText());
                System.out.println(cliente.getTDatosBasicosPersona().getTdbpNombre());
                TPrestamo prestamo = new TPrestamo(cliente, valorprestamo.intValue(), Integer.parseInt(prestamo_actual.getText()), Integer.parseInt(cantidad_cuotas.getText()), Integer.parseInt((String) interes.getText()), (String) metodo.getSelectedItem(), fecha.getDate(), valortotal, vcuota, null,null);
                if (pmodel.insertar(prestamo,"PRESTAMO") != null) {
                    String msg = "<html>Prestamo realizado correctamente:<ul><li>Valor a entregar : $<b>"+formateador.format(valorprestamo-(Long) formateador.parse(prestamo_actual.getText()))+"</b></li>"
        + "<li>Valor cuota : $<b>"+valor_cuota.getText()+"</b></li>"
        + "</ul></html>";
        JLabel label = new JLabel(msg);
        label.setFont(new Font("serif", Font.PLAIN, 14));
                    JOptionPane.showMessageDialog(null, label);
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
        TPersona temp = new TPersona();
        TDatosBasicosPersona datos = new TDatosBasicosPersona();
        datos.setTdbpCedula(cc);
        temp.setTDatosBasicosPersona(datos);
        temp.setTperTipo("cliente");
        TPersona cliente = null;
        Persona_Model cmodel = new Persona_Model();
        try {
            cliente = (TPersona) cmodel.SelectOne(temp);
            setCliente(cliente);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(nombre, "Numero de cedula ¡No existe!", "Error C.c", JOptionPane.INFORMATION_MESSAGE);
            setClienteError(cliente);
        }

        return cliente;
    }

    public void setClienteError(TPersona cliente) {
        cliente = null;
        nombre.setText("");
        Prestamo_ui.P_tel.setText("");
        Prestamo_ui.P_dir.setText("");
        prestamo_actual.setText("");
        Prestamo_ui.jPanel2.setVisible(false);
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
    public void calcularCuota() {                
        String presta = valor_prestamo.getText().equals("") ? "0" : parsearFormato(valor_prestamo.getText());
        int prestamo = Integer.parseInt(presta);
        float inter = (1 + ((float) Integer.parseInt(String.valueOf(interes.getText())) / 100));
        int dias = cantidad_cuotas.getText().equals("") || cantidad_cuotas.getText().equals("0") ? 1 : Integer.parseInt(cantidad_cuotas.getText());
        float valorcuota = (prestamo * inter) / dias;
        if (Math.round(valorcuota) > 0) {
            valor_cuota.setText(formateador.format(Math.round(valorcuota)));
        } else {
            valor_cuota.setText("0");
        }
    }

    public void setCliente(TPersona cliente) {
        Prestamo_ui.jPanel2.setVisible(true);
        nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido());
        Prestamo_ui.P_tel.setText(cliente.getTDatosBasicosPersona().getTdbpTel());
        Prestamo_ui.P_dir.setText(cliente.getTperCasDir());
        //Trae el ultimo prestamo del cliente                
        ultimaCuota(cliente, 'p');
    }

    protected String parsearFormato(String cadena) {
        String resp = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == '.') {
            } else {
                resp += cadena.charAt(i);
            }
        }
        try {
            int i = Integer.parseInt(resp);
        } catch (NumberFormatException ex) {
            resp = "0";
        }

        return resp;
    }
    protected TCuota ultimaCuota(TPersona cliente, char controller){
        Set a = cliente.getTPrestamos();
        TCuota cuota = new TCuota();
        if (a.size() > 0) {
            Date mayor = new Date(2000);
            int obj = 0;
            for (int i = 0; i < a.size(); i++) {
                TPrestamo tp = (TPrestamo) a.toArray()[i];
                System.out.println("fecha prestamo "+tp.getTpreFechaEntrega()+" fecha mayor "+mayor);
                if (tp.getTpreFechaEntrega().after(mayor)) {
                    obj = i;
                    mayor = tp.getTpreFechaEntrega();
                }
            }            
            TPrestamo tp = (TPrestamo) a.toArray()[obj];
            cuota.setTPrestamo(tp);
            if (tp.getTCuotas().size() > 0) {
                //Trae la ultima cuota del prestamo                
                int mayor2=0;
                int obj2=0;                
                for (int i = 0; i < tp.getTCuotas().size(); i++) {
                    TCuota tcuota =(TCuota) tp.getTCuotas().toArray()[i];                    
                    if(tcuota.getTcuoNuevoSaldo()>mayor2){
                        obj2 = i;
                        mayor2 = (int) tcuota.getTcuoNuevoSaldo();
                    }
                }       
                cuota = (TCuota) tp.getTCuotas().toArray()[obj2];                
                //set a prestamo actual
                if (controller == 'p') {
                    prestamo_actual.setText(tp.getTpreValorTotal()-cuota.getTcuoNuevoSaldo() + "");
                }                
            } else {
                if (controller == 'p') {
                    prestamo_actual.setText(tp.getTpreValorTotal() + "");
                }
            }

        }else{
            if (controller == 'p') {
                prestamo_actual.setText("0");
            }
            cuota = null;
        }
        return cuota;
    }
    
    public boolean update(JTable tabla){
        int conteo = 0;
       DefaultTableModel model = (DefaultTableModel)tabla.getModel();
       TPrestamo prestamo = new TPrestamo();
        for (int i = 0; i < model.getRowCount(); i++) {            
            prestamo.setTpreId(Integer.parseInt(String.valueOf(model.getValueAt(i, 1))));
            prestamo.setTpreValorPrestamo(Integer.parseInt(String.valueOf(model.getValueAt(i, 2))));
            prestamo.setTpreNumCuotas(Integer.parseInt(String.valueOf(model.getValueAt(i, 3))));
            prestamo.setTpreIntereses(Integer.parseInt(String.valueOf(model.getValueAt(i, 4))));
            prestamo.setTpreMetodPago(String.valueOf(model.getValueAt(i, 5)));                            
           try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
               Date parsedDate;
               parsedDate = dateFormat.parse(String.valueOf(model.getValueAt(i, 6)));
               prestamo.setTpreFechaEntrega(new java.sql.Timestamp(parsedDate.getTime()));
           } catch (ParseException ex) {
               JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
           }                
            prestamo.setTpreValorTotal(Integer.parseInt(String.valueOf(model.getValueAt(i, 7))));
            prestamo.setTpreValorCuota(Integer.parseInt(String.valueOf(model.getValueAt(i, 8))));
            prestamo.setTPersona((TPersona) model.getValueAt(i, 10));
            if(pmodel.editar(prestamo, "PRESTAMO")){
                conteo++;
           }
        }        
        if (conteo == model.getRowCount()) {
            return true;
        } else {
            return false;
        }
    }
     
}
