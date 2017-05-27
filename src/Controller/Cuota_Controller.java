/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Entity.TPersona;
import Entity.TCuota;
import Entity.TPago;
import Entity.TPrestamo;
import Model.Cobrador_Model;
import Model.Persona_Model;
import Model.Prestamo_model;
import Model.TPagos_Model;
import UI.Cuota_UI;
import UI.Multa_Ui;
import UI.Prestamo_ui;
import java.text.ParseException;
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
    private Cobrador_Model cmodel;
    private TPagos_Model pamodel;
    public DefaultTableModel dfm;
    private List<TPersona> listp;
    private List<TCobrador> listc;
    private List<TPago> listpa;
    public static String indicador = "cuota";

    public Cuota_Controller() {
        pmodel = new Prestamo_model();
        if (Cuota_UI.a_fecha != null) {
            Cuota_UI.a_fecha.setDate(new Date());
        }
        if (Multa_Ui.a_fecha != null) {
            Multa_Ui.a_fecha.setDate(new Date());
        }
        cmodel = new Cobrador_Model();
        pamodel = new TPagos_Model();
    }

    @Override
    public void setCliente(TPersona cliente) {
        if (indicador.equals("multa")) {
            Multa_Ui.a_nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido());
        } else {
            Cuota_UI.a_nombre.setText(cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido());
        }
    }

    @Override
    public void setClienteError(TPersona Cliente) {
    }

    public boolean setData(String cc) {
        if (indicador.equals("multa")) {
            TPersona cliente = consultarCliente(cc);

            if (cliente != null) {
                abono = ultimaCuota(cliente, 'c');
                if (abono != null) {
                    prestamo = abono.getTPrestamo();
                    Long valorc = prestamo.getTpreValorCuota();
                    Multa_Ui.a_totalcuota.setText(valorc + "");
                    Multa_Ui.a_cuotaneto.setText(valorc - (valorc * ((float) prestamo.getTpreIntereses() / 100)) + "");
                    Multa_Ui.a_interes.setText(valorc * ((float) prestamo.getTpreIntereses() / 100) + "");
                    Multa_Ui.a_cnumcuotas.setText(String.valueOf(prestamo.getTpreNumCuotas()));
                    Multa_Ui.a_cuotaspend.setText(String.valueOf(prestamo.getTpreNumCuotas() - abono.getTcuoCuotasPagadas()));
                    if (abono.getTcuoNuevoSaldo() >= prestamo.getTpreValorTotal()) {
                        cliente = null;
                        JOptionPane.showMessageDialog(null, "Este cliente no tiene prestamo activo");
                        Multa_Ui.jPanel5.setVisible(false);
                        return false;
                    }
                } else {
                    cliente = null;
                    Multa_Ui.jPanel5.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Este cliente no tiene prestamo");
                    return false;
                }

                return true;
            } else {
                abono = null;
                prestamo = null;
                return false;
            }
        } else {
            TPersona cliente = consultarCliente(cc);

            if (cliente != null) {
                abono = ultimaCuota(cliente, 'c');
                if (abono != null) {
                    prestamo = abono.getTPrestamo();
                    Long valorc = prestamo.getTpreValorCuota();
                    float interes_cuota = (prestamo.getTpreValorTotal()-prestamo.getTpreValorPrestamo())/( prestamo.getTpreNumCuotas());
                    Cuota_UI.a_totalcuota.setText(valorc + "");
                    Cuota_UI.a_cuotaneto.setText(valorc-interes_cuota + "");
                    Cuota_UI.a_interes.setText(interes_cuota + "");
                    Cuota_UI.a_cnumcuotas.setText(String.valueOf(prestamo.getTpreNumCuotas()));
                    Cuota_UI.a_cuotaspend.setText(String.valueOf(prestamo.getTpreNumCuotas() - abono.getTcuoCuotasPagadas()));
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
    }

    public void insertar() {
        if (indicador.equals("multa")) {
            if (validar()) {
                calcularCantidad();
                int r = JOptionPane.showConfirmDialog(null, "Esta seguro de multar con \n $ -" + formateador.format(Integer.parseInt(Multa_Ui.a_abono.getText())), "Agregar abono", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION) {
                    Multa_Ui.jPanel1.setVisible(false);
                    Multa_Ui.jPanel2.setVisible(false);
                    Multa_Ui.jPanel3.setVisible(false);
                    Multa_Ui.jPanel5.setVisible(false);
                    Long saldo;
                    int cpagadas;
                    if (abono != null) {
                        saldo = abono.getTcuoNuevoSaldo() + Long.parseLong(Multa_Ui.a_abono.getText());
                        cpagadas = (int) ((float) saldo / prestamo.getTpreValorCuota());
                    } else {
                        saldo = Long.parseLong(Multa_Ui.a_abono.getText());
                        cpagadas = Integer.parseInt(Multa_Ui.a_cantcuotas.getText());
                    }
                    if (prestamo != null) {
                        //error
                        TCobrador cobrador = new TCobrador();
                        cobrador.setTcobNombre("multa");
                        TPago pagos = new TPago();
                        pagos.setTipo("multa-.");
                        TCuota cuota = new TCuota(cmodel.SelectOne(cobrador), pamodel.SelectOne(pagos), prestamo, Multa_Ui.a_fecha.getDate(), Long.parseLong("-" + Multa_Ui.a_abono.getText()), saldo, cpagadas);
                        if (pmodel.insertar(cuota, "PRESTAMO") != null) {
                            clearPanel(Multa_Ui.jPanel2);
                            clearPanel(Multa_Ui.jPanel3);
                            JOptionPane.showMessageDialog(null, "Multa Agregada al cliente ");
                            Multa_Ui.a_cobrador.setText("multa");
                        }
                    }
                }
            }
        } else if (validar()) {
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
                    //error
                    TCobrador cobrador = new TCobrador();
                    if (Cuota_UI.a_cobrador.getText().equals("Por defecto")) {
                        cobrador = cmodel.first();                                
                    } else {
                        cobrador.setTcobNombre(Cuota_UI.a_cobrador.getText());
                    }                    
                    TPago pagos = new TPago();
                    if (Cuota_UI.a_metodo.getText().equals("Por defecto")) {
                        pagos = pamodel.first();
                    } else {
                        pagos.setTipo(Cuota_UI.a_metodo.getText());
                    }                    
                    TCuota cuota = new TCuota(cmodel.SelectOne(cobrador), pamodel.SelectOne(pagos), prestamo, Cuota_UI.a_fecha.getDate(), Long.parseLong(Cuota_UI.a_abono.getText()), saldo, cpagadas);
                    if (pmodel.insertar(cuota, "PRESTAMO") != null) {
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
        
        System.err.println(indicador);
        if (indicador.equals("multa")) {
            int valorcuota = (int) prestamo.getTpreValorCuota();
            int abonotemp = Integer.parseInt(Multa_Ui.a_abono.getText());
            int cantidad = (int) ((float) abonotemp / valorcuota);
            Multa_Ui.a_cantcuotas.setText(String.valueOf(cantidad));
        } else {
            int valorcuota = (int) prestamo.getTpreValorCuota();
            int abonotemp = Integer.parseInt(Cuota_UI.a_abono.getText());
            int cantidad = (int) ((float) abonotemp / valorcuota);
            Cuota_UI.a_cantcuotas.setText(String.valueOf(cantidad));
        }

    }

    private boolean validar() {
        if (indicador.equals("multa")) {
            String msj = Multa_Ui.a_abono == null ? "Debes ingresar el abono \n" : "";
            try {
                Integer.parseInt(Multa_Ui.a_abono.getText());
            } catch (NumberFormatException ex) {
                msj += "El valor de la cuota debe ser numerico \n";
            }
            if (msj.equals("")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, msj, "ERROR AL REGISTAR ", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            String msj = "";
            msj += Cuota_UI.a_cobrador.getText().equals("") ? "Debe ingresar el cobrador \n" : "";
            msj += Cuota_UI.a_metodo.getText().equals("") ? "Debe ingresar el metodo de pago \n" : "";
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

    }

    public List<TPersona> selectPersona() {
        listp = new ArrayList<TPersona>();
        TPersona temp = new TPersona();
        temp.setTperTipo("CLIENTE");
        listp = new Persona_Model().SelectAllWhere(temp);
        return listp;
    }

    public List<TPago> selectPago() {
        listpa = new ArrayList<TPago>();
        listpa = pamodel.findAll(TPago.class);
        return listpa;
    }

    public List<TCobrador> selectCobrador() {
        listc = new ArrayList<TCobrador>();
        listc = cmodel.findAll(TCobrador.class);
        return listc;
    }

    public void initTable(JTable table, String indicador) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);
        if (indicador.equals("pyc")) {
            dfm.setColumnIdentifiers(new Object[]{"Nombre y Apellido", "Cedula"});
            selectPersona();
            for (int i = 0; i < listp.size(); i++) {
                dfm.addRow(new Object[]{listp.get(i).getTDatosBasicosPersona().getTdbpNombre() + " " + listp.get(i).getTDatosBasicosPersona().getTdbpApellido(), listp.get(i).getTDatosBasicosPersona().getTdbpCedula()});
            }
        } else if (indicador.equals("cobrador")) {
            dfm.setColumnIdentifiers(new Object[]{"#", "Nombre"});
            selectCobrador();
            for (int i = 0; i < listc.size(); i++) {
                dfm.addRow(new Object[]{(i + 1), listc.get(i).getTcobNombre()});

            }
        } else if (indicador.equals("pago")) {
            dfm.setColumnIdentifiers(new Object[]{"Banco", "# Cuenta"});
            selectPago();
            for (int i = 0; i < listpa.size(); i++) {
                String dato = listpa.get(i).getTipo();
                String[] tipo = dato.split("-");
                dfm.addRow(new Object[]{tipo[0], tipo[1]});

            }
        }

    }

    public void mouseClickedTable(JTable table, String ind) {
        String Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
        if (ind.equals("prestamo") || ind.equals("cuota")) {
            for (int i = 0; i < listp.size(); i++) {
                if (listp.get(i).getTDatosBasicosPersona().getTdbpCedula().equals(Select)) {
                    if (ind.equals("prestamo")) {
                        Prestamo_ui.P_cedula.setText(listp.get(i).getTDatosBasicosPersona().getTdbpCedula());
                    } else if (ind.equals("cuota")) {
                        Cuota_UI.a_cedula.setText(listp.get(i).getTDatosBasicosPersona().getTdbpCedula());
                    }
                }
            }
        } else if (ind.equals("cobrador")) {
            for (int i = 0; i < listc.size(); i++) {
                if (listc.get(i).getTcobNombre().equals(Select)) {
                    Cuota_UI.a_cobrador.setText(listc.get(i).getTcobNombre());
                }
            }
        } else if (ind.equals("pago")) {
            Select = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
            for (int i = 0; i < listpa.size(); i++) {
                String dato = listpa.get(i).getTipo();
                String[] tipo = dato.split("-");
                if (tipo[0].equals(Select)) {
                    Cuota_UI.a_metodo.setText(listpa.get(i).getTipo());
                }
            }
        } else if (ind.equals("multa")) {
            for (int i = 0; i < listp.size(); i++) {
                if (listp.get(i).getTDatosBasicosPersona().getTdbpCedula().equals(Select)) {
                    Multa_Ui.a_cedula.setText(listp.get(i).getTDatosBasicosPersona().getTdbpCedula());
                }
            }
        }
    }

    public boolean insertCobrador(String nombre) {
        TCobrador cobrador = new TCobrador();
        cobrador.setTcobNombre(nombre);
        boolean r = false;
        try {
            if (Integer.parseInt(String.valueOf(pmodel.insertar(cobrador, "prestamo"))) <= 0) {
                Cuota_UI.a_cobrador.setText(nombre);
                cobrador = null;
                r = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error: Cobrador no agregado");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error: Cobrador no agregado");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cobrador ya existe");
        }
        cobrador = null;
        return r;
    }

    public boolean insertPago(String nombre) {
        TPago pago = new TPago();
        pago.setTipo(nombre);
        boolean r = false;
        try {
            if (Integer.parseInt(String.valueOf(pmodel.insertar(pago, "prestamo"))) <= 0) {
                Cuota_UI.a_metodo.setText(nombre);
                pago = null;
                r = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error: Cobrador no agregado");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error: Cobrador no agregado");
        }
        pago = null;
        return r;
    }

    public boolean updateCuota(JTable tabla) {
        int conteo = 0;
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        TCuota cuota = new TCuota();
        for (int i = 0; i < model.getRowCount(); i++) {
            cuota.setTcuoId(Integer.parseInt(String.valueOf(model.getValueAt(i, 1))));
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate;
                parsedDate = dateFormat.parse(String.valueOf(model.getValueAt(i, 2)));
                cuota.setTcuoFecha(new java.sql.Date(parsedDate.getTime()));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            }
            cuota.setTcuoAbono(Integer.parseInt(String.valueOf(model.getValueAt(i, 3))));
            cuota.setTcuoNuevoSaldo(Integer.parseInt(String.valueOf(model.getValueAt(i, 4))));
            cuota.setTcuoCuotasPagadas(Integer.parseInt(String.valueOf(model.getValueAt(i, 5))));
            cuota.setTPago((TPago) model.getValueAt(i, 9));
            cuota.setTCobrador((TCobrador) model.getValueAt(i, 10));
            cuota.setTPrestamo((TPrestamo) model.getValueAt(i, 11));
            if (cmodel.editar(cuota, "PRESTAMO")) {
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
