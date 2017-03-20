/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Entity.TCuota;
import Entity.TPrestamo;
import Model.Persona_Model;
import Model.Prestamo_model;
import UI.Cuota_UI;
import UI.ListaPersonas_UI;
import UI.Prestamo_ui;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Usuario
 */
public class Cuota_Controller extends Prestamo_Controller {

    private final Prestamo_model pmodel;
    private TPrestamo prestamo;
    private TCuota abono;
    public DefaultTableModel dfm;
     private List<TPersona> listp;     ;
     
    public Cuota_Controller() {
        pmodel = new Prestamo_model();
        if (Cuota_UI.a_fecha != null) {
            Cuota_UI.a_fecha.setDate(new Date());
        }
        
    }

    @Override
    public void setCliente(TPersona cliente) {
        Cuota_UI.a_nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido());
    }

    @Override
    public void setClienteError(TPersona Cliente) {        
    }

    public boolean setData(String cc) {        
        TPersona cliente = consultarCliente(cc);

        if (cliente != null) {            
            abono = ultimaCuota(cliente, 'c');
            if (abono != null) {                
                prestamo = abono.getTPrestamo();
                Long valorc = prestamo.getTpreValorCuota();
                Cuota_UI.a_totalcuota.setText(valorc + "");
                Cuota_UI.a_cuotaneto.setText(valorc - (valorc * ((float) prestamo.getTpreIntereses() / 100)) + "");
                Cuota_UI.a_interes.setText(valorc * ((float) prestamo.getTpreIntereses() / 100) + "");
                Cuota_UI.a_cnumcuotas.setText(String.valueOf(prestamo.getTpreNumCuotas()));
                Cuota_UI.a_cuotaspend.setText(String.valueOf(prestamo.getTpreNumCuotas()-abono.getTcuoCuotasPagadas()));
                if (abono.getTcuoNuevoSaldo() >= prestamo.getTpreValorTotal()) {
                    cliente = null;
                    JOptionPane.showMessageDialog(null, "Este cliente no tiene prestamo activo");
                    return false;
                }
            } else {
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
        if (validar()) {
            calcularCantidad();
            int r = JOptionPane.showConfirmDialog(null, "Esta seguro de abonar \n $" + formateador.format(Integer.parseInt(Cuota_UI.a_abono.getText())), "Agregar abono", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                Cuota_UI.jPanel1.setVisible(true);
                Cuota_UI.jPanel2.setVisible(false);
                Cuota_UI.jPanel3.setVisible(false);
                Long saldo;
                int cpagadas;
                if (abono != null) {
                    saldo = abono.getTcuoNuevoSaldo() + Long.parseLong(Cuota_UI.a_abono.getText());
                    cpagadas = (int) ((float) saldo / prestamo.getTpreValorCuota());
                } else {
                    saldo = Long.parseLong(Cuota_UI.a_abono.getText());
                    cpagadas = Integer.parseInt(Cuota_UI.a_cantcuotas.getText());
                }
                if (prestamo != null) {
                    TCuota cuota = new TCuota(prestamo, Cuota_UI.a_fecha.getDate(), Long.parseLong(Cuota_UI.a_abono.getText()), saldo, cpagadas, String.valueOf(Cuota_UI.a_metodo.getSelectedItem()), Cuota_UI.a_cobrador.getText());
                    if (pmodel.insertar(cuota) != null) {                       
                        Cuota_UI.a_debe.setText(prestamo.getTpreValorTotal() - cuota.getTcuoNuevoSaldo() + "");
                        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MMM-dd");
                        Cuota_UI.a_fechault.setText(dt1.format(cuota.getTcuoFecha()));
                        Cuota_UI.a_totalPrestamo.setText(prestamo.getTpreValorTotal() + "");
                        Cuota_UI.a_abonado.setText(cuota.getTcuoNuevoSaldo() + "");
                        Cuota_UI.a_cuotaspag.setText(cuota.getTcuoCuotasPagadas() + "");
                        Cuota_UI.a_pnumcuotas.setText(String.valueOf(prestamo.getTpreNumCuotas()));
                        Cuota_UI.a_valorprestamo.setText(prestamo.getTpreValorPrestamo() + "");
                        clearPanel(Cuota_UI.jPanel2);
                        clearPanel(Cuota_UI.jPanel3);
                        Cuota_UI.a_cobrador.setText("Cobrador");
                    }
                }
            }
        }
    }

    public void calcularCantidad() {
        int valorcuota = (int) prestamo.getTpreValorCuota();
        int abonotemp = Integer.parseInt(Cuota_UI.a_abono.getText());
        int cantidad = (int) ((float) abonotemp / valorcuota);
        Cuota_UI.a_cantcuotas.setText(String.valueOf(cantidad));
    }

    private boolean validar() {
        String msj = "";
        msj += Cuota_UI.a_cobrador.getText().equals("") ? "Debe ingresar el cobrador \n" : "";
        msj += Cuota_UI.a_fecha.getDate() == null ? "Debes seleccionar la fecha de inicio \n" : "";
        msj += Cuota_UI.a_abono == null ? "Debes ingresar el abono \n" : "";
        try {
            Integer.parseInt(Cuota_UI.a_abono.getText());
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
    public List<TPersona> selectAll() {
        listp = new ArrayList<TPersona>();
        TPersona temp = new TPersona();
        temp.setTperTipo("CLIENTE");
        listp = new Persona_Model().SelectAllWhere(temp);
        return listp;
    }
    public void initTable(JTable table) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);

        dfm.setColumnIdentifiers(new Object[]{"Nombre y Apellido", "Cedula"});
        selectAll();
        for (int i = 0; i < listp.size(); i++) {
            dfm.addRow(new Object[]{listp.get(i).getTDatosBasicosPersona().getTdbpNombre() + " " + listp.get(i).getTDatosBasicosPersona().getTdbpApellido(), listp.get(i).getTDatosBasicosPersona().getTdbpCedula()});

        }
    }

    public void mouseClickedTable(JTable table, ListaPersonas_UI lpUI, String ind) {
        String Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));                        
        for (int i = 0; i < listp.size(); i++) {
            if (listp.get(i).getTDatosBasicosPersona().getTdbpCedula().equals(Select)) {                
                if (ind.equals("prestamo")) {
                    Prestamo_ui.P_cedula.setText(listp.get(i).getTDatosBasicosPersona().getTdbpCedula());                    
                }else if(ind.equals("cuota")){
                    Cuota_UI.a_cedula.setText(listp.get(i).getTDatosBasicosPersona().getTdbpCedula());
                }                    
            }
        }        
    }
}
