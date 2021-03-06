/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Entity.TPersona;
import Entity.TCuota;
import Entity.TDatosBasicosPersona;
import Entity.TPago;
import Entity.TPrestamo;
import Entity.TRefinanciacion;
import Model.Cobrador_Model;
import Model.Persona_Model;
import Model.Prestamo_model;
import Model.TPagos_Model;
import UI.EliminarP_UI;
import UI.Prestamo_ui;
import UI.Refinancia_UI;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
public class Prestamo_Controller extends Controllers {

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
    public static List<TCuota> listc;
    public long saldo = 0;

    public Prestamo_Controller() {
        this.cc = Prestamo_ui.P_cedula;
        this.nombre = Prestamo_ui.P_nombre;
        this.prestamo_actual = Prestamo_ui.p_deuda;
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
            //calcularCuota();
            double inter = ((double) Integer.parseInt((String) interes.getText()) / 100) + 1;
            Long vcuota = null;
            Long valorprestamo = null;
            Long valortotal = null;
            try {
                vcuota = (Long) formateador.parse(valor_cuota.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "ERROR EN parseo!!");
            }
            try {
                valorprestamo = ((Long) formateador.parse(valor_prestamo.getText()));
                valortotal = vcuota * Long.parseLong(cantidad_cuotas.getText());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "ERROR EN parseo!!");
            }

            System.out.println(cliente.getTDatosBasicosPersona().getTdbpNombre());
            TPrestamo prestamo = new TPrestamo(cliente, valorprestamo.intValue(), Integer.parseInt(Prestamo_ui.p_deuda.getText()), Integer.parseInt(cantidad_cuotas.getText()), Integer.parseInt((String) interes.getText()), (String) metodo.getSelectedItem(), fecha.getDate(), valortotal, vcuota, null, null, null);
            String msg = "";
            try {
                msg = "<html>Prestamo realizado correctamente:<ul><li>Valor a entregar : $<b>" + formateador.format(valorprestamo - (Long) formateador.parse(Prestamo_ui.p_deuda.getText())) + "</b></li>"
                        + "<li>Valor cuota : $<b>" + valor_cuota.getText() + "</b></li>"
                        + "</ul></html>";
            } catch (ParseException ex) {
                msg = "<html>Prestamo realizado correctamente:<ul><li>Valor a entregar : $<b>" + (valorprestamo - Long.parseLong(Prestamo_ui.p_deuda.getText())) + "</b></li>"
                        + "<li>Valor cuota : $<b>" + valor_cuota.getText() + "</b></li>"
                        + "</ul></html>";
            }
            JLabel label = new JLabel(msg);
            label.setFont(new Font("serif", Font.PLAIN, 14));
            if (Cierre_Controller.consutarCierre(prestamo.getTpreFechaEntrega())) {
                if (JOptionPane.showConfirmDialog(null, label, "Prestamo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {                    
                    Serializable idprestamo = pmodel.insertarPrestamo(prestamo, "PRESTAMO");
                    if (Long.parseLong(Prestamo_ui.p_deuda.getText()) > 0 && listc == null) {
                        TRefinanciacion refinaceo = new TRefinanciacion(Long.parseLong(Prestamo_ui.p_deuda.getText()), (int)idprestamo, (int)idprestamo, new Date());
                        pmodel.insertar(refinaceo, "PRESTAMO");
                    }                    
                    if (idprestamo != null) {
                        JOptionPane.showMessageDialog(null, "ID del prestamo : "+(int)idprestamo);
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
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            }
        }
    }

    //consulta y trae el objeto cliente 
    public TPersona consultarCliente(String cc) {
        saldo = 0;
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
            JOptionPane.showMessageDialog(nombre, "Numero de cedula ¡No existe! " + ex.getLocalizedMessage(), "Error C.c", JOptionPane.INFORMATION_MESSAGE);
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
        int mes = 0;
        switch (Prestamo_ui.P_metodo.getSelectedIndex()) {
            case 0: {
                Double d = Math.ceil(Double.parseDouble(Prestamo_ui.P_cantcuotas.getText()) / 30);
                mes = d.intValue();
                break;
            }
            case 1: {
                Double d = Math.ceil(Double.parseDouble(Prestamo_ui.P_cantcuotas.getText()) / 4);
                mes = d.intValue();
                break;
            }
            case 2: {
                Double d = Math.ceil(Double.parseDouble(Prestamo_ui.P_cantcuotas.getText()) / 2);
                mes = d.intValue();
                break;
            }
            case 3: {
                Double d = Math.ceil(Double.parseDouble(Prestamo_ui.P_cantcuotas.getText()) / 1);
                mes = d.intValue();
                break;
            }
            default:
                break;
        }
        String presta = valor_prestamo.getText().equals("") ? "0" : parsearFormato(valor_prestamo.getText());
        int prestamo = Integer.parseInt(presta);
        float inter = ((float) Integer.parseInt(String.valueOf(interes.getText())) / 100);
        int dias = cantidad_cuotas.getText().equals("") || cantidad_cuotas.getText().equals("0") ? 1 : Integer.parseInt(cantidad_cuotas.getText());
        mes = mes == 0 ? 1 : mes;
        float interesN = (prestamo * inter) * mes;
        float valorcuota = (prestamo + interesN) / dias;
        if (Math.round(valorcuota) > 0) {
            valor_cuota.setText(formateador.format(Math.round(valorcuota)));
        } else {
            valor_cuota.setText("0");
        }
    }

    public void setCliente(TPersona cliente) {
        Prestamo_ui.jPanel2.setVisible(true);
        Prestamo_ui.refinanciar.setVisible(true);
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

    protected TCuota ultimaCuota(TPersona cliente, char controller) {
        Set a = cliente.getTPrestamos();
        TCuota cuota = new TCuota();
        if (a.size() > 0) {
//            Date mayor = new Date(2000);
//            int obj = 0;
//            for (int i = 0; i < a.size(); i++) {
//                TPrestamo tp = (TPrestamo) a.toArray()[i];
//                System.out.println("fecha prestamo " + tp.getTpreFechaEntrega() + " fecha mayor " + mayor);
//                if (tp.getTpreFechaEntrega().after(mayor)) {
//                    obj = i;
//                    mayor = tp.getTpreFechaEntrega();
//                }
//                
//            }            
            List<TPrestamo> listp = new ArrayList<>(a);
            //TPrestamo tp = (TPrestamo) a.toArray()[obj];
            TPrestamo tp = listp.stream().max((x,y)-> x.getTpreId() - y.getTpreId()).get();
            cuota.setTPrestamo(tp);
            if (tp.getTCuotas().size() > 0) {
                //Trae la ultima cuota del prestamo                
                int mayor2 = 0;
                int obj2 = 0;                
                for (int i = 0; i < tp.getTCuotas().size(); i++) {                    
                    TCuota tcuota = (TCuota) tp.getTCuotas().toArray()[i];
                    saldo += tcuota.getTcuoAbono();
                    if (tcuota.getTcuoNuevoSaldo() > mayor2) {
                        obj2 = i;
                        mayor2 = (int) tcuota.getTcuoNuevoSaldo();
                    }
                }
                cuota = (TCuota) tp.getTCuotas().toArray()[obj2];
                //set a prestamo actual
                if (controller == 'p') {
                    prestamo_actual.setText(tp.getTpreValorTotal() - saldo + "");
                }
            } else {
                if (controller == 'p') {
                    prestamo_actual.setText(tp.getTpreValorTotal() + "");
                }
            }

        } else {
            if (controller == 'p') {
                prestamo_actual.setText("0");
            }
            cuota = null;
        }
        return cuota;
    }

    public boolean update(JTable tabla) {
        int conteo = 0;
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
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
            
            if (!comparePrestamo(prestamo)){
                if (Cierre_Controller.consutarCierre(prestamo.getTpreFechaEntrega())) {
                    if (pmodel.editar(prestamo, "PRESTAMO")) {
                        conteo++;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizó cierre ID del prestamo: "+prestamo.getTpreId());
                }
            }else {conteo++;}
        }
        return conteo == model.getRowCount();
    }

    public void initTableRefinancia(JTable table) {
        List<Object> listp = pmodel.refinanciaPrestamo();
        DefaultTableModel dfm = new TableModel().listaClientesRefinancia();
        table.setModel(dfm);
        Iterator itr = listp.iterator();
        Object[] f = new Object[7];
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            if (!Prestamo_ui.P_cedula.getText().equals(String.valueOf(obj[2]))) {
                if (obj[4] == null) {
                    f[0] = false;
                    f[1] = obj[1];
                    f[2] = obj[2];
                    f[3] = obj[3];
                    f[4] = obj[0];
                    f[5] = obj[3];
                    f[6] = obj[5];
                    dfm.addRow(f);
                } else if (Integer.parseInt(String.valueOf(obj[4])) > 0) {
                    f[0] = false;
                    f[1] = obj[1];
                    f[2] = obj[2];
                    f[3] = obj[4];
                    f[4] = obj[0];
                    f[5] = obj[3];
                    f[6] = obj[5];
                    dfm.addRow(f);
                }
            }
        }
    }

    public void abonarDeudas(JTable table, Refinancia_UI refinancia) {
        DefaultTableModel dfm = (DefaultTableModel) table.getModel();
        listc = new ArrayList();
        int totalRefinanciados = 0;
        for (int i = 0; i < dfm.getRowCount(); i++) {
            if (dfm.getValueAt(i, 0).equals(Boolean.TRUE)) {
                TCuota cuota = new TCuota();
                cuota.setTcuoAbono(Long.parseLong(String.valueOf(dfm.getValueAt(i, 3))));
                cuota.setTcuoFecha(new Date());
                cuota.setTcuoCuotasPagadas(Integer.parseInt(String.valueOf(dfm.getValueAt(i, 6))));
                cuota.setTPrestamo((TPrestamo) pmodel.consultar(TPrestamo.class, Integer.parseInt(String.valueOf(dfm.getValueAt(i, 4)))));
                cuota.setTcuoNuevoSaldo(Long.parseLong(String.valueOf(dfm.getValueAt(i, 5))));
                TCobrador cobrador = new TCobrador();
                cobrador.setTcobNombre("Refinanciado");
                TPago pago = new TPago();
                pago.setTipo("Refinanciado-.");
                TCobrador cobradorTemp = null;
                TPago pagoTemp = null;
                try {
                    cobradorTemp = new Cobrador_Model().SelectOne(cobrador);
                    pagoTemp = new TPagos_Model().SelectOne(pago);
                    System.out.println(cobradorTemp.toString());
                    System.out.println(pagoTemp.toString());
                } catch (NullPointerException e) {
                    System.out.println("entre a insertar cobrador refinanciado");
                    pmodel.insertar(cobrador, "prestamo");
                    cobradorTemp = new Cobrador_Model().SelectOne(cobrador);
                    pmodel.insertar(pago, "prestamo");
                    pagoTemp = new TPagos_Model().SelectOne(pago);
                }
                cuota.setTCobrador(cobradorTemp);
                cuota.setTPago(pagoTemp);
                totalRefinanciados += cuota.getTcuoAbono();
                listc.add(cuota);
            }
        }
        Prestamo_ui.p_deuda.setText(Integer.parseInt(Prestamo_ui.p_deuda.getText()) + totalRefinanciados + "");
        Prestamo_ui.refinanciar.setVisible(false);
        refinancia.dispose();
    }

    public void eliminarPrestamo() {
        if (EliminarP_UI.id_eliminar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el ID por favor.");
        } else {
            EliminarP_UI.msj_eliminar.setVisible(false);
            TPrestamo p = (TPrestamo) pmodel.consultar(TPrestamo.class, Integer.parseInt(EliminarP_UI.id_eliminar.getText()));
            if (p != null) {
                if (JOptionPane.showConfirmDialog(null, "Esta segur@ de eliminar el prestamo?", "Prestamo de " + p.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + p.getTPersona().getTDatosBasicosPersona().getTdbpApellido(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    if (Cierre_Controller.consutarCierre(p.getTpreFechaEntrega())) {
                        if (pmodel.eliminar(p, "PRESTAMO")) {
                            EliminarP_UI.msj_eliminar.setVisible(true);
                            EliminarP_UI.id_eliminar.setText("");
                        } else {
                            EliminarP_UI.id_eliminar.setText("");
                            JOptionPane.showMessageDialog(null, "Error no se puede borrar el prestamo. Verifique que no tenga abonos");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
                    }
                } else {
                    EliminarP_UI.id_eliminar.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error Prestamo no existe");
            }
        }
    }
    
    public int cantCuotas(){
        return pmodel.cantCuotas(1);
    }
    
    private boolean comparePrestamo(TPrestamo prestamo){
        boolean cambio = true;
        TPrestamo prestamoOriginal; 
        prestamoOriginal = (TPrestamo) pmodel.consultar(TPrestamo.class,prestamo.getTpreId());
        
        if(prestamoOriginal.getTpreValorPrestamo() != prestamo.getTpreValorPrestamo()){
            cambio = false;
        }        
        if(prestamoOriginal.getTpreNumCuotas()!= prestamo.getTpreNumCuotas()){
            cambio = false;
        }        
        if(prestamoOriginal.getTpreIntereses()!= prestamo.getTpreIntereses()){
            cambio = false;
        }        
        if(!prestamoOriginal.getTpreMetodPago().equals(prestamo.getTpreMetodPago())){
            cambio = false;
        }        
        if(prestamoOriginal.getTpreFechaEntrega().compareTo(prestamo.getTpreFechaEntrega()) != 0){
            cambio = false;
        }        
        if(prestamoOriginal.getTpreValorTotal() != prestamo.getTpreValorTotal()){
            cambio = false;
        }        
        if(prestamoOriginal.getTpreValorCuota() != prestamo.getTpreValorCuota()){
            cambio = false;
        }
        return cambio;
    }
}
